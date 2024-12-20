package android.vafeen.directrefresher.presentation

import android.vafeen.direct_refresher.downloader.DownloadStatus
import android.vafeen.direct_refresher.refresher.Refresher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class MainActivityViewModel(
    private val refresher: Refresher
) : ViewModel() {
    private val _isUpdateInProcessFlow = MutableStateFlow(false)
    val isUpdateInProcessFlow = _isUpdateInProcessFlow.asStateFlow()
    private val _percentageFlow = MutableSharedFlow<Float>()
    val percentageFlow = _percentageFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            refresher.progressFlow.collect { status ->
                when (status) {
                    DownloadStatus.Started -> {
                        _isUpdateInProcessFlow.emit(true)
                    }

                    is DownloadStatus.InProgress -> {
                        _percentageFlow.emit(status.percentage)
                    }

                    DownloadStatus.Success -> {
                        _isUpdateInProcessFlow.emit(false)
                    }

                    is DownloadStatus.Error -> {
                        _isUpdateInProcessFlow.emit(false)
                    }
                }
            }
        }
    }

    fun update() {
        viewModelScope.launch(Dispatchers.IO) {
            refresher.refresh(viewModelScope, TestData.testUrl, TestData.testName)
        }
    }

}