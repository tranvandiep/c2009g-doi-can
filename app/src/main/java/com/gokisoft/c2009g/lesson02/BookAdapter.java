package com.gokisoft.c2009g.lesson02;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gokisoft.c2009g.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends BaseAdapter {
    Activity mActivity;
    List<Book> dataList;

    public BookAdapter(Activity activity, List<Book> dataList) {
        this.mActivity = activity;
        this.dataList = dataList;
    }

    public void setDataList(List<Book> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = mActivity.getLayoutInflater().inflate(R.layout.item_book_new, null);

        TextView titleView = view.findViewById(R.id.ibn_title);
        TextView authorView = view.findViewById(R.id.ibn_author);
        TextView priceView = view.findViewById(R.id.ibn_price);
        ImageView imageView = view.findViewById(R.id.ibn_thumbnail);

        //bind du lieu
        Book book = dataList.get(i);

        titleView.setText(book.getBookName());
        authorView.setText(book.getAuthorName());
        priceView.setText(book.getPrice() + "");
//        imageView.setImageResource(R.drawable.android);
        Picasso.with(mActivity).load("https://cdn-media-1.freecodecamp.org/images/cxyJa1qZG5uNkwE2yOwLfOiyVDid7QVYTOj7").into(imageView);

        return view;
    }
}
