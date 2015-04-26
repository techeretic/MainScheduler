package prathamesh.shetye.seqsched;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by p.shetye on 4/20/15.
 */
public class RecViewAdapter extends
        RecyclerView.Adapter<RecViewAdapter.ViewHolder> {
    Context mContext;
    List<String> mRunns;
    List<ProgressTask> mPTasks = new LinkedList<>();
    static ExecutorService mExec;
    static HashSet<Integer> mProcessed = new HashSet<>();



    public RecViewAdapter(Context context, List<String> runns) {
        mContext = context;
        mRunns = runns;
        mExec = Executors.newFixedThreadPool(1);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.runnabel_item, viewGroup,
                false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("Adapter","Calling onBindViewHolder for Position : " + position);
        holder.runnName.setText(mRunns.get(position));
        //mPTasks.add(new ProgressTask(holder.pBar, position));
        if (!mProcessed.contains(position)) {
            Log.d("Adapter","Adding AsynTask for : " + position);
            new ProgressTask(holder.pBar, position).executeOnExecutor(mExec, null);
            mProcessed.add(position);
        }
    }

    @Override
    public int getItemCount() {
        return mRunns.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView runnName;
        ProgressBar pBar;

        public ViewHolder(View view) {
            super(view);
            runnName = (TextView) view.findViewById(R.id.textView);
            pBar = (ProgressBar) view.findViewById(R.id.progressBar);
            pBar.setIndeterminate(false);
            pBar.setMax(100);
            pBar.setProgress(0);

        }
    }
}
