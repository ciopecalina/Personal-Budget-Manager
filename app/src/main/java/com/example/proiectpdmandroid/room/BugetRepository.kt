package com.example.proiectpdmandroid.room

import androidx.lifecycle.LiveData

class BugetRepository(private val bugetDao : BugetDao) {
    val readAllData: LiveData<List<BugetDataClass>> = bugetDao.readAllData()
   // val readAllDataAsList : List<BugetDataClass> = bugetDao.readAllDataAsList()

   suspend fun addBuget(buget: BugetDataClass){
        bugetDao.addBuget(buget)
    }

    fun updateLuna(buget : BugetDataClass){
        bugetDao.updateLuna(buget)
    }

    fun deleteDb(){
        bugetDao.deleteDb()
    }
}