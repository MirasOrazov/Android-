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


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.NewsViewHolder> {

    List<Posts> main_list;
    private @Nullable ItemClickListener listener;
    private @Nullable FragmentButtonListener fragmentButtonListener;
    private @Nullable FragmentLikeListener fragmentLikeListener;

    public PostsAdapter(List<Posts> postsList,
                        @Nullable ItemClickListener listener,
                        @Nullable FragmentButtonListener fragmentButtonListener,
                        @Nullable FragmentLikeListener fragmentLikeListener) {
        Posts.postsList = postsList;
        main_list = postsList;
        this.listener = listener;
        this.fragmentButtonListener = fragmentButtonListener;
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
        final Posts posts = Posts.postsList.get(getItemViewType(position));
        holder.profilePhoto.setImageResource(posts.getProfilePhoto());
        holder.postImage.setImageResource(posts.getPostImage());
        holder.author.setText(posts.getAuthor());
        String s = "<b>"+ posts.getAuthor()+ "</b>" + " "+ posts.getPostText();
        holder.postText.setText(Html.fromHtml(s));
        holder.date.setText(posts.getDate());
        holder.likesCnt.setText(posts.getLikesCnt()+" likes");

        if (posts.isLiked()==true)holder.likeBtn.setImageResource(R.drawable.liked);
        else holder.likeBtn.setImageResource(R.drawable.like);

        holder.likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentButtonListener!=null){
                    if (posts.isLiked()){
                        holder.likeBtn.setImageResource(R.drawable.like);
                        //fragmentButtonListener.myClick(news, 2);
                        fragmentLikeListener.removeItemLike(posts);
                        posts.setLiked(false);
                    } else
                    {
                        holder.likeBtn.setImageResource(R.drawable.liked);
                        fragmentButtonListener.myClick(posts, 1);
                        posts.setLiked(true);
                    }
                }
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
        return Posts.postsList.size();
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

    public interface FragmentButtonListener{
        void myClick(Posts posts, int option);
    }

    interface ItemClickListener{
        void ItemClick(int position, Posts item);
    }
    public interface FragmentLikeListener{
        void removeItemLike(Posts posts);
    }

    public void removeLike(Posts posts){
        int n = Posts.postsList.indexOf(posts);
        posts.setLiked(false);
        posts.setLikeBtn(R.drawable.like);
        Posts.postsList.set(n, posts);
        main_list.set(n, posts);
        this.notifyItemChanged(n);
    }
}

