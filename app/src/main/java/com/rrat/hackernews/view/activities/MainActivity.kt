package com.rrat.hackernews.view.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.rrat.hackernews.HackerNewsApplication
import com.rrat.hackernews.databinding.ActivityMainBinding
import com.rrat.hackernews.model.data.entities.Post
import com.rrat.hackernews.view.adapters.HackerNewsAdapter
import com.rrat.hackernews.view.helpers.SwipeDeletePost
import com.rrat.hackernews.viewmodel.HackerNewsViewModel
import com.rrat.hackernews.viewmodel.PostViewModel
import com.rrat.hackernews.viewmodel.PostViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var hackerNewsViewModel: HackerNewsViewModel

    private val postViewModel: PostViewModel by viewModels{
        PostViewModelFactory((this.application as HackerNewsApplication).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        hackerNewsViewModel = ViewModelProvider(this@MainActivity)
            .get(HackerNewsViewModel::class.java)

        binding.srlPosts.setOnRefreshListener {
            hackerNewsViewModel.getHackerNewsFromAPI("mobile")
        }

        hackerNewsViewModel.getHackerNewsFromAPI("mobile")

        hackerNewsViewModelObserver()


        initRecyclerView()

    }

    private fun hackerNewsViewModelObserver()
    {
        hackerNewsViewModel.hackerNewsResponse.observe(this)
        { hackerNewsData ->
            for(hit in hackerNewsData.hits) {

                val url = if(hit.story_url.isNullOrEmpty()) hit.url else hit.story_url
                val title = if(hit.story_title.isNullOrEmpty()) hit.title else hit.story_title
                postViewModel.insert(
                    Post(
                        hit.created_at_i,
                        title as String,
                        hit.author,
                        hit.created_at,
                        url as String,
                        false
                    )

                )
            }
        }

        hackerNewsViewModel.isLoading.observe(this) {
            isLoading ->
                isLoading?.let {
                    binding.srlPosts.isRefreshing = false
                }
        }
    }

    private fun initRecyclerView()
    {
        val adapter = HackerNewsAdapter(this)
        binding.rvHackerNews.adapter = adapter
        binding.rvHackerNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvHackerNews.setHasFixedSize(true)
        val itemTouchHelper = ItemTouchHelper(SwipeDeletePost( adapter))
        itemTouchHelper.attachToRecyclerView(binding.rvHackerNews)

        postViewModel.allPosts.observe(this)
        {
            posts->
                Log.i(TAG, "DATA FROM ROOM: ${posts.size}")
                adapter.setPosts(posts)
        }
    }

    fun swipeDeletePost(post: Post)
    {
        val postDelete = Post(post.created_at_i,
            post.story_title,
            post.author,
            post.created_at,
            post.story_url,
            true)

        postViewModel.update(postDelete)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}