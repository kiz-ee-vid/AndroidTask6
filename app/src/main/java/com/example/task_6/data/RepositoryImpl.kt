package com.example.task_6.data

import com.example.task_6.domain.IRepository
import io.reactivex.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: ApiService) : IRepository {

    override fun getListOfAtm(city: String): Single<ArrayList<BankItem>> {
        return apiService.getListOfAtm(city)
    }

    override fun getListOfInfobox(city: String): Single<ArrayList<BankItem>> {
        return apiService.getListOfInfobox(city)
    }

    override fun getListOfFilials(city: String): Single<ArrayList<BankItem>> {
        return apiService.getListOfFilials(city)
    }
}