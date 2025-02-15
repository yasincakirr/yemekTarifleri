package com.example.yemektarifleri.data.Sql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.yemektarifleri.data.Entity.Yemekler

class YemeklerDatabaseHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object{
        private const val DATABASE_NAME = "notes.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "yemekler"
        private const val COLUMN_ID = "id"
        private const val COLUMN_YEMEKAD = "yemekad"
        private const val COLUMN_YEMEKICERIK = "yemekicerik"
        private const val COLUMN_YEMEKYAPILIS = "yemekyapilis"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID  INTEGER PRIMARY KEY, $COLUMN_YEMEKAD TEXT, $COLUMN_YEMEKICERIK TEXT, $COLUMN_YEMEKYAPILIS TEXT ) "

        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val tabloyuSil = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(tabloyuSil)
        onCreate(db)
    }


    fun insertyemek(yemek : Yemekler){

        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_YEMEKAD, yemek.yemekad)
            put(COLUMN_YEMEKICERIK, yemek.yemekicerik)
            put(COLUMN_YEMEKYAPILIS, yemek.yemekyapilis)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }




    fun yemekleriListele():List<Yemekler>{
        val yemekList = mutableListOf<Yemekler>()
        val db =readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val yemekAd = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_YEMEKAD))
            val yemekicerik = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_YEMEKICERIK))
            val yemekyapilis = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_YEMEKYAPILIS))

            val yemek = Yemekler(id, yemekAd, yemekicerik, yemekyapilis)

            yemekList.add(yemek)
        }
        cursor.close()
        db.close()
        return yemekList

    }


    fun yemekleriGuncelle(yemek : Yemekler){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_YEMEKAD, yemek.yemekad)
            put(COLUMN_YEMEKICERIK, yemek.yemekicerik)
            put(COLUMN_YEMEKYAPILIS, yemek.yemekyapilis)

        }

        val whereClouse = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(yemek.id.toString())

        db.update(TABLE_NAME, values, whereClouse, whereArgs)
        db.close()

    }






    fun yemekSil(yemekid: Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(yemekid.toString())

        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }


}