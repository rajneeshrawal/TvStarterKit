package com.rr.tvstarterkit.di.module

import com.rr.tvstarterkit.network.IAppService
import com.rr.tvstarterkit.repository.HomeRepoImpl
import com.rr.tvstarterkit.repository.IHomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @Singleton
    fun provideHomeRepository(
        iAppService: IAppService
    ): IHomeRepo {
        return HomeRepoImpl(iAppService)
    }


}