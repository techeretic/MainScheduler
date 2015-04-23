package prathamesh.shetye.seqsched;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by prathamesh on 4/21/15.
 */
public class ProgressTask extends AsyncTask<Integer, Void, Void> {

    private final String LOG_TAG = "ProgressTask";
    private int count;

    private ProgressBar mPBar;

    public ProgressTask (ProgressBar progressBar) {
        mPBar = progressBar;
        count = 0;
    }

    @Override
    protected void onPreExecute() {
        if (mPBar == null) {
            Log.d(LOG_TAG, "Cancelling TASK as ProgressBar object is NULL");
            this.cancel(true);
            return;
        }
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        while(count < 100) {
            try {
                Thread.sleep(125);
                mPBar.setProgress(count);
                count+=5;
            } catch (InterruptedException e) {
                Log.e(LOG_TAG, "Exception in ProgressTask", e);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
