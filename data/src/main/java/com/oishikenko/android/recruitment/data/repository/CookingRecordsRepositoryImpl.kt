package com.oishikenko.android.recruitment.data.repository

import com.oishikenko.android.recruitment.data.model.CookingRecords
import com.oishikenko.android.recruitment.data.network.CookingRecordsNetworkApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject

class CookingRecordsRepositoryImpl @Inject constructor (
    var cookingRecordsNetworkApi: CookingRecordsNetworkApi
): CookingRecordsRepository {
    override fun getCookingRecords(offet: Int, limit: Int): Response<CookingRecords>{
        return runBlocking{
            cookingRecordsNetworkApi.getCookingRecords(offset = offet, limit = limit)
        }
//        cookingRecordsNetworkApi.getCookingRecords(offset = offet, limit = limit)
    }
}