package com.rss.steel_production.workProcedure.controller.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 调度序列，<br/>
 * 指一包铁水从铁水接受到进入连铸机进行浇铸这一个流程的信息，要包含高炉炉号，转炉炉号。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScrapRefConvererBean {

    /**
     * 废钢序号
     */
    private Integer steelScrapInfoSn;

    /**
     * 转炉工序序号需要从调度序号关联过来
     */
//    private Integer convererInfoSn;


    /**
     * 调度序号
     */
    private String scheduleSeqId;

}
