package com.rr.tvstarterkit.repository

import com.rr.tvstarterkit.network.IAppService
import javax.inject.Inject

class HomeRepoImpl @Inject constructor(private val iAppService: IAppService) : IHomeRepo {

    override suspend fun requestLogin() {
       iAppService.requestLogin()
    }


}