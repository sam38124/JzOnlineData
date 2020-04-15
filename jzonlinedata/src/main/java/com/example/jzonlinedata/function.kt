package com.example.jzfixlanguage

import android.app.Dialog
import android.util.Log
import android.view.View
import android.view.ViewGroup

class function{
    companion object{
        fun getAllChildViews(view: View): List<View> {
            val allchildren = ArrayList<View>()
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    val viewchild = view.getChildAt(i)
                    allchildren.add(viewchild)
                    allchildren.addAll(getAllChildViews(viewchild))
                }
            }
            return allchildren
        }
    }

}