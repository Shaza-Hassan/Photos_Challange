package com.shaza.photoschallange.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shaza.photoschallange.network.RetrofitClient

/**
 * Created by Shaza Hassan on 31/May/2023.
 */
class PhotosDataSource (private val retrofitClient: RetrofitClient) : PagingSource<Int,Photo>(){
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = retrofitClient.webService.getImages(page,20).body()
            val pages = response?.photos?.pages ?: 0
            for (i in 1..4){
                val index = 5*i + (i-1)
                response?.photos?.photo?.add(index, Photo("","","","",0,"",0,0,0,ad = true))
            }

            LoadResult.Page(
                data = response?.photos?.photo ?: listOf(),
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (page >= pages ) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}