package com.bookstore.application.frontend.index

import com.bookstore.application.frontend.dashboard.dashboard
import com.bookstore.application.frontend.navbar.navbar
import kotlinx.html.*

fun HTML.index() {
    classes = setOf("h-full", "bg-gray-100")
    head {
        title {
            +"Bookstore"
        }
        script { src = "https://cdn.tailwindcss.com" }
        script { src = "https://unpkg.com/htmx.org@1.9.12" }
    }
    body {
        classes = setOf("h-full")
        div {
            classes = setOf("min-h-full")
            navbar("Dashboard", "Items")
            dashboard()
        }
    }
}
