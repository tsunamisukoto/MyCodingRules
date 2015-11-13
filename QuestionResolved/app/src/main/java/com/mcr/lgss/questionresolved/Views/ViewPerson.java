package com.mcr.lgss.questionresolved.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mcr.lgss.questionresolved.Entities.Person;
import com.mcr.lgss.questionresolved.R;
import com.mcr.lgss.questionresolved.Services.DatabaseHelper;

public class ViewPerson extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_person);

        Button btnBack = (Button) findViewById(R.id.btn_backToHome);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnToHome(v);
            }
        });

        TextView txt_id = (TextView) findViewById(R.id.lbl_id);
        TextView txt_name = (TextView)  findViewById(R.id.lbl_name);
        TextView txt_desc = (TextView) findViewById(R.id.lbl_description);
        TextView txt_missing = (TextView) findViewById(R.id.lbl_missing);

        Intent callingIntent = getIntent();
        int id = Integer.parseInt(callingIntent.getStringExtra("ID"));
        txt_id.setText(id + "");

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        Person person = dbHelper.GetPerson(id);

        if(person != null) {
            txt_name.setText(person.Name+"");
            txt_desc.setText(person.Description+"");
        } else {
            txt_missing.setVisibility(View.VISIBLE);
            txt_name.setVisibility(View.INVISIBLE);
            txt_desc.setVisibility(View.INVISIBLE);
        }
    }

    public void returnToHome(View v) {
        finish();
    }
}
