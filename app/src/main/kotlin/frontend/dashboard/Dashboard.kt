package com.bookstore.application.frontend.dashboard

import kotlinx.html.*


fun FlowContent.dashboard() = div {
    id = "content"
    header {
        classes = setOf("bg-white", "shadow")
        div {
            classes = setOf("mx-auto", "max-w-7xl", "py-6", "px-4", "sm:px-6", "lg:px-8")
            h1 {
                classes = setOf("text-3xl", "font-bold", "tracking-tight", "text-gray-900")
                +"Dashboard"
            }
        }
    }
    main {
        classes = setOf("mx-auto", "max-w-7xl", "py-6", "sm:px-6", "lg:px-8")
        div {
            classes = setOf("px-4", "py-6", "bg-white", "shadow", "sm:rounded-lg", "sm:px-6")
            h2 {
                classes = setOf("text-lg", "font-semibold", "text-gray-900")
                +"Welcome to the dashboard!"
            }
            p {
                classes = setOf("mt-2", "text-sm", "text-gray-500")
                +"This is a simple dashboard example."
            }
        }
    }
}