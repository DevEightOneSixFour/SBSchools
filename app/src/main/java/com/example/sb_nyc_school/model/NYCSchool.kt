package com.example.sb_nyc_school.model

import com.google.gson.annotations.SerializedName

data class NYCSchool(
    val dbn: String,
    @SerializedName("overview_paragraph")
    val overviewParagraph: String,
    val city: String,
    @SerializedName("fax_number")
    val faxNumber: String,
    val location: String,
    val neighborhood: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("primary_address_line_1")
    val primaryAddressLine1: String,
    @SerializedName("school_email")
    val schoolEmail: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("state_code")
    val stateCode: String,
    @SerializedName("total_students")
    val totalStudents: String,
    val website: String,
    val zip: String
)
