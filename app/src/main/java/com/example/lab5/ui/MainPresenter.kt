package com.example.lab5.ui

import com.example.lab5.data.api.Item
import com.example.lab5.di.DiHelper
import com.example.lab5.model.IDataSource

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {
    val apiService: IDataSource = DiHelper.getService()
    // Отримання локальних елементів з сервера
    override fun getLocalItems() {
        apiService.getLocalItems(object : IDataSource.ItemsCallback {
            override fun onSuccess(items: List<Item>) {
                // Дані успішно отримано, працюємо з ними
                view.setupSearchView(ItemAdaptor(items), items)
            }

            override fun onFailure() {
                // Помилка при отриманні даних
                view.displayError()
            }
        })
    }
}

