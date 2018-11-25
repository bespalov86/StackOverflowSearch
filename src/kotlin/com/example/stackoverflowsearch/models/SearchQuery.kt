package com.example.stackoverflowsearch.models

internal class SearchQuery(
    val value: String? = null
)

internal class SearchResult(
    val query: SearchQuery?,
    val data: List<String>?
)