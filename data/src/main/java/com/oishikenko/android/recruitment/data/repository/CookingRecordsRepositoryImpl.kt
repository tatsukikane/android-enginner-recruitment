package com.oishikenko.android.recruitment.data.repository

import com.oishikenko.android.recruitment.data.model.CookingRecords
import com.oishikenko.android.recruitment.data.network.CookingRecordsNetworkApi
import retrofit2.Response
import javax.inject.Inject

class CookingRecordsRepositoryImpl @Inject constructor(
    var cookingRecordsNetworkApi: CookingRecordsNetworkApi
) : CookingRecordsRepository {
//    [pagingテスト]
//    var error = 0
    override suspend fun getCookingRecords(offet: Int, limit: Int): Response<CookingRecords> {
//        [pagingテスト]
//        delay(3000L)
//        error++
//        if (error == 4)
//            throw IOException("some error")
        return cookingRecordsNetworkApi.getCookingRecords(offset = offet, limit = limit)
    }
}