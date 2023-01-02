package com.example.android.redditreader.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.redditreader.R;
import com.example.android.redditreader.databinding.PostItemBinding;
import com.example.android.redditreader.model.Children;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Children> childrenList;
    private RecyclerViewClickInterface recyclerViewClickInterface;

    public PostAdapter(List<Children> children, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.childrenList = children;
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        PostItemBinding postItemBinding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.post_item, parent, false);
        return new PostViewHolder(postItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Children children = childrenList.get(position);
        holder.postItemBinding.setInnerDataLayer(children.getData());
    }

    @Override
    public int getItemCount() {
        return childrenList.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        private PostItemBinding postItemBinding;

        public PostViewHolder(@NonNull PostItemBinding postItemBinding) {
            super(postItemBinding.getRoot());
            this.postItemBinding = postItemBinding;

            postItemBinding.postImage.setOnClickListener(
                    view -> recyclerViewClickInterface.onImageClick(getAdapterPosition()));

            postItemBinding.button.setOnClickListener(
                    view -> recyclerViewClickInterface.onDownloadButtonClick(getAdapterPosition()));
        }
    }
}
