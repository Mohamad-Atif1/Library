package com.example.liabrary;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utils {

    private SharedPreferences sharedPreferences;
    private static final String ALL_BOOKS_KYE = "ALL_BOOKS_KYE";
    private static final String ALREADY_READ_BOOKS = "ALREADY_READ_BOOKS";
    private static final String WANT_TO_READ_BOOKS = "WANT_TO_READ_BOOKS";
    private static final String CURRENTLY_READING_BOOKS = "CURRENTLY_READING_BOOKS";
    private static final String FAVORITE_BOOKS = "FAVORITE_BOOKS";


    private static Utils instance;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> allBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favoriteBooks;
    public final static String BOOK_ID = "bookId";


    private Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);
        if (getAllBooks() == null) {
            initData();
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        if (getAlreadyReadBooks() == null) {
            editor.putString(ALREADY_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getWantToReadBooks() == null) {
            editor.putString(WANT_TO_READ_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getCurrentlyReadingBooks() == null) {
            editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }
        if (getFavoriteBooks() == null) {
            editor.putString(FAVORITE_BOOKS,gson.toJson(new ArrayList<Book>()));
            editor.commit();
        }


    }

    public static Utils getInstance(Context context) {
        if (instance == null) {
            instance = new Utils(context);
        }
        return instance;
    }

    private void initData() {

        ArrayList<Book> books  = new ArrayList<>();

        final String QUIET_BOOK_Url = "https://images-na.ssl-images-amazon.com/images/I/81PFSx4F1KL.jpg";
        final String ALGORITHMS_BOOK_URL = "https://m.media-amazon.com/images/I/513P8XoCAEL._AC_SY780_.jpg";
        final String DATASTR_AND_ALGO_URL = "https://images-na.ssl-images-amazon.com/images/I/71kBRLo8ZtL.jpg";
        final String DATABASEURL = "https://images-na.ssl-images-amazon.com/images/I/71OXZNV89+L.jpg";

        books.add(new Book("Quiet","Unknown", QUIET_BOOK_Url, 1, 5, "QUIET_BOOK", "Long Description..."));
        books.add(new Book("algorithms","Unknown", ALGORITHMS_BOOK_URL, 2, 200, "ALGORITHMS_BOOK", "Long Description..."));
        books.add(new Book("data structure and algorithms","Unknown", DATASTR_AND_ALGO_URL, 3, 545, "DATASTR_AND_ALGO", "Long Description..."));
        books.add(new Book("data base ","Unknown", DATABASEURL, 4, 4, " DATABASEURL", "Long Description..."));

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson(); // Gson is used to convert any type to String

        // (Key,String), we convert ArrayList<Book>  to String using gson library
        editor.putString(ALL_BOOKS_KYE,gson.toJson(books));

        /* editor.apply() applied in the background thread , we will not block the main thread
        here we can use commit because our data is very small */
        editor.commit();


    }

    public  ArrayList<Book> getAlreadyReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType(); // save ArrayList<Book> dataType in type
        ArrayList<Book> books = gson.fromJson( sharedPreferences.getString(ALREADY_READ_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Book> getAllBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType(); // save ArrayList<Book> dataType in type
        ArrayList<Book> books = gson.fromJson( sharedPreferences.getString(ALL_BOOKS_KYE,null),type);
        return books;
    }

    public  ArrayList<Book> getWantToReadBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType(); // save ArrayList<Book> dataType in type
        ArrayList<Book> books = gson.fromJson( sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Book> getCurrentlyReadingBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType(); // save ArrayList<Book> dataType in type
        ArrayList<Book> books = gson.fromJson( sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Book> getFavoriteBooks() {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Book>>(){}.getType(); // save ArrayList<Book> dataType in type
        ArrayList<Book> books = gson.fromJson( sharedPreferences.getString(FAVORITE_BOOKS,null),type);
        return books;
    }

    public Book getBookById(int id){

        ArrayList<Book> books = getAllBooks();
        if (books != null) {
            for (Book book : books) {

                if (book.getId() == id) {
                    return book;
                }

            }
        }
        return null;
    }

    public void addCurrentlyReadingBooks(Book addBook){
        ArrayList<Book> books  = getCurrentlyReadingBooks();
        if (books != null){
            if (books.add(addBook)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(CURRENTLY_READING_BOOKS); // remove the old version of the book
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books)) ;// add the new version
                editor.commit();
            }
        }
        
    }
    public  void addAlreadyReadBooks(Book addBook){
        ArrayList<Book> books  = getAlreadyReadBooks();
        if (books != null){
            if (books.add(addBook)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(ALREADY_READ_BOOKS); // remove the old version of the book
                editor.putString(ALREADY_READ_BOOKS, gson.toJson(books)) ;// add the new version
                editor.commit();
            }
        }
    }
    public  void addWantToReadBooks(Book addBook){
        ArrayList<Book> books  = getWantToReadBooks();
        if (books != null){
            if (books.add(addBook)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(CURRENTLY_READING_BOOKS); // remove the old version of the book
                editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books)) ;// add the new version
                editor.commit();
            }
        }
    }
    public  void addAllBooks(Book addBook){
        ArrayList<Book> books  = getAllBooks();
        if (books != null){
            if (books.add(addBook)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(ALL_BOOKS_KYE); // remove the old version of the book
                editor.putString(ALL_BOOKS_KYE, gson.toJson(books)) ;// add the new version
                editor.commit();
            }
        }
    }
    public  void addFavoriteBooks(Book addBook){
        ArrayList<Book> books  = getFavoriteBooks();
        if (books != null){
            if (books.add(addBook)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                editor.remove(FAVORITE_BOOKS); // remove the old version of the book
                editor.putString(FAVORITE_BOOKS, gson.toJson(books)) ;// add the new version
                editor.commit();
            }
        }    }

    public  void delAlreadyReadBooks(Book delBook){
        ArrayList<Book> books  = getAlreadyReadBooks();
        if (books != null){
            for(Book book : books){
                if (book.getId() == delBook.getId()) {
                    if (books.remove(book)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(ALREADY_READ_BOOKS); // remove the old version of the book
                        editor.putString(ALREADY_READ_BOOKS, gson.toJson(books));// add the new version
                        editor.commit();
                        break;
                    }
                }
            }

        }
    }
    public  void delWantToReadBooks(Book delBook){
        ArrayList<Book> books  = getWantToReadBooks();
        if (books != null){
            for(Book book : books){
                if (book.getId() == delBook.getId()) {
                    if (books.remove(book)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(WANT_TO_READ_BOOKS); // remove the old version of the book
                        editor.putString(WANT_TO_READ_BOOKS, gson.toJson(books));// add the new version
                        editor.commit();
                        break;
                    }
                }
            }

        }
    }
    public  void delAllBooks(Book delBook){
        ArrayList<Book> books  = getAllBooks();
        if (books != null){
            for(Book book : books){
                if (book.getId() == delBook.getId()) {
                    if (books.remove(book)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(ALL_BOOKS_KYE); // remove the old version of the book
                        editor.putString(ALL_BOOKS_KYE, gson.toJson(books));// add the new version
                        editor.commit();
                        break;
                    }
                }
            }

        }
    }
    public  void delFavoriteBooks(Book delBook){
        ArrayList<Book> books  = getFavoriteBooks();
        if (books != null){
            for(Book book : books){
                if (book.getId() == delBook.getId()) {
                    if (books.remove(book)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(FAVORITE_BOOKS); // remove the old version of the book
                        editor.putString(FAVORITE_BOOKS, gson.toJson(books));// add the new version
                        editor.commit();
                        break;
                    }
                }
            }

        }
    }
    public  void delCurrentlyReadingBooks(Book delBook){
        ArrayList<Book> books  = getCurrentlyReadingBooks();
        if (books != null){
            for(Book book : books){
                if (book.getId() == delBook.getId()) {
                    if (books.remove(book)) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        editor.remove(CURRENTLY_READING_BOOKS); // remove the old version of the book
                        editor.putString(CURRENTLY_READING_BOOKS, gson.toJson(books));// add the new version
                        editor.commit();
                        break;
                    }
                }
            }

        }
    }
}
