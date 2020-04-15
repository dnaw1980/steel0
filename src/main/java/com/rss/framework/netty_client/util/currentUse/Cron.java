package com.rss.framework.netty_client.util.currentUse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * *@author Yu Tongxin
 *
 * @date 2018/12/17
 */
public class Cron {
    /***
     *
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /***
     * convert Date to cron ,eg. "0 06 10 15 1 ? 2014"
     * @param date : 时间点
     * @return
     */
    public static String getCron(String date) {
        String dateFormat = "ss mm HH dd MM ? yyyy";
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date newDate = df.parse(date);
            return formatDateByPattern(newDate, dateFormat);
        }catch (ParseException pe){
            System.out.println("转换失败");
            return  null;
        }

    }


}
