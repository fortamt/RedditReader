package com.example.android.redditreader.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.redditreader.R;
import com.example.android.redditreader.model.Children;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private ArrayList<Children> children;

    public PostAdapter(ArrayList<Children> children) {
        this.children = children;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.post_item, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Picasso.get().load(children.get(position).getData().getThumbnail())
                .placeholder(R.drawable.loading)
                .fit()
                .centerInside()
                .into(holder.postImageImageView);
        holder.authorNameTextView.setText(children.get(position).getData().getAuthor());
        holder.quantityOfCommentsTextView.setText(Integer.toString(children.get(position).getData().getNumComments()));
        holder.postedAgoTextView.setText(children.get(position).getData().getCreated());
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView postImageImageView;
        TextView authorNameTextView;
        TextView quantityOfCommentsTextView;
        TextView postedAgoTextView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            postImageImageView = itemView.findViewById(R.id.postImage);
            authorNameTextView = itemView.findViewById(R.id.authorName);
            quantityOfCommentsTextView = itemView.findViewById(R.id.quantityOfComments);
            postedAgoTextView = itemView.findViewById(R.id.timeAgo);

        }
    }
}
