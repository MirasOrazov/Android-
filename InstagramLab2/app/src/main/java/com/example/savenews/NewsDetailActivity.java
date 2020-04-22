package com.example.savenews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDetailActivity extends AppCompatActivity {

    ImageButton back;
    ImageView likeBtn;
    ImageView saveBtn;
    ImageView profilePhoto;
    TextView author;
    ImageView postImage;
    TextView postText;
    TextView date;
    TextView likesCnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        back = findViewById(R.id.back);
        likeBtn = findViewById(R.id.likeBtn);
        saveBtn = findViewById(R.id.saveBtn);
        profilePhoto = findViewById(R.id.profilePhoto);
        author = findViewById(R.id.author);
        postImage = findViewById(R.id.postImage);
        postText = findViewById(R.id.postText);
        date = findViewById(R.id.date);
        likesCnt = findViewById(R.id.likesCnt);

        final Posts posts = (Posts)getIntent().getSerializableExtra("news");

        profilePhoto.setImageResource(posts.getProfilePhoto());
        postImage.setImageResource(posts.getPostImage());

        author.setText(posts.getAuthor());
        String s = "<b>"+ posts.getAuthor() + "</b>" + " " + posts.getPostText();
        postText.setText(Html.fromHtml(s));
        date.setText(posts.getDate());
        likesCnt.setText(posts.getLikesCnt()+" likes");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posts.getLikeBtn()==R.drawable.like){
                    likeBtn.setImageResource(R.drawable.liked);
                    posts.setLikeBtn(R.drawable.liked);
                } else {
                    likeBtn.setImageResource(R.drawable.like);
                    posts.setLikeBtn(R.drawable.like);
                }
            }
        });
    }
}
