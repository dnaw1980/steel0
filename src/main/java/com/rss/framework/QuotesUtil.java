package com.rss.framework;

import java.util.ArrayList;
import java.util.List;

public class QuotesUtil {
    public static String addQuotesToString(List<String> list){
        List<String> newIdsList=new ArrayList<>();
        for(String str:list){
            str="'"+str+"'";
            newIdsList.add(str);
        }
        String idString=String.join(",", newIdsList);
        return idString;
    }
}
