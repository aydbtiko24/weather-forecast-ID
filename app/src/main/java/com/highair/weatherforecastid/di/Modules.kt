package com.highair.weatherforecastid.di

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.highair.weatherforecastid.data.repository.RegionRepository
import com.highair.weatherforecastid.data.repository.RegionRepositoryImpl
import com.highair.weatherforecastid.data.repository.WeatherRepository
import com.highair.weatherforecastid.data.repository.WeatherRepositoryImpl
import com.highair.weatherforecastid.data.source.PreferencesLocalDataSource
import com.highair.weatherforecastid.data.source.RegionLocalDataSource
import com.highair.weatherforecastid.data.source.RegionRemoteDataSource
import com.highair.weatherforecastid.data.source.WeatherLocalDataSource
import com.highair.weatherforecastid.data.source.WeatherRemoteDataSource
import com.highair.weatherforecastid.data.source.local.AppDatabase
import com.highair.weatherforecastid.data.source.local.PreferencesLocalDataSourceImpl
import com.highair.weatherforecastid.data.source.local.RegionLocalDataSourceImpl
import com.highair.weatherforecastid.data.source.local.WeatherLocalDataSourceImpl
import com.highair.weatherforecastid.data.source.remote.ApiService
import com.highair.weatherforecastid.data.source.remote.RegionRemoteDataSourceImpl
import com.highair.weatherforecastid.data.source.remote.WeatherRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by aydbtiko on 6/11/2021.
 * Data Module
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    // database
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return AppDatabase.create(application)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    // api service
    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiService.create()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    // preference
    @Provides
    @Singleton
    fun provideDataStore(application: Application): DataStore<Preferences> {
        return PreferencesLocalDataSource.create(
            application
        )
    }

    @Provides
    @Singleton
    fun providePreferencesDataSource(
        dataStore: DataStore<Preferences>
    ): PreferencesLocalDataSource {
        return PreferencesLocalDataSourceImpl(
            dataStore
        )
    }

    // weather data source
    @Provides
    @Singleton
    fun provideWeatherLocalDataSource(
        appDatabase: AppDatabase
    ): WeatherLocalDataSource {
        return WeatherLocalDataSourceImpl(
            appDatabase.weatherDao()
        )
    }

    @Provides
    @Singleton
    fun provideWeatherRemoteDataSource(
        apiService: ApiService
    ): WeatherRemoteDataSource {
        return WeatherRemoteDataSourceImpl(apiService)
    }

    // weather repository
    @Provides
    @Singleton
    fun provideWeatherRepository(
        localDataSource: WeatherLocalDataSource,
        remoteDataSource: WeatherRemoteDataSource
    ): WeatherRepository {
        return WeatherRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    // region data source
    @Provides
    @Singleton
    fun provideRegionLocalDataSource(
        appDatabase: AppDatabase
    ): RegionLocalDataSource {
        return RegionLocalDataSourceImpl(
            appDatabase.regionDao()
        )
    }

    @Provides
    @Singleton
    fun provideRegionRemoteDataSource(
        apiService: ApiService
    ): RegionRemoteDataSource {
        return RegionRemoteDataSourceImpl(apiService)
    }

    // region repository
    @Provides
    @Singleton
    fun provideRegionRepository(
        localDataSource: RegionLocalDataSource,
        remoteDataSource: RegionRemoteDataSource
    ): RegionRepository {
        return RegionRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }
}
