package com.example.sekaipodcast.onboarding

data class Page(
    val title: String,
    val subtitle: String
)

val pages = listOf(
    Page(
        title = "SEKAI PODCAST",
        subtitle = "#1 LEARNING LANGUAGE PLATFORM"
    ),
    Page(
        title = "LISTEN, EXERCISE, REPEAT",
        subtitle = "MULTIPLE LANGUAGE WE HAVE, VARIOUS EXERCISE, SPESIFICATION TOPIC, AND THE LIST STILL GOES ON..."
    ),
    Page(
        title = "CONNECTING PEOPLE AROUND THE WORLD",
        subtitle = "THE BEST EXERCISE IS EXCHANGE LANGUAGE WITH THE SAME LEARNING PASSION."
    )
)
