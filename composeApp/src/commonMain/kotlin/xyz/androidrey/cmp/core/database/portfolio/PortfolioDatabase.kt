package xyz.androidrey.cmp.core.database.portfolio

import androidx.room.Database
import androidx.room.RoomDatabase
import xyz.androidrey.cmp.portfolio.data.local.PortfolioCoinEntity
import xyz.androidrey.cmp.portfolio.data.local.PortfolioDao

@Database(entities = [PortfolioCoinEntity::class], version = 1)
abstract class PortfolioDatabase: RoomDatabase() {
    abstract fun portfolioDao(): PortfolioDao
}