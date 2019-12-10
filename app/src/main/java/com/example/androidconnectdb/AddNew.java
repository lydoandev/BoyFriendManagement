package com.example.androidconnectdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddNew extends AppCompatActivity {
    AppDatabase db;
    EditText txtName, txtDate;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        btnAdd = findViewById(R.id.btnAdd);
        txtName = findViewById(R.id.txtName);
        txtDate = findViewById(R.id.edittext_date);
        txtDate.setFocusable(false);
        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickDate();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBoyFriend();

            }
        });
    }

    private void addBoyFriend() {
        final BoyFriend user1 = new BoyFriend();
        user1.name = txtName.getText().toString();
        user1.date = txtDate.getText().toString();
        Log.i("Date", user1.date);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.boyFriendDao().insert(user1);

                return null;
            }

            protected void onPostExecute(Void aVoid){
                Toast.makeText(AddNew.this, "Add boy friend successful", Toast.LENGTH_SHORT).show();
                finish();

            }

        }.execute();
    }

    private void pickDate() {
        new DatePickerDialog(AddNew.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                String date = "" + year + "/" + (month + 1) + "/" + day;
                txtDate.setText(date);

            }
        }, 2019, 11, 1).show();
    }
}
