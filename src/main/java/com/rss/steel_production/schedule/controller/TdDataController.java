package com.rss.steel_production.schedule.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.framework.netty_client.EchoClient;
import com.rss.steel_production.foundation.service.SteelDeviceService;
import com.rss.steel_production.schedule.controller.bean.*;
import com.rss.steel_production.schedule.model.*;
import com.rss.steel_production.schedule.service.*;
import com.rss.tools.DateUtil;
import com.rss.tools.Tools;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/steel_production/schedule/tdData")
@Api
public class TdDataController {

    @Autowired
    private TdStaService tdStaService;

    @Autowired
    private TdChannelService tdChannelService;

    @Autowired
    private TdDataBatService tdDataBatService;

    @Autowired
    private TdHistDataService tdHistDataService;

    @Autowired
    private SteelScheduleService steelScheduleService;

    /**
     * 测试生成数据
     * @return
     */
    @GetMapping("/genData")
    public Result genData() {
        this.tdStaService.show();

        return ResultGenerator.genSuccessResult("生成数据");
    }

    /**
     * 根据工位名称返回生产实时数据和调度数据
     *
     * @param stationNo 站点名称<br/>
     *                  1#KR、1#BOF、1#CAS、1#LF、1#CC
     * @return
     */
    @GetMapping("/realData/{stationNo}")
    public Result realData(@PathVariable(required = true) String stationNo) {

        RealDataBean rsBean = new RealDataBean();
        rsBean.setRealDataList(new ArrayList<RealData>());

        /**
         * 1、查询本工位的，有实时进站时间，没有实际出站时间的调度记录
         * 2、查询本工位信息，查询本工位对应的通道信息（按重要级别和排序号进行排序）
         */

        {
            Condition scheduleCondition = new Condition(SteelSchedule.class);
            Condition.Criteria criteria = scheduleCondition.createCriteria();

            criteria.andLike("stationName", "%" + stationNo + "%");
            criteria.andIsNotNull("actualEnter");
            criteria.andIsNull("actualExit");

            List<SteelSchedule> steelScheduleList = steelScheduleService.findByCondition(scheduleCondition);

            if (Tools.notEmpty(steelScheduleList)) {
                SteelSchedule steelSchedule = steelScheduleList.get(0);

                rsBean.getRealDataList().add(
                        new RealData("炉次号", steelSchedule.getChargeNo(), 1)
                );

                rsBean.getRealDataList().add(
                        new RealData("浇次号", steelSchedule.getCastNo(), 1)
                );

                rsBean.getRealDataList().add(
                        new RealData("进站时间", DateUtil.dateToString(steelSchedule.getActualEnter(), DateUtil.DATETIME_FMT), 1)
                );

                //温度
                String temp = this.lastElement(steelSchedule.getTemperature());
                rsBean.getRealDataList().add(new RealData("温度", temp, 1));

                //温度
                String weight = this.lastElement(steelSchedule.getWeight());
                rsBean.getRealDataList().add(new RealData("重量", weight, 1));
            }
        }

        //查本工位信息
        TdSta sta = null;
        {
            Condition staCondition = new Condition(TdSta.class);
            Condition.Criteria criteria = staCondition.createCriteria();

            criteria.andEqualTo("scheduleStation", stationNo);

            List<TdSta> staList = this.tdStaService.findByCondition(staCondition);

            if (Tools.notEmpty(staList)) {
                sta = staList.get(0);

                rsBean.setDtTime(
                        DateUtil.datetimeToString(sta.getDatDt())
                );
            }
        }

        //查数据通道信息
        {
            Condition chCondition = new Condition(TdChannel.class);
            Condition.Criteria criteria = chCondition.createCriteria();
            chCondition.setOrderByClause("is_important desc, order_sn asc");
            criteria.andEqualTo("staId", sta.getStaId());

            List<TdChannel> chList = this.tdChannelService.findByCondition(chCondition);

            for (TdChannel ch : chList) {

                RealData rd = new RealData();
                rd.setLabel(ch.getChName());
                rd.setShow(ch.getIsImportant());

                rsBean.getRealDataList().add(rd);

                DecimalFormat df1 = new DecimalFormat("0.######");

                //采集数据
//                if (Tools.empty(ch.getInputComTag())) {
                    switch (ch.getDkCls()) {

                        case 0://开关量
                            String val = ch.getDatVal().intValue() == 1 ? ch.getSw1Stat() : ch.getSw0Stat();
                            rd.setValue(val);
                            break;
                        case 1://模拟量
                        case 2://累计量
                            rd.setValue(df1.format(ch.getDatVal()));
                            break;
                        case 3://日期
                            String dt = DateUtil.datetimeToString(DateUtil.getDateTime(ch.getDatVal().longValue()));
                            rd.setValue(dt);
                            break;
                        default:
                            break;
                    }
//                } else {//计算数据
//
//                }
            }


        }

        return ResultGenerator.genSuccessResult(rsBean);
    }

    private String lastElement(String src) {
        String desc = "";
        if (!Tools.empty(src)) {
            String[] tArr = src.split(",");
            desc = tArr[tArr.length - 1];
        }
        return desc;
    }

}
