package com.rss.steel_production.workProcedure.model;

public class ConvererCpHelper {

    public static void copyInfo(WpConvererSteelScrapInfo desc, WpConvererInfo src) {
        desc.setCarTrackNo(src.getCarTrackNo());
        desc.setConvererInfoSn(src.getConvererInfoSn());
        desc.setScrabWeigh(src.getScrabWeigh());
        desc.setTankCarNo(src.getTankCarNo());
        desc.setTankGrossWeight(src.getTankGrossWeight());
        desc.setTankNo(src.getTankNo());
        desc.setTankSteelRegTm(src.getTankSteelRegTm());
        desc.setTankTareWeight(src.getTankTareWeight());
    }

    public static void copyInfo(WpConvererInfo desc, WpConvererSteelScrapInfo src) {
        desc.setCarTrackNo(src.getCarTrackNo());
        desc.setConvererInfoSn(src.getConvererInfoSn());
        desc.setScrabWeigh(src.getScrabWeigh());
        desc.setTankCarNo(src.getTankCarNo());
        desc.setTankGrossWeight(src.getTankGrossWeight());
        desc.setTankNo(src.getTankNo());
        desc.setTankSteelRegTm(src.getTankSteelRegTm());
        desc.setTankTareWeight(src.getTankTareWeight());
    }
}
