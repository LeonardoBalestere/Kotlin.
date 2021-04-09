package com.example.contentprovidermain.database

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.media.UnsupportedSchemeException
import android.net.Uri
import android.os.Build
import android.provider.BaseColumns._ID
import androidx.annotation.RequiresApi
import com.example.contentprovidermain.database.NotesDataBaseHelper.Companion.TABLE_NOTES

class NotesProvider : ContentProvider() {

    lateinit var mUriMatcher: UriMatcher
    lateinit var dbHelper: NotesDataBaseHelper

    override fun onCreate(): Boolean {
        mUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        mUriMatcher.addURI(AUTHORITY, "notes", NOTES)
        mUriMatcher.addURI(AUTHORITY,"notes/#", NOTES_BY_ID)
        if(context != null){
            dbHelper = NotesDataBaseHelper(context as Context)
        }
        return true
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        if(mUriMatcher.match(uri) == NOTES_BY_ID) {
            val db: SQLiteDatabase = dbHelper.writableDatabase
            val linesAffected: Int = db.delete(TABLE_NOTES, "$_ID =?",arrayOf(uri.lastPathSegment))
            db.close()
            context?.contentResolver?.notifyChange(uri,null)
            return linesAffected
        } else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                throw UnsupportedSchemeException("Not possible to delete URI")
            } else {
                throw Exception("Not possible to delete Uri")}
        }
    }

    override fun getType(uri: Uri): String? = "Not implemented"

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        if (mUriMatcher.match(uri) == NOTES){
            val db:SQLiteDatabase = dbHelper.writableDatabase
            val id = db.insert(TABLE_NOTES, null, values)
            val insertUri = Uri.withAppendedPath(BASE_URI, id.toString())
            db.close()
            context?.contentResolver?.notifyChange(uri,null)
            return insertUri
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                throw UnsupportedSchemeException("Not possible to insert URI")
            } else {
                throw Exception("Not possible to insert Uri")}
        }
    }


    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String? ): Cursor? {
        return when {
            mUriMatcher.match(uri) == NOTES -> {
                val db: SQLiteDatabase = dbHelper.writableDatabase
                val cursor:Cursor = db.query(TABLE_NOTES,projection,selection,selectionArgs,null,null,sortOrder)
                cursor.setNotificationUri(context?.contentResolver, uri)
                return cursor
            }
            mUriMatcher.match(uri) == NOTES_BY_ID -> {
                val db: SQLiteDatabase = dbHelper.writableDatabase
                val cursor = db.query(TABLE_NOTES, projection, "$_ID = ?",arrayOf(uri.lastPathSegment), null, null, sortOrder )
                cursor.setNotificationUri((context as Context).contentResolver, uri)
                cursor
            }
            else -> {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    throw UnsupportedSchemeException("Not implemented URI")
                } else {
                    throw Exception("Not implemented Uri")}
            }
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        if(mUriMatcher.match(uri) == NOTES_BY_ID){
            val db: SQLiteDatabase = dbHelper.writableDatabase
            val lineAffected: Int = db.update(TABLE_NOTES,values, "$_ID =?",arrayOf(uri.lastPathSegment))
            db.close()
            context?.contentResolver?.notifyChange(uri,null)
            return lineAffected
        }else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                throw UnsupportedSchemeException("Not possible to insert URI")
            } else {
                throw Exception("Not possible to insert Uri")
            }
        }
    }

    companion object{
        const val AUTHORITY = "com.example.contentprovidermain.provider"
        val BASE_URI = Uri.parse("content://$AUTHORITY")
        val URI_NOTES = Uri.withAppendedPath(BASE_URI,"notes")
        const val NOTES_BY_ID = 2
        const val NOTES = 1
    }
}