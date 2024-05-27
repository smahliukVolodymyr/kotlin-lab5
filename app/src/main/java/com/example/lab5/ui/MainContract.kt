package com.example.lab5.ui

import com.example.lab5.data.api.Item

interface MainContract {
    interface View {
        fun displayError()
        fun setupSearchView(resultsAdapter: ItemAdaptor, dataList: List<Item>)
    }
    interface Presenter{
        fun getLocalItems()
    }
}