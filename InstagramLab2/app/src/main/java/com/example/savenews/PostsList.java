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

public class PostsList extends Fragment {

    RecyclerView recyclerView;
    private PostsAdapter postsAdapter;
    private PostsAdapter.ItemClickListener listener;
    private PostsAdapter.FragmentButtonListener fragmentButtonListener = null;
    private PostsAdapter.FragmentLikeListener fragmentLikeListener = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater
                .inflate(R.layout.page, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listener = new PostsAdapter.ItemClickListener() {
            @Override
            public void ItemClick(int position, Posts item) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("news", item);
                startActivity(intent);
            }
        };
        fragmentButtonListener = new PostsAdapter.FragmentButtonListener() {
            @Override
            public void myClick(Posts posts, int option) {
                ((MainActivity) getActivity()).myClick(posts, option);
            }
        };
        fragmentLikeListener = new PostsAdapter.FragmentLikeListener() {
            @Override
            public void removeItemLike(Posts posts) {
                ((MainActivity)getActivity()).removeItemLike(posts);
            }
        };
        postsAdapter = new PostsAdapter(newsGenerator(), listener, fragmentButtonListener, fragmentLikeListener);
        recyclerView.setAdapter(postsAdapter);
        return rootView;
    }

    private List<Posts> newsGenerator(){
        List<Posts> items = new ArrayList<>();
        Posts posts1 = new Posts(
                1,
                "Real Madrid",
                "May 14, 2019",
                R.drawable.realmadrid,
                27080,
                R.drawable.elclassico,
                "Real Madrid vs Barcelona "
        );
        items.add(posts1);
        Posts posts2 = new Posts(
                2,
                "KBTU",
                "December 3, 2019",
                R.drawable.kbtu,
                5912,
                R.drawable.kbtu2,
                "The best university!"
        );
        items.add(posts2);
        Posts posts3 = new Posts(
                3,
                "UFC",
                "January 8, 2020",
                R.drawable.ufc,
                29010,
                R.drawable.ufc2,
                "UFC"
        );
        items.add(posts3);
        Posts posts4 = new Posts(
                4,
                "Kaz_news",
                "January 8, 2020",
                R.drawable.kz,
                5976,
                R.drawable.kz2,
                "Coronovirus "
        );
        items.add(posts4);
        Posts posts5 = new Posts(
                5,
                "Adidas",
                "January 29, 2019",
                R.drawable.adidas,
                91743,
                R.drawable.adidas2,
                "New colliection of sneakers"
                );
        items.add(posts5);
        Posts posts6 = new Posts(
                6,
                "IscoAlarcon",
                "January 27, 2019",
                R.drawable.isco,
                896352,
                R.drawable.isco2,
                "+3 points!!"
                );
        items.add(posts6);
        Posts posts7 = new Posts(
                7,
                "WillSmith",
                "January 15, 2019",
                R.drawable.will,
                18678,
                R.drawable.will2,
                "Like son, like father ... Love you J-Diggy"
        );
        items.add(posts7);
        Posts posts8 = new Posts(
                8,
                "Tesla",
                "January 20, 2018",
                R.drawable.tesla,
                181,
                R.drawable.tesla2,
                "Charge at home, anytime."
        );
        items.add(posts8);
        Posts posts9 = new Posts(
                9,
                "Games",
                "January 15",
                R.drawable.post9,
                8474,
                R.drawable.post9,
                "Fallout 4"
        );
        items.add(posts9);
        Posts posts10 = new Posts(
                10,
                "Barcelona",
                "May 6",
                R.drawable.barca,
                510716,
                R.drawable.barca2,
                "FC Barcelona"
        );
        items.add(posts10);
        Posts posts11 = new Posts(
                11,
                "Mersedez",
                "January 6",
                R.drawable.mersed,
                18857,
                R.drawable.mers2,
                "New model of Mers"
        );
        items.add(posts11);
        Posts posts12 = new Posts(
                12,
                "Juventus",
                "February 20",
                R.drawable.juve,
                25809,
                R.drawable.juve2,
                "Hala"
        );
        items.add(posts12);
        Posts posts13 = new Posts(
                13,
                "BMW",
                "January 18",
                R.drawable.bmw,
                69792,
                R.drawable.bmw2,
                "New concept of M4"
        );
        items.add(posts13);
        Posts posts14 = new Posts(
                14,
                "Cs-GO",
                "September 12",
                R.drawable.csgo,
                78956,
                R.drawable.csgo2,
                "Version 5.6.4"
        );
        items.add(posts14);
        Posts posts15 = new Posts(
                15,
                "Scrip14",
                "August 3",
                R.drawable.scrip,
                944583,
                R.drawable.scrip2,
                "New album in AppleMusic"
        );
        items.add(posts15);
        Posts posts16 = new Posts(
                16,
                "Nurlan Saburov",
                "February 3, 2019",
                R.drawable.nurlan,
                78945,
                R.drawable.nurlan2,
                "Тур-2020"
        );
        items.add(posts16);
        Posts posts17 = new Posts(
                17,
                "Pavlodar-Irtish",
                "january 9, 2019",
                R.drawable.pavlodar,
                456123,
                R.drawable.pavlodar2,
                "Kairat:Irtish-0:5"
        );
        items.add(posts17);
        Posts posts18 = new Posts(
                18,
                "Tokaev",
                "September 7, 2019",
                R.drawable.tokaev,
                45128,
                R.drawable.tokas,
                "Biz-birgemiz!"
        );
        items.add(posts18);
        Posts posts19 = new Posts(
                19,
                "LeonelMessi",
                "April 19, 2019",
                R.drawable.messi,
                3003426,
                R.drawable.messi2,
                "Ballon-Dor"
        );
        items.add(posts19);
        Posts posts20 = new Posts(
                20,
                "ChelseFC",
                "October 23, 2018",
                R.drawable.chelsea,
                45612,
                R.drawable.chea2,
                "Good-team"
        );
        items.add(posts20);
        return items;
    }

    public void removeLike(Posts posts){
        postsAdapter.removeLike(posts);
    }
}
