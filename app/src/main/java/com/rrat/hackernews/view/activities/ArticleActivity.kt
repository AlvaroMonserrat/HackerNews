package com.rrat.hackernews.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.rrat.hackernews.databinding.ActivityArticleBinding
import com.rrat.hackernews.utils.Constants

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.title = "Back"
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val bundle: Bundle? = intent.extras
        if(bundle != null) {
            val link = bundle.getString(Constants.LINK_URL).toString()
            val title = bundle.getString(Constants.STORY_TITLE).toString()

            binding.tvStoryTitle.text = title

            binding.progressBar.visibility = View.VISIBLE
            binding.webViewArticle.loadUrl(link)
            loadWebView()
        }
    }

    private fun loadWebView()
    {
        binding.webViewArticle.webViewClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.webViewArticle.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}