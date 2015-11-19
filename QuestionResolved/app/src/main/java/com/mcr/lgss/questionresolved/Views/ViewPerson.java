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

    private static final int EDIT_PERSON = 47;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_person);
//
//        Button btnEdit = (Button) findViewById(R.id.btn_edit);
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                goToEdit(v);
//            }
//        });
//
//        TextView txt_id = (TextView) findViewById(R.id.lbl_id);
//        TextView txt_name = (TextView)  findViewById(R.id.lbl_name);
//        TextView txt_desc = (TextView) findViewById(R.id.lbl_description);
//        TextView txt_missing = (TextView) findViewById(R.id.lbl_missing);
//
//        Intent callingIntent = getIntent();
//        int id = Integer.parseInt(callingIntent.getStringExtra("ID"));
//        txt_id.setText(id + "");
//
//        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
//        Person person = dbHelper.GetPerson(id);
//
//        if(person != null) {
//            txt_name.setText(person.Name+"");
//            txt_desc.setText(person.Description+"");
//        } else {
//            btnEdit.setVisibility(View.INVISIBLE);
//            txt_missing.setVisibility(View.VISIBLE);
//            txt_name.setVisibility(View.INVISIBLE);
//            txt_desc.setVisibility(View.INVISIBLE);
//        }
    }

    protected void onActivityResult(int aRequestCode, int aResultCode, Intent aData)
    {
        if (aRequestCode == EDIT_PERSON) {
            if (aResultCode == RESULT_OK) {
                Person newPerson = new Person(-1, aData.getStringExtra("name"), aData.getStringExtra("description"), null);
                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                dbHelper.UpdatePerson(newPerson, null);
            }
        }
        super.onActivityResult(aRequestCode, aResultCode, aData);
    }

    public void goToEdit(View v){
        TextView txt_name = (TextView)  findViewById(R.id.lbl_name);
        TextView txt_desc = (TextView) findViewById(R.id.lbl_description);

        Intent edit = new Intent(ViewPerson.this, EditPerson.class);
        edit.putExtra("name", txt_name.getText().toString());
        edit.putExtra("description", txt_desc.getText().toString());
        startActivityForResult(edit, EDIT_PERSON);
    }
}
