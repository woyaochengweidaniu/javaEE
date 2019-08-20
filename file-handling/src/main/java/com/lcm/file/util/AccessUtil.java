package com.lcm.file.util;


import com.healthmarketscience.jackcess.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lcm
 */
public class AccessUtil {

    /**
     * 导出access文件
     *
     * @param heads     field :  类型
     * @param data      存放数据
     * @param path      导出路径
     * @param tableName 表名
     * @throws IOException
     */

    public static void exportAccess(Map<String, Integer> heads, List<Object[]> data, String path, String tableName) throws IOException {

        //创建表
        Database db = DatabaseBuilder.create(Database.FileFormat.V2000, new File(path + File.separator + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".mdb"));
        Table newTable;
        TableBuilder tableBuilder = new TableBuilder(tableName);
        List<ColumnBuilder> columns = new ArrayList<>();
        //将字段存放在list中
        for (String colum : heads.keySet()) {
            try {
                ColumnBuilder columnBuilder = new ColumnBuilder(colum).setSQLType(heads.get(colum));
                columns.add(columnBuilder);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //将字段添加道表中
        newTable = tableBuilder.addColumns(columns).toTable(db);
        //添加数据
        for (Object[] s : data) {
            newTable.addRow(s);
        }


    }
}
