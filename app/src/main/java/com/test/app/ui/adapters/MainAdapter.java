package com.test.app.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.app.R;
import com.test.app.entity.Post;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Post> posts;

    private CallbackItem callbackItem;

    public MainAdapter(CallbackItem callbackItem) {
        posts = new ArrayList<>();
        this.callbackItem = callbackItem;
    }

    public void addItems(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        String title = posts.get(position).title;
        holder.tvTitle.setText(title);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);

            tvTitle.setOnClickListener(view ->
                    callbackItem.onCardClick(posts.get(getAdapterPosition()).id, posts.get(getAdapterPosition()).title));
        }
    }

    public interface CallbackItem {
        void onCardClick(int id, String title);
    }
}
