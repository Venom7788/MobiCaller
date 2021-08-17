package com.example.mobicaller;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class DisplaySheet extends AppCompatActivity {
String TAG="vats";
HSSFSheet mysheet;
HSSFWorkbook myworkbook;
Working_with_excel myexcel;
ListView listView;
TextView title_textview;
EditText data_edittext;
ArrayList<String> titles_list,data_list;
String filename;
myAdapter adapter;
int sheetnumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sheet);
        filename = getIntent().getStringExtra("File_name");
        sheetnumber = getIntent().getIntExtra("sheetnumber", 0);
        myexcel = new Working_with_excel();
        myworkbook = myexcel.getMeAWorkbook(filename);
        mysheet = myexcel.giveMeASheet(myworkbook, sheetnumber);
        listView = (ListView) findViewById(R.id.listview);

        



        printExcel(mysheet, 1);

    }

    class myAdapter extends ArrayAdapter<String>
         {

             ArrayList<String> mytitle;
             ArrayList<String> mydata;


             myAdapter(Context context,ArrayList<String> mytitle,ArrayList<String> mydata)
             {
                super(context,R.layout.row,R.id.title,mytitle);
                this.mytitle=mytitle;
                this.mydata=mydata;
             }

             @NonNull
             @Override
             public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
             {
                 
                 Log.d(TAG, parent.toString());
                super.getView(position, convertView, parent);
                 Log.d(TAG, "BOya2 ");
                LayoutInflater inflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                 Log.d(TAG, "BOya3 ");
                View row=inflater.inflate(R.layout.row,parent,false);
                Log.d(TAG, mydata.get(position)+"HEYAYYAYAYA");
                title_textview=row.findViewById(R.id.title);
                data_edittext=row.findViewById(R.id.herewillbedata);

                title_textview.setText(mytitle.get(position));
                data_edittext.setText(mydata.get(position));

                return row;
             }
         }



    public void printExcel(HSSFSheet mySheet, int rownumber)
    {

        HSSFRow FirstRow = mysheet.getRow(mySheet.getFirstRowNum());
        Iterator cellIter = FirstRow.cellIterator();
        int j=0;
        titles_list=new ArrayList<String>();

        while (cellIter.hasNext())
        {
            HSSFCell myCell = (HSSFCell) cellIter.next();

            titles_list.add(j,myCell.toString());
        }

        HSSFRow dataRow=mySheet.getRow(rownumber);
        cellIter = dataRow.cellIterator();
        data_list=new ArrayList<String>();
        while (cellIter.hasNext())
        {
            HSSFCell myCell = (HSSFCell) cellIter.next();

            data_list.add(j,myCell.toString());
        }
        int counter=0;
        while(titles_list.get(counter).equals("Status"))
        {


        }

        adapter=new myAdapter(this,titles_list,data_list);
        listView.setAdapter(adapter);
    }



}


