package com.example.mobicaller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Integer.parseInt;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

public static String TAG = "vats";
Button getfile;
int sheetnumber;
EditText sheetnumber_edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getfile=(Button)findViewById(R.id.getfilebutton);
        getfile.setOnClickListener(this);
         sheetnumber_edit_text=(EditText)findViewById(R.id.sheetnumber);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {   sheetnumber = parseInt(sheetnumber_edit_text.getText().toString());
            Uri filename_uri = data.getData();
            Log.d(TAG, filename_uri.getPath());

            MediaUtility getrealpathclass=new MediaUtility();
            String filepath=getrealpathclass.getPath(this,filename_uri);

            Intent send_file_name=new Intent(this,DisplaySheet.class);
            send_file_name.putExtra("File_name",filepath);
            send_file_name.putExtra("sheetnumber",sheetnumber);
            startActivity(send_file_name);


        }
    }


    @Override
    public void onClick(View v) {
        Intent readexcel_intent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
        readexcel_intent.setType("application/vnd.ms-excel");
        readexcel_intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            startActivityForResult(readexcel_intent,1);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}

