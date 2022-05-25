package com.harets.kisah25nabi.ui

import android.database.DatabaseErrorHandler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harets.kisah25nabi.data.KisahResponse
import com.harets.kisah25nabi.data.network.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    val kisahResponse = MutableLiveData<List<KisahResponse>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Throwable>()

    fun getKisahNabi(
        responsHandle: (List<KisahResponse>) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        ApiClient.getApiService().getKisahNabi().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                responsHandle(it)
            }, {
                errorHandler(it)
            })
    }

    fun getData(){
        isLoading.postValue(true)
        getKisahNabi({
            isLoading.postValue(true)
            kisahResponse.value = it
        },{
            isLoading.postValue(false)
            isError.value = it
        }) //lambda
    }
}