package com.kunal.movie.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * Local storage which can be used to store key value pair.
 * Recommended For storing the user preferences
 */
class DatastoreManager(
    private val context: Context
) {

    companion object {
        private const val USER_STORE_NAME = "user_data_store"
    }

    private val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(name = USER_STORE_NAME)

}