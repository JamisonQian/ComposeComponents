package com.jamison.codeing.compose.screen.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * @FileName DialogViewModel
 * @Description
 * @Author arjun
 * @Date 2022/6/27 14:36
 * @Version
 */
class DialogViewModel :ViewModel(){

    private val _showDialog = MutableStateFlow(false)
    val showDialog:StateFlow<Boolean> = _showDialog.asStateFlow()

    fun openDialog(){
        _showDialog.value = true
    }
    fun dismissDialog(){
        _showDialog.value = false
    }
}