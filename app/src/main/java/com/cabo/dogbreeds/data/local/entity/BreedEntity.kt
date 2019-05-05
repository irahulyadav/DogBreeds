package com.cabo.dogbreeds.data.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity

@Entity(primaryKeys = ["breed"])
data class BreedEntity(val breed: String) : Parcelable {

    var image: String? = null
//    @ColumnInfo(name = "subBreed")
//    var subBreed: List<String> = java.util.ArrayList()

    constructor(parcel: Parcel) : this(parcel.readString() ?: "") {
        image = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(breed)
        parcel.writeString(image)
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