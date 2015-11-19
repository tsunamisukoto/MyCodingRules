package com.mcr.lgss.questionresolved.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.mcr.lgss.questionresolved.Entities.Person;
import com.mcr.lgss.questionresolved.R;
import com.mcr.lgss.questionresolved.Services.DatabaseHelper;

public class    EditPerson extends AppCompatActivity{
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit_person);

            Button btnEdit = (Button) findViewById(R.id.btn_saveedit);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    saveEdit(v);
                }
            });

            EditText edittxt_name = (EditText)  findViewById(R.id.lbl_editname);
            EditText edittxt_desc = (EditText) findViewById(R.id.lbl_editdesc);

            Intent callingIntent = getIntent();

            edittxt_name.setText(callingIntent.getStringExtra("name"));
            edittxt_desc.setText(callingIntent.getStringExtra("description"));
        }

        public void saveEdit(View v){
            EditText edittxt_name = (EditText)  findViewById(R.id.lbl_editname);
            EditText edittxt_desc = (EditText) findViewById(R.id.lbl_editdesc);

            Intent saveEditIntent = new Intent();
            saveEditIntent.putExtra("name", edittxt_name.getText());
            saveEditIntent.putExtra("description", edittxt_desc.getText());
            setResult(RESULT_OK, saveEditIntent);
            finish();
        }
    }
