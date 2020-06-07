package com.shovon.roomdatabase.ui

import android.content.Context
import android.widget.Toast

/**
 * Created by Itz Shovon on 4/16/2020
 */
fun Context.toast(msg:String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
