package com.example.minggu6sqlite;


import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity2 extends AppCompatActivity {
    TextView etNamaFile, etIsiFile;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etNamaFile = (EditText) findViewById(R.id.etNamaFile);
        etIsiFile = (EditText) findViewById(R.id.etIsiFile);
    }

    public boolean isExternalStorageReadable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    public void bacaData(View view){
        if (isExternalStorageReadable()){
            StringBuilder stringBuilder = new StringBuilder();

            try {
//                    File textfile = new File(Environment.getExternalStorageDirectory(),
//                            etNamaFile.getText().toString());
                FileInputStream fis = new FileInputStream(getFilePath());
                if (fis != null) {
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader bufferedReader = new BufferedReader(isr);

                    String line = null;

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }
                    fis.close();
                }
                etIsiFile.setText(stringBuilder);
            } catch (IOException e) {
                Toast.makeText(this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "File tidak bisa dibaca", Toast.LENGTH_SHORT).show();
        }

    }

    private String getFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(directory, etNamaFile.getText().toString());
        return file.getPath();

    }
}