package org.tbm.gloria

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform