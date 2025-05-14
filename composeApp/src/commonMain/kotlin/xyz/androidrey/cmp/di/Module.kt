package xyz.androidrey.cmp.di

import androidx.room.RoomDatabase
import io.ktor.client.HttpClient
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module
import xyz.androidrey.cmp.coins.data.remote.impl.KtorCoinsRemoteDataSource
import xyz.androidrey.cmp.coins.domain.GetCoinDetailsUseCase
import xyz.androidrey.cmp.coins.domain.GetCoinPriceHistoryUseCase
import xyz.androidrey.cmp.coins.domain.GetCoinsListUseCase
import xyz.androidrey.cmp.coins.domain.api.CoinsRemoteDataSource
import xyz.androidrey.cmp.coins.presentation.CoinListViewModel
import xyz.androidrey.cmp.core.database.portfolio.PortfolioDatabase
import xyz.androidrey.cmp.core.database.portfolio.getPortfolioDatabase
import xyz.androidrey.cmp.core.network.HttpClientFactory

fun initKoin(config: KoinAppDeclaration? = null) = startKoin {
    config?.invoke(this)
    modules(
        sharedModule,
        platformModule
    )
}

expect val platformModule: Module

val sharedModule = module {
    single<HttpClient> { HttpClientFactory.create(get()) }

    single {
        getPortfolioDatabase(get<RoomDatabase.Builder<PortfolioDatabase>>())
    }

    viewModel { CoinListViewModel(get(), get()) }
    singleOf(::GetCoinsListUseCase)
    singleOf(::KtorCoinsRemoteDataSource).bind<CoinsRemoteDataSource>()
    singleOf(::GetCoinDetailsUseCase)
    singleOf(::GetCoinPriceHistoryUseCase)
}