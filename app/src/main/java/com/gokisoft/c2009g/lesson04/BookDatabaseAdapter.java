package com.gokisoft.c2009g.lesson04;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.gokisoft.c2009g.R;
import com.gokisoft.c2009g.lesson02.Book;

public class BookDatabaseAdapter extends CursorAdapter {
    Activity activity;

    public BookDatabaseAdapter(Activity activity, Cursor c) {
        super(activity, c);
        this.activity = activity;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = activity.getLayoutInflater().inflate(R.layout.item_book_new, null);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleView = view.findViewById(R.id.ibn_title);
        TextView authorView = view.findViewById(R.id.ibn_author);
        TextView priceView = view.findViewById(R.id.ibn_price);

        Book book = new Book();
        book.setData(cursor);

        titleView.setText(book.getBookName());
        authorView.setText(book.getAuthorName());
        priceView.setText(book.getPrice() + "");
    }
}
