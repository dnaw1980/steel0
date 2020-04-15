package com.rss.platform.portal.controller;//package com.rss.platform.portal.controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.*;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.rss.framework.UUIDGenerator;
//import com.rss.platform.portal.model.DataObject;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class DataObjectLoader {
//    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/Steel";
//    static final String USER = "root";
//    static final String PASS = "huyueyan";
//
//    private HashMap<String, DataObject> objectMap;
//
//    public HashMap<String, DataObject> getObjectMap() {
//        return objectMap;
//    }
//
//    public static void persistObject(List<DataObject> objectList) {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        try {
//            // 注册 JDBC 驱动
//            Class.forName(JDBC_DRIVER);
//            // 打开链接
//            System.out.println("连接数据库...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            String sql = "insert into data_object(objectID,objectName,appID,moduleID,uniqueString,objectType,referTable,attributes,data_objectUID) values (?,?,?,?,?,?,?,?,?)";
//            System.out.println("实例化Statement对象...");
//            stmt = conn.prepareStatement(sql);
//            for (DataObject dataObject : objectList) {
//                stmt.setString(1, dataObject.getObjectID());
//                stmt.setString(2, dataObject.getObjectName());
//                stmt.setString(3, dataObject.getAppID());
//                stmt.setString(4, dataObject.getModuleID());
//                stmt.setString(5, dataObject.getUniqueString());
//                stmt.setString(6, dataObject.getObjectType());
//                stmt.setString(7, dataObject.getReferTable());
//                stmt.setString(8, dataObject.getAttributes());
//                stmt.setString(9, UUIDGenerator.generate());
//                stmt.executeUpdate();
//            }
//            stmt.close();
//            conn.close();
//        } catch (SQLException se) {
//            // 处理 JDBC 错误
//            se.printStackTrace();
//        } catch (Exception e) {
//            // 处理 Class.forName 错误
//            e.printStackTrace();
//        } finally {
//            // 关闭资源
//            try {
//                if (stmt != null)
//                    stmt.close();
//            } catch (SQLException se2) {
//            }
//            try {
//                if (conn != null)
//                    conn.close();
//            } catch (SQLException se) {
//                se.printStackTrace();
//            }
//        }
//        System.out.println("Goodbye!");
//    }
//
//    public DataObjectLoader() {
//    }
//
//    public static DataObject getObject(XSSFSheet sheet) {
//        DataObject dataObject = new DataObject();
//        XSSFRow objectRow = sheet.getRow(0);
//        if (objectRow == null)
//            return null;
//        String cellValue = objectRow.getCell(1).getStringCellValue();
//        if (cellValue != null && cellValue.length() > 0)
//            dataObject.setObjectID(cellValue);
//        else
//            return null;
//        System.out.println("对象标识：" + dataObject.getObjectID());
//        cellValue = objectRow.getCell(3).getStringCellValue();
//        if (cellValue != null && cellValue.length() > 0) {
//            dataObject.setObjectName(cellValue);
//            dataObject.setObjectDesc(cellValue);
//        }
//        System.out.println("对象名称：" + dataObject.getObjectName());
//        System.out.println("对象描述：" + dataObject.getObjectDesc());
//        cellValue = objectRow.getCell(5).getStringCellValue();
//        if (cellValue != null && cellValue.length() > 0)
//            dataObject.setObjectType(cellValue);
//        System.out.println("对象类型：" + dataObject.getObjectType());
//
//        XSSFCell cellObj = objectRow.getCell(7);
//        if (cellObj != null) {
//            cellValue = cellObj.getStringCellValue();
//            if (cellValue != null && cellValue.length() > 0) {
//                dataObject.setReferTable(cellValue);
//
//            }
//        }
//        System.out.println("数据库表：" + dataObject.getReferTable());
//        cellObj = objectRow.getCell(9);
//        if (cellObj != null) {
//            cellValue = objectRow.getCell(9).getStringCellValue();
//            if (cellValue != null && cellValue.length() > 0) {
//                dataObject.setUniqueString(cellValue);
//
//            }
//        }
//        System.out.println("唯一性：" + dataObject.getUniqueString());
//        JSONArray arrAttibute = new JSONArray();
//        //List<ObjectAttribute> attrList=new ArrayList<ObjectAttribute>();
//        for (int rowIndex = 3; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
//            System.out.println("===得到对象第" + (rowIndex - 2) + "个属性==");
//            XSSFRow attrRow = sheet.getRow(rowIndex);
//            if (attrRow == null) break;
//            JSONObject attr = new JSONObject();
//            cellObj = attrRow.getCell(0);
//            if (cellObj != null) {
//                cellValue = cellObj.getStringCellValue();
//                if (cellValue.length() == 0) break;
//                attr.put("attributeID", cellValue);
//                System.out.println("属性标识：" + cellValue);
//            } else break;
//
//            cellObj = attrRow.getCell(1);
//            if (cellObj != null) {
//                cellValue = cellObj.getStringCellValue();
//                attr.put("attributeName", cellValue);
//                System.out.println("属性名称：" + cellValue);
//            } else{
//                attr.put("attributeName","");
//                System.out.println("属性名称：" + "");
//            }
//
//            cellObj = attrRow.getCell(2);
//            if (cellObj != null) {
//                cellValue = cellObj.getStringCellValue();
//                attr.put("dataType", cellValue);
//                System.out.println("数据类型：" + cellValue);
//            }else{
//                attr.put("dataType","");
//                System.out.println("数据类型：" + "");
//            }
//
//            cellObj = attrRow.getCell(3);
//            if (cellObj != null) {
//                cellValue = cellObj.getRawValue();
//                attr.put("dataLength", cellValue);
//                System.out.println("数据长度：" + cellValue);
//            }else{
//                attr.put("dataLength","");
//                System.out.println("数据长度：" + "");
//            }
//
//            cellObj = attrRow.getCell(4);
//            if (cellObj != null) {
//                cellValue = cellObj.getStringCellValue();
//                attr.put("isKey", cellValue);
//                System.out.println("是否主键：" + cellValue);
//            }else{
//                attr.put("isKey","");
//                System.out.println("是否主键：" + "");
//            }
//
//            cellObj = attrRow.getCell(5);
//            if (cellObj != null) {
//                cellValue = cellObj.getStringCellValue();
//                    attr.put("isFilter",cellValue);
//                System.out.println("是否过滤：" + cellValue);
//            }else{
//                attr.put("isFilter","");
//                System.out.println("是否过滤：" + "");
//            }
//
//            cellObj = attrRow.getCell(6);
//            if (cellObj != null) {
//                cellValue = cellObj.getStringCellValue();
//                    attr.put("isSort", cellValue);
//                System.out.println("是否排序：" + cellValue);
//            }else{
//                attr.put("isSort","");
//                System.out.println("是否排序：" + "");
//            }
//
//            cellObj = attrRow.getCell(7);
//            if (cellObj != null) {
//                cellValue = cellObj.getStringCellValue();
//                attr.put("isNotNull", cellValue);
//                System.out.println("是否非空：" + cellValue);
//            }else{
//                attr.put("isNotNull","");
//                System.out.println("是否非空：" + "");
//            }
//
//            cellObj = attrRow.getCell(8);
//            if (cellObj != null) {
//                cellValue = cellObj.getStringCellValue();
//                attr.put("isHidden", cellValue);
//                System.out.println("是否隐含：" + cellValue);
//            }else{
//                attr.put("isHidden","");
//                System.out.println("是否隐含：" + "");
//            }
//
//            cellObj = attrRow.getCell(9);
//            if (cellObj != null) {
//                cellValue = cellObj.getStringCellValue();
//                if (cellValue != null && cellValue.length() > 0) {
//                    attr.put("displayType", cellValue);
//                    System.out.println("显示类型：" + cellValue);
//                } else {
//                    attr.put("displayType", "");
//                    System.out.println("显示类型：" + "");
//                }
//            }else{
//                attr.put("displayType","");
//                System.out.println("显示类型：" + "");
//            }
//
//            cellObj = attrRow.getCell(10);
//            if (cellObj != null) {
//                cellValue = cellObj.getStringCellValue();
//                if (cellValue != null && cellValue.length() > 0) {
//                    attr.put("displayLogic", cellValue);
//                    System.out.println("显示逻辑：" + cellValue);
//                } else {
//                    attr.put("displayLogic", "");
//                    System.out.println("显示逻辑：" + "");
//                }
//            }else{
//                attr.put("displayLogic","");
//                System.out.println("显示逻辑：" + "");
//            }
//
//            arrAttibute.add(attr);
//        }
//        dataObject.setAttributes(arrAttibute.toJSONString());
//        return dataObject;
//    }
//
//    public static HashMap<String, DataObject> load(MultipartFile file) {
//        HashMap<String, DataObject> objectMap = new HashMap<>();
//        try {
////			FileInputStream inputStream = new FileInputStream(file);
//            InputStream inputStream = file.getInputStream();
//            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//            //循环读取所有工作表sheet
//            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
//                System.out.println("=========得到第" + (sheetIndex + 1) + "个数据对象===========");
//                XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
//                DataObject dataObject = getObject(sheet);
//                if (dataObject != null) {
//                    objectMap.put(dataObject.getObjectID(), dataObject);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return objectMap;
//    }
//
//}
