package ash.pickshunter

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fly365.shared.service.ApiClient
import com.fly365.shared.service.ApiInterface
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    var endpoints: ApiClient = ApiClient()

    fun login(loginRequest: LoginRequest): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        var params = HashMap<String, String>()
        params["Email"] = loginRequest.Email!!
        params["Password"] = loginRequest.Password!!

        val call: Call<ApiResponse> = apiService.login(params)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    apiResponse.postValue(null)
                }
            }

        })

        return apiResponse
    }

    fun checkIfIntrestsSaved(id: String): LiveData<Boolean> {
        val apiResponse = MutableLiveData<Boolean>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<Boolean> = apiService.checkIfIntrestsSaved(id)
        call.enqueue(object : Callback<Boolean> {
            override fun onFailure(call: Call<Boolean>?, t: Throwable?) {
                apiResponse.postValue(false)
            }

            override fun onResponse(call: Call<Boolean>?, response: Response<Boolean>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    apiResponse.postValue(null)
                }
            }

        })

        return apiResponse
    }

    fun register(registrationRequest: RegistrationRequest): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ApiResponse> = apiService.register(registrationRequest)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun updateUser(registrationRequest: RegistrationRequest): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ApiResponse> = apiService.updateUser(registrationRequest, registrationRequest.customer.id.toString())
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun getUser(id: String): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ApiResponse> = apiService.getUser(id)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
//                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(
                call: Call<ApiResponse>?,
                response: Response<ApiResponse>?
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

    fun updateUserAddress(registrationRequest: RegistrationRequest): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ApiResponse> = apiService.addNewAddress(registrationRequest)
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun getBrands(): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ApiResponse> = apiService.getBrands()
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
                    apiResponse.postValue(body)
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
                    val body: ApiResponse = Gson().fromJson(response.errorBody()!!.string(), ApiResponse::class.java)
                    apiResponse.postValue(body)
                }
            }

        })

        return apiResponse
    }

    fun updateGenderInterest(ids: String, userId: Int): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        var params = HashMap<String, String>()
        params["ids"] = ids

        val call: Call<ApiResponse> = apiService.updateGenderInterest(params, userId.toString())
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    apiResponse.postValue(null)
                }
            }

        })

        return apiResponse
    }

    fun saveBrands(ids: String, userId: Int): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        var params = HashMap<String, String>()
        params["ids"] = ids

        val call: Call<ApiResponse> = apiService.saveBrands(params, userId.toString())
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    apiResponse.postValue(null)
                }
            }

        })

        return apiResponse
    }

    fun saveCategories(ids: String, userId: Int): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        var params = HashMap<String, String>()
        params["ids"] = ids

        val call: Call<ApiResponse> = apiService.saveCategories(params, userId.toString())
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    apiResponse.postValue(null)
                }
            }

        })

        return apiResponse
    }

    fun verifyPhone(code: String, userId: Int): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        var params = HashMap<String, String>()
        params["code"] = code

        val call: Call<ApiResponse> = apiService.verifyPhone(params, userId.toString())
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    apiResponse.postValue(null)
                }
            }

        })

        return apiResponse
    }

    fun resendVerificationCode(userId: Int): LiveData<ApiResponse> {
        val apiResponse = MutableLiveData<ApiResponse>()
        val apiService = endpoints.getClient()!!.create(ApiInterface::class.java)

        val call: Call<ApiResponse> = apiService.resendVerificationCode(userId.toString())
        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>?, t: Throwable?) {
                apiResponse.postValue(ApiResponse(t!!))
            }

            override fun onResponse(call: Call<ApiResponse>?, response: Response<ApiResponse>?) {
                if (response!!.isSuccessful) {
                    apiResponse.postValue(response.body()!!)
                } else {
                    apiResponse.postValue(null)
                }
            }

        })

        return apiResponse
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance
                    ?: UserRepository().also { instance = it }
            }
    }
}
