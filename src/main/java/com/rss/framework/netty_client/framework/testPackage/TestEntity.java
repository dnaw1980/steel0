package com.rss.framework.netty_client.framework.testPackage;


import com.rss.framework.netty_client.framework.business.BusinessEntityAttribute;
import com.rss.framework.netty_client.framework.business.BusinessObjectEntity;
import com.rss.framework.netty_client.framework.business.JavaBeanAdapter;

/**
 * @author Yu
 */
public class TestEntity {

    public static void main(String[] args){

        BusinessObjectEntity b = new BusinessObjectEntity();
        BusinessObjectEntity c = new BusinessObjectEntity();

        b.addChild(c);

    /*    System.out.println("ousad"+JavaBeanAdapter.buildMessageFromObject(b));

        System.out.println("test1:"+JavaBeanAdapter.buildMessageFromObject(b));
      */
        Person person = new Person("Tom","16","man");

        /**
         * getBusinessObjectFromObject:把一个bean类转换成对应的businessObjectEntity
         *  addAttribute：增加属性
         *  addChild:增加子BusinessObjectEntity
         */
        BusinessObjectEntity testb = JavaBeanAdapter.getBusinessObjectFromObject(person);
        BusinessObjectEntity testa = new BusinessObjectEntity();
        testa.addAttribute("attr","testa");
        testa.setObjectID("testa");

        testb.addChild(testa);
        testb.addAttribute("test","test");
        testb.setObjectID("order");
        testb.setSender("sender");
        testb.setType("type");

        BusinessEntityAttribute businessEntityAttribute =
                new BusinessEntityAttribute();
        businessEntityAttribute.setAttributeID("name");
        businessEntityAttribute.setAttributeValue("yu");

        testb.setAttribute(businessEntityAttribute);

        /**
         * buildMessageFromObject:把BusinessObjectEntity转换成对应的xml
         */

        System.out.println("testb\n"+JavaBeanAdapter.buildMessageFromObject(testb));

/**
 * getBusinessObjectFromMessage:把xml转换成对应的BusinessObjectEntity
 */
        BusinessObjectEntity s = JavaBeanAdapter.getBusinessObjectFromMessage(
                "<request sender='LogisticControlSystem' type='vehicleQueue'>" +
                "<VehicleQueue>" +
                        "<request_ID>02699A2BFA43CCD6E30</request_ID>" +
                        "<vehicleNo>鲁B66270</vehicleNo>" +
                        "<rfidNo>210899991111</rfidNo>" +
                        "<queueTime>2018-09-24 21:05:59</queueTime>" +
                    "</VehicleQueue>" +
                "</request>");

        System.out.println(s.getChildList().get(0).getAttributeValue("request_ID"));

        /*
        setObjectID改两边的标签
         */
        b.setObjectID("entity");
        b.setType("testType");
        b.setSender("testSender");
        System.out.println("test2:"+JavaBeanAdapter.buildMessageFromObject(b));

        Person person1 = new Person("Tom","18","man");
        BusinessObjectEntity personEntity = JavaBeanAdapter.getBusinessObjectFromObject(person1);
        System.out.println("test3:"+JavaBeanAdapter.buildMessageFromObject(personEntity));

        Person person2 = new Person("lilu",null,null);
        JavaBeanAdapter.setFieldValue(person2,personEntity);

        System.out.println("test4:"+person2.toString());
        Person person3 = new Person("lilu",null,null);

        JavaBeanAdapter.setObjectXOR(person3,person2);

        System.out.println("test5:"+person2.toString());


        Person person4 = new Person("a",null,"woman");
        Person person5 = new Person("b","78",null);

        JavaBeanAdapter.setObjectXOR(person4,person5);

    }
}
