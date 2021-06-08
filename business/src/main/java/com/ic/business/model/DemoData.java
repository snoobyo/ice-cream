package com.ic.business.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

public class DemoData {

    @ExcelProperty("用户名")
    private String username;

    @ExcelProperty("密码")
    private String password;

    @ExcelProperty("忽略字段")
    @ColumnWidth(20)
    private String ignore;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIgnore() {
        return ignore;
    }

    public void setIgnore(String ignore) {
        this.ignore = ignore;
    }
}
