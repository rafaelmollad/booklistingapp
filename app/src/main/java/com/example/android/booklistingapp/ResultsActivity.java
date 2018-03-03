package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {


    private static final int BOOK_LOADER_ID = 1;
    private boolean mFirstLoaderCreated = false;
    private BookAdapter mAdapter;
    private String mFinalUrl;

/*    private static class myAsyncTask extends AsyncTask<String, Void, List<Book>> {

        @Override
        protected List<Book> doInBackground(String...myString) {
            if (myString[0] != null) {
                List<Book> books = QueryUtils.fetchBookData(myString[0]);
                return books;
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            if (books != null) {
                for (int i = 0; i < books.size(); i++) {
                    Log.v("ResultsActivity.java", "First book title: " + books.get(i).mTitle);
                }
            } else {
                Log.v("ResultsActivity.java", "This book doesn't exist in our stock");
            }
        }
    }*/

    // Get a reference to the LoaderManager in order to interact with Loaders
    LoaderManager loaderManager = getLoaderManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);

        // Get intent from MainActivity.java
        Intent getMainActivityIntent = getIntent();

        // Get formatted URL to make request from MainActivity.java
        mFinalUrl = getMainActivityIntent.getStringExtra("finalUrl");

        ListView booksList = (ListView) findViewById(R.id.list);

        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        booksList.setAdapter(mAdapter);


        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(BOOK_LOADER_ID, null, ResultsActivity.this);

         loaderManager.restartLoader(BOOK_LOADER_ID, null, ResultsActivity.this);

    }


    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        return new BookLoader(this, mFinalUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {


        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        if (mAdapter != null) {
            mAdapter.clear();
        }

        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.v("ResultsActivity.java", "This is onLoaderReset");
    }
}