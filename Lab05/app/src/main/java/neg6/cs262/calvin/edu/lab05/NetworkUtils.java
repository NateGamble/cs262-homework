package neg6.cs262.calvin.edu.lab05;

import android.net.Uri;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static final String BOOK_BASE_URL =  "https://www.googleapis.com/books/v1/volumes?"; // Base URI for the Books API
    private static final String QUERY_PARAM = "q"; // Parameter for the search string
    private static final String MAX_RESULTS = "maxResults"; // Parameter that limits search results
    private static final String PRINT_TYPE = "printType";   // Parameter to filter by print type

    static String getBookInfo(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try {
            //Build up your query URI, limiting results to 10 items and printed books
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();
            URL requestURL = new URL(builtURI.toString());
        } catch (Exception ex) {

        } finally {
            return bookJSONString;
        }


    }
}
