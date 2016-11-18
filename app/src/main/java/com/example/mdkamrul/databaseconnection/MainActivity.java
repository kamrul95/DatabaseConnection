package com.example.mdkamrul.databaseconnection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mdkamrul.databaseconnection.Database.DatabaseHelper;
import com.example.mdkamrul.databaseconnection.Model.Student;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText eTUserName, eTPassword;
    Button saveBtn;
    boolean error;
    ListView listView;
    ArrayList<Student> arrayListStd;
    ArrayAdapter<Student > adapter;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTUserName = (EditText) findViewById(R.id.editTextUserName);
        eTPassword = (EditText) findViewById(R.id.editTextPassword);
        saveBtn = (Button) findViewById(R.id.saveButton);
        listView = (ListView) findViewById(R.id.listViewStudentInfo);

        db = new DatabaseHelper(MainActivity.this);
        arrayListStd = db.getAllStudents();
        adapter = new ArrayAdapter<Student>(MainActivity.this, android.R.layout.simple_list_item_1, arrayListStd);
        listView.setAdapter(adapter);



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error = false;
                String username = eTUserName.getText().toString();
                String password = eTPassword.getText().toString();


                Student stdObj = new Student();
                stdObj.setUserName(username);
                stdObj.setPassword(password);
                db.insertStudent(stdObj);
                arrayListStd = db.getAllStudents();
                adapter = new ArrayAdapter<Student>(MainActivity.this, android.R.layout.simple_list_item_1, arrayListStd);
                listView.setAdapter(adapter);
                Toast.makeText(MainActivity.this, "Data is saved!", Toast.LENGTH_LONG).show();
            }

        });
    }
}
