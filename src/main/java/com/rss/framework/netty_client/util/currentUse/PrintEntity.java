package com.rss.framework.netty_client.util.currentUse;


import com.rss.framework.netty_client.framework.business.BusinessObjectEntity;

import java.text.SimpleDateFormat;

public class PrintEntity {
    /*
    public static void printEntity(BusinessObjectEntity b){
        printAttributes(b);
        if(b.getChildList()!=null){
            for(BusinessObjectEntity e:b.getChildList()){
                System.out.println("<---child-----"+e.getObjectID()+"---child--->");
                printEntity(e);
                System.out.println("<---child-----"+e.getObjectID()+"---child--->");
                System.out.println();

            }
        }
    }

    private static String printAttributes(BusinessObjectEntity b){
        String result="";
        String attribute=null;
        String value=null;
        for (int i = 0; i < b.getAttributeList().size(); i++) {
            try {
                attribute = b.getAttributeList().get(i).getAttributeID();
                value = b.getAttributeValue(attribute).toString();
                result = result+attribute + "	:	" + value+"\n";
                System.out.println(attribute + "	:	" + value);
            }catch (NullPointerException n){
                System.out.println(attribute+"无法打印");
                n.printStackTrace();
            }
        }
        return result;
    }
*/
    public static String getEntity(String type, BusinessObjectEntity b) {
        String attribute;
        String value;
        String result = "";
        String messageType;
        if(type==null){
            type="";
        }
        if (type.equals("sender")) {
            messageType = "发出指令";
        } else {
            messageType = "打印消息";
        }
        result += "[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[\n";
        result += "**************************" + messageType + "***********************************\n" +
                "发送者：" + b.getSender() + "\n";

        result +=getResult(b).toString();

        SimpleDateFormat aDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long now = System.currentTimeMillis();
        String time = aDate.format(now);

        result += messageType + "时间：" + time + "\n";
        result += "]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]\n";
        String message = "{\"type\":\"" + type + "\",\"content\":\"" + result + "\"}";
        return message;
    }

    private static StringBuffer getResult(BusinessObjectEntity b){
        StringBuffer result=new StringBuffer();
        String attribute;
        String value;
        for (int i = 0; i < b.getAttributeList().size(); i++) {
            attribute = b.getAttributeList().get(i).getAttributeID();
            if(b.getAttributeValue(attribute)==null){
                value="";
            }else{
                value = b.getAttributeValue(attribute).toString();
            }
            result.append(attribute + ":" + value + "\n");
            //result += ;
        }


        if(b.getChildList()!=null){
            for(BusinessObjectEntity e:b.getChildList()){
                if(e.getType()!=null&&!e.getType().equals("")){
                    result.append("<start---child-----"+e.getObjectID()+"---child--"+e.getType()+"--start>\n");
                    //getResult(e);
                    result.append(getResult(e));
                    result.append("<end---child-----"+e.getObjectID()+"---child--"+e.getType()+"--end>\n");
                }else{
                    result.append("<start---child-----"+e.getObjectID()+"---child----start>\n");
                    //getResult(e);
                    result.append(getResult(e));
                    result.append("<end---child-----"+e.getObjectID()+"---child----end>\n");
                }

            }
        }
        return result;
    }
}
