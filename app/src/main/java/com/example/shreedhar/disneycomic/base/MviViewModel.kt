package com.example.shreedhar.disneycomic.base

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

abstract class MviViewModel<STATE : BaseViewState<*>, EVENT> : MvvmViewModel() {

    private val _uiState = mutableStateOf<BaseViewState<*>>(BaseViewState.Empty)
    val uiState: State<BaseViewState<*>> = _uiState

    abstract fun onTriggerEvent(eventType: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        _uiState.value = state
    }

    override fun startLoading() {
        super.startLoading()
        _uiState.value = BaseViewState.Loading
    }

    override fun handleError(exception: Throwable) {
        super.handleError(exception)
        _uiState.value = BaseViewState.Error(exception)
    }
}