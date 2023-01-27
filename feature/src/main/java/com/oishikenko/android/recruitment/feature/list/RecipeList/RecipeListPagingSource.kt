package com.oishikenko.android.recruitment.feature.list.RecipeList

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oishikenko.android.recruitment.data.model.CookingRecord
import com.oishikenko.android.recruitment.data.repository.CookingRecordsRepository

class RecipeListPagingSource(
    //データ取得用の関数を利用するため
    private val repo: CookingRecordsRepository
) : PagingSource<Int, CookingRecord>() {
    //取得する必要があるページ番号を取得する関数
    override fun getRefreshKey(state: PagingState<Int, CookingRecord>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CookingRecord> {
        return try {
            var page = params.key ?: 1
            var response = repo.getCookingRecords(page, params.loadSize)
            LoadResult.Page(
                data = response.body()!!.cookingRecords,
                prevKey = null,
                nextKey = if (response.body()!!.cookingRecords.isNotEmpty()) response.body()!!.pagination.offset + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
