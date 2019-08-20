package com.lcm.poi.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class MyTest {
    public static void main(String[] args) throws IOException {
        //create a new workbook
        Workbook wb = new XSSFWorkbook(); //or new HSSFWorkbook();

        //add picture data to this workbook.
        InputStream is = new FileInputStream("C:\\Users\\lcm\\Pictures\\Saved Pictures\\11.jpg");
        byte[] bytes = IOUtils.toByteArray(is);
        int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
        is.close();

        CreationHelper helper = wb.getCreationHelper();

        //create sheet
        Sheet sheet = wb.createSheet();

        // Create the drawing patriarch.  This is the top level container for all shapes.
        Drawing drawing = sheet.createDrawingPatriarch();

        //add a picture shape
        ClientAnchor anchor = helper.createClientAnchor();
        //set top-left corner of the picture,
        //subsequent call of Picture#resize() will operate relative to it
        anchor.setCol1(3);
        anchor.setRow1(2);
        Picture pict = drawing.createPicture(anchor, pictureIdx);

        //auto-size picture relative to its top-left corner
        pict.resize();

        //save workbook
        String file = "picture.xls";
        if(wb instanceof XSSFWorkbook) file += "x";
        try (OutputStream fileOut = new FileOutputStream(file)) {
            wb.write(fileOut);

        }catch (Exception e){
            e.printStackTrace();
        }




    }


}
