import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyHelper (context: Context?): SQLiteOpenHelper(context,"CUSTOMER",null,1){
    override fun onCreate(dp: SQLiteDatabase?) {
        dp?.execSQL("CREATE TABLE CUSTOMER (_Id INTEGER PRIMARY KEY,Name TEXT,Phone INTEGER,Email TEXT )")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}