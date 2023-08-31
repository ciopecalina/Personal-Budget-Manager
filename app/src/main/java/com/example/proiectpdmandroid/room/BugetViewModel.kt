package com.example.proiectpdmandroid.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BugetViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<BugetDataClass>>
    private val repository: BugetRepository

    init {
        val bugetDao = BugetDatabase.getDatabase(application).bugetDao()
        repository = BugetRepository(bugetDao)
        readAllData = repository.readAllData

    }

    fun addBuget(buget: BugetDataClass){
        viewModelScope.launch(Dispatchers.IO){
            repository.addBuget(buget)
        }
    }

    fun updateLuna(buget: BugetDataClass){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateLuna(buget)
        }
    }

    fun deleteDb(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteDb()
        }
    }

//    fun readAllDataAsList() : List<BugetDataClass>{
//        var list : List<BugetDataClass> = listOf()
//        CoroutineScope(Dispatchers.IO).launch {
//            list = repository.readAllDataAsList
//        }
//        return list
//    }


}