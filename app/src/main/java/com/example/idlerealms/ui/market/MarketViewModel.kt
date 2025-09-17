package com.example.idlerealms.ui.market

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MarketViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "What you want buy?"
    }
    val text: LiveData<String> = _text
}