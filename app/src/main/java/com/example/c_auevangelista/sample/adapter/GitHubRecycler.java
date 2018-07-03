package com.example.c_auevangelista.sample.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c_auevangelista.sample.R;
import com.example.c_auevangelista.sample.models.GitHubModel;

import java.util.ArrayList;
import java.util.List;

public class GitHubRecycler extends
        RecyclerView.Adapter<GitHubRecycler.ViewHolder> {

    private List<GitHubModel> mAndroidList;
    private Context context;
    Dialog dialogViewPost;

    public GitHubRecycler(Context context,List<GitHubModel> androidList) {
        this.mAndroidList = androidList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTvName.setText(mAndroidList.get(position).getTitle());
        Glide.with(context)
                .load(mAndroidList.get(position).getUrl())
                .fitCenter() // scale to fit entire image within ImageView
                .into(holder.mTvVersion1);
        Glide.with(context)
                .load(mAndroidList.get(position).getThumbnailUrl())
                .fitCenter() // scale to fit entire image within ImageView
                .into(holder.mTvVersion);

        holder.llView.setOnClickListener(v -> {
            dialogViewAuditor(mAndroidList.get(position).title, mAndroidList.get(position).url, mAndroidList.get(position).thumbnailUrl);
        });
    }

    @Override
    public int getItemCount() {
        return mAndroidList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvName;
        private ImageView mTvVersion,mTvVersion1;
        private LinearLayout llView;
        public ViewHolder(View view) {
            super(view);

            mTvName = (TextView)view.findViewById(R.id.title);
            mTvVersion = (ImageView)view.findViewById(R.id.thumb);
            mTvVersion1 = (ImageView)view.findViewById(R.id.thumb1);
            llView = (LinearLayout) view.findViewById(R.id.ll_view);
        }
    }

    public void dialogViewAuditor(String postTitle, String postBody, String postUrl) {
        dialogViewPost = new Dialog(context);
        dialogViewPost.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogViewPost.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogViewPost.setCancelable(false);
        dialogViewPost.setContentView(R.layout.dialog_auditor_view);
        dialogViewPost.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        TextView title = (TextView) dialogViewPost.findViewById(R.id.title);
        ImageView mTvVersion = (ImageView)dialogViewPost.findViewById(R.id.urlimage);
        ImageView  mTvVersion1 = (ImageView)dialogViewPost.findViewById(R.id.urlimage1);
        Button btnDone = (Button) dialogViewPost.findViewById(R.id.btn_done);

        title.setText(postTitle);
        Glide.with(context)
                .load(postBody)
                .fitCenter() // scale to fit entire image within ImageView
                .into(mTvVersion);
        Glide.with(context)
                .load(postUrl)
                .fitCenter() // scale to fit entire image within ImageView
                .into(mTvVersion1);


        btnDone.setOnClickListener(v -> dialogViewPost.dismiss());

        dialogViewPost.show();
    }
}
