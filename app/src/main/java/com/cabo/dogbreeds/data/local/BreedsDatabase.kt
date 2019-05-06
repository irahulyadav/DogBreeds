package com.cabo.dogbreeds.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cabo.dogbreeds.data.local.dao.BreedDao
import com.cabo.dogbreeds.data.local.entity.BreedEntity
import com.cabo.dogbreeds.repository.BreedRepository

@Database(entities = [BreedEntity::class], version = 1, exportSchema = false)
abstract class BreedsDatabase : RoomDatabase() {

    abstract fun breedDao(): BreedDao

    companion object {
        var TEST_MODE = false
        private var INSTANCE: BreedsDatabase? = null

        fun getInstance(context: Context): BreedsDatabase? {
            if (INSTANCE == null) {
                synchronized(BreedsDatabase::class) {

                    INSTANCE =
                        if (TEST_MODE) Room
                            .inMemoryDatabaseBuilder(context, BreedsDatabase::class.java)
                            .allowMainThreadQueries()
                            .build()
                        else
                            Room
                                .databaseBuilder(
                                    context.getApplicationContext(),
                                    BreedsDatabase::class.java,
                                    "BreedDatabase.db"
                                ).addCallback(RoomCallBack)
                                .build()

                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private fun close() {
            INSTANCE?.close()
        }

        private val RoomCallBack = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                BreedRepository.Companion.DaoTask<Unit>(action = {
                    val breedDao = INSTANCE?.breedDao()
                    if (breedDao != null) {
                        // affenpinscher=[], african=[], airedale=[]
//                        breedDao.insertBreedEntity(BreedEntity("affenpinscher"))
//                        breedDao.insertBreedEntity(BreedEntity("african"))
//                        breedDao.insertBreedEntity(BreedEntity("airedale"))
                    }
                }).execute()
            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
            }
        }

    }
}