package com.example.sb_nyc_school.api

import com.example.sb_nyc_school.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface SchoolRepository {
    suspend fun fetchNYCSchools(): Flow<UIState>
    suspend fun fetchNYCScore(dbn: String): Flow<UIState>
}

class SchoolRepositoryImpl @Inject constructor(
    private val service: SchoolApiService
): SchoolRepository {
    override suspend fun fetchNYCSchools(): Flow<UIState> =
        flow {
            emit(UIState.Loading)
            try {
                val response = service.fetchNYCSchools()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(UIState.Success(it))
                    } ?: throw Exception("Response is null")
                }
                else throw Exception("Failed NYCSchool network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

    override suspend fun fetchNYCScore(dbn: String): Flow<UIState> =
        flow {
            try {
                val response = service.fetchNYCScore(dbn)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(UIState.Success(it))
                    } ?: throw Exception("Response is null")
                }
                else throw Exception("Failed Score network call")
            } catch (e: Exception) {
                emit(UIState.Error(e))
            }
        }

}