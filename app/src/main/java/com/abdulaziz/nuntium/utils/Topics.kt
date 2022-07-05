package com.abdulaziz.nuntium.utils

object Topics {
    fun getTopicsList(): ArrayList<String> {
        return arrayListOf(
            "\uD83D\uDCF0   General",
            "⚖️   Politics",
            "\uD83C\uDFC8   Sports",
            "\u200E\u200D\uD83D\uDCBC   Business",
            "✈️Travel",
            "\uD83E\uDD16   Tech",
            "\uD83D\uDC68\u200D⚕️   Health",
            "\uD83C\uDF54   Food",
            "\uD83D\uDD2C   Science",
            "\uD83C\uDFA1   Entertainment"
        )
    }
    fun getTopicsListText(): ArrayList<String> {
        return arrayListOf(
            "General",
            "Politics",
            "Sports",
            "Business",
            "Travel",
            "Tech",
            "Health",
            "Food",
            "Science",
            "Entertainment"
        )
    }
}