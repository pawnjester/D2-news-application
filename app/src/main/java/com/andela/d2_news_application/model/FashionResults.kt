package com.andela.d2_news_application.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.provider.BaseColumns
import com.google.gson.annotations.SerializedName

@Entity(tableName = "fahionArticles")
data class FashionResults (
        @PrimaryKey(autoGenerate = true) var id: Long? = null,

        @Ignore var columnId: String? = BaseColumns._ID,

        @Ignore @SerializedName("per_facet") val perFacet: List<Any?>? = null,

        @Ignore @SerializedName("subsection") val subsection: String? = null,

        @Ignore @SerializedName("item_type") val itemType: String? = null,

        @Ignore @SerializedName("org_facet") val orgFacet: List<String?>? = null,

        @ColumnInfo(name= "section") @SerializedName("section") var section: String? = null,

        @ColumnInfo(name= "newsType") @SerializedName("newsType") var newsType: String? = null,

        @Ignore @SerializedName("abstract") val jsonMemberAbstract: String? = null,

        @ColumnInfo(name = "title") @SerializedName("title") var title: String? = null,

        @Ignore @SerializedName("des_facet") val desFacet: List<String?>? = null,

        @ColumnInfo(name = "url") @SerializedName("url") var url: String? = null,

        @Ignore @SerializedName("short_url") val shortUrl: String? = null,

        @Ignore @SerializedName("material_type_facet") val materialTypeFacet: String? = null,

        @ColumnInfo(name = "multimedia") @SerializedName("multimedia") var multimedia: List<MultimediaItem?>? = null,

        @Ignore @SerializedName("geo_facet") val geoFacet: List<Any?>? = null,

        @ColumnInfo(name = "updatedAt") @SerializedName("updated_date") var updatedDate: String? = null,

        @ColumnInfo(name = "createdAt") @SerializedName("created_date") var createdDate: String? = null,

        @Ignore @SerializedName("byline") val byline: String? = null,

        @ColumnInfo(name = "publishedDate") @SerializedName("published_date") var publishedDate: String? = null,

        @Ignore @SerializedName("kicker") val kicker: String? = null
)