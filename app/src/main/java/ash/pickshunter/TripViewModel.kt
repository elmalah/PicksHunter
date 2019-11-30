package ash.pickshunter

import android.graphics.Picture
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ash.pickshunter.country.Option
import okhttp3.ResponseBody
import java.text.ParsePosition


class TripViewModel(private val repository: TripRepository) : ViewModel() {

    private var apiResponse: MutableLiveData<ApiResponse> = MutableLiveData()

    private var tripDetailsResponse: MutableLiveData<ArrayList<TripDetailsResponse>> =
        MutableLiveData()
    var attributeApiResponse: MutableLiveData<AttributeResponse> = MutableLiveData()

    var createTripApiResponse: MutableLiveData<Trip> = MutableLiveData()

    var addProductApiResponse: MutableLiveData<ProductResponse> = MutableLiveData()

    var addOrderApiResponse: MutableLiveData<OrderResponse> = MutableLiveData()

    var addPictureApiResponse: MutableLiveData<PictureResponse> = MutableLiveData()

    private var countries: MutableLiveData<ArrayList<Country>> = MutableLiveData()

    var shops: MutableLiveData<ArrayList<Shop>> = MutableLiveData()

    fun createTrip(tripRequest: TripRequest): LiveData<Trip> {
        createTripApiResponse = repository.createTrip(tripRequest) as MutableLiveData<Trip>
        return createTripApiResponse
    }

    fun getCategories(): LiveData<ApiResponse> {
        apiResponse = repository.getCategories() as MutableLiveData<ApiResponse>
        return apiResponse
    }

    fun getAttributes(id: Int): LiveData<AttributeResponse> {
        attributeApiResponse =
            repository.getAttributes(id.toString()) as MutableLiveData<AttributeResponse>
        return attributeApiResponse
    }

    fun onOptionChange(option: Option, optionPos: Int, attPos: Int) {
        val attributes = attributeApiResponse.value!!
        attributes.productAttributes!![attPos].options!![optionPos].selected = !option.selected
        attributeApiResponse.postValue(attributes)
    }

    fun getCategoriesList(): List<Category> {
        return apiResponse.value!!.categories!!
    }

    fun addProduct(productRequest: ProductRequest, id: Int): LiveData<ProductResponse> {
        addProductApiResponse =
            repository.addProduct(productRequest, id.toString()) as MutableLiveData<ProductResponse>
        return addProductApiResponse
    }

    fun addOrder(orderRequest: OrderRequest): LiveData<OrderResponse> {
        addOrderApiResponse =
            repository.addOrder(orderRequest) as MutableLiveData<OrderResponse>
        return addOrderApiResponse
    }

    fun addPicture(pictureRequest: PictureRequest): LiveData<PictureResponse> {
        addPictureApiResponse =
            repository.addPicture(pictureRequest) as MutableLiveData<PictureResponse>
        return addPictureApiResponse
    }

    fun getProduct(id: Int): LiveData<ProductResponse> {
        addProductApiResponse =
            repository.getProduct(id.toString()) as MutableLiveData<ProductResponse>
        return addProductApiResponse
    }

    fun updateProduct(productRequest: ProductRequest, id: Int): LiveData<ProductResponse> {
        addProductApiResponse = repository.updateProduct(
            productRequest,
            id.toString()
        ) as MutableLiveData<ProductResponse>
        return addProductApiResponse
    }

    fun getTripStores(tripId: Int): LiveData<ArrayList<Shop>> {
        return repository.getTripStores(tripId.toString())
    }

    fun getCountries(): LiveData<ArrayList<Country>> {
        countries = repository.getCountries() as MutableLiveData<ArrayList<Country>>
        return countries
    }

    fun getShops(): LiveData<ArrayList<Shop>> {
        shops = repository.getShops() as MutableLiveData<ArrayList<Shop>>
        return shops
    }

    fun getAllCountries(): ArrayList<Country> {
        return countries.value!!
    }

    fun checkIn(tripId: Int, storeId: Int): LiveData<ResponseBody> {
        return repository.checkIn(tripId.toString(), storeId.toString())
    }

    fun checkOut(tripId: Int): LiveData<ResponseBody> {
        return repository.checkOut(tripId.toString())
    }

    fun calculatePrice(priceRequest: PriceRequest): LiveData<PriceResponse> {
        return repository.calculatePrice(priceRequest)
    }

    fun getTimelineProduct(): LiveData<ArrayList<ProductView>> {
        return repository.getTimelineProduct()
    }

    fun addComment(params: ProductCommentRequest): LiveData<ProductView> {
        return repository.addComment(params)
    }

    fun getTripDetails(
        userId: Int,
        isLatest: String
    ): MutableLiveData<ArrayList<TripDetailsResponse>> {
        tripDetailsResponse = repository.getTripDetails(
            userId,
            isLatest
        ) as MutableLiveData<ArrayList<TripDetailsResponse>>
        return tripDetailsResponse
    }
}