package com.rss.steel_production.workProcedure.model.gantt;

import lombok.Data;

import java.util.List;

@Data
public class DpGanttBean {

    private List<String> routerNames;

    private List<Charge> charges;
}
