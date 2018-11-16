package neg6.cs262.calvin.edu.homework2;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
//    private static final String BOOK_BASE_URL =  "https://calvincs262-monopoly.appspot.com/monopoly/v1/players"; // Base URI for the Books API
//    private static final String QUERY_PARAM = "q"; // Parameter for the search string
//    private static final String MAX_RESULTS = "maxResults"; // Parameter that limits search results
//    private static final String PRINT_TYPE = "printType";   // Parameter to filter by print type

    static String getBookInfo(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String PlayersJSONString = null;
        String baseURL = "https://calvincs262-monopoly.appspot.com/monopoly/v1/player";
        URL requestURL;

        try {
            //Build up your query URI, limiting results to 10 items and printed books
//            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
//                    .appendQueryParameter(QUERY_PARAM, queryString)
//                    .appendQueryParameter(MAX_RESULTS, "10")
//                    .appendQueryParameter(PRINT_TYPE, "books")
//                    .build();

            if ("me@calvin.edu".contains(queryString) || "1".contains(queryString)) {
                requestURL = new URL(baseURL + "/1");
            } else if ("The King".contains(queryString) || "king@gmail.edu".contains(queryString) || "2".contains(queryString)) {
                requestURL = new URL(baseURL + "/2");
            } else if ("Dogbreath".contains(queryString) || "dog@gmail.edu".contains(queryString) || "3".contains(queryString)) {
                requestURL = new URL(baseURL + "/3");
            } else {
                requestURL = new URL(baseURL + "s");
            }


            //make URL request
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Read response and convert it to a string
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                /* Since it's JSON, adding a newline isn't necessary (it won't affect
                   parsing) but it does make debugging a *lot* easier if you print out the
                   completed buffer for debugging. */
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            PlayersJSONString = buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d(LOG_TAG, PlayersJSONString);
        return PlayersJSONString;

    }
}
