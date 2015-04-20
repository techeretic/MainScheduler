package prathamesh.shetye.seqsched;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by p.shetye on 4/20/15.
 */
public class RecViewAdapter extends
        RecyclerView.Adapter<RecViewAdapter.ViewHolder> {
    Context mContext;
    List<String> mRunns;

    public RecViewAdapter(Context context, List<String> runns) {
        mContext = context;
        mRunns = runns;
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
        holder.runnName.setText(mRunns.get(position));
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
