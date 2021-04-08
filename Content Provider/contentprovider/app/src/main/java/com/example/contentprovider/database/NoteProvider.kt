package com.example.contentprovider.database

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.provider.BaseColumns._ID
import com.example.contentprovider.database.NotesDataBaseHelper.Companion.TABLE_NOTES
import java.lang.Exception


class NoteProvider : ContentProvider() {

    private  lateinit var mUriMatcher: UriMatcher
    private  lateinit var dbHelper: NotesDataBaseHelper

    override fun onCreate(): Boolean {
        mUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        mUriMatcher.addURI(AUTHORITY, "notes", NOTES)
        mUriMatcher.addURI(AUTHORITY, "notes/#", NOTES_BY_ID)
        if(context != null){dbHelper = NotesDataBaseHelper(context as Context) }
        return true
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
       if(mUriMatcher.match(uri) == NOTES_BY_ID){
           val db: SQLiteDatabase = dbHelper.writableDatabase
           val linesAffect:Int = db.delete(TABLE_NOTES, "$_ID", arrayOf(uri.lastPathSegment))
           db.close()
           context?.contentResolver?.notifyChange(uri,null)
           return linesAffect
       } else{
            throw Exception("Invalid exclusion Uri")
       }
    }

    override fun getType(uri: Uri): String? = throw Exception("Invalid implemented Uri")

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        if(mUriMatcher.match(uri) == NOTES){
            val db: SQLiteDatabase = dbHelper.writableDatabase
            val id = db.insert(TABLE_NOTES, null, values)
            val insertUri:Uri = Uri.withAppendedPath(BASE_URI, id.toString())
            db.close()
            context?.contentResolver?.notifyChange(uri,null)
            return insertUri
        } else{
            throw Exception("Invalid insertion Uri")
        }
    }
    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return when{
            mUriMatcher.match(uri) == NOTES -> {
                val db:SQLiteDatabase = dbHelper.writableDatabase
                val cursor:Cursor = db.query(TABLE_NOTES,projection,selection,selectionArgs,null,null,sortOrder)
                cursor.setNotificationUri(context?.contentResolver, uri)
                cursor
            }
            mUriMatcher.match(uri) == NOTES_BY_ID -> {
                val db: SQLiteDatabase = dbHelper.writableDatabase
                val cursor:Cursor = db.query(TABLE_NOTES,projection,"$_ID = ?", arrayOf((uri.lastPathSegment)),null,null,sortOrder)
                cursor.setNotificationUri((context as Context).contentResolver,uri)
                cursor
            }
            else -> throw Exception("Not implemented Uri")
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
       if(mUriMatcher.match(uri) == NOTES_BY_ID) {
        val db:SQLiteDatabase = dbHelper.writableDatabase
        val lineAffect = db.update(TABLE_NOTES,values,"$_ID = ?", arrayOf(uri.lastPathSegment))
        db.close()
        context?.contentResolver?.notifyChange(uri,null)
        return lineAffect
       }else {
           throw Exception("Not implemented Uri")
       }
    }
    companion object{
        const val AUTHORITY = "com.example.contentprovider.provider"
        val BASE_URI: Uri = Uri.parse("contend://$AUTHORITY")
        val URI_NOTES: Uri = Uri.withAppendedPath(BASE_URI, "notes")

        const val NOTES = 1
        const val NOTES_BY_ID = 2
    }
}