package com.example.exercicio03.viewmodel

import androidx.lifecycle.*
import com.example.exercicio03.data.Cities
import com.example.exercicio03.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ApiViewModel(
    private val repository: Repository = Repository.instance,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : ViewModel() {

    private val _error: MutableLiveData<Boolean> = MutableLiveData(false)
    val error: LiveData<Boolean> = _error

    private val _loading: MutableLiveData<Boolean> = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading


    private val _cityDetails = MutableLiveData<ArrayList<Cities>>()
    val cityDetails: LiveData<ArrayList<Cities>>
        get() = _cityDetails


    fun getWeatherDetails() = viewModelScope.launch(Dispatchers.IO) {
        repository
            .requestListCities()
            .onStart { _loading.postValue(true) }
            .catch { _error.postValue(true) }
            .onCompletion { _loading.postValue(false) }
            .collect {
                _cityDetails.postValue(it)
            }
    }

    fun getWeather() =
        repository.requestListCities().flowOn(dispatcher).asLiveData()

    fun getWeatherOpen(latitude: String, longitude: String) =
        repository.requestOpenWeather(latitude, longitude).flowOn(dispatcher).asLiveData()


}