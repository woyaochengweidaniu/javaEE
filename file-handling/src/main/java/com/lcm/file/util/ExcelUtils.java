package com.lcm.file.util;

import cn.hutool.core.util.URLUtil;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.poi.util.StringUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelUtils {

    public static void exportModule(String filename, HttpServletResponse response, Class<? extends BaseRowModel> classModule, List<? extends BaseRowModel> data) throws IOException {

        String fileName = new String((filename+ new SimpleDateFormat("yyyyMMdd").format(new Date()))
                .getBytes(), "UTF-8");
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+ URLUtil.encode(fileName, StringUtil.UTF8) +".xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        Sheet sheet = new Sheet(1, 0, classModule, filename, null);
        sheet.setAutoWidth(Boolean.TRUE);
        writer.write(data,sheet);
        writer.finish();
        out.close();

    }

    public static void exportMap(String filename, HttpServletResponse response, List<List<String>> headList, List<? extends BaseRowModel> data) throws IOException {

        String fileName = new String((filename+ new SimpleDateFormat("yyyyMMdd").format(new Date()))
                .getBytes(), "UTF-8");
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+ URLUtil.encode(fileName, StringUtil.UTF8) +".xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        Sheet sheet = new Sheet(1,0);
        sheet.setAutoWidth(Boolean.TRUE);
        sheet.setHead(headList);
        writer.write(data,sheet);
        writer.finish();
        out.close();

    }

}
