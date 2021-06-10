package com.highair.weatherforecastid.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import com.highair.weatherforecastid.data.Result

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 * [Architecture Guide](https://developer.android.com/arch).
 * [ResultType] represents the type for database.
 * [RequestType] represents the type for network.
 */
abstract class NetworkBoundResource<ResultType, RequestType> {

    fun asFlow() = flow<Result<ResultType>> {
        // emit Database data first
        if (shouldFetch(fetchFromLocal().first())) {
            emit(Result.Loading)

            // fetch from remote
            val apiResponse = fetchFromRemote()

            // check for response validation
            if (apiResponse is Result.Success) {
                // save response into persistence storage
                saveRemoteData(apiResponse.data)
            } else {
                // something when wrong! emit error state
                emit(Result.Error((apiResponse as Result.Error).exception))
            }
        }
        emitAll(fetchFromLocal().map { Result.Success(it) })
    }

    /**
     * Force to get the data from the remote end point if true
     */
    protected abstract fun shouldFetch(data: ResultType): Boolean

    /**
     * Saves retrieved from remote into persistence storage.
     */
    protected abstract suspend fun saveRemoteData(response: RequestType)

    /**
     * Retrieved all data from persistence storage.
     */
    protected abstract fun fetchFromLocal(): Flow<ResultType>

    /**
     * Fetches [Response] from the remote end point.
     */
    protected abstract suspend fun fetchFromRemote(): Result<RequestType>
}