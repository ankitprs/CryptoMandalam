package com.lamdapay.CryptoMandalam.di

import android.app.Application
import com.lamdapay.CryptoMandalam.data.FirestoreRepoImpl
import com.lamdapay.CryptoMandalam.domain.repository.FirestoreRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseRepo(): FirestoreRepo = FirestoreRepoImpl()

}