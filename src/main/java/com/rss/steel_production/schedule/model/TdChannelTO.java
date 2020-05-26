package com.rss.steel_production.schedule.model;

import lombok.Data;

@Data
public class TdChannelTO {

    private int pageNo = 0;

    private int pageSize = 0;

    private TdChannel tdChannel;
}