package com.andela.d2_news_application.model

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(

		@SerializedName("copyright") val copyright: String? = null,

        @SerializedName("last_updated") val lastUpdated: String? = null,

        @SerializedName("section") val section: String? = null,

        @SerializedName("results") val results: T? = null,

        @SerializedName("num_results") val numResults: Int? = null,

        @SerializedName("status") val status: String? = null
)