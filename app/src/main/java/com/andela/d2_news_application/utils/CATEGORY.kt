package com.andela.d2_news_application.utils

enum class CATEGORY {
    FOOD_NEWS {
        override fun toString(): String {
            return "food-news"
        }
    },
    FASHION_NEWS {
        override fun toString(): String {
            return "fashion-news"
        }
    },
    HOME_NEWS {
        override fun toString(): String {
            return "home_news"
        }
    }
}