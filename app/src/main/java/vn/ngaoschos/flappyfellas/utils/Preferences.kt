package vn.ngaoschos.flappyfellas.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Preferences(context : Context) {
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences("fellas_ngaos",Context.MODE_PRIVATE)

    fun saveDataLong( key : String , data : Long){
        sharedPreferences.edit { putLong(key, data) }
    }

    fun getDataLong(key : String) : Long{
       return sharedPreferences.getLong(key,0)
    }

}