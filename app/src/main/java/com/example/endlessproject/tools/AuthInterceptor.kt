package com.example.endlessproject.tools

import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val sharedPref: SharedPreferences) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Authorization", sharedPref.getString("token", "") ?: "")
                .addHeader("Myket-Version", "700")
                .build()
        )
    }
}
