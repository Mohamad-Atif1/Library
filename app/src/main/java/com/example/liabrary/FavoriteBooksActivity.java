package com.example.liabrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class FavoriteBooksActivity extends AppCompatActivity {

    private RecyclerView favoriteBooksView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_books);

        favoriteBooksView = findViewById(R.id.favoriteBooksView);
        BooksRecycleViewAdapter adapter = new BooksRecycleViewAdapter(this);
        adapter.setBooks(Utils.getInstance(this).getFavoriteBooks());

        favoriteBooksView.setAdapter(adapter);
        favoriteBooksView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}