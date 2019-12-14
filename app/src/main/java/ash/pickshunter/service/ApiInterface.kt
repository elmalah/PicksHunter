package ash.pickshunter.service

import ash.pickshunter.model.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @POST("api/customers/login")
    @FormUrlEncoded
    fun login(@FieldMap params: Map<String, String>): Call<ApiResponse>


    @POST("api/customers")
    fun register(@Body params: RegistrationRequest): Call<ApiResponse>


    @POST("api/customers/is-interests-saved/{id}")
    fun checkIfIntrestsSaved(@Path("id") id: String): Call<Boolean>


    @POST("api/customers/")
    fun addNewAddress(@Body params: RegistrationRequest): Call<ApiResponse>


    @POST("api/trip/{id}/products")
    fun addProduct(@Body params: ProductRequest, @Path("id") id: String): Call<ProductResponse>


    @POST("/api/products/add-picture")
    @Multipart
    //@FormUrlEncoded
    fun addPicture(@Part file: MultipartBody.Part): Call<PictureResponse>


    @POST("/api/products/add-pictures")
    @Multipart
    //@FormUrlEncoded
    fun addPictures(@Part files: List<MultipartBody.Part>): Call<ArrayList<PictureResponse>>


    @POST("api/trip/create")
    fun createTrip(@Body params: TripRequest): Call<Trip>


    @POST("api/trip/end-trip/{tripId}")
    fun endTrip(@Path("tripId") tripId: String): Call<TripDetailsResponse>


    @GET("api/common/countries")
    fun getCountries(): Call<ArrayList<Country>>


    @GET("api/shops")
    fun getShops(): Call<ArrayList<Shop>>


    @POST("api/trip/{tripId}/check-in/{storeId}")
    fun checkIn(@Path("tripId") tripId: String, @Path("storeId") storeId: String): Call<ResponseBody>


    @GET("api/categories/{id}/attributes")
    fun getAttributes(@Path("id") id: String): Call<AttributeResponse>


    @POST("api/trip/check-out/{tripShopId}")
    fun checkOut(@Path("tripShopId") tripShopId: String): Call<ResponseBody>


    @GET("api/trip/shops/{id}")
    fun getTripShops(@Path("id") id: String): Call<ArrayList<Shop>>


    @PUT("api/customers/{id}")
    fun updateUser(@Body params: RegistrationRequest, @Path("id") id: String): Call<ApiResponse>


    @GET("api/customers/{id}")
    fun getUser(@Path("id") id: String): Call<ApiResponse>


    @PUT("api/products/{id}")
    fun updateProduct(@Body params: ProductRequest, @Path("id") id: String): Call<ProductResponse>


    @GET("api/products/{id}")
    fun getProduct(@Path("id") id: String): Call<ProductResponse>


    @GET("api/manufacturers?Fields=id,name,image")
    fun getBrands(): Call<ApiResponse>


    @GET("api/categories?Fields=id,name,image")
    fun getCategories(): Call<ApiResponse>


    @POST("api/customers/gender-interests/{id}")
    @FormUrlEncoded
    fun updateGenderInterest(@FieldMap params: Map<String, String>, @Path("id") id: String): Call<ApiResponse>


    @POST("api/customers/brand-interests/{id}")
    @FormUrlEncoded
    fun saveBrands(@FieldMap params: Map<String, String>, @Path("id") id: String): Call<ApiResponse>


    @POST("api/customers/category-interests/{id}")
    @FormUrlEncoded
    fun saveCategories(@FieldMap params: Map<String, String>, @Path("id") id: String): Call<ApiResponse>


    @POST("api/customers/verify-phone/{id}")
    @FormUrlEncoded
    fun verifyPhone(@FieldMap params: Map<String, String>, @Path("id") id: String): Call<ApiResponse>


    @POST("api/customers/resend-verification-code/{id}")
    fun resendVerificationCode(@Path("id") id: String): Call<ApiResponse>


    @POST("api/products/calculate-product-price")
    fun calculatePrice(@Body params: PriceRequest): Call<PriceResponse>


    @GET("api/timeline/products/{customerId}")
    fun getTimelineProduct(@Path("customerId") id: String): Call<TimelineResult>


    @POST("api/timeline/comment")
    fun addComment(@Body params: ProductCommentRequest): Call<ProductView>


    @GET("api/trip/customer-trips/{id}")
    fun getTripDetails(
        @Path("id") id: Int,
        @Query("latest") isLatest: String,
        @Query("getProducts") getProducts: String,
        @Query("getShops") getShops: String,
        @Query("getOrders") getOrders: String
    ): Call<ArrayList<TripDetailsResponse>>


    @GET("api/orders/customer-orders/{customerId}")
    fun getCustomerOrders(@Path("customerId") customerId: Int): Call<ArrayList<OrderView>>


    @PUT("api/orders/update-status/{id}")
    fun updateOrderStatus(
        @Path("id")
        id: Int?,
        @Query("liveOrderStatusId") liveOrderStatusId: Int?,
        @Query("liveOrderRejectionReasonId") liveOrderRejectionReasonId: Int?
    ): Call<ResponseBody>


    @POST("api/orders")
    fun addOrder(@Body params: OrderRequest): Call<OrderResponse>

    @GET("api/customers/profile/{customerId}/{profileCustomerId}")
    fun getHunterProfile(@Path("customerId") customerId: Int, @Path("profileCustomerId") profileCustomerId: Int): Call<Profile>
}
