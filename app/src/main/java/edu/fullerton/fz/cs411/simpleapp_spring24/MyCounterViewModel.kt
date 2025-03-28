package edu.fullerton.fz.cs411.simpleapp_spring24

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch

class MyCounterViewModel: ViewModel() {

    private var counter: Int = 0
    public var happy: Boolean = false

    private val myDataRepository: MyDataRepository.

    public fun setCounter(i: Int) {
        counter = i
    }

    public fun getCounter(): Int {
        return counter
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(LOG_TAG, "MyCounterViewModel instance is about to be destroyed")
    }




}