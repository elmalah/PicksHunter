package ash.pickshunter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ash.pickshunter.model.*
import ash.pickshunter.repository.UserRepository


class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private var apiResponse: MutableLiveData<ApiResponse> = MutableLiveData()

    fun login(loginRequest: LoginRequest): LiveData<ApiResponse> {
        apiResponse = repository.login(loginRequest) as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun checkIfIntrestsSaved(id: String): LiveData<Boolean> {
        return repository.checkIfIntrestsSaved(id) as MutableLiveData<Boolean>
    }

    fun register(registrationRequest: RegistrationRequest): LiveData<ApiResponse> {
        apiResponse = repository.register(registrationRequest) as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun updateUser(registrationRequest: RegistrationRequest): LiveData<ApiResponse> {
        apiResponse = repository.updateUser(registrationRequest) as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun getUser(id: String): LiveData<ApiResponse> {
        apiResponse = repository.getUser(id) as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun updateUserAddress(registrationRequest: RegistrationRequest): LiveData<ApiResponse> {
        apiResponse = repository.updateUserAddress(registrationRequest) as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun getBrands(): LiveData<ApiResponse> {
        apiResponse = repository.getBrands() as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun getBrandsList(): List<Manufacturer> {
        return apiResponse.value!!.manufacturers!!
    }

    fun updateSelectedBrand(position: Int) : List<Manufacturer> {
        val apiResponse = apiResponse.value
        val manufactureses = apiResponse!!.manufacturers
        manufactureses!![position].selected = !manufactureses[position].selected
        apiResponse.manufacturers = manufactureses
        this.apiResponse.postValue(apiResponse)
        return manufactureses
    }

    fun getCategories(): LiveData<ApiResponse> {
        apiResponse = repository.getCategories() as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun getCategoriesList(): List<Category> {
        return apiResponse.value!!.categories!!
    }

    fun updateSelectedCategory(position: Int) {
        val apiResponse = apiResponse.value
        val categories = apiResponse!!.categories
        categories!![position].selected = !categories[position].selected
        apiResponse.categories = categories
        this.apiResponse.postValue(apiResponse)
    }

    fun updateGenderInterest(ids: String, userId: Int): LiveData<ApiResponse> {
        apiResponse = repository.updateGenderInterest(ids, userId) as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun saveBrands(ids: String, userId: Int): LiveData<ApiResponse> {
        apiResponse = repository.saveBrands(ids, userId) as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun saveCategories(ids: String, userId: Int): LiveData<ApiResponse> {
        apiResponse = repository.saveCategories(ids, userId) as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun verifyPhone(code: String, userId: Int): LiveData<ApiResponse> {
        apiResponse = repository.verifyPhone(code, userId) as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun resendVerificationCode(userId: Int): LiveData<ApiResponse> {
        apiResponse = repository.resendVerificationCode(userId) as MutableLiveData<ApiResponse>
        return apiResponse
    }
}