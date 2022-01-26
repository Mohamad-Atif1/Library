package com.example.liabrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAllBooks,btnReadBooks,btnCurrentBook,btnAbout,btnFavorites,btnWishList;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inti();
        btnAllBooks.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,AllBooksActivity.class);
            startActivity(intent);
        });

    }

    public void inti(){

    btnAbout = findViewById(R.id.btnAbout);
    btnAllBooks = findViewById(R.id.btnAllBooks);
    btnCurrentBook = findViewById(R.id.btnCurrentBook);
    btnFavorites = findViewById(R.id.btnFavorites);
    btnReadBooks = findViewById(R.id.btnReadBooks);
    btnWishList = findViewById(R.id.btnWishlist);

    btnAllBooks.setOnClickListener(this);
    btnWishList.setOnClickListener(this);
    btnReadBooks.setOnClickListener(this);
    btnFavorites.setOnClickListener(this);
    btnCurrentBook.setOnClickListener(this);
    btnAbout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnCurrentBook:
                 intent = new Intent(this , CurrentlyReadingActivity.class);
                startActivity(intent);
                break;
            case R.id.btnFavorites:
                 intent = new Intent(this , FavoriteBooksActivity.class);
                startActivity(intent);
                break;
            case R.id.btnWishlist:
                intent = new Intent(this , WishListActivity.class);
                startActivity(intent);
                break;
            case R.id.btnReadBooks:
                intent = new Intent(this , AlreadyReadBooksActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAbout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("the developer");
                builder.setMessage("Designed And Development With Love By Mohamad Atif\n" +
                        "Check My Linkedin To See My Cv");
                builder.setPositiveButton("View",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intent = new Intent(MainActivity.this,WebsiteActivity.class);
                        intent.putExtra("URL","https://www.linkedin.com/in/%D9%85%D8%AD%D9%85%D8%AF-%D8%A7%D9%84%D8%B4%D8%A8%D8%B1%D8%A7%D9%88%D9%8A-847455194/");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Dismiss", (dialog, which) -> {
                });
                builder.create().show();
                break;
            default:
                break;

        }

    }
}