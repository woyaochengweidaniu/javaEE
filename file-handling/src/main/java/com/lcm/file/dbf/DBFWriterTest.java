package com.lcm.file.dbf;

import com.linuxense.javadbf.DBFDataType;
import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * javadbf包   导入导出
 *
 * 参考文章：http://priede.bf.lu.lv/ftp/pub/DatuBazes/DBF/javadbf/javadbf-tutorial.html
 * @author lcm
 */
public class DBFWriterTest {

    public static void main( String args[])
            throws DBFException, IOException {

        // let us create field definitions first
        // we will go for 3 fields
        //
        DBFField fields[] = new DBFField[ 3];

        fields[0] = new DBFField();
        fields[0].setName( "emp_code");
        fields[0].setType(DBFDataType.CHARACTER);
        fields[0].setLength( 10);

        fields[1] = new DBFField();
        fields[1].setName( "emp_name");

        fields[1].setType(DBFDataType.CHARACTER);
        fields[1].setLength( 20);

        fields[2] = new DBFField();
        fields[2].setName( "salary");
        fields[2].setType(DBFDataType.FLOATING_POINT );
        fields[2].setLength( 12);
        fields[2].setDecimalCount( 2);

        FileOutputStream fos = new FileOutputStream("C:\\temp\\test.dbf");
        DBFWriter writer = new DBFWriter(fos, Charset.defaultCharset());
        writer.setFields( fields);

        // now populate DBFWriter
        //

        Object[] rowData = new Object[3];
        rowData[0] = "1000";
        rowData[1] = "John";
        rowData[2] = 5000.00;

        writer.addRecord( rowData);

        rowData = new Object[3];
        rowData[0] = "1001";
        rowData[1] = "Lalit";
        rowData[2] = 3400.00;

        writer.addRecord( rowData);

        rowData = new Object[3];
        rowData[0] = "1002";
        rowData[1] = "Rohit";
        rowData[2] = 7350.00;

        writer.addRecord( rowData);
        writer.close();
        fos.close();
    }
}
