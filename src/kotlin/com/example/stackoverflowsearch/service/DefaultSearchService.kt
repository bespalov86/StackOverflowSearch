package com.example.stackoverflowsearch.service

import com.example.stackoverflowsearch.models.Question
import com.example.stackoverflowsearch.models.SearchQuery
import com.example.stackoverflowsearch.models.SearchResult
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.apache.http.impl.client.HttpClientBuilder
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@JsonIgnoreProperties(ignoreUnknown = true)
private class SearchResultItems(
    val items: List<Question>
)

@Service
internal class DefaultSearchService : SearchService {

    // TODO move to configuration
    private val url = "http://api.stackexchange.com/2.2/search?order=desc&sort=creation&site=stackoverflow"

    private val restTemplate = RestTemplate(HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()))

    override fun search(query: SearchQuery): SearchResult {

        // TODO make pageable request

        val result = try {
            restTemplate.getForObject("$url&intitle=${query.value}", SearchResultItems::class.java)?.items
        } catch (e: Exception) {
            emptyList<Question>()
        }

        return SearchResult(query, result)
    }
}