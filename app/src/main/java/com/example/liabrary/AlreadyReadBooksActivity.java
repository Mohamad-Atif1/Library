package com.example.liabrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


public class AlreadyReadBooksActivity extends AppCompatActivity {

    private RecyclerView alreadyBooksReadView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_books);



        alreadyBooksReadView = findViewById(R.id.alreadyBooksReadView);
        BooksRecycleViewAdapter adapter = new BooksRecycleViewAdapter(this);
        adapter.setBooks(Utils.getInstance(this).getAlreadyReadBooks());
        alreadyBooksReadView.setAdapter(adapter);
        alreadyBooksReadView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}