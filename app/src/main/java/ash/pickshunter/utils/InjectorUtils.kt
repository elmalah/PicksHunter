package ash.pickshunter.utils

import android.content.Context
import ash.pickshunter.repository.TripRepository
import ash.pickshunter.viewModel.TripViewModelFactory
import ash.pickshunter.repository.UserRepository
import ash.pickshunter.viewModel.UserViewModelFactory

object InjectorUtils {

    private fun getUserRepository(context: Context): UserRepository {
        return UserRepository.getInstance()
    }

    fun provideUserViewModelFactory(context: Context): UserViewModelFactory {
        val repository = getUserRepository(context)
        return UserViewModelFactory(repository)
    }


    private fun getTripRepository(context: Context): TripRepository {
        return TripRepository.getInstance()
    }

    fun provideTripViewModelFactory(context: Context): TripViewModelFactory {
        val repository = getTripRepository(context)
        return TripViewModelFactory(repository)
    }
}
