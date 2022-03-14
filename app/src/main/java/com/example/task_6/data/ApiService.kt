package com.example.task_6.data

import com.example.task_6.domain.utils.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.ATM_URL)
    fun getListOfAtm(@Query("city") city: String): Single<ArrayList<BankItem>>

    @GET(Constants.INFOBOX_URl)
    fun getListOfInfobox(@Query("city") city: String): Single<ArrayList<BankItem>>

    @GET(Constants.FILIAL_URL)
    fun getListOfFilials(@Query("city") city: String): Single<ArrayList<BankItem>>
}