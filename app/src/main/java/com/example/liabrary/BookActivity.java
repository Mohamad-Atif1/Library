package com.example.liabrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.liabrary.Utils.BOOK_ID;


public class BookActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtNumPages, bookNametxt, authorNametxt, longDescription;
    private ImageView imageViewBook;
    private Button btnCurrentlyReading, btnWantToRead, btnAddToFavorites, btnAlreadyRead;
    private Book clickedBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        // Get the data from recycleView
        initViews();
        Intent incomingIntent = getIntent();
        if (incomingIntent != null) {
            int clickedBookId = incomingIntent.getIntExtra(BOOK_ID, -1);
            if (clickedBookId != -1) {
                clickedBook = Utils.getInstance(this).getBookById(clickedBookId);
                setBookData(clickedBook);
            }
        }
        enableDisableBtn();

    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnCurrentlyReading:
                if (!isBookExist(Utils.getInstance(this).getCurrentlyReadingBooks())) {
                    Utils.getInstance(this).addCurrentlyReadingBooks(clickedBook);
                    Toast.makeText(this, "add to CurrentlyReading successfully ", Toast.LENGTH_SHORT).show();
                    btnCurrentlyReading.setEnabled(false);
                    intent = new Intent(this, CurrentlyReadingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "you add this book before", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnAlreadyRead:
                if (!isBookExist(Utils.getInstance(this).getAlreadyReadBooks())) {
                    Utils.getInstance(this).addAlreadyReadBooks(clickedBook);
                    Toast.makeText(this, "add to AlreadyReadBooks successfully", Toast.LENGTH_SHORT).show();
                    btnAlreadyRead.setEnabled(false);
                    intent = new Intent(this, AlreadyReadBooksActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "you add this book before", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnAddToFavorites:
                if (!isBookExist(Utils.getInstance(this).getFavoriteBooks())) {
                    Utils.getInstance(this).addFavoriteBooks(clickedBook);
                    Toast.makeText(this, "add to FavoriteBooks successfully", Toast.LENGTH_SHORT).show();
                    btnAddToFavorites.setEnabled(false);
                    intent = new Intent(this, FavoriteBooksActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "you add this book before", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnWantToRead:


                if (!isBookExist(Utils.getInstance(this).getWantToReadBooks())) {
                    Utils.getInstance(this).addWantToReadBooks(clickedBook);
                    Toast.makeText(this, " add to WantToReadBooks successfully", Toast.LENGTH_SHORT).show();
                    btnWantToRead.setEnabled(false);
                    intent = new Intent(this, WishListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "you add this book before", Toast.LENGTH_SHORT).show();
                }
                ;
                break;
            default:
                break;


        }
    }

    private void setBookData(Book clickedBook) {
        txtNumPages.setText(String.valueOf(clickedBook.getPages()));
        bookNametxt.setText(clickedBook.getName());
        authorNametxt.setText(clickedBook.getAuthor());
        longDescription.setText(clickedBook.getLongDes());
        Glide.with(this).asBitmap().load(clickedBook.getUrlImage()).into(imageViewBook);

    }

    private void initViews() {

        txtNumPages = findViewById(R.id.txtNumPages);
        bookNametxt = findViewById(R.id.bookNametxt);
        imageViewBook = findViewById(R.id.imageViewBook);
        authorNametxt = findViewById(R.id.authorNametxt);
        longDescription = findViewById(R.id.longDescription);

        btnAlreadyRead = findViewById(R.id.btnAlreadyRead);
        btnAddToFavorites = findViewById(R.id.btnAddToFavorites);
        btnCurrentlyReading = findViewById(R.id.btnCurrentlyReading);
        btnWantToRead = findViewById(R.id.btnWantToRead);

        btnWantToRead.setOnClickListener(this);
        btnCurrentlyReading.setOnClickListener(this);
        btnAddToFavorites.setOnClickListener(this);
        btnAlreadyRead.setOnClickListener(this);
    }



    private boolean isBookExist(ArrayList<Book> book) {
        boolean bookExist = false;
        loop1:
        for (Book b : book) {
            if (b.getId() == clickedBook.getId()) {
                bookExist = true;
                break loop1;
            }
        }
        return bookExist;

    }

    private void enableDisableBtn() {
        if (isBookExist(Utils.getInstance(this).getFavoriteBooks()))
            btnAddToFavorites.setEnabled(false);
        if (isBookExist(Utils.getInstance(this).getAlreadyReadBooks()))
            btnAlreadyRead.setEnabled(false);
        if (isBookExist(Utils.getInstance(this).getCurrentlyReadingBooks()))
            btnCurrentlyReading.setEnabled(false);
        if (isBookExist(Utils.getInstance(this).getWantToReadBooks()))
            btnWantToRead.setEnabled(false);
    }

}
