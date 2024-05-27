package com.example.lab5.model

import com.example.lab5.data.api.Item

interface IDataSource {
    fun getLocalItems(callback: ItemsCallback)
    interface ItemsCallback {
        fun onSuccess(items: List<Item>)
        fun onFailure()
    }
}