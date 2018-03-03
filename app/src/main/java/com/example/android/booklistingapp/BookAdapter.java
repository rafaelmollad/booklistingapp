package com.example.android.booklistingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rafael on 28/2/2018.
 */

public class BookAdapter extends ArrayAdapter<Book> {
        /**
         *
         * @param context of the app
         * @param books is the list of books which is the data source of the adapter
         */
        public BookAdapter(Context context, List<Book> books) {
            super(context, 0, books);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;

            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.book_item, parent, false);
            }

            Book currentBook = getItem(position);

            // Set book title
            TextView bookTitle = (TextView) listItemView.findViewById(R.id.book_title);
            bookTitle.setText(currentBook.getBookTitle());

            // Set book description
            TextView bookDescription = (TextView) listItemView.findViewById(R.id.book_description);
            bookDescription.setText(currentBook.getBookDescription());

            // Set book Authors
            TextView bookAuthors = (TextView) listItemView.findViewById(R.id.book_authors);
            bookAuthors.setText(currentBook.getBookAuthors());

            // Set book page count
            TextView bookPageCount = (TextView) listItemView.findViewById(R.id.book_page_count);
            bookPageCount.setText(currentBook.getBookPageCount());

            // Set book published date
            TextView bookPublishedDate = (TextView) listItemView.findViewById(R.id.book_published_date);
            bookPublishedDate.setText(currentBook.getBookPublishedDate());

            // Set book image
            ImageView bookImage = (ImageView) listItemView.findViewById(R.id.book_thumbnail);
            bookImage.setImageBitmap(currentBook.getBookThumbnail());

            return listItemView;
        }


    }
