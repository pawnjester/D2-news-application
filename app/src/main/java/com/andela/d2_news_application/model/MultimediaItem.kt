package com.andela.d2_news_application.model


import com.google.gson.annotations.SerializedName

data class MultimediaItem(

	@SerializedName("copyright")
	val copyright: String? = null,

	@SerializedName("subtype")
	val subtype: String? = null,

	@SerializedName("format")
	val format: String? = null,

	@SerializedName("width")
	val width: Int? = null,

	@SerializedName("caption")
	val caption: String? = null,

	@SerializedName("type")
	val type: String? = null,

	@SerializedName("url")
	val url: String? = null,

	@SerializedName("height")
	val height: Int? = null
)