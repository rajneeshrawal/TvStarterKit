package com.rr.tvstarterkit.viewmodel

import com.rr.tvstarterkit.repository.HomeRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvHomeViewModel @Inject constructor(var repoImpl: HomeRepoImpl):TvBaseViewModel() {

}