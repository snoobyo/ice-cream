package com.ic.business.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.ic.business.model.DemoData;
import com.ic.common.util.JacksonUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel工具类
 *
 * @author wind
 */
public class ExcelUtil {

    public static <T> void write(String fileName, Class<T> clazz, List<T> data) {
        HttpServletResponse response = HttpUtil.getResponse();
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String excelFileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + excelFileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), clazz).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(data);
        } catch (Exception e) {
            e.printStackTrace();
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            try {
                response.getWriter().println(JacksonUtil.toJson(map));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static <T> List<T> read(MultipartFile file, Class<T> clazz) {
        List<T> data = new ArrayList<>();
        try {
            EasyExcel.read(file.getInputStream(), DemoData.class, new AnalysisEventListener<T>() {
                @Override
                public void invoke(T t, AnalysisContext analysisContext) {
                    data.add(t);
                }

                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {

                }
            }).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
