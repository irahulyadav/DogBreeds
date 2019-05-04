package com.cabo.dogbreeds.data.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["id"])
data class BreedEntity(@SerializedName("id") val id: Long, val breed: String) : Parcelable {

//    @ColumnInfo(name = "subBreed")
//    var subBreed: List<String> = java.util.ArrayList()

    constructor(parcel: Parcel) : this(parcel.readLong(), parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BreedEntity> {
        override fun createFromParcel(parcel: Parcel): BreedEntity {
            return BreedEntity(parcel)
        }

        override fun newArray(size: Int): Array<BreedEntity?> {
            return arrayOfNulls(size)
        }
    }

}