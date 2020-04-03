package com.example.instagramlab


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramlab.model.Post
import com.squareup.picasso.Picasso

class PostsAdapter(val posts: ArrayList<Post>, var context: Context): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun getItemCount()= posts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.username.text = posts[position].username
        holder.text.text = posts[position].text
        holder.prosmotri.text = posts[position].prosmotri
        holder.name.text = posts[position].name
        holder.time.text = posts[position].time
        Picasso.get().load(posts[position].photo).into(holder.photo)
        Picasso.get().load(posts[position].avatar).into(holder.avatar)

        holder.detail.setOnClickListener {
            val intent = Intent(context,PostDetailsActivity::class.java)
            intent.putExtra("username",posts[position].username)
            intent.putExtra("text",posts[position].text)
            intent.putExtra("photo",posts[position].photo)
            intent.putExtra("avatar",posts[position].avatar)
            intent.putExtra("prosmotri",posts[position].prosmotri)
            intent.putExtra("name",posts[position].name)
            intent.putExtra("time",posts[position].time)
            context.startActivity(intent)
        }
    }

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val username:TextView = itemView.findViewById(R.id.username)
        val text: TextView = itemView.findViewById(R.id.text)
        val photo : ImageView = itemView.findViewById(R.id.photo)
        val avatar: ImageView = itemView.findViewById(R.id.avatar)
        val prosmotri:TextView = itemView.findViewById(R.id.prosmotri)
        val name:TextView=itemView.findViewById(R.id.name)
        val time:TextView=itemView.findViewById(R.id.time)
        val detail:TextView = itemView.findViewById(R.id.detail)

    }

}

