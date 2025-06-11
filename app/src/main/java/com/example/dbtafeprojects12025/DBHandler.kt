package com.example.dbtafeprojects12025

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(
    context: Context?,
    factory: SQLiteDatabase.CursorFactory?,
) : SQLiteOpenHelper(context, DATABASENAME, factory, DBVERSION) {

    // declare table and fields
        private val tableName = "Person"
        private val keyID = "ID"
        private val keyName = "NAME"
        private val keyMobile = "MOBILE"
        private val keyEmail = "EMAIL"
        private val keyAddress = "ADDRESS"
        private val keyImageFile = "IMAGEFILE"

    companion object{
        const val DATABASENAME="HRManagement.db"
        const val DBVERSION=1
    }
    override fun onCreate(db: SQLiteDatabase) {
        // create sql statement
        val createTable = "CREATE TABLE $tableName ($keyID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$keyName TEXT, $keyImageFile TEXT, $keyAddress TEXT, $keyMobile TEXT, $keyEmail TEXT)"
        // execute statement
        db.execSQL(createTable)
        // add one sample record using contentValue object
        val cv = ContentValues()
        cv.put(keyName,"Damus Bryant")
        cv.put(keyMobile,"0401234567")
        cv.put(keyAddress,"Sydney")
        cv.put(keyImageFile,"first")
        cv.put(keyEmail,"damus.bryant@gmail.com")
        db.insert(tableName, null,cv)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $tableName")
        onCreate(db)
    }
    // get all the records from Database
    fun getAllPersons():ArrayList<Person>{
        // declare an arraylist
        val personList = ArrayList<Person>()
        // create sql qu
        val selectQuery = "SELECT * FROM $tableName"
        // get readable database
        val db = this.readableDatabase
        // run
        val cursor = db.rawQuery(selectQuery,null)
        // move
        if(cursor.moveToFirst()){
            // loop
            do {
                val person = Person()
                person.id = cursor.getInt(0)
                person.name = cursor.getString(1)
                person.imageFile = cursor.getString(2)
                person.address = cursor.getString(3)
                person.mobile = cursor.getString(4)
                person.email = cursor.getString(5)
                personList.add(person)
            } while (cursor.moveToNext())
        }
            // close cursor and db
        cursor.close()
        db.close()
        //return arrayList
        return personList
    }

    fun getEmployee(id: Int): Person {
        //
        val db =this.readableDatabase
        //
        val employee = Person()
        //
        val cursor = db.query(tableName,
            arrayOf(keyID,keyName,keyImageFile,keyAddress,keyMobile,keyEmail),
            "$keyID=?",
            arrayOf(id.toString()),
            null,
            null,
            null)
        if(cursor!=null){
            cursor.moveToFirst()
            employee.id = cursor.getInt(0)
            employee.name = cursor.getString(1)
            employee.imageFile = cursor.getString(2)
            employee.address = cursor.getString(3)
            employee.mobile = cursor.getString(4)
            employee.email = cursor.getString(5)

        }
        cursor.close()
        db.close()
        return employee

    }

    fun updateEmployee(employee: Person) {
        //
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(keyName,employee.name)
        cv.put(keyAddress,employee.address)
        cv.put(keyEmail,employee.email)
        cv.put(keyMobile,employee.mobile)
        cv.put(keyImageFile,employee.imageFile)
        db.update(tableName,cv,"$keyID=?", arrayOf(employee.id.toString()))
        //
        db.close()

    }

    fun addEmpolyee(employee: Person) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(keyName,employee.name)
        cv.put(keyAddress,employee.address)
        cv.put(keyEmail,employee.email)
        cv.put(keyMobile,employee.mobile)
        cv.put(keyImageFile,employee.imageFile)
        db.update(tableName,cv,"$keyID=?", arrayOf(employee.id.toString()))
        //
        db.insert(tableName,null,cv)
        db.close()

    }

    fun deleteEmployee(id: Int) {
        //
        val db = this.writableDatabase
        db.delete(tableName,"$keyID=?", arrayOf(id.toString()))
        //
        db.close()
    }
}