package com.ic.business.controller;

import com.alibaba.excel.EasyExcel;
import com.ic.business.model.DemoData;
import com.ic.business.util.ExcelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @GetMapping("/export")
    public void exportExcel() {
        List<DemoData> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData demoData = new DemoData();
            demoData.setUsername("1");
            demoData.setPassword("2");
            demoData.setIgnore("3");
            data.add(demoData);
        }
        ExcelUtil.write("fruit", DemoData.class, data);
    }

    @PostMapping("/import")
    public String importExcel(MultipartFile file) {
        List<DemoData> data = ExcelUtil.read(file, DemoData.class);
        System.out.println(data);
        return "导入成功";
    }
}
