package com.rrat.hackernews.model.network.data

object HackerNews{

data class Hits(
    val exhaustiveNbHits: Boolean,
    val exhaustiveTypo: Boolean,
    val hits: List<Hit>,
    val hitsPerPage: Int,
    val nbHits: Int,
    val nbPages: Int,
    val page: Int,
    val params: String,
    val processingTimeMS: Int,
    val query: String
)

data class Hit(
    val _highlightResult: HighlightResult,
    val _tags: List<String>,
    val author: String,
    val comment_text: String,
    val created_at: String,
    val created_at_i: Int,
    val num_comments: Any,
    val objectID: String,
    val parent_id: Int,
    val points: Any,
    val story_id: Int,
    val story_text: Any,
    val story_title: String,
    val story_url: String,
    val title: Any,
    val url: Any
)

data class HighlightResult(
    val author: Author,
    val comment_text: CommentText,
    val story_title: StoryTitle,
    val story_url: StoryUrl,
    val title: Title,
    val url: Url
)

data class Author(
    val matchLevel: String,
    val matchedWords: List<Any>,
    val value: String
)

data class CommentText(
    val fullyHighlighted: Boolean,
    val matchLevel: String,
    val matchedWords: List<String>,
    val value: String
)

data class StoryTitle(
    val matchLevel: String,
    val matchedWords: List<Any>,
    val value: String
)

data class StoryUrl(
    val matchLevel: String,
    val matchedWords: List<Any>,
    val value: String
)

data class Title(
    val fullyHighlighted: Boolean,
    val matchLevel: String,
    val matchedWords: List<String>,
    val value: String
)

data class Url(
    val fullyHighlighted: Boolean,
    val matchLevel: String,
    val matchedWords: List<String>,
    val value: String
)
}