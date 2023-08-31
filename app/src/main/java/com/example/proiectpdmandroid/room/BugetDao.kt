package com.example.proiectpdmandroid.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.proiectpdmandroid.Buget

@Dao
interface BugetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addBuget(buget : BugetDataClass)

    @Query("SELECT * FROM buget_table")
    fun readAllData(): LiveData<List<BugetDataClass>>

    @Query("SELECT COUNT (*) FROM buget_table WHERE buget_table.luna = :luna")
    fun getCountLuna(luna : String) : Int

    @Update
    fun updateLuna(vararg buget : BugetDataClass)

    @Query("SELECT * FROM buget_table")
    fun readAllDataAsList(): List<BugetDataClass>

    @Query("DELETE FROM buget_table")
    fun deleteDb()

}