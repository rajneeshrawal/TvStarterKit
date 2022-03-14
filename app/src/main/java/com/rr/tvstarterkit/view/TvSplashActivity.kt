package com.rr.tvstarterkit.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rr.tvstarterkit.base.TvBaseActivity
import com.rr.tvstarterkit.databinding.ActivitySplashBinding
import com.rr.tvstarterkit.viewmodel.TvHomeViewModel


class TvSplashActivity : TvBaseActivity() {

    lateinit var binding: ActivitySplashBinding
    var homeViewModel: TvHomeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[TvHomeViewModel::class.java]
    }
}