package com.example.lab5.di

import com.example.lab5.ui.MainContract
import com.example.lab5.ui.MainPresenter
import com.example.lab5.data.api.RetrofitApiHelper
import com.example.lab5.model.IDataSource
import com.example.lab5.model.TestApiService

class DiHelper {
    companion object{
        private var mainPresenter: MainContract.Presenter? = null
        private var service: IDataSource? = null
        private var retrofitHelper: RetrofitApiHelper? = null

        fun getPresenter(view: MainContract.View): MainContract.Presenter {
            if(mainPresenter ==null){
                mainPresenter = MainPresenter(view)
            }
            return mainPresenter!!
        }

        fun getService(): IDataSource {
            if(service ==null){
                service = TestApiService()
            }
            return service!!
        }
        fun getRetrofitHelper(): RetrofitApiHelper {
            if(retrofitHelper ==null){
                retrofitHelper = RetrofitApiHelper()
                retrofitHelper?.init()
            }
            return retrofitHelper!!
        }
    }
}