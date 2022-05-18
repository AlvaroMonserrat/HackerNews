package com.rrat.hackernews.view.adapters

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rrat.hackernews.databinding.ItemHitLayoutBinding
import com.rrat.hackernews.model.data.entities.Post
import com.rrat.hackernews.model.network.data.HackerNews
import com.rrat.hackernews.utils.Constants
import com.rrat.hackernews.utils.DateUtils
import com.rrat.hackernews.view.activities.ArticleActivity
import com.rrat.hackernews.view.activities.MainActivity
import java.time.Duration
import java.time.Instant
import java.time.LocalDateTime
import java.util.concurrent.TimeUnit

class HackerNewsAdapter(private val activity: Activity) : RecyclerView.Adapter<HackerNewsAdapter.ViewHolder>() {

    private var posts: List<Post> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemHitLayoutBinding = ItemHitLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = posts[position]
        holder.tvStoryTitle.text = post.story_title
        holder.tvAuthor.text = post.author


        holder.tvCreatedAt.text = post.created_at?.let { DateUtils.elapsedTime(it) }

        holder.llPost.setOnClickListener {
            val intent = Intent(it.context, ArticleActivity::class.java)
            intent.putExtra(Constants.STORY_TITLE, post.story_title)
            intent.putExtra(Constants.LINK_URL, post.story_url)
            it.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(list: List<Post>)
    {
        posts = list
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int)
    {
        if(activity is MainActivity) activity.swipeDeletePost(posts[position])
    }
    class ViewHolder(view: ItemHitLayoutBinding) : RecyclerView.ViewHolder(view.root)
    {
        val tvStoryTitle = view.tvStoryTitle
        val tvAuthor = view.tvAuthor
        val tvCreatedAt = view.tvCreatedAt
        val llPost = view.llPost
    }
}