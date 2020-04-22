package com.example.savenews;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class SavesListAdapter extends RecyclerView.Adapter<SavesListAdapter.NewsViewHolder> {
    private List<Posts> postsList;
    private @Nullable ItemClickListener listener;
    private @Nullable FragmentLikeListener fragmentLikeListener;


    public SavesListAdapter(List<Posts> postsList,
                           @Nullable ItemClickListener listener,
                           @Nullable FragmentLikeListener fragmentLikeListener) {
        this.postsList = postsList;
        this.listener = listener;
        this.fragmentLikeListener = fragmentLikeListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext())
                .inflate(
                        R.layout.item_news,
                        null,
                        false);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(params);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsViewHolder holder, final int position) {
        final Posts posts = postsList.get(getItemViewType(position));
        holder.profilePhoto.setImageResource(posts.getProfilePhoto());
        holder.postImage.setImageResource(posts.getPostImage());
        holder.author.setText(posts.getAuthor());
        String s = "<b>"+ posts.getAuthor()+ "</b>" + " "+ posts.getPostText();
        holder.postText.setText(Html.fromHtml(s));
        holder.date.setText(posts.getDate());
        holder.likesCnt.setText(posts.getLikesCnt()+" likes");
        holder.likeBtn.setImageResource(R.drawable.liked);

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentLikeListener!=null)
                    fragmentLikeListener.removeItemLike(posts);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null)
                    listener.ItemClick(position, posts);
            }
        });

    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView author;
        TextView likesCnt;
        TextView postText;
        TextView date;
        ImageView postImage;
        ImageView profilePhoto;
        ImageView likeBtn;
        ImageView saveBtn;

        public NewsViewHolder(@NonNull final View itemView){
            super(itemView);
            author = itemView.findViewById(R.id.author);
            likesCnt = itemView.findViewById(R.id.likesCnt);
            postImage = itemView.findViewById(R.id.postImage);
            postText = itemView.findViewById(R.id.postText);
            date = itemView.findViewById(R.id.date);
            profilePhoto = itemView.findViewById(R.id.profilePhoto);
            likeBtn = itemView.findViewById(R.id.likeBtn);
            saveBtn = itemView.findViewById(R.id.saveBtn);
        }
    }

    public int getItemViewType(int position){
        return position;
    }

    interface ItemClickListener{
        void ItemClick(int position, Posts item);
    }
    public interface FragmentLikeListener{
        void removeItemLike(Posts posts);
    }
}


