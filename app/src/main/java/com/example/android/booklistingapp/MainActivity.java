package com.example.android.booklistingapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes?maxResults=10";
    private String mFinalUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to search button
        Button searchButton = (Button) findViewById(R.id.search_button);

        /**
         * This event will be called when user clicks on search button
         */
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get a reference to search bar
                EditText searchBar = (EditText) findViewById(R.id.search_bar);

                // Store keyword typed by the user, i.e, Harry Potter
                String keyword = searchBar.getText().toString();

                mFinalUrl = getFinalUrl(keyword);

                // Create intent to open ResultsActivity.java and show searching results
                Intent openResultsActivity = new Intent(MainActivity.this, ResultsActivity.class);
                openResultsActivity.putExtra("finalUrl", mFinalUrl);
                MainActivity.this.startActivity(openResultsActivity);

//                new myAsyncTask().execute(finalUrl);

                Log.v("ResultsActivity.java", "keyword value: " + keyword);
                Log.v("ResultsActivity.java", "finalUrl value: " + mFinalUrl);

            }
        });
    }

    /**
     * This method defines the final url which will be used to make the request
     * @param keyword is the word that the user entered in the search  box
     * @return the formatted string
     */
    private String getFinalUrl(String keyword) {
        // Create a URI object
        Uri baseUri = Uri.parse(BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        // Append query parameter "q" to URI
        uriBuilder.appendQueryParameter("q", keyword);

        // Return URI in String format
        return uriBuilder.toString();

    }
}
