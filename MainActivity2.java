package com.example.mobicaller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity2 extends AppCompatActivity {
    String TAG="vats";
    HSSFSheet mysheet;
    HSSFWorkbook myworkbook;
    Working_with_excel myexcel;
    private  String titles[];
    ArrayList<String> titles_list,data_list;
    String filename;
    int sheetnumber;
    LinearLayout linearLayout_ofactivity2;
    EditText myEditText;
    View layoutInflater1,layoutInflater2;
    TextView title1,data1;
    ArrayList<View> views;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         linearLayout_ofactivity2 = (LinearLayout) findViewById(R.id.linearlayout2);
        filename = getIntent().getStringExtra("File_name");
        sheetnumber=getIntent().getIntExtra("sheetnumber",0);
        myexcel=new Working_with_excel();
        myworkbook=myexcel.getMeAWorkbook(filename);
        mysheet=myexcel.giveMeASheet(myworkbook,sheetnumber);
        printExcel(mysheet,1);
        layoutInflater1=getLayoutInflater().inflate(R.layout.row, null,false);
         layoutInflater2=getLayoutInflater().inflate(R.layout.row2,null,false);
         title1=(TextView) layoutInflater1.findViewById(R.id.title);
         data1=(TextView)layoutInflater1.findViewById(R.id.herewillbedata);
     }


    public void printExcel(HSSFSheet mySheet,int rownumber )
    {


        //  We now need something to iterate through the cells.

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
            //viewAdderForStaticData(titles_list.get(counter),data_list.get(counter));
            title1.setText(titles_list.get(counter));
            data1.setText(data_list.get(counter));
            views.add(layoutInflater1);
            counter++;

        }
        linearLayout_ofactivity2.addChildrenForAccessibility(views);
       /* int id_for_edit_text=101;
        while(counter<titles_list.size())
        {
            viewAdderForDynamicData(titles_list.get(counter),id_for_edit_text);
            id_for_edit_text++;
            counter++;
        }

        */
    }


    public void viewAdderForStaticData(String titlestring, String datastring)
    {




    }

    public void viewAdderForDynamicData(String titlestring,int id)
    {

        TextView title=(TextView)layoutInflater2.findViewById(R.id.textview_for_data);
        /*myEditText=(EditText)layoutInflater2.findViewById(R.id.edittextfordata);
        title.setText(titlestring);
        myEditText.setId(id);
        linearLayout_ofactivity2.addView(layoutInflater2);
*/
    }
}

