package com.rss.framework.netty_client.util.currentUse;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedule {



    public static String parseLong(long time){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(time);
    }
    public static String getDate(long time){
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        return df.format(time);
    }



    public static long dateToStamp(String s){
        //String res;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(s);
            long ts = date.getTime();
            //res = String.valueOf(ts);
            return ts;
        }catch (ParseException pe){
            System.out.println("无法转换");
            pe.printStackTrace();
        }
        return -1;
    }

}
