package com.example.admin.vietcdsee04473;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvStudent;
    private DBManager dbManager;
    private List<Person> studentList;
    ArrayAdapter<Person> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvStudent = findViewById(R.id.listView);

        dbManager = new DBManager(this);
        studentList = dbManager.getAllStudent();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        lvStudent.setAdapter(adapter);

        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Message");
                alertBuilder.setMessage("Do you want to delete?");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbManager.deleteStudent(studentList.get(position).getId());
                        studentList.remove(position);
                        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, studentList);
                        lvStudent.setAdapter(adapter);
                    }
                });
                alertBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = alertBuilder.create();
                alert.show();
                return true;
            }
        });

    }
    public void addNew (View view)
    {
        Intent intent = new Intent(this, AddNew.class);
        startActivityForResult(intent, 100);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== 100)
        {
            if(resultCode == 200) {
                Person person = (Person) data.getSerializableExtra("person");
                dbManager.addChampion(person);
                //studentList.add(person);
                studentList = dbManager.getAllStudent();
                adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
                lvStudent.setAdapter(adapter);
            }
        }
    }

}
