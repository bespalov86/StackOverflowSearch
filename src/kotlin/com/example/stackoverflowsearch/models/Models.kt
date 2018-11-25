package com.example.stackoverflowsearch.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import java.util.*

internal class SearchQuery(
    val value: String? = null
)

internal class SearchResult(
    val query: SearchQuery?,
    val data: List<Question>?
)

@JsonIgnoreProperties(ignoreUnknown = true)
internal class Owner(
    @JsonProperty("user_id")
    val id: Long,

    @JsonProperty("display_name")
    val name: String?
)

@JsonIgnoreProperties(ignoreUnknown = true)
internal class Question(
    val title: String,

    @JsonProperty("creation_date")
    @JsonDeserialize(using = UnixTimeDeserializer::class)
    val creationDate: Date,

    val owner: Owner?,

    @JsonProperty("is_answered")
    val isAnswered: Boolean,

    val link: String
)

private class UnixTimeDeserializer : JsonDeserializer<Date>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?) = Date((p?.valueAsLong ?: 0) * 1000)
}
