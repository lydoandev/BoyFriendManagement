package com.example.androidconnectdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvUsers;
    AppDatabase db;
    Button btnAdd;
    BoyFriendAdapter adapter;
    List<BoyFriend> boyFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.addNew);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNew.class);

                startActivity(intent);
            }
        });

        rvUsers = findViewById(R.id.rvUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter = new BoyFriendAdapter();
        adapter.listener = new BoyFriendAdapter.OnItemListener() {
            @Override
            public void onDeleteClicked(int position) {
                deleteItem(position);
            }
        };

        rvUsers.setAdapter(adapter);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {


                boyFriends = db.boyFriendDao().getAll();
                Log.i("ly", "Ly" + boyFriends.size());
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.boyFriends = boyFriends;
                adapter.notifyDataSetChanged();
            }
        }.execute(); 
    }

    private void deleteItem(int position) {
        final BoyFriend deleteBoyFriend = adapter.boyFriends.get(position);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.boyFriendDao().delete(deleteBoyFriend);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                adapter.boyFriends.remove(deleteBoyFriend);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

}
