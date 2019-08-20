package com.lcm.file.dbf;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lcm
 */
public class JavaDBFReaderTest {

    public static void main( String args[]) {

        try {

            // create a DBFReader object
            //
            InputStream inputStream  = new FileInputStream(new File("C:\\temp\\test.dbf"));
            DBFReader reader = new DBFReader( inputStream);

            // get the field count if you want for some reasons like the following
            //
            int numberOfFields = reader.getFieldCount();

            // use this count to fetch all field information
            // if required
            //
            for( int i=0; i<numberOfFields; i++) {

                DBFField field = reader.getField( i);

                // do something with it if you want
                // refer the JavaDoc API reference for more details
                //
                System.out.println( field.getName());
            }

            // Now, lets us start reading the rows
            //
            Object []rowObjects;

            while( (rowObjects = reader.nextRecord()) != null) {
                //每次读取一行
                for( int i=0; i<rowObjects.length; i++) {

                    System.out.println( rowObjects[i]);
                }
            }

            // By now, we have itereated through all of the rows

            inputStream.close();
        }
        catch( DBFException e) {

            System.out.println( e.getMessage());
        }
        catch( IOException e) {

            System.out.println( e.getMessage());
        }
    }
}
