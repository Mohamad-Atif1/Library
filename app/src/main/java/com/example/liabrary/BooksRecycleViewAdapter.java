package com.example.liabrary;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.liabrary.Utils.BOOK_ID;

public class BooksRecycleViewAdapter extends RecyclerView.Adapter<BooksRecycleViewAdapter.ViewHolder> {


    private ArrayList<Book>  books = new ArrayList<>();
    private Context context;




    public BooksRecycleViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksRecycleViewAdapter.ViewHolder holder, int position) {

        holder.txtNameBook.setText(books.get(position).getName());
        Glide.with(context).asBitmap().load(books.get(position).getUrlImage()).into(holder.bookImage);

        holder.parent.setOnClickListener(v -> {
            Intent intent = new Intent(context,BookActivity.class);
            intent.putExtra(BOOK_ID, books.get(position).getId());
            context.startActivity(intent);
        });

        // Arrows for setting the state of isExpended variable
        holder.downArrow.setOnClickListener(v -> {
            books.get(position).setExpended(!books.get(position).isExpended());
            notifyItemChanged(position);

        });
        holder.upArrow.setOnClickListener(v -> {
            books.get(position).setExpended(!books.get(position).isExpended());
            notifyItemChanged(position);

        });

        if (books.get(position).isExpended())
        {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedDesLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if(context.getClass() == AllBooksActivity.class)
                holder.btnDelete.setVisibility(View.GONE);

            holder.btnDelete.setOnClickListener(v -> {
                if (context.getClass() == AlreadyReadBooksActivity.class){


                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete" + books.get(position).getName() + " book ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Utils.getInstance(context).delAlreadyReadBooks(books.get(position));
                            notifyDataSetChanged();
                            Toast.makeText(context, "item deleted successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", (dialog, which) -> {
                    });
                    builder.create().show();
                }

                else if (context.getClass() == WishListActivity.class){

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete"  + " book ?");
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        Utils.getInstance(context).delWantToReadBooks(books.get(position));
                        Toast.makeText(context, "item deleted successfully", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    });
                    builder.setNegativeButton("No", (dialog, which) -> {
                    });
                    builder.create().show();
                }

                else if (context.getClass() == FavoriteBooksActivity.class){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete" + books.get(position).getName() + " book ?");
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        Utils.getInstance(context).delFavoriteBooks(books.get(position));
                        Toast.makeText(context, "item deleted successfully", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                    });
                    builder.setNegativeButton("No", (dialog, which) -> {
                    });
                    builder.create().show();
                }

                else if (context.getClass() == CurrentlyReadingActivity.class){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure you want to delete" + books.get(position).getName() + " book ?");
                    builder.setPositiveButton("Yes", (dialog, which) -> {
                        Utils.getInstance(context).delCurrentlyReadingBooks(books.get(position));
                        notifyDataSetChanged();
                        Toast.makeText(context, "item deleted successfully", Toast.LENGTH_SHORT).show();

                    });
                    builder.setNegativeButton("No", (dialog, which) -> {
                    });
                    builder.create().show();
                }
            });


        }
        else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedDesLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);

        }

    }




    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNameBook;
        private CardView parent;
        private ImageView bookImage;

        private TextView txtAuthor,txtAuthorName,shortDes , btnDelete ;
        private ImageView upArrow,downArrow;
        private RelativeLayout expandedDesLayout,shortDesLayout;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            txtNameBook = itemView.findViewById(R.id.txtBookName);
            bookImage = itemView.findViewById(R.id.bookImage);
            parent = itemView.findViewById(R.id.parent);

            txtAuthor = itemView.findViewById(R.id.txtAuthor);
            txtAuthorName= itemView.findViewById(R.id.txtAuthorName);
            shortDes = itemView.findViewById(R.id.shortDes);
            upArrow = itemView.findViewById(R.id.upArrow);
            downArrow = itemView.findViewById(R.id.downArrow);
            expandedDesLayout = itemView.findViewById(R.id.expandedDesLayout);
            shortDesLayout = itemView.findViewById(R.id.shortDesLayout);
            btnDelete= itemView.findViewById(R.id.btnDelete);
        }
    }



}
