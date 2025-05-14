package xyz.androidrey.cmp.di

import androidx.room.RoomDatabase
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import xyz.androidrey.cmp.core.database.portfolio.PortfolioDatabase
import xyz.androidrey.cmp.core.database.getPortfolioDatabaseBuilder

actual val platformModule = module {
    single<HttpClientEngine> { Android.create()}
    singleOf(::getPortfolioDatabaseBuilder).bind<RoomDatabase.Builder<PortfolioDatabase>>()
}