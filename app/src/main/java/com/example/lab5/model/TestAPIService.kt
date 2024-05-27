package com.example.lab5.model

import android.util.Log
import com.example.lab5.data.api.Item
import com.example.lab5.data.api.RetrofitApiHelper
import com.example.lab5.data.api.TestApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class ApiResponse(
    val record: List<Item>,
    val metadata: Metadata
)

data class Metadata(
    val id: String,
    val private: Boolean,
    val createdAt: String,
    val name: String
)

class TestApiService : IDataSource {
    private val api: TestApi = RetrofitApiHelper().init().retrofit!!.create(TestApi::class.java)
    private val SECRET_KEY = "\$2a\$10\$ug7XYv49Fiu7HfldyQfJLOCEtOUzaMmy452q1BNRRxfp7i5iNffE2"

    override fun getLocalItems(callback: IDataSource.ItemsCallback) {

        api.getLocalItems(SECRET_KEY).enqueue(object : Callback<ApiResponse> {

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    callback.onSuccess(response.body()?.record!!)
                } else {
                    Log.e("API", "Failed to get local items: ${response.errorBody()?.string()}")
                    callback.onFailure()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("API", "Network call failed: ${t.message}")
                callback.onFailure()
            }
        })
    }

}