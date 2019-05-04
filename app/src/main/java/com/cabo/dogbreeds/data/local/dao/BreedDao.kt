package com.cabo.dogbreeds.data.local.dao

import androidx.room.*
import com.cabo.dogbreeds.data.local.entity.BreedEntity

@Dao
interface BreedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreedEntity(movies: List<BreedEntity>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreedEntity(movie: BreedEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBreedEntity(movie: BreedEntity): Int

    @Query("SELECT * FROM BreedEntity where id = :id")
    fun getBreedEntityById(id: Long): BreedEntity?

    @Query("SELECT * FROM BreedEntity where breed = :breed")
    fun getBreedEntityByBreed(breed: String): BreedEntity?

    @Query("SELECT * FROM BreedEntity")
    fun getDogInfoList(): List<BreedEntity>

//    @Query("SELECT * FROM BreedEntity where id = :id")
//    fun getBreedEntityDetailById(id: Long?): Flowable<BreedEntity>

}