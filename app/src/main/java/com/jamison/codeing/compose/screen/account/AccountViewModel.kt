package com.jamison.codeing.compose.screen.account

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel

/**
 * @FileName AccountViewModel
 * @Description
 * @Author arjun
 * @Date 2022/6/8 10:00HO
 * @Version
 */
class AccountViewModel :ViewModel(){

    val lazyListState = LazyListState()

    val list = (0..100).toList()

    init {
        Log.d("TAG","AccountViewModel")
    }
}