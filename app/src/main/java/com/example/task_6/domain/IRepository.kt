package com.example.task_6.domain

import com.example.task_6.data.BankItem
import io.reactivex.Single

interface IRepository {
    fun getListOfAtm(city: String): Single<ArrayList<BankItem>>

    fun getListOfInfobox(city: String): Single<ArrayList<BankItem>>

    fun getListOfFilials(city: String): Single<ArrayList<BankItem>>
}