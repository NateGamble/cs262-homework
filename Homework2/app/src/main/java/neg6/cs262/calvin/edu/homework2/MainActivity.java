package neg6.cs262.calvin.edu.homework2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    private EditText mBookInput;
    private TextView mEmailText;
    private TextView mIdText;
    private TextView mNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookInput = findViewById(R.id.bookInput);
        mEmailText = findViewById(R.id.authorText);
        mIdText = findViewById(R.id.titleText);
        mNameText = findViewById(R.id.nameText);
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    public void searchBooks(View view) {
        String queryString = mBookInput.getText().toString();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
//            new FetchBook(mIdText, mEmailText).execute(queryString);
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
            mEmailText.setText("");
            mNameText.setText("");
            mIdText.setText(R.string.loading);
        } else {
            if (queryString.length() == 0) {
                mEmailText.setText("");
                mNameText.setText("");
                mIdText.setText(R.string.empty_query);
            } else {
                mEmailText.setText("");
                mNameText.setText("");
                mIdText.setText(R.string.no_network_connection);
            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new BookLoader(this, bundle.getString("queryString"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
//            JSONArray itemsArray = jsonObject.names();

            //Iterate through the results
//            for(int i = 0; i<itemsArray.length(); i++){
            JSONObject book = jsonObject; //Get the current item
            String id = null;
            String email = null;
            String name = null;

            try {
                id = book.getString("id");
                email = book.getString("emailAddress");
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                name = book.getString("name");
            } catch (Exception e) {
                name = "no name";
            }
            //If both a id and email exist, update the TextViews and return
            if (id != null && email != null) {
                mIdText.setText("id: " + id);
                mEmailText.setText("email: " + email);
                mNameText.setText("name: " + name);
                return;
            }

            mIdText.setText("No Results Found");
            mEmailText.setText("");
            mNameText.setText("");
        } catch (Exception e) {
            mIdText.setText("No Results Found");
            mEmailText.setText("");
            mNameText.setText("");
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
