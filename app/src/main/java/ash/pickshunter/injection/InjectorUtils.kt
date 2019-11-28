package com.fly365.utils.injection

import android.content.Context
import androidx.fragment.app.FragmentActivity
import ash.pickshunter.TripRepository
import ash.pickshunter.TripViewModelFactory
import ash.pickshunter.UserRepository
import ash.pickshunter.UserViewModelFactory

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
