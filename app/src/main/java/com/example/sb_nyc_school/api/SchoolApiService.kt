package com.example.sb_nyc_school.api

import com.example.sb_nyc_school.model.NYCSchool
import com.example.sb_nyc_school.model.NYCScore
import com.example.sb_nyc_school.utils.SCHOOL_ENDPOINT
import com.example.sb_nyc_school.utils.SCORE_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolApiService {
    @GET(SCHOOL_ENDPOINT)
    suspend fun fetchNYCSchools(): Response<List<NYCSchool>>

    @GET(SCORE_ENDPOINT)
    suspend fun fetchNYCScore(
        @Query("dbn") dbn: String
    ): Response<List<NYCScore>>
}