package com.rss.steel_production.schedule.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rss.framework.AbstractService;
import com.rss.steel_production.process.dao.RealDataDAO;
import com.rss.steel_production.process.model.RealData;
import com.rss.steel_production.schedule.dao.TdChannelDAO;
import com.rss.steel_production.schedule.dao.TdStaDAO;
import com.rss.steel_production.schedule.model.SteelSchedule;
import com.rss.steel_production.schedule.model.TdChannel;
import com.rss.steel_production.schedule.model.TdSta;
import com.rss.steel_production.schedule.service.TdStaService;
import com.rss.tools.Tools;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Service
@EnableScheduling
//@Transactional
public class TdStaImpl extends AbstractService<TdSta> implements TdStaService {

    @Resource
    private TdStaDAO tdStaDAO;

    @Resource
    private TdChannelDAO tdChannelDAO;

    @Resource
    private RealDataDAO realDataDAO;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void show() {
        /**
         * 1、遍历 tdSta 表
         * 2、使用 real_data_key 查询 real_data 表，核对 chk_tag 是否一致，如一致，说明数据没有更新，跳过，
         *      否则，将数据串转成 Map
         * 3、查询 stSta 对应的 channel 信息，生成Map，key 与real_data 里面的串生成的Map 一致。
         * 4、先处理需要计算的值，更新到数据库。
         * 5、处理直接采的值，更新到数据库
         * 6、更新数据时间和 chk_tag
         */

        //1、遍历 tdSta 表
        List<TdSta> staList = tdStaDAO.selectAll();

        for (TdSta sta : staList) {

//            if (!sta.getStaId().equals("0157b43e-9ef6-11ea-aeba-fa163e16816f")) {
//                continue;
//            }
            this.parseSta(sta);

            //调试，只执行一次
            //return;
        }
    }

    private void parseSta(TdSta sta) {
        /**
         * 2、使用 real_data_key 查询 real_data 表，核对 chk_tag 是否一致，如一致，说明数据没有更新，跳过，
         *      否则，将数据串转成 Map(转成大写)
         * 3、查询 stSta 对应的 channel 信息，生成Map，key 与real_data 里面的串生成的Map 一致。
         * 4、先处理需要计算的值，更新到数据库。
         * 5、处理直接采的值，更新到数据库
         * 6、更新数据时间和 chk_tag
         */

        RealData rd = realDataDAO.selectByPrimaryKey(sta.getRealDataKey());

        if (rd.getChkTag().equals(sta.getChkTag())) {
            return;
        }

        String valStr = rd.getDatVal();
        System.out.println("valStr:\n" + valStr);
        valStr = "{" + valStr.substring(1, valStr.length() - 1) + "}";
        System.out.println("valStr:\n" + valStr);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = null;
        try {
            map = mapper.readValue(valStr, new TypeReference<Map<String, String>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(map.size());

        //要重新生成目标MAP，key都要转成大写，因为 板柸从数据库转来的都是大写的。
        Map<String, String> valMap = new HashMap<String, String>();
        Set<String> keySet = map.keySet();

        for (String key : keySet) {
            valMap.put(key.toUpperCase(), map.get(key));
        }

        //3、查询 stSta 对应的 channel 信息，生成Map，key 与real_data 里面的串生成的Map 一致。
        List<TdChannel> chList = listChannelForSta(sta);
        System.out.println("chList size: " + chList.size());

        //TODO 4、先处理需要计算的值，更新到数据库。

        //5、处理直接采的值，更新到数据库
        for (TdChannel ch : chList) {
            //如果是计算通道，跳过
            if (Tools.notEmpty(ch.getInputComTag())) {
                continue;
            }

            //从valMap中取数据
            String val = valMap.get(ch.getComTag().toUpperCase());

            if (Tools.notEmpty(val) && !Tools.isDigit(val)) {
                System.out.println("val:" + val + " 不是有效数字");
                continue;
            }

            if (Tools.empty(val)) {
                ch.setDatVal(new BigDecimal(0));
            } else {
                ch.setDatVal(new BigDecimal(val));
            }

            ch.setDatDt(rd.getDatTm());

            this.tdChannelDAO.updateByPrimaryKeySelective(ch);

        }

    }

    private List<TdChannel> listChannelForSta(TdSta sta) {

        Condition condition = new Condition(TdChannel.class);
        Condition.Criteria criteria = condition.createCriteria();

        criteria.andEqualTo("staId", sta.getStaId());

        List<TdChannel> channelList = this.tdChannelDAO.selectByCondition(condition);
        return channelList;
    }

}
