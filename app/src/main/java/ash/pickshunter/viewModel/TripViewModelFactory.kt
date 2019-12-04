package ash.pickshunter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ash.pickshunter.repository.TripRepository

class TripViewModelFactory(
    private val repository: TripRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = TripViewModel(
        repository
    ) as T
}
