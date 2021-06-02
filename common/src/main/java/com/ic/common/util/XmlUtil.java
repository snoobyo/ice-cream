package com.ic.common.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

public class XmlUtil {

    public static void parse(String filePath) {
        // 1.创建Reader对象
        SAXReader reader = new SAXReader();
        // 2.加载xml
        Document document = null;
        try {
            document = reader.read(new File(filePath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 3.获取根节点
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());
        recursion(rootElement);
    }

    public static void recursion(Element element) {
        Iterator iterator = element.elementIterator();
        while (iterator.hasNext()) {
            Element nextElement = (Element) iterator.next();
            System.out.println(nextElement.getName());
            recursion(nextElement);
        }
    }

    public static void main(String[] args) {
        parse("/Users/wind/Desktop/test.xml");
//        parse("/Users/wind/Desktop/演示一线#10.kml");
    }
}