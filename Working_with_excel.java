package com.example.mobicaller;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Working_with_excel {
    String TAG = "vats";

    public Working_with_excel() {

    }

    public HSSFWorkbook getMeAWorkbook(String filepath)
    { HSSFWorkbook myWorkBook=null;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state))
        {

            Log.d(TAG, "might have given persmission so entered if ");


            try { // Creating Input Stream

                Log.d(TAG, filepath);
                //Log.d(TAG, String.valueOf(Environment.getExternalStorageDirectory()));
                File file = new File(filepath);
                if (file.isFile() && file.length()!=0) {
                    Log.d(TAG, "got excel file ");

                    Log.d(TAG, "creating fileinputstrea");
                FileInputStream myInput = new FileInputStream(file);
                // Create a POIFSFileSystem object
                POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
                // Create a workbook using the File System
                    Log.d(TAG, "createing workbook");
                  myWorkBook = new HSSFWorkbook(myFileSystem);
                Log.d(TAG, String.valueOf(myWorkBook.getSheetName(0)));
                }

                else {
                    Log.d(TAG, "didnt get excel file ");
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            myWorkBook=null;
        }
        return myWorkBook;
    }


    public HSSFSheet giveMeASheet(HSSFWorkbook myWorkBook,int sheetnumber)
    {

            HSSFSheet mySheet = myWorkBook.getSheetAt(sheetnumber);
        return mySheet;
     }





}
