package com.example.task_6.presentation.ui.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task_6.data.BankItem
import com.example.task_6.domain.BanksInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor (repository: BanksInteractor) : ViewModel() {

    var banksList: MutableLiveData<ArrayList<BankItem>> = MutableLiveData()

    init {
            val points = ArrayList<BankItem>()
            repository.getBanks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { value -> points.add(value) },
                    { Log.e("error", "error") },
                    { banksList.value = points })
    }
}