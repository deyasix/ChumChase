package ua.nure.chumchase.core.base

import androidx.lifecycle.*

abstract class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    private val _error = MutableLiveData<Int?>()
    val error: LiveData<Int?>
        get() = _error

    protected fun handleResult(result: BaseResult) {
        _isSuccess.postValue(result.isSuccess)
        _error.postValue(result.error?.message)
        _isLoading.postValue(false)
    }

    protected fun startLoading() {
        _isLoading.value = true
    }
}