package com.oishikenko.android.recruitment.data.repository

import com.oishikenko.android.recruitment.data.model.CookingRecords
import retrofit2.Response

interface CookingRecordsRepository {
    suspend fun getCookingRecords(offet: Int, limit: Int): Response<CookingRecords>
}