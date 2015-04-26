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
    private int taskNumber;

    public ProgressTask (ProgressBar progressBar, int taskNumber) {
        mPBar = progressBar;
        count = 0;
        this.taskNumber = taskNumber;
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
                count+=5;
                publishProgress();
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

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        mPBar.setProgress(count);
    }

}
