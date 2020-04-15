package com.rss.framework.netty_client;


import com.rss.framework.netty_client.framework.business.BusinessObjectEntity;
import com.rss.framework.netty_client.framework.business.JavaBeanAdapter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.Charset;
import java.sql.*;

/**
 *客户端ERP
 * @author Yu Tongxin
 * @date 2018/12/4
 */
public class CommonBusiness implements ClientBusiness {

    @Override
    public void execute(ChannelHandlerContext channelHandlerContext, BusinessObjectEntity businessObjectEntity) {
        System.out.println("CommonBusiness接收到有效消息："+ JavaBeanAdapter.buildMessageFromObject(businessObjectEntity));
        if (!businessObjectEntity.getObjectID().equals("response")) {
            switch (businessObjectEntity.getType()) {
                case "beat":
                    System.out.println("CommonBusiness接收到心跳包消息");
                    break;
                case "finishSchedule":
                    System.out.println("CommonBusiness接收到完成调度消息");
                    String message = null;
                    try {
                        message = getNext(businessObjectEntity.getChildList().get(0).getAttributeValue("steel_scheduleUID").toString());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if(!message.equals("")) {
                        EchoClient.getInstance().cH.sendToch(message);
                    }
                    break;
                default:
                    System.out.println("CommonBusiness未定义的消息类型：" + businessObjectEntity.getType());
                    break;
            }
        }
    }

    private void sendToServer(ChannelHandlerContext channelHandlerContext,String message){
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer(message,Charset.forName("utf-8")));
    }

    @Override
    public String firstMessage(String type) {
        String message = null;
        if (type.equals("beatMessage")) {
            message = "<request sender='CommonBusiness' type='beat'>" +
//                    "<targetSystem>BulkScheduleServer</targetSystem>" +
                    "<beat>OK</beat>" +
                    "</request>";
        }
        return message;
    }

    public String getNext(String uid) throws SQLException {
        String message = "";
        String url = "jdbc:mysql://localhost:3306/ssm?useUnicode=true&characterEncoding=utf8";
        String user= "root";
        String passwd= "manager";
        Connection conn= null;
        try {
            conn = DriverManager.getConnection(url,user,passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Statement state1 = conn.createStatement();
        String s1 = "select chargeNo,stationName from steel_schedule where steel_scheduleUID=\""+uid+"\"";
        ResultSet resultSet = state1.executeQuery(s1);
        if (resultSet.next()) {
            String chargeNo = resultSet.getString("chargeNo");
            String stationName = resultSet.getString("stationName");
            String processName = stationName.split("#")[1];

            state1 = conn.createStatement();
            String s2 = "select processRoute from charge_plan where chargeNo=\"" + chargeNo + "\"";
            resultSet = state1.executeQuery(s2);
            if (resultSet.next()) {
                String processRoute = resultSet.getString("processRoute");
                String[] processList = processRoute.split("_");
                String next = "";
                for (int i = 0; i < processList.length; i++) {
                    if (processList[i].equals(processName) && i != processList.length - 1) {
                        next = processList[i + 1];
                        break;
                    }
                }
                if (next.equals("")) {
                    message = "";
                } else {
                    state1 = conn.createStatement();
                    String s3 = "select * from steel_schedule where chargeNo=\"" + chargeNo + "\" and stationName like \"%" + next + "%\"";
                    resultSet = state1.executeQuery(s3);
                    if (resultSet.next()) {
                        message = "<request sender='scheduleServer' type='steelSchedule'>" +
                                "<steelSchedule>" +
                                "<steel_scheduleUID>" + resultSet.getString("steel_scheduleUID") + "</steel_scheduleUID>" +
                                "<chargeNo>" + resultSet.getString("chargeNo") + "</chargeNo>" +
                                "<castNo>" + resultSet.getString("castNo") + "</castNo>" +
                                "<castSeq>" + resultSet.getString("castSeq") + "</castSeq>" +
                                "<stationName>" + resultSet.getString("stationName") + "</stationName>" +
                                "<planEnter>" + resultSet.getString("planEnter") + "</planEnter>" +
                                "<planExit>" + resultSet.getString("planExit") + "</planExit>" +
                                "<ironNo>" + resultSet.getString("ironNo") + "</ironNo>" +
                                "<ironSeq>" + resultSet.getString("ironSeq") + "</ironSeq>" +
                                "</steelSchedule>" +
                                "</request>";
                    } else {
                        message = "";
                    }
                }
            } else {
                message = "";
            }
        } else {
            message = "";
        }
        return message;
    }
}
