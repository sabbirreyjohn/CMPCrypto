package xyz.androidrey.cmp

import androidx.compose.ui.window.ComposeUIViewController
import xyz.androidrey.cmp.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) { App() }