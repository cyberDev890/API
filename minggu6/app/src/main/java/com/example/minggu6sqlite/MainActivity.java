package com.example.minggu6sqlite;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView etNamaFile, etIsiFile;
    private int MY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNamaFile = (EditText) findViewById(R.id.etNamaFile);
        etIsiFile = (EditText) findViewById(R.id.etIsiFile);

    }

    public boolean checkPermission(String permission) {
        if (Build .VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(permission)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{permission}, MY_REQUEST_CODE);
            } else {
                Toast.makeText(this, "Permission diberikan", Toast.LENGTH_SHORT).show();
            }
        }
        int check = ContextCompat.checkSelfPermission(this, permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

    public void buatFile(View view) {
        if (isExternalStorageWriteable() &&
                checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {


//            File textfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),
//                    etNamaFile.getText().toString());
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(getFilePath());
                fileOutputStream.write(etIsiFile.getText().toString().getBytes());
                Toast.makeText(this, "File berhasil disimpan", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "File tidak dapat disimpan", Toast.LENGTH_SHORT).show();
        }
    }



    public boolean isExternalStorageWriteable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    public void bacaData(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    private String getFilePath(){
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(directory, etNamaFile.getText().toString());
        return file.getPath();

    }




}