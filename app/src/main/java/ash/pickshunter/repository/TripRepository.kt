package ash.pickshunter.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ash.pickshunter.model.Shop
import ash.pickshunter.model.Trip
import ash.pickshunter.model.TripDetailsResponse
import ash.pickshunter.model.TripRequest
import ash.pickshunter.model.*
import com.fly365.shared.service.ApiClient
import com.fly365.shared.service.ApiInterface
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.MultipartBody
import okhttp3.RequestBody


class TripRepository {

    var endpoints: ApiClient = ApiClient()

    fun getCountries(): LiveData<ArrayList<Country>> {
        val apiResponse = MutableLiveData<ArrayList<Country>>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ArrayList<Country>> = apiService.getCountries()
        call.enqueue(object : Callback<ArrayList<Country>> {
            override fun onFailure(call: Call<ArrayList<Country>>?, t: Throwable?) {
//                apiResponse.postValue(t!!))
            }

            override fun onResponse(
                call: Call<ArrayList<Country>>?,
                response: Response<ArrayList<Country>>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
//                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun getShops(): LiveData<ArrayList<Shop>> {
        val apiResponse = MutableLiveData<ArrayList<Shop>>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ArrayList<Shop>> = apiService.getShops()
        call.enqueue(object : Callback<ArrayList<Shop>> {
            override fun onFailure(call: Call<ArrayList<Shop>>?, t: Throwable?) {
//                apiResponse.postValue(t!!))
            }

            override fun onResponse(
                call: Call<ArrayList<Shop>>?,
                response: Response<ArrayList<Shop>>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
//                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun checkIn(tripId: String, storeId: String): LiveData<ResponseBody> {
        val apiResponse = MutableLiveData<ResponseBody>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ResponseBody> = apiService.checkIn(tripId, storeId)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
//                apiResponse.postValue(t!!))
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
//                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun checkOut(tripId: String): LiveData<ResponseBody> {
        val apiResponse = MutableLiveData<ResponseBody>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ResponseBody> = apiService.checkOut(tripId)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
//                apiResponse.postValue(t!!))
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
//                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }


    fun addProduct(productRequest: ProductRequest, id: String): LiveData<ProductResponse> {
        val apiResponse = MutableLiveData<ProductResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ProductResponse> = apiService.addProduct(productRequest, id)
        call.enqueue(object : Callback<ProductResponse> {
            override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
//                apiResponse.postValue(t!!))
            }

            override fun onResponse(
                call: Call<ProductResponse>?,
                response: Response<ProductResponse>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
//                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun addOrder(productRequest: OrderRequest): LiveData<OrderResponse> {
        val apiResponse = MutableLiveData<OrderResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<OrderResponse> = apiService.addOrder(productRequest)
        call.enqueue(object : Callback<OrderResponse> {
            override fun onFailure(call: Call<OrderResponse>?, t: Throwable?) {
                var error = t
            }

            override fun onResponse(
                call: Call<OrderResponse>?,
                response: Response<OrderResponse>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                }
            }

        })

        return apiResponse
    }

    fun addPicture(pictureRequest: PictureRequest): LiveData<PictureResponse> {
        val apiResponse = MutableLiveData<PictureResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        // create RequestBody instance from file
        val requestFile =
            RequestBody.create(MediaType.parse("image/jpeg"), pictureRequest.file)

        // MultipartBody.Part is used to send also the actual file name
        val body =
            MultipartBody.Part.createFormData("file", pictureRequest.file?.getName(), requestFile)

        // var params = HashMap<String, RequestBody>()
        // params["file"] = body!!.body()

        val call: Call<PictureResponse> = apiService.addPicture(body)
        call.enqueue(object : Callback<PictureResponse> {
            override fun onFailure(call: Call<PictureResponse>?, t: Throwable?) {
                var xx = t!!

            }

            override fun onResponse(
                call: Call<PictureResponse>?,
                response: Response<PictureResponse>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
//                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }


    fun getTimelineProduct(): LiveData<ArrayList<ProductView>> {
        val apiResponse = MutableLiveData<ArrayList<ProductView>>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ArrayList<ProductView>> = apiService.getTimelineProduct()
        call.enqueue(object : Callback<ArrayList<ProductView>> {
            override fun onFailure(call: Call<ArrayList<ProductView>>?, t: Throwable?) {
//                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(
                call: Call<ArrayList<ProductView>>?,
                response: Response<ArrayList<ProductView>>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
//                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun addComment(params: ProductCommentRequest): LiveData<ProductView> {
        val apiResponse = MutableLiveData<ProductView>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ProductView> = apiService.addComment(params)
        call.enqueue(object : Callback<ProductView> {
            override fun onFailure(call: Call<ProductView>?, t: Throwable?) {
//                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ProductView>?, response: Response<ProductView>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
//                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun calculatePrice(priceRequest: PriceRequest): LiveData<PriceResponse> {
        val apiResponse = MutableLiveData<PriceResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<PriceResponse> = apiService.calculatePrice(priceRequest)
        call.enqueue(object : Callback<PriceResponse> {
            override fun onFailure(call: Call<PriceResponse>?, t: Throwable?) {
//                apiResponse.postValue(t!!))
            }

            override fun onResponse(
                call: Call<PriceResponse>?,
                response: Response<PriceResponse>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
//                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun getCategories(): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ApiResponse> = apiService.getCategories()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    val body: ApiResponse =
                        Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun getTripDetails(
        tripId: Int,
        isLatest: Boolean,
        getProducts: Boolean,
        getShops: Boolean,
        getOrders: Boolean
    ): LiveData<ArrayList<TripDetailsResponse>> {
        val apiResponse = MutableLiveData<ArrayList<TripDetailsResponse>>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ArrayList<TripDetailsResponse>> = apiService.getTripDetails(
            tripId,
            isLatest.toString(),
            getProducts.toString(),
            getShops.toString(),
            getOrders.toString()
        )
        call.enqueue(object : Callback<ArrayList<TripDetailsResponse>> {
            override fun onFailure(call: Call<ArrayList<TripDetailsResponse>>?, t: Throwable?) {
                apiResponse.postValue(apiResponse.value)
            }

            override fun onResponse(
                call: Call<ArrayList<TripDetailsResponse>>?,
                response: Response<ArrayList<TripDetailsResponse>>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ArrayList<TripDetailsResponse> = Gson().fromJson(response.errorBody()!!.string(), TripDetailsResponse::class.java)
//                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun getCustomerOrders(customerId: Int): LiveData<ArrayList<OrderView>> {
        val apiResponse = MutableLiveData<ArrayList<OrderView>>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ArrayList<OrderView>> = apiService.getCustomerOrders(customerId)
        call.enqueue(object : Callback<ArrayList<OrderView>> {
            override fun onFailure(call: Call<ArrayList<OrderView>>?, t: Throwable?) {
                apiResponse.postValue(apiResponse.value)
            }

            override fun onResponse(
                call: Call<ArrayList<OrderView>>?,
                response: Response<ArrayList<OrderView>>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {

                }
            }

        })

        return apiResponse
    }

    fun updateOrderStatus(
        orderId: Int?,
        liveOrderStatusId: Int?,
        liveOrderRejectionReasonId: Int?
    ): LiveData<ResponseBody> {
        val apiResponse = MutableLiveData<ResponseBody>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ResponseBody> =
            apiService.updateOrderStatus(orderId, liveOrderStatusId, liveOrderRejectionReasonId)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {

                }
            }

        })

        return apiResponse
    }

    fun getAttributes(id: String): LiveData<AttributeResponse> {
        val apiResponse = MutableLiveData<AttributeResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<AttributeResponse> = apiService.getAttributes(id)
        call.enqueue(object : Callback<AttributeResponse> {
            override fun onFailure(call: Call<AttributeResponse>?, t: Throwable?) {
//                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(
                call: Call<AttributeResponse>?,
                response: Response<AttributeResponse>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
//                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }


    fun createTrip(tripRequest: TripRequest): LiveData<Trip> {
        val apiResponse = MutableLiveData<Trip>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<Trip> = apiService.createTrip(tripRequest)
        call.enqueue(object : Callback<Trip> {
            override fun onFailure(call: Call<Trip>?, t: Throwable?) {
//                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<Trip>?, response: Response<Trip>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
                    apiResponse.postValue(null)
                }
            }

        })

        return apiResponse
    }

    fun getProduct(id: String): LiveData<ProductResponse> {
        val apiResponse = MutableLiveData<ProductResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ProductResponse> = apiService.getProduct(id)
        call.enqueue(object : Callback<ProductResponse> {
            override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
//                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(
                call: Call<ProductResponse>?,
                response: Response<ProductResponse>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
                    apiResponse.postValue(null)
                }
            }

        })

        return apiResponse
    }

    fun updateProduct(productRequest: ProductRequest, id: String): LiveData<ProductResponse> {
        val apiResponse = MutableLiveData<ProductResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ProductResponse> = apiService.updateProduct(productRequest, id)
        call.enqueue(object : Callback<ProductResponse> {
            override fun onFailure(call: Call<ProductResponse>?, t: Throwable?) {
//                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(
                call: Call<ProductResponse>?,
                response: Response<ProductResponse>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
                    apiResponse.postValue(null)
                }
            }

        })

        return apiResponse
    }

    fun getTripStores(tripId: String): LiveData<ArrayList<Shop>> {
        val apiResponse = MutableLiveData<ArrayList<Shop>>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ArrayList<Shop>> = apiService.getTripShops(tripId)
        call.enqueue(object : Callback<ArrayList<Shop>> {
            override fun onFailure(call: Call<ArrayList<Shop>>?, t: Throwable?) {
//                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(
                call: Call<ArrayList<Shop>>?,
                response: Response<ArrayList<Shop>>?
            ) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
//                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
                    apiResponse.postValue(null)
                }
            }

        })

        return apiResponse
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: TripRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: TripRepository().also { instance = it }
            }
    }
}
