package com.gokisoft.c2009g.lesson04;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gokisoft.c2009g.R;
import com.gokisoft.c2009g.lesson02.Book;

import java.util.List;

public class BookRecyleViewAdapter extends RecyclerView.Adapter<BookRecyleViewAdapter.BookViewHolder> {
    Activity activity;
    List<Book> bookList;

    public BookRecyleViewAdapter(Activity activity, List<Book> bookList) {
        this.activity = activity;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_book_new, null);

        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        holder.titleView.setText(book.getBookName());
        holder.authorView.setText(book.getAuthorName());
        holder.priceView.setText(book.getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    class BookViewHolder extends  RecyclerView.ViewHolder {
        TextView titleView;
        TextView authorView;
        TextView priceView;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.ibn_title);
            authorView = itemView.findViewById(R.id.ibn_author);
            priceView = itemView.findViewById(R.id.ibn_price);
        }
    }
}
