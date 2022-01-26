package com.example.liabrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class WishListActivity extends AppCompatActivity {

    private RecyclerView wantToReadView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read);

        wantToReadView = findViewById(R.id.wantToReadView);
        BooksRecycleViewAdapter adapter = new BooksRecycleViewAdapter(this);
        adapter.setBooks(Utils.getInstance(this).getWantToReadBooks());
        wantToReadView.setAdapter(adapter);
        wantToReadView.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}