package com.shaza.photoschallange.photolist.model

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bumptech.glide.load.HttpException
import com.shaza.photoschallange.shared.network.RetrofitClient
import com.shaza.photoschallange.shared.room.PhotosDB
import com.shaza.photoschallange.shared.room.RemoteKeys
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by Shaza Hassan on 02/Jun/2023.
 */
@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator(
    private val retrofitClient: RetrofitClient,
    private val photosDB: PhotosDB
) : RemoteMediator<Int, Photo>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)

        return if (System.currentTimeMillis() - (photosDB.getRemoteKeysDao().getCreationTime()
                ?: 0) < cacheTimeout
        ) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }


    override suspend fun load(loadType: LoadType, state: PagingState<Int, Photo>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                null
            }

            LoadType.PREPEND -> {
                return MediatorResult.Success(endOfPaginationReached = true)
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextKey = remoteKeys?.nextKey
                nextKey
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        } ?: 1

        try {
            val response = retrofitClient.webService.getImages(page, 20).body()
            val photos = response?.photos?.photo
            val endOfPaginationReached = (photos?.size ?: 0) < 20

            photosDB.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    photosDB.getRemoteKeysDao().clearRemoteKeys()
                    photosDB.getPhotoDao().clearAllMovies()
                }
                val prevKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPaginationReached) null else page + 1
                val remoteKeys = photos?.map {
                    RemoteKeys(id = it.id, prevKey = prevKey, currentPage = page, nextKey = nextKey)
                }

                if (remoteKeys != null) {
                    photosDB.getRemoteKeysDao().insertAll(remoteKeys)
                }
                photos?.onEachIndexed { _, _ -> }
                    ?.let { photosDB.getPhotoDao().insertAll(it) }
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (error: IOException) {
            return MediatorResult.Error(error)
        } catch (error: HttpException) {
            return MediatorResult.Error(error)
        } catch (error: Exception) {
            return MediatorResult.Error(error)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Photo>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                photosDB.getRemoteKeysDao().getRemoteKeyByPhotoID(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Photo>): RemoteKeys? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { movie ->
            photosDB.getRemoteKeysDao().getRemoteKeyByPhotoID(movie.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Photo>): RemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { movie ->
            photosDB.getRemoteKeysDao().getRemoteKeyByPhotoID(movie.id)
        }
    }
}