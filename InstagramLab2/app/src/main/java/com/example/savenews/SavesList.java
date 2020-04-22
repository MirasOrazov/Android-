package com.example.savenews;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SavesList extends Fragment {
    RecyclerView recyclerView;
    private SavesListAdapter adapter;
    private List<Posts> postsList;
    private SavesListAdapter.ItemClickListener listener;
    private SavesListAdapter.FragmentLikeListener fragmentLikeListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.page, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listener = new SavesListAdapter.ItemClickListener() {
            @Override
            public void ItemClick(int position, Posts item) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("news", item);
                startActivity(intent);
            }
        };
        fragmentLikeListener = new SavesListAdapter.FragmentLikeListener() {
            @Override
            public void removeItemLike(Posts posts) {
                ((MainActivity)getActivity()).removeItemLike(posts);
            }
        };
        postsList = new ArrayList<>();
        adapter = new SavesListAdapter(postsList, listener, fragmentLikeListener);
        recyclerView.setAdapter(adapter);
        return rootView;
    }

    public void saveNews(Posts posts){
        postsList.add(posts);
        recyclerView.getAdapter().notifyItemInserted(postsList.size()-1);
    }
    public void removeNews(Posts posts){
        if (postsList.indexOf(posts)==0){
            postsList.remove(posts);
        } else {
            int position = postsList.indexOf(posts);
            postsList.remove(posts);
            recyclerView.getAdapter().notifyItemRemoved(position);
        }
    }
    public void removeLike(Posts posts){
        int n = postsList.indexOf(posts);
        this.removeNews(posts);
        adapter.notifyItemRemoved(n);
    }
}
