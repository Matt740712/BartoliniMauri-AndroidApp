package com.example.bartolini_mauri_login.ViewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bartolini_mauri_login.NetworkManager
import com.example.bartolini_mauri_login.models.policy.Policy
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var policies = MutableLiveData<List<Policy>>()
}

