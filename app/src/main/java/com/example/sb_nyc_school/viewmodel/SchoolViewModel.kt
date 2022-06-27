package com.example.sb_nyc_school.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sb_nyc_school.model.NYCSchool
import com.example.sb_nyc_school.api.SchoolRepository
import com.example.sb_nyc_school.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(
    private val repository: SchoolRepository
    ): ViewModel() {

    private val _schoolLiveData: MutableLiveData<UIState> = MutableLiveData()
    val schoolLiveData: LiveData<UIState> get() = _schoolLiveData

    private val _scoreLiveData: MutableLiveData<UIState> = MutableLiveData(UIState.Loading)
    val scoreLiveData: LiveData<UIState> get() = _scoreLiveData

    var currentSchool: NYCSchool? = null

    init {
        fetchNYCSchools()
    }

    private fun fetchNYCSchools() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchNYCSchools().collect { state ->
                _schoolLiveData.postValue(state)
            }
        }
    }

    fun fetchNYCScore(dbn: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchNYCScore(dbn).collect { state ->
                _scoreLiveData.postValue(state)
            }
        }
    }

    fun setSchool(nycSchool: NYCSchool?) {
        currentSchool = nycSchool
        _scoreLiveData.value = UIState.Loading
    }
}