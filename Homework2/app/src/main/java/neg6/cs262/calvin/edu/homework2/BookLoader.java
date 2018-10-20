package neg6.cs262.calvin.edu.homework2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class BookLoader extends AsyncTaskLoader {

    private String mQueryString;

    public BookLoader(@NonNull Context context, String QueryString) {
        super(context);
        mQueryString = QueryString;
    }

    @Nullable
    @Override
    public Object loadInBackground() {
        return NetworkUtils.getBookInfo(mQueryString);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
