package com.cabo.dogbreeds.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.cabo.dogbreeds.data.local.entity.BreedEntity

@Dao
interface BreedDao : BreedProtocol {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertBreedEntity(list: List<BreedEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insertBreedEntity(entity: BreedEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    override fun updateBreedEntity(entity: BreedEntity)

    @Query("DELETE FROM BreedEntity")
    override fun flushBreedData()

    @Query("SELECT count(*) FROM BreedEntity")
    override fun getBreedCount(): Int

    @Query("SELECT * FROM BreedEntity where breed = :breed")
    override fun getBreedEntityByBreed(breed: String): LiveData<BreedEntity>?


    @Query("SELECT * FROM BreedEntity")
    override fun getAllPaged(): DataSource.Factory<Int, BreedEntity>

//    @Query("SELECT * FROM BreedEntity where id = :id")
//    fun getBreedEntityDetailById(id: Long?): Flowable<BreedEntity>

}

interface BreedProtocol {

    fun insertBreedEntity(list: List<BreedEntity>)

    fun insertBreedEntity(entity: BreedEntity)

    fun updateBreedEntity(entity: BreedEntity)

    fun flushBreedData()

    fun getBreedCount(): Int

    fun getBreedEntityByBreed(breed: String): LiveData<BreedEntity>?

    fun getAllPaged(): DataSource.Factory<Int, BreedEntity>
}