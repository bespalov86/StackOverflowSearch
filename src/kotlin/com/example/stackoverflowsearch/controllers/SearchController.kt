package com.example.stackoverflowsearch.controllers

import com.example.stackoverflowsearch.models.SearchQuery
import com.example.stackoverflowsearch.models.SearchResult
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.util.Collections.emptyList


@Controller
internal class SearchController {

    @GetMapping
    fun index(@ModelAttribute("searchResult") searchResult: SearchResult, model: Model): String {

        model.addAttribute("searchQuery", searchResult.query ?: SearchQuery())

        return "index"
    }

    @PostMapping
    fun executeSearch(@ModelAttribute("searchQuery") query: SearchQuery, attributes: RedirectAttributes): RedirectView {

        attributes.addFlashAttribute("searchResult", SearchResult(query, emptyList()))
        return RedirectView("index")
    }
}