package com.lcm.file.easyexcel.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author lcm
 */
@Data
public class UserWriteModule extends BaseRowModel {

    private Integer id;

    @ExcelProperty(value = "用户名",index = 0)
    private String userName;

    @ExcelProperty(value = "年龄",index = 1)
    private Integer age;

    @ExcelProperty(value = "性别",index = 2)
    private String sex;

    @ExcelProperty(value = "家庭住址",index = 3)
    private String address;

    @ExcelProperty(value = "手机电话",index = 4)
    private String phone;

    @ExcelProperty(value = "业余爱好",index = 5)
    private String hobby;

    @ExcelProperty(value = "出生日期",index = 6)
    private LocalDate birthday;
}
