package com.fly365.shared.service

import android.media.Image
import ash.pickshunter.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

interface ApiInterface {

//    @Headers("authorization: XXu5WbKbM6XHbU5VKNETr6AMnNaVNd9E")
//    @GET("api/suggest/airport/search")
//    fun searchAirline(
//        @Query("q") searchKey: String
//    ): Call<ArrayList<Airport>>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/customers/login")
    @FormUrlEncoded
    fun login(
        @FieldMap params: Map<String, String>
    ): Call<ApiResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/customers")
    fun register(
        @Body params: RegistrationRequest
    ): Call<ApiResponse>


    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/customers/is-interests-saved/{id}")
    fun checkIfIntrestsSaved(
        @Path("id") id: String
    ): Call<Boolean>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/customers/")
    fun addNewAddress(
        @Body params: RegistrationRequest
    ): Call<ApiResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/trip/{id}/products")
    fun addProduct(
        @Body params: ProductRequest, @Path("id") id: String
    ): Call<ProductResponse>

    @Headers(
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("/api/products/add-picture")
    @Multipart
    //@FormUrlEncoded
    fun addPicture(
        @Part file: MultipartBody.Part
    ): Call<PictureResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/trip/create")
    fun createTrip(
        @Body params: TripRequest
    ): Call<Trip>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @GET("api/common/countries")
    fun getCountries(
    ): Call<ArrayList<Country>>


    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @GET("api/shops")
    fun getShops(
    ): Call<ArrayList<Shop>>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/trip/{tripId}/check-in/{storeId}")
    fun checkIn(
        @Path("tripId") tripId: String, @Path("storeId") storeId: String
    ): Call<ResponseBody>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @GET("api/categories/{id}/attributes")
    fun getAttributes(
        @Path("id") id: String
    ): Call<AttributeResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/trip/check-out/{tripShopId}")
    fun checkOut(
        @Path("tripShopId") tripShopId: String
    ): Call<ResponseBody>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @GET("api/trip/shops/{id}")
    fun getTripShops(
        @Path("id") id: String
    ): Call<ArrayList<Shop>>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @PUT("api/customers/{id}")
    fun updateUser(
        @Body params: RegistrationRequest, @Path("id") id: String
    ): Call<ApiResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @GET("api/customers/{id}")
    fun getUser(
        @Path("id") id: String
    ): Call<ApiResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @PUT("api/products/{id}")
    fun updateProduct(
        @Body params: ProductRequest, @Path("id") id: String
    ): Call<ProductResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @GET("api/products/{id}")
    fun getProduct(
        @Path("id") id: String
    ): Call<ProductResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @GET("api/manufacturers?Fields=id,name,image")
    fun getBrands(
    ): Call<ApiResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @GET("api/categories?Fields=id,name,image")
    fun getCategories(
    ): Call<ApiResponse>


    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/customers/gender-interests/{id}")
    @FormUrlEncoded
    fun updateGenderInterest(
        @FieldMap params: Map<String, String>, @Path("id") id: String
    ): Call<ApiResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/customers/brand-interests/{id}")
    @FormUrlEncoded
    fun saveBrands(
        @FieldMap params: Map<String, String>, @Path("id") id: String
    ): Call<ApiResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/customers/category-interests/{id}")
    @FormUrlEncoded
    fun saveCategories(
        @FieldMap params: Map<String, String>, @Path("id") id: String
    ): Call<ApiResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/customers/verify-phone/{id}")
    @FormUrlEncoded
    fun verifyPhone(
        @FieldMap params: Map<String, String>, @Path("id") id: String
    ): Call<ApiResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/customers/resend-verification-code/{id}")
    fun resendVerificationCode(
        @Path("id") id: String
    ): Call<ApiResponse>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/products/calculate-product-price")
    fun calculatePrice(
        @Body params: PriceRequest
    ): Call<PriceResponse>


    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @GET("api/timeline/products")
    fun getTimelineProduct(
    ): Call<ArrayList<ProductView>>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/timeline/comment")
    fun addComment(
        @Body params: ProductCommentRequest
    ): Call<ProductView>

    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @GET("api/trip/customer-trips/{id}")
    fun getTripDetails(
        @Path("id") id: Int, @Query("latest") isLatest: String
    ): Call<ArrayList<TripDetailsResponse>>


    @Headers(
        "Content-Type: application/x-www-form-urlencoded",
        "Authorization: bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYmYiOjE1NzI4MzkxNTMsImV4cCI6MTg4ODE5OTE1MywiaXNzIjoiaHR0cDovL3BpY2tzaHVudGVyLWRldjEuaGFsZmhhcmR5LmNvbSIsImF1ZCI6WyJodHRwOi8vcGlja3NodW50ZXItZGV2MS5oYWxmaGFyZHkuY29tL3Jlc291cmNlcyIsIm5vcF9hcGkiXSwiY2xpZW50X2lkIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwic3ViIjoiZWM2MTJkMmMtN2I4ZC00MmJiLWFmYjEtYzVjNTEyOGM5MjNlIiwiYXV0aF90aW1lIjoxNTcyODM4OTM0LCJpZHAiOiJsb2NhbCIsInNjb3BlIjpbIm5vcF9hcGkiLCJvZmZsaW5lX2FjY2VzcyJdLCJhbXIiOlsicHdkIl19.N-g4uaOYTGWfO6-0JlX4WDBjn_z7qbKEGgZRozl2LyeUUXJH7ezEzzP04jY7lzeMU0tlRdlZIZ8vR_1aAiCT6YspseZ90KgZtSaDl2cDcoH76mNQJMqmFA3DVcAVKu0xW7ZaekIN2mKGBxT2RXZ9oZZocJXMnE7dC7J0YqC8a_Oy8yVNthmT2YeJXip8m9LEasRDgtmuX7bLChTRd8G26qPI6tFOkydBZelOvt1DUFbSSpspPfJpHr0ynnigDXJekGTg1mgEUnInIVXBM5n4OaDoEtEj5GulgnkRrPE52PrFXWhdCgBtEOY2S-ENw-z_TZuFwgUljAG6BVsZ7sqU8g"
    )
    @POST("api/orders")
    fun addOrder(
        @Body params: OrderRequest
    ): Call<OrderResponse>


//    @Headers("authorization: XXu5WbKbM6XHbU5VKNETr6AMnNaVNd9E")
//    @POST("api/flight/cart")
//    fun createCart(
//        @Body itinerary: Itinerary
//    ): Call<Cart>
//
//    @Headers("authorization: XXu5WbKbM6XHbU5VKNETr6AMnNaVNd9E")
//    @POST("api/flight/cart/{flightCartId}/passenger")
//    fun addPassengers(
//        @Body addPassengersPost: AddPassengersPost, @Path("flightCartId") flightCartId: String
//    ): Call<Cart>
//
//    @Headers("authorization: XXu5WbKbM6XHbU5VKNETr6AMnNaVNd9E")
//    @POST("api/flight/cart/{flightCartId}/checkout")
//    fun checkout(
//        @Body checkout: Checkout, @Path("flightCartId") flightCartId: String
//    ): Call<CheckoutResponse>
//
//    @Headers("authorization: XXu5WbKbM6XHbU5VKNETr6AMnNaVNd9E")
//    @GET("api/cms/country?page=1&pageSize=1000")
//    fun getCountries(): Call<CountryResponse>
//
//    @Headers("authorization: XXu5WbKbM6XHbU5VKNETr6AMnNaVNd9E")
//    @GET("api/user/order/find")
//    fun getOrder(
//        @Query("orderId") orderId: String, @Query("orderNumber") orderNumber: String
//    ): Call<Cart>
}
