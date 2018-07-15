package com.example.admin.vietcdsee04473;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.Serializable;

public class AddNew extends AppCompatActivity {

    EditText etName;
    RadioButton rbMale;
    RadioButton rbFemale;
    EditText etAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        etName = findViewById(R.id.etName);
        rbFemale = findViewById(R.id.rbFemale);
        rbMale = findViewById(R.id.rbMale);
        etAddress = findViewById(R.id.etType);
    }
    private Person createStudent() {
        String gender = null;
        String name = etName.getText().toString();
        String address = String.valueOf(etAddress.getText());
        if(rbMale.isChecked())
        {
            gender = "Male";
        }
        else
        {
            gender = "Female";
        }
        Person person = new Person(name, gender, address);
        return person;
    }
    public void btnSave(View view )
    {
        Person person = this.createStudent();
        Intent intent = new Intent();
        intent.putExtra("person", person);
        setResult(200, intent);
        finish();
    }
}
