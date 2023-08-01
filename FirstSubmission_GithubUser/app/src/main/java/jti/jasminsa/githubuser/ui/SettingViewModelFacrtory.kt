package jti.jasminsa.githubuser.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import jti.jasminsa.githubuser.ui.main.MainViewModel

class SettingViewModelFacrtory(private val pref: SettingPreferences) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}