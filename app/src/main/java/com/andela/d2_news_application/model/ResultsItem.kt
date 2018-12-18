package com.andela.d2_news_application.model


import com.google.gson.annotations.SerializedName

data class ResultsItem(

        @SerializedName("per_facet") val perFacet: List<Any?>? = null,

        @SerializedName("subsection") val subsection: String? = null,

        @SerializedName("item_type") val itemType: String? = null,

        @SerializedName("org_facet") val orgFacet: List<String?>? = null,

        @SerializedName("section") val section: String? = null,

        @SerializedName("abstract") val jsonMemberAbstract: String? = null,

        @SerializedName("title") val title: String? = null,

        @SerializedName("des_facet") val desFacet: List<String?>? = null,

        @SerializedName("url") val url: String? = null,

        @SerializedName("short_url") val shortUrl: String? = null,

        @SerializedName("material_type_facet") val materialTypeFacet: String? = null,

        @SerializedName("multimedia") val multimedia: List<MultimediaItem?>? = null,

        @SerializedName("geo_facet") val geoFacet: List<Any?>? = null,

        @SerializedName("updated_date") val updatedDate: String? = null,

        @SerializedName("created_date") val createdDate: String? = null,

        @SerializedName("byline") val byline: String? = null,

        @SerializedName("published_date") val publishedDate: String? = null,

        @SerializedName("kicker") val kicker: String? = null
)