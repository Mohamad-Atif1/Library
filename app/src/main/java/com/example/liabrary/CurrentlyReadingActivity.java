package com.example.liabrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class CurrentlyReadingActivity extends AppCompatActivity {

    RecyclerView currentlyReadingView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currently_reading);

        currentlyReadingView = findViewById(R.id.currentlyReadingView);
        BooksRecycleViewAdapter adapter = new BooksRecycleViewAdapter(this);
        adapter.setBooks(Utils.getInstance(this).getCurrentlyReadingBooks());
        currentlyReadingView.setAdapter(adapter);
        currentlyReadingView.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}