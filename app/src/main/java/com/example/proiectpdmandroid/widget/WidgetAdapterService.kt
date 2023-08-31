package com.example.proiectpdmandroid.widget

import android.content.Intent
import android.widget.RemoteViewsService

class WidgetAdapterService : RemoteViewsService(){
    override fun onGetViewFactory(p0: Intent?): RemoteViewsFactory {
        return WidgetAdapter(applicationContext)
    }

}