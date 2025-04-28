package xyz.androidrey.cmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform