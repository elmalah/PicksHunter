package ash.pickshunter.service

import ash.pickshunter.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


class ApiClient {

    private val baseURL = "http://pickshunter-dev1.halfhardy.com/"
    private var retrofit: Retrofit? = null
    private val REQUEST_TIMEOUT = 60

    private var logging = HttpLoggingInterceptor()

    private var token =
        "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"

    private fun getHttpLogClient(): OkHttpClient {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor() {
                val newRequest = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build()
                it.proceed(newRequest)
            }

        if (BuildConfig.DEBUG) {
            logging.level = HttpLoggingInterceptor.Level.BODY
        }

        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    fun getClient(baseUrl: String = this.baseURL): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpLogClient())
                .build()
        }
        return retrofit
    }
}