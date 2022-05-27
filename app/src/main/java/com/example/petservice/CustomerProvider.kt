package com.example.petservice

import MyHelper
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri

class CustomerProvider: ContentProvider() {
    companion object{

        val PROVIDER_NAME = "com.example.petservice.CustomerProvider"
        val URL = "content://$PROVIDER_NAME/CUSTOMER"
        val CONTENT_URI = Uri.parse(URL)

        val _Id="_id"
        val Name = "name"
        val Phone ="phone"
        val Email= "email"
        val Date = "date"

        lateinit var db:SQLiteDatabase
    }

    override fun onCreate(): Boolean {
        var helper=MyHelper(context)
        db=helper.writableDatabase
        return if(db==null) false else true
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        order: String?
    ): Cursor? {
        return db.query("CUSTOMER",cols,condition,condition_val,null,null,order)
    }

    override fun getType(p0: Uri): String? {
       return "vnd.android.cursor.dir/vnd.example.CUSTOMER"
    }

    override fun insert(uri: Uri, cv: ContentValues?): Uri? {
        db.insert("CUSTOMER",null,cv)
        context?.contentResolver?.notifyChange(uri,null)
        return uri
    }

    override fun delete(uri: Uri, condition: String?, condition_val: Array<out String>?): Int {
        var count = db.delete("CUSTOMER",condition,condition_val)
        context?.contentResolver?.notifyChange(uri,null)
        return count
    }

    override fun update(uri: Uri, cv: ContentValues?, condition: String?, condition_value: Array<out String>?): Int {
        var count = db.update("CUSTOMER",cv,condition,condition_value)
        context?.contentResolver?.notifyChange(uri,null)
        return count

    }
}

/*
* Helper class
* */
