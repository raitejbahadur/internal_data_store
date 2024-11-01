package com.example.internal_store_data;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText edit_one;

    static final int RED_BLOCK_SIZE=100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_one=(EditText) findViewById(R.id.edit_one);
    }

    public void save(View view)
    {
        try {

            String data=edit_one.getText().toString();

            FileOutputStream fileOutputStream=openFileOutput("abcd.txt",MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(data);

            outputStreamWriter.flush();
            outputStreamWriter.close();

            Toast.makeText(this, "file save success.....!", Toast.LENGTH_SHORT).show();
            edit_one.setText(" ");
        }
       catch (Exception e)
       {
           e.printStackTrace();
       }
    }

    public void load(View view)
    {

        try {
            FileInputStream fileInputStream=openFileInput("abcd.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            char[] iChars=new char[RED_BLOCK_SIZE];
            String s=" ";

            int charRead;

            while ((charRead=inputStreamReader.read(iChars))>0)
            {
                String raedString=String.copyValueOf(iChars,0,charRead);
                s+=raedString;
                iChars=new char[RED_BLOCK_SIZE];
            }
            edit_one.setText(s);

            Toast.makeText(this, "file load success....", Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}