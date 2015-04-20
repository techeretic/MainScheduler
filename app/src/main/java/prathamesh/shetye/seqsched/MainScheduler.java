package prathamesh.shetye.seqsched;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MainScheduler extends ActionBarActivity {
    private FloatingActionButton mFAddButton;
    private RecyclerView mRecView;
    private Context mContext;
    private List<String> mRunns = new LinkedList<>();
    private RecViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scheduler);
        mContext = this;
        mRecView = (RecyclerView) findViewById(R.id.recycleThreads);

        mFAddButton = new FloatingActionButton.Builder(this)
                .withDrawable(getDrawable(R.drawable.abc_ic_go_search_api_mtrl_alpha))
                .withButtonColor(getResources().getColor(R.color.ripple_material_dark))
                .withGravity(Gravity.BOTTOM | Gravity.END).withMargins(0, 0, 15, 15).create();

        NotifAnimator.animateFAB(getApplicationContext(), mFAddButton, NotifAnimator.IN,
                NotifAnimator.BOTTOM);

        mFAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


        mAdapter = new RecViewAdapter(mContext, mRunns);
        mRecView.setAdapter(mAdapter);
        mRecView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    private void updateRecView() {
        RecViewAdapter mAdapter = new RecViewAdapter(mContext, mRunns);
        mRecView.setAdapter(mAdapter);
        mRecView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    private void showDialog() {
        Button dialogOKBtn;
        final EditText editText;

        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.diag_add_name);
        dialog.setCancelable(true);
        dialogOKBtn = (Button) dialog.findViewById(R.id.button);
        editText = (EditText) dialog.findViewById(R.id.editText);
        dialogOKBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                if (!name.isEmpty()) {
                    mRunns.add(name);
                    mAdapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });
        dialog.setTitle("Add Runnable Name");
        dialog.show();
    }
}
