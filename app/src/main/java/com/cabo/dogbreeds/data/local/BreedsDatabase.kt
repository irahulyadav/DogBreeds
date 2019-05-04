package com.cabo.dogbreeds.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cabo.dogbreeds.data.local.dao.BreedDao
import com.cabo.dogbreeds.data.local.entity.BreedEntity

@Database(entities = [BreedEntity::class], version = 1, exportSchema = false)
abstract class BreedsDatabase : RoomDatabase() {

    abstract fun breedDao(): BreedDao

    companion object {
        private var INSTANCE: BreedsDatabase? = null

        fun getInstance(context: Context): BreedsDatabase? {
            if (INSTANCE == null) {
                synchronized(BreedsDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.getApplicationContext(),
                        BreedsDatabase::class.java, "BreedDatabase.db"
                    )
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}