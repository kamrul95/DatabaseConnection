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
    EditText UserName,UserPassword;
    Button SaveButton;
    ListView listViewStudent;
    DatabaseHelper db;
    ArrayList<Student> arrayListStudent;
    ArrayAdapter<Student> adapterStudentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserName = (EditText)findViewById (R.id.editTextUserName);
        UserPassword = (EditText)findViewById(R.id.editTextPassword);
        SaveButton = (Button)findViewById(R.id.saveButton);
        listViewStudent = (ListView)findViewById(R.id.listViewStudentInfo);

        db = new DatabaseHelper(MainActivity.this);
        arrayListStudent = db.getAllStudents();
        adapterStudentList=new ArrayAdapter<Student>(MainActivity.this,android.R.layout.simple_list_item_1,arrayListStudent);
        listViewStudent.setAdapter(adapterStudentList);

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = UserName.getText().toString();
                String Password = UserPassword.getText().toString();
                Student studentObject = new Student();
                studentObject.setUserName(Name);
                studentObject.setPassword(Password);
                db.insertStudent (studentObject);
                arrayListStudent= db.getAllStudents ();
                adapterStudentList=new ArrayAdapter<Student>(MainActivity.this,android.R.layout.simple_list_item_1,arrayListStudent);
                listViewStudent.setAdapter(adapterStudentList);
                Toast.makeText(MainActivity.this,"Data Saved",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
