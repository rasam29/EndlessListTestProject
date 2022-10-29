package com.example.endlessproject.app

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.endlessproject.R
import com.example.endlessproject.appList.list.TabPagerAdapter
import com.example.endlessproject.authentication.AuthViewModel
import com.example.endlessproject.databinding.ActivityMainBinding
import com.uxcam.UXCam
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.authenticate()
        viewModel.navigateToMainPage.observe(this) {
            binding.viewPager.adapter = TabPagerAdapter(this)
        }
        UXCam.tagScreenName("main activity")
    }
}
