package ua.nure.chumchase.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    protected fun handleResult(result: BaseResult<out Any>) {
        _isSuccess.postValue(result.isSuccess)
        _error.postValue(result.error)
        _isLoading.postValue(false)
    }

    protected fun startLoading() {
        _isLoading.value = true
    }
}