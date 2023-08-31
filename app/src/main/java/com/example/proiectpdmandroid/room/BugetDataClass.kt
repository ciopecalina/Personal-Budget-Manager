package com.example.proiectpdmandroid.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buget_table")
data class BugetDataClass(
    @PrimaryKey
    var luna: String,
    var VSalariu: Double,
    var VInvestitii: Double,
    var VAltele: Double,
    var CUtilitati: Double,
    var CAlimente: Double,
    var CTransport: Double,
    var CCumparaturi: Double,
    var CMedical: Double,
    var CAltele: Double,
    var TotalVenituri: Double,
    var TotalCheltuieli: Double,
    var Economii: Double
) {
}