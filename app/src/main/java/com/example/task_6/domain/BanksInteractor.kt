package com.example.task_6.domain

import com.example.task_6.data.BankItem
import com.example.task_6.data.RepositoryImpl
import com.example.task_6.domain.utils.Constants
import com.example.task_6.domain.utils.Constants.Companion.city
import com.example.task_6.domain.utils.Constants.Companion.homiel
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt

class BanksInteractor @Inject constructor(private val repositoryImpl: RepositoryImpl) {
    fun getBanks(): Observable<BankItem> {
        val single = Single.zip(
            repositoryImpl.getListOfAtm(city),
            repositoryImpl.getListOfFilials(city),
            repositoryImpl.getListOfInfobox(city),
            { banks, filials, infoboxes ->
                banks.forEach { it.type = Constants.ATM }
                filials.forEach { it.type = Constants.FILIAL }
                infoboxes.forEach { it.type = Constants.INFOBOX }
                banks + filials + infoboxes
            })
            .map { list ->
                list.sortedWith(
                    compareBy {
                        sqrt(
                            (homiel.latitude - it.gps_x).pow(2) + (homiel.longitude - it.gps_y).pow(2))
                    })
            }
            .flatMapObservable { Observable.fromIterable(it) }
            .take(10)
        return single
    }
}