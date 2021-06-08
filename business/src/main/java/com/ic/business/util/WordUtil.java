package com.ic.business.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordUtil {

    public static void write() {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        // 要填入模本的数据文件
        Map dataMap = new HashMap();
        dataMap.put("title", "啦啦啦");
        dataMap.put("username", "???123456");
        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        // 这里我们的模板是放在com.havenliu.document.template包下面
        configuration.setClassForTemplateLoading(WordUtil.class, "/word");
        Template t = null;
        try {
            // test.ftl为要装载的模板
            t = configuration.getTemplate("test.ftl");
            t.setEncoding("utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 输出文档路径及名称
        File outFile = new File("/Users/wind/Desktop/my.docx");
        Writer out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        try {
            t.process(dataMap, out);
            out.close();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
