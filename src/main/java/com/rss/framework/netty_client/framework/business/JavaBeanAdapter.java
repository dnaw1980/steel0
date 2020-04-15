package com.rss.framework.netty_client.framework.business;

import com.rss.framework.netty_client.framework.util.XMLBuilder;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public final class JavaBeanAdapter {

    private static final Logger logger = Logger.getLogger(JavaBeanAdapter.class);

    /**
     * Characters that need conversion to entities in XML.
     */
    private static String SPECIAL_CHARS = "\"<>&";

    /**
     * Map providing correlation between special characters and corresponding
     * entities.
     */
    private static java.util.Hashtable specialCharsMap = new java.util.Hashtable();

    static {
        // Initialeze special character Map.
        specialCharsMap.put("<", "&lt;");
        specialCharsMap.put(">", "&gt;");
        specialCharsMap.put("\"", "&quot;");
        specialCharsMap.put("\'", "&apos;");
        specialCharsMap.put("&", "&amp;");
    }

    /**
     * Returns passed in string with all the special characters dissalowed in
     * XML attributes and elements replaced with the corresponding entities.
     *
     * @param aStr String that possibly contains characters that have to be
     *             escaped.
     */
    public static String xmlize(String aStr) {
        if (aStr == null) {
            return "";
        }
        StringTokenizer st = new StringTokenizer(aStr, SPECIAL_CHARS, true);
        StringBuffer sb = new StringBuffer();

        while (st.hasMoreTokens()) {
            String token = st.nextToken();

            if (SPECIAL_CHARS.indexOf(token) >= 0) {
                sb.append((String) specialCharsMap.get(token));

            } else {
                sb.append(token);
            }
        }
        return sb.toString();
    }

    public static Document buildDOMFromFile(File pFile) {
        logger.info("***************FUNCTION [buildDOMFromFile] START*****************");
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(pFile);

        } catch (Exception pException) {
        }
        logger.info("***************FUNCTION [buildDOMFromFile] END*****************");
        return doc;

    }

    public static Document buildDomFromXMLString(String xmlString) {
        logger.info("***************FUNCTION [buildDomFromXMLString] START*****************");
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringReader sr = new StringReader(removeXMLStringSpace(xmlString));
            doc = builder.parse(new InputSource(sr));

        } catch (Exception ex) {
            logger.info("IOException : " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            logger.info("***************FUNCTION [buildDomFromXMLString] END*****************");
        }
        return doc;

    }

    public final static String removeXMLStringSpace(String xmlString) {
//		xmlString = xmlString.replaceAll("\n", "");
        xmlString = xmlString.replaceAll("\t", "");
//		xmlString = xmlString.replaceAll("\\s{1,}", " ");
        xmlString = xmlString.replaceAll("> <", "><");
        xmlString.trim();
        return xmlString;
    }

    public final static String getNodeType(Node pNode) {
        String type;
        switch (pNode.getNodeType()) {
            case Node.ELEMENT_NODE:
                type = "Element";
                break;
            case Node.ATTRIBUTE_NODE:
                type = "Attribute";
                break;
            case Node.TEXT_NODE:
                type = "Text";
                break;
            case Node.CDATA_SECTION_NODE:
                type = "CData section";
                break;
            case Node.ENTITY_REFERENCE_NODE:
                type = "Entity reference";
                break;
            case Node.PROCESSING_INSTRUCTION_NODE:
                type = "Processing instruction";
                break;
            case Node.COMMENT_NODE:
                type = "Comment";
                break;
            case Node.DOCUMENT_NODE:
                type = "Document";
                break;
            case Node.DOCUMENT_TYPE_NODE:
                type = "Document type";
                break;
            case Node.DOCUMENT_FRAGMENT_NODE:
                type = "Document fragment";
                break;
            case Node.NOTATION_NODE:
                type = "Notiation";
                break;
            default:
                type = "Unknown";
                break;
        }
        return type;
    }

    public static String buildMessageFromObjectList(List<BusinessObjectEntity> objectList) {
        logger.info("***************FUNCTION [buildMessageFromObjectList] START*****************");
        XMLBuilder builder = new XMLBuilder();
        if (objectList != null && objectList.size() > 0) {
            String objectID = objectList.get(0).getObjectID();
            builder.addStartTag(objectID + "List");
            for (BusinessObjectEntity object : objectList) {
                builder.addXML(buildXMLFromObject(object));
            }
            builder.addEndTag(objectID + "List");
        }
        logger.info("***************FUNCTION [buildMessageFromObjectList] END*****************");
        return builder.getResult();
    }

    public static String buildMessageFromObject(BusinessObjectEntity entity) {
        logger.info("***************FUNCTION [buildMessageFromObject] START*****************");
        XMLBuilder builder = new XMLBuilder();
        if (entity != null) {
            //20180906改动
            //builder.addStartTag(entity.getObjectID() + "List");
            builder.addXML(buildXMLFromObject(entity));
            //20180906改动
            //builder.addEndTag(entity.getObjectID() + "List");
        }
        logger.info("***************FUNCTION [buildMessageFromObject] END*****************");
        return builder.getResult();
    }

    private static String buildXMLFromObject(BusinessObjectEntity object) {
        XMLBuilder builder = new XMLBuilder();
        if (object != null) {
            builder.addBeginOfStartTag(object.getObjectID());
            String sender = object.getSender();
            if (sender != null) {
                builder.addRequiredAttribute("sender", sender);
            }
            String type = object.getType();
            if (type != null) {
                builder.addRequiredAttribute("type", type);
            }
            builder.addEndOfStartTag();
            for (BusinessEntityAttribute attr : object.getAttributeList()) {
                if (attr.getAttributeValue() != null) {
                    builder.addStartTag(attr.getAttributeID());
                    builder.addText(attr.getAttributeValue().toString());
                    builder.addEndTag(attr.getAttributeID());
                }
            }
            ArrayList<BusinessObjectEntity> childList = object.getChildList();
            if (childList != null && childList.size() > 0) {
                for (BusinessObjectEntity child : childList) {
                    builder.addXML(buildXMLFromObject(child));
                }
            }
            builder.addEndTag(object.getObjectID());
        }
        return builder.getResult();
    }

    public static ArrayList<BusinessObjectEntity> getBusinessObjectListFromMessage(String message) {
        logger.info("***************FUNCTION [getBusinessObjectListFromMessage] START*****************");
        ArrayList<BusinessObjectEntity> result = new ArrayList<BusinessObjectEntity>();
        Document doc = buildDomFromXMLString(message);
        doc.normalize();
        Element root = doc.getDocumentElement();

        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Element element = (Element) childNodes.item(i);
            result.add(getObjectFromElement(element));
        }
        logger.info("***************FUNCTION [getBusinessObjectListFromMessage] END*****************");
        return result;
    }

    public static BusinessObjectEntity getBusinessObjectFromMessage(String message) {
        logger.info("***************FUNCTION [getBusinessObjectFromMessage] START*****************");
        //ArrayList<BusinessObjectEntity> result = new ArrayList<BusinessObjectEntity>();
        Document doc = buildDomFromXMLString(message);
        doc.normalize();
        Element root = doc.getDocumentElement();

        Element element = (Element) root;

        logger.info("***************FUNCTION [getBusinessObjectFromMessage] END*****************");
        return getObjectFromElement(element);
    }
    /**
     *     bean中已set的属性放到beanTobeSet中去，
     *     理解为bean中已有的属性值替换掉对应的beanToSet里的属性值
     */
    public static void setObjectXOR(Object bean, Object beanToSet) {
        Class<?> cls = bean.getClass();
        Class<?> bls= beanToSet.getClass();
        // 取出bean里的所有方法
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        String value;
        for (Field field : fields) {
            try {
                String fieldGetName = parGetName(field.getName());
                Method fieldGetMet = cls.getMethod(fieldGetName);
                value =(String)fieldGetMet.invoke(bean);
                if(value!=null&&value!=""){
                    String fieldSetName = parSetName(field.getName());
                    if (!checkSetMet(methods, fieldGetName)) {
                        continue;
                    }
                    try {
                        Method fieldSetMet = bls.getMethod(fieldSetName, field.getType());
                        fieldSetMet.invoke(beanToSet, value);
                    }catch(NoSuchMethodException nms){
                        System.out.println("No such method:"+fieldSetName);
                        continue;
                    }
                }

            }catch (NoSuchMethodException e){
                System.out.println("No such Get method:");
                continue;
            }
            catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

    /**
     *     bean中已set的属性放到beanTobeSet中去
     */
    public static void setObjectFromBusinessObject(BusinessObjectEntity businessObject,Object bean) {
        Class<?> cls = bean.getClass();
        Field[] fields = cls.getDeclaredFields();
        String value;
        for (Field field : fields) {
            String fieldSetName = parSetName(field.getName());
            if(field.getName() == null){
                continue;
            }

            if(businessObject.getAttributeValue(field.getName())==null){
                continue;
            }
            value = businessObject.getAttributeValue(field.getName()).toString();

            if(value==null){
                continue;
            }
            try {
                Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());
                fieldSetMet.invoke(bean, value);
                }catch(NoSuchMethodException nms){
                System.out.println("No such method:"+fieldSetName);
                continue;
                }
                catch (Exception e){
                System.out.println("setObjectFromBusinessObject Exception");
                e.printStackTrace();
                }
        }
    }



    /**
           * set BusinessObjectEntity所有属性的值到Bean
           * 理解为如果businessObjectEntity中有的attribute在bean中，
           * attribute会完全替换掉对应的bean中的属性值
           * @param bean
           * @param businessObjectEntity
           */
    public static void setFieldValue(Object bean, BusinessObjectEntity businessObjectEntity) {
        Class<?> cls = bean.getClass();
        // 取出bean里的所有方法
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldSetName = parSetName(field.getName());
                if (!checkSetMet(methods, fieldSetName)) {
                    continue;
                }
                Method fieldSetMet = cls.getMethod(fieldSetName, field.getType());

                String fieldKeyName = field.getName();
                String value = businessObjectEntity.getAttributeValue(fieldKeyName).toString();
                if (null != value && !"".equals(value)) {
                    String fieldType = field.getType().getSimpleName();
                    if ("String".equals(fieldType)) {
                        fieldSetMet.invoke(bean, value);
                    } else {
                        System.out.println("not supper type" + fieldType);
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    /**
     *       * 拼接在某属性的 set方法
     *       *
     *       * @param fieldName
     *       * @return String
     *
     */
    public static String parSetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_'){
            startIndex = 1;
        }
        return "set"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     *       * 拼接在某属性的 get方法
     *       *
     *       * @param fieldName
     *       * @return String
     *
     */
    public static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_') {
            startIndex = 1;
        }
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     *       * 判断是否存在某属性的 set方法
     *       *
     *       * @param methods
     *       * @param fieldSetMet
     *       * @return boolean
     *
     */
    public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }
    /**
     *       * 判断是否存在某属性的 set方法
     *       *
     *       * @param methods
     *       * @param fieldSetMet
     *       * @return boolean
     *
     */
    public static boolean checkSetMet(Method[] methods, String fieldSetMet) {
        for (Method met : methods) {
            if (fieldSetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }


    public static BusinessObjectEntity getBusinessObjectFromObject(Object object) {
        String detailName = object.getClass().getName();
        String[] names = detailName.split("\\.");
        String className = names[names.length - 1];
        BusinessObjectEntity businessObjectEntity = new BusinessObjectEntity();
        Method[] methods = object.getClass().getDeclaredMethods();
        String methodName = null;
        String attribute = null;
        for (Method method : methods) {
            methodName = method.getName();
            if (methodName.startsWith("get")) {
                Object value = null;
                try {
                    value = method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (value != null && !"".equals(value.toString())) {
                    attribute = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                    businessObjectEntity.addAttribute(attribute, value.toString());
                }
            }
        }
        String objectId = className.substring(0, 1).toLowerCase() + className.substring(1);
        businessObjectEntity.setObjectID(objectId);
        return businessObjectEntity;
    }

    /**
     * 获取对应结点的名字
     *
     * @param name 不包含root结点的拼接，ie: abc.def.i
     * @return
     */
    private static String[] parsePropertyName(String name) {
        //结点序号从0开始
        int size = name.split("\\.").length;

        String[] parms = name.split("\\.");
        return parms;
    }

    private static BusinessObjectEntity getObjectFromElement(Element element) {
        logger.info("***************FUNCTION [getObjectFromElement] START*****************");
        BusinessObjectEntity newEntity = new BusinessObjectEntity();
        newEntity.setObjectID(element.getNodeName());
        //20180727修改
        Node operationNode = element.getAttributes().getNamedItem("operation");
        Node senderNode = element.getAttributes().getNamedItem("sender");
        Node sendertypeNode = element.getAttributes().getNamedItem("type");
        if (operationNode != null) {
            newEntity.setOperation(operationNode.getNodeValue());
        }
        if (senderNode != null) {
            newEntity.setSender(senderNode.getNodeValue());
        }
        if (sendertypeNode != null) {
            newEntity.setType(sendertypeNode.getNodeValue());
        }

        //
        Node pageSizeNode = element.getAttributes().getNamedItem("pageSize");
        if (pageSizeNode != null) {
            newEntity.setPageSize(pageSizeNode.getNodeValue());
        }
        Node pageNoNode = element.getAttributes().getNamedItem("pageNo");
        if (pageNoNode != null) {
            newEntity.setPageNo(pageNoNode.getNodeValue());
        }
        Node orderByNode = element.getAttributes().getNamedItem("orderBy");
        if (orderByNode != null) {
            newEntity.setOrderBy(orderByNode.getNodeValue());
        }
        Node uniqueNode = element.getAttributes().getNamedItem("unique");
        if (uniqueNode != null) {
            String[] uniqueArray = uniqueNode.getNodeValue().split(",");
            for (String unique : uniqueArray) {
                newEntity.addUnique(unique);
            }
        }
        Node isSaveLogNode = element.getAttributes().getNamedItem("isSaveLog");
        newEntity.setSaveLog(false);
        if (isSaveLogNode != null) {
            String isSaveLogFlag = isSaveLogNode.getNodeValue();
            if ("true".equals(isSaveLogFlag)) {
                newEntity.setSaveLog(true);
            }
        }
        NodeList attrNodes = element.getChildNodes();
        for (int i = 0; i < attrNodes.getLength(); i++) {
            Node attrNode = attrNodes.item(i);
            if (attrNode.hasChildNodes()) {
                Node firstNode = attrNode.getFirstChild();
                if (firstNode.getNodeType() == Node.ELEMENT_NODE) {
                    newEntity.addChild(getObjectFromElement((Element) attrNode));
                } else if (firstNode.getNodeType() == Node.TEXT_NODE) {

                    BusinessEntityAttribute attr = new BusinessEntityAttribute();
                    Node typeNode = attrNode.getAttributes().getNamedItem("dataType");
                    String dataType = null;
                    if (typeNode != null) {
                        dataType = typeNode.getNodeValue();
                    }


                    attr.setAttributeID(attrNode.getNodeName());
                    String value = firstNode.getNodeValue();
                    if (dataType == null || "string".equals(dataType)) {
                        attr.setAttributeValue(value);
                    } else if ("datetime".equals(dataType)) {
                        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        try {
                            attr.setAttributeValue(formatDate.parse(value));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else if ("date".equals(dataType)) {
//						SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
//						try {
//							attr.setAttributeValue(formatDate.parse(value));
//						} catch (ParseException e) {
//							e.printStackTrace();
//						}
                        attr.setAttributeValue(value);
                    } else if ("int".equals(dataType)) {
                        attr.setAttributeValue(Integer.parseInt(value));
                    } else if ("double".equals(dataType)) {
                        attr.setAttributeValue(Double.parseDouble(value));
                    }

                    Node operatorNode = attrNode.getAttributes().getNamedItem("operator");
                    if (operatorNode != null) {
                        attr.setOperator(operatorNode.getNodeValue());
                    }
                    newEntity.addAttribute(attr);
                }
            }
        }
        logger.info("***************FUNCTION [getObjectFromElement] END*****************");
        return newEntity;
    }

    public static String getSQLFromSQLMessage(String message) {
        logger.info("***************FUNCTION [getSQLFromSQLMessage] START*****************");
        Document doc = buildDomFromXMLString(message);
        doc.normalize();
        Element root = doc.getDocumentElement();
        Node firstNode = root.getFirstChild();
        logger.info("***************FUNCTION [getSQLFromSQLMessage] END*****************");
        return firstNode.getNodeValue();
    }


    public static String buildAttributeMessageFromObjectList(List<BusinessObjectEntity> objectList) {
        logger.info("***************FUNCTION [buildAttributeMessageFromObjectList] START*****************");
        XMLBuilder builder = new XMLBuilder();
        if (objectList != null && objectList.size() > 0) {
            String objectID = objectList.get(0).getObjectID();
            builder.addStartTag(objectID + "List");
            for (BusinessObjectEntity object : objectList) {
                builder.addXML(buildAttributeXMLFromObject(object));
            }
            builder.addEndTag(objectID + "List");
        }
        logger.info("***************FUNCTION [buildAttributeMessageFromObjectList] END*****************");
        return builder.getResult();
    }

    private static String buildAttributeXMLFromObject(BusinessObjectEntity object) {
        XMLBuilder builder = new XMLBuilder();
        if (object != null) {
            builder.addBeginOfStartTag(object.getObjectID());
            for (BusinessEntityAttribute attr : object.getAttributeList()) {
                if (attr.getAttributeValue() != null) {
                    builder.addRequiredAttribute(attr.getAttributeID(), attr.getAttributeValue().toString());
                }
            }
            builder.addEndOfStartTag();

            ArrayList<BusinessObjectEntity> childList = object.getChildList();
            if (childList != null && childList.size() > 0) {
                for (BusinessObjectEntity child : childList) {
                    builder.addXML(buildAttributeXMLFromObject(child));
                }
            }
            builder.addEndTag(object.getObjectID());
        }
        return builder.getResult();
    }
}
