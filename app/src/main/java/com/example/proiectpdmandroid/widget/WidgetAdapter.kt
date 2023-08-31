package com.example.proiectpdmandroid.widget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.lifecycle.ViewModelProvider
import com.example.proiectpdmandroid.R
import com.example.proiectpdmandroid.room.BugetDataClass
import com.example.proiectpdmandroid.room.BugetDatabase
import com.example.proiectpdmandroid.room.BugetViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WidgetAdapter(val context: Context) : RemoteViewsService.RemoteViewsFactory {

   private val database : BugetDatabase = BugetDatabase.getDatabase(context)
    private var listBuget : List<BugetDataClass> = listOf()

    override fun onCreate() {
     getListBuget()
    }

    private fun getListBuget(){
        CoroutineScope(Dispatchers.IO).launch{
            listBuget = database.bugetDao().readAllDataAsList()
        }
    }

    override fun onDataSetChanged() {
        CoroutineScope(Dispatchers.IO).launch{
            listBuget = database.bugetDao().readAllDataAsList()
        }
    }

    override fun onDestroy() {

    }

    override fun getCount(): Int {
        return listBuget.size
    }

    override fun getViewAt(p0: Int): RemoteViews {
        val buget = listBuget[p0]
        val views = RemoteViews(context.packageName, R.layout.widget_history_item)

        views.setTextViewText(R.id.tvLunaW, "Luna: " + buget.luna)
        views.setTextViewText(R.id.tvVenituriW,"Total Venituri: " + buget.TotalVenituri.toString() + " lei")
        views.setTextViewText(R.id.tvCheltuieliW, "Total Cheltuieli: " + buget.TotalCheltuieli.toString() + " lei")
        views.setTextViewText(R.id.tvEconomiiW, "Economii: " + buget.Economii.toString() + " lei")

        return views
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun hasStableIds(): Boolean {
        return true
    }
}