package com.example.android.redditreader.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.android.redditreader.model.Children;

public class ChildrenComparator extends DiffUtil.ItemCallback<Children> {
    @Override
    public boolean areItemsTheSame(@NonNull Children oldItem, @NonNull Children newItem) {
        return oldItem.getData().getId().equals(newItem.getData().getId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Children oldItem, @NonNull Children newItem) {
        return oldItem.equals(newItem);
    }
}
