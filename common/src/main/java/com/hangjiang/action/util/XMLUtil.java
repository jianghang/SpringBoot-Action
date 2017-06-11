package com.hangjiang.action.util;

import org.dom4j.*;

import java.util.Iterator;

/**
 * Created by jianghang on 2017/6/11.
 */
public class XMLUtil {

    public static void main(String[] args) throws DocumentException {
        Document documentCreate = DocumentHelper.createDocument();
        Element elementCreate = documentCreate.addElement("parent");
        Element modulesElement = elementCreate.addElement("modules");
        Element module = modulesElement.addElement("module");

        Element nameElement = module.addElement("name");
        nameElement.setText("test");
        Element valueElement = module.addElement("value");
        valueElement.setText("1");
        System.out.println(documentCreate.getRootElement().asXML());

        String xmlStr =
                "<parent> \n" +
                "   <modules id=\"123\">  \n" +
                "       <module>      \n" +
                "           <name>oa</name>  \n" +
                "           <value>系统基本配置</value>  \n" +
                "           <descript>对系统的基本配置根目录</descript>  \n" +
                "       </module>  \n" +
                "       <module>      \n" +
                "           <name>oa2</name>  \n" +
                "           <value>系统基本配置2</value>  \n" +
                "           <descript>对系统的基本配置根目录2</descript>  \n" +
                "       </module>  \n" +
                "   </modules>  \n" +
                "</parent>";
        System.out.println(xmlStr);

        Document document = DocumentHelper.parseText(xmlStr);

        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());
        System.out.println(rootElement.attributeCount());
        System.out.println(rootElement.getStringValue());

        Element element = rootElement.element("modules");
        System.out.println(element.getStringValue());
        System.out.println(element.getText());

        Iterator iterator = element.elements("module").iterator();
        while (iterator.hasNext()){
            Element moduleElement = (Element) iterator.next();
            Element element1 = moduleElement.element("name");
            System.out.println(element1.getName() + " : " + element1.getText());
            Element element2 = moduleElement.element("value");
            System.out.println(element2.getName() + " : " + element2.getText());
            Element element3 = moduleElement.element("descript");
            System.out.println(element3.getName() + " : " + element3.getText());
        }
    }
}
