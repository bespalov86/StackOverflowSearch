package com.example.stackoverflowsearch.service

import com.example.stackoverflowsearch.models.SearchQuery
import com.example.stackoverflowsearch.models.SearchResult

internal interface SearchService {
    fun search(query: SearchQuery): SearchResult
}

