package com.rss.steel_production.process.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rss.framework.QuotesUtil;
import com.rss.framework.Result;
import com.rss.framework.ResultGenerator;
import com.rss.framework.UUIDGenerator;
import com.rss.steel_production.process.model.*;
import com.rss.steel_production.process.service.*;
import com.rss.steel_production.schedule.model.SteelSchedule;
import com.rss.steel_production.schedule.service.SteelScheduleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/steel_production/process/casInfo")
@Api
@Slf4j
public class CasInfoController {
    @Resource
    private CasInfoService casInfoService;

    @Resource
    private SteelScheduleService steelScheduleService;


    @PostMapping
    public Result add(@RequestBody CasInfo casInfo) {
        casInfo.setCas_infoUID(UUIDGenerator.generate());
        casInfoService.insert(casInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody CasInfo casInfo) {
        casInfoService.updateByPrimaryKey(casInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/updateByPrimaryKeySelective")
    public Result updateByPrimaryKeySelective(@RequestBody CasInfo casInfo) {
        casInfoService.update(casInfo);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping
    public Result delete(@RequestBody List<String> ids) {
        String idString = QuotesUtil.addQuotesToString(ids);
        casInfoService.deleteByIds(idString);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/find")
    public Result find(@RequestBody CasInfoTO casInfoTO) {
        int pageNo = casInfoTO.getPageNo();
        int pageSize = casInfoTO.getPageSize();
        CasInfo casInfo = casInfoTO.getCasInfo();
        PageHelper.startPage(pageNo, pageSize);
        PageInfo pageInfo = new PageInfo(casInfoService.findByModel(casInfo));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/findBy")
    public Result findBy(@RequestBody CasInfoTO casInfoTO) {
        int pageNo = casInfoTO.getPageNo();
        int pageSize = casInfoTO.getPageSize();
        CasInfo model = casInfoTO.getCasInfo();
        PageHelper.startPage(pageNo, pageSize);
        Condition condition = new Condition(CasInfo.class);
        condition.setOrderByClause("acquireTime desc");
        Condition.Criteria criteria = condition.createCriteria();
        if (model != null) {
            if (model.getChargeNo() != null) {
                criteria.andLike("chargeNo", "%" + model.getChargeNo() + "%");
            }
            if (model.getStationNo() != null) {
                criteria.andLike("stationNo", "%" + model.getStationNo() + "%");
            }
        }
        PageInfo pageInfo = new PageInfo(casInfoService.findByCondition(condition));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Resource
    private ConvererInfoService convererInfoService;

    @Resource
    private BilletProductionService billetProductionService;

    @Resource
    private SlabProduction2Service slabProduction2Service;

    @Resource
    private SlabProduction3Service slabProduction3Service;

    @Resource
    private LfInfoService lfInfoService;

    @Resource
    private CompositionInfoService compositionInfoService;

	@Resource
	private CompositionStandardService compositionStandardService;


    /**
     * ????????????????????????
     *
     * @param chargeNo
     * @return
     */
    private List compositionInfo(String chargeNo) {

        Condition condition = new Condition(CompositionInfo.class);
        condition.setOrderByClause("acquireTime desc");
        Condition.Criteria criteria = condition.createCriteria();

        criteria.andLike("chargeNo", "%" + chargeNo + "%");

        return compositionInfoService.findByCondition(condition);
    }

    /**
     * ????????????????????????
     * @param sampleType
     * @return
     */
    private List<CompositionStandard> compStandInfo(String sampleType){
        Condition condition=new Condition(CompositionStandard.class);
        Condition.Criteria criteria=condition.createCriteria();
        criteria.andLike("sampleType", "%" + sampleType + "%");

        List<CompositionStandard> composStand = this.compositionStandardService.findByCondition(condition);
        return composStand;
    }

    /**
     * ?????????????????????????????????????????????
     *
     * @param steelSchedule
     * @return
     */
    private Object stationInfo(SteelSchedule steelSchedule) {

        String stationType = steelSchedule.getStationName().split("#")[1];
        System.out.println("stationType:\t" + stationType);

        Object rs = null;
        switch (stationType) {
            case "BOF": {
                Condition condition = new Condition(ConvererInfo.class);
                condition.setOrderByClause("acquireTime desc");
                Condition.Criteria criteria = condition.createCriteria();

                criteria.andLike("stationNo", "%" + steelSchedule.getStationName() + "%");

                List<ConvererInfo> convererInfoList = this.convererInfoService.findByCondition(condition);
                if (convererInfoList == null || convererInfoList.size() == 0) {
                    rs = null;
                } else {
					ConvererInfo rsInfo = convererInfoList.get(0);
                    rs = rsInfo;

                    //?????????
					List cList = this.compositionInfo(rsInfo.getChargeNo());
					rsInfo.setCompositionList(cList);

					//????????????????????????
                    List<CompositionStandard> composStand = this.compStandInfo("??????");
                    rsInfo.setComposStand(composStand);

					//?????????
					List<SteelSchedule> schList = this.findScheduleList(rsInfo.getChargeNo());
					rsInfo.setSteelScheduleList(schList);

					int size = schList.size();
					for (int i = 0; i < size; i++) {
						if(schList.get(i).getStationName().equals(rsInfo.getStationNo())){
							rsInfo.setScheduleIdx(i);
							break;
						}
					}
                }
            }
            break;
            case "CC": {
                String stationNum = steelSchedule.getStationName().split("#")[0];

                switch (stationNum) {
                    case "1": {
                        Condition condition = new Condition(BilletProduction.class);
                        condition.setOrderByClause("acquireTime desc");
                        Condition.Criteria criteria = condition.createCriteria();

                        //criteria.andLike("stationNo", "%" + steelSchedule.getStationName() + "%");

                        List<BilletProduction> billetProductionList = this.billetProductionService.findByCondition(condition);
                        if (billetProductionList == null || billetProductionList.size() == 0) {
                            rs = null;
                        } else {
                            rs = billetProductionList.get(0);
                        }
                    }
                    break;
                    case "2": {
                        Condition condition = new Condition(SlabProduction2.class);
                        condition.setOrderByClause("acquireTime desc");
                        Condition.Criteria criteria = condition.createCriteria();

                        criteria.andLike("stationNo", "%" + steelSchedule.getStationName() + "%");

                        List<SlabProduction2> billetProductionList = this.slabProduction2Service.findByCondition(condition);
                        if (billetProductionList == null || billetProductionList.size() == 0) {
                            rs = null;
                        } else {
                            rs = billetProductionList.get(0);
                        }
                    }
                    break;
                    case "3": {
                        Condition condition = new Condition(SlabProduction3.class);
                        condition.setOrderByClause("acquireTime desc");
                        Condition.Criteria criteria = condition.createCriteria();

                        criteria.andLike("stationNo", "%" + steelSchedule.getStationName() + "%");

                        List<SlabProduction3> billetProductionList = this.slabProduction3Service.findByCondition(condition);
                        if (billetProductionList == null || billetProductionList.size() == 0) {
                            rs = null;
                        } else {
                            rs = billetProductionList.get(0);
                        }
                    }
                    break;
                }
            }
            break;
            case "LF": {
                Condition condition = new Condition(LfInfo.class);
                condition.setOrderByClause("acquireTime desc");
                Condition.Criteria criteria = condition.createCriteria();

                criteria.andLike("stationNo", "%" + steelSchedule.getStationName() + "%");

                List<LfInfo> LfInfoList = this.lfInfoService.findByCondition(condition);
                if (LfInfoList == null || LfInfoList.size() == 0) {
                    rs = null;
                } else {
                    rs = LfInfoList.get(0);
					LfInfo rsInfo = LfInfoList.get(0);
					rs = rsInfo;

                    //?????????
					List cList = this.compositionInfo(rsInfo.getChargeNo());
					rsInfo.setCompositionList(cList);

                    //??????????????????LF
                    List<CompositionStandard> composStand = this.compStandInfo("LF");
                    rsInfo.setComposStand(composStand);

					//?????????
					List<SteelSchedule> schList = this.findScheduleList(rsInfo.getChargeNo());
					rsInfo.setSteelScheduleList(schList);

					int size = schList.size();
					for (int i = 0; i < size; i++) {
						System.out.println("");
						if(schList.get(i).getStationName().equals(rsInfo.getStationNo())){
							rsInfo.setScheduleIdx(i);
							break;
						}
					}
                }
            }
            break;
        }

        return rs;
    }

	/**
	 * ?????????????????????
	 * @param chargeNo
	 * @return
	 */
	private List<SteelSchedule> findScheduleList(String chargeNo){
		//???????????????
		Condition scheduleCond = new Condition(SteelSchedule.class);
		scheduleCond.setOrderByClause("planEnter asc");
		Condition.Criteria scheduleCriteria = scheduleCond.createCriteria();

		scheduleCriteria.andLike("chargeNo", "%" + chargeNo + "%");
		return this.steelScheduleService.findByCondition(scheduleCond);
	}
    /**
     * ??????CAS??????????????????
     *
     * @param stationNo
     * @return
     */
    @GetMapping("/workingInfo/{stationNo}")
    public Result workingInfo(@PathVariable(required = true) String stationNo) {
		/*
		???????????????
		1?????????????????????????????????????????????????????????
		2???????????????????????????????????????????????????????????? steel_schedule?????????????????????????????????
			???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
		3??????????????????????????????????????????????????????????????????
		4??????????????????????????????????????????????????????????????????
		 */
        System.out.println("workingInfo stationNo:\t" + stationNo);

        //???CAS ??????
        Condition casCond = new Condition(CasInfo.class);
        casCond.setOrderByClause("acquireTime desc");
        Condition.Criteria casCriteria = casCond.createCriteria();

        casCriteria.andLike("stationNo", "%" + stationNo + "%");
        List<CasInfo> casList = casInfoService.findByCondition(casCond);

        if (casList == null || casList.size() == 0) {
            return ResultGenerator.genFailResult("??????????????? CAS ??????");
        }

        CasInfo casInfo = casList.get(0);

		//???????????????
		List<SteelSchedule> steelScheduleList = this.findScheduleList(casInfo.getChargeNo());

        int size = steelScheduleList.size();
        SteelSchedule last = null, curr = null, next = null;

        for (int i = 0; i != size; ++i) {
            SteelSchedule _SteelSchedule = steelScheduleList.get(i);

            if (curr != null) {
                last = curr;
            }

            curr = _SteelSchedule;
            if (curr.getStationName().equals(stationNo)) {
                if (i < size - 1) {
                    next = steelScheduleList.get(i + 1);
                }
                break;
            }
        }

        //?????????
        List cList = this.compositionInfo(casInfo.getChargeNo());
        casInfo.setCompositionList(cList);

        //??????????????????LF
        List<CompositionStandard> composStand = this.compStandInfo("LF");
        casInfo.setComposStand(composStand);

		size = steelScheduleList.size();
		int startPos = -1, endPos = -1;
		for (int i = 0; i < size; i++) {
			System.out.println("");
			//?????????????????????????????????????????????
            if(steelScheduleList.get(i).getActualEnter() != null){
                startPos = i;
            }

            if(steelScheduleList.get(i).getActualExit() != null){
                endPos = i;
            }

//			if(steelScheduleList.get(i).getStationName().equals(casInfo.getStationNo())){
//				casInfo.setScheduleIdx(i);
//				break;
//			}
		}

		if(startPos != -1 && startPos == endPos){
            casInfo.setScheduleIdx(size);
        }else{
            casInfo.setScheduleIdx(startPos);
        }

        System.out.println("steelScheduleList:\t" + ResultGenerator.genSuccessResult(steelScheduleList));
        System.out.println("last:\t" + ResultGenerator.genSuccessResult(last));
        System.out.println("curr:\t" + ResultGenerator.genSuccessResult(curr));
        System.out.println("next:\t" + ResultGenerator.genSuccessResult(next));

        //?????????????????????????????????????????????????????????????????????

        //curr = casInfo;

        this.stationInfo(last);

        Map rsMap = new HashMap<String, Object>();
        rsMap.put("steelScheduleList", steelScheduleList);

        Object lastInfo = this.stationInfo(last);
        rsMap.put("last", lastInfo);
        rsMap.put("curr", casInfo);

        Object nextInfo = this.stationInfo(next);
        rsMap.put("next", nextInfo);


        return ResultGenerator.genSuccessResult(rsMap);
    }
}
