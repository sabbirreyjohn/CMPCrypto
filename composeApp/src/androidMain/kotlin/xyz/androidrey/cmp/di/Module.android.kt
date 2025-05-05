package xyz.androidrey.cmp.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.dsl.module

actual val platformModule = module {
    single<HttpClientEngine> { Android.create()}
}