package com.example.idlerealms.ui.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

data class ApiResponse(
    val id: Number,
    val from: String,
    val quote: String,
)
class WorldViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Loading..."
    }
    val text: LiveData<String> = _text

    init {
        fetchApiMessage()
    }

    private fun fetchApiMessage() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val url = URL("https://quotes.domiadi.com/api")
                val connection = url.openConnection() as HttpsURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 5000
                connection.readTimeout = 5000

                val responseCode = connection.responseCode
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    val response = connection.inputStream.bufferedReader().use { it.readText() }
                    val jsonObj = JSONObject(response)
                    val apiResponse = ApiResponse(
                        id = jsonObj.getInt("id"),
                        from = jsonObj.getString("from"),
                        quote = jsonObj.getString("quote")
                    )

                    _text.postValue("${apiResponse.from} once said \"${apiResponse.quote}\"")
                } else {
                    _text.postValue("Req failed ${connection.responseCode}, ${connection.responseMessage}")
                }

                connection.disconnect()
            } catch (e: Exception) {
                _text.postValue("Error: ${e.message}")
            }
        }
    }
}