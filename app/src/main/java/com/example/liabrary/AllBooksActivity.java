package com.example.liabrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    private RecyclerView allBookView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
        allBookView = findViewById(R.id.allBookView);

        BooksRecycleViewAdapter adapter = new BooksRecycleViewAdapter(this);

        adapter.setBooks(Utils.getInstance(this).getAllBooks());

        allBookView.setAdapter(adapter);
        allBookView.setLayoutManager(new LinearLayoutManager(this));

    }
}







