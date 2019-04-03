package com.lazylee.lzywanandroid.data.entity

import android.os.Parcel
import android.os.Parcelable

class ProjectCategory protected constructor(`in`: Parcel) : Parcelable {

    var id: Int = 0
    var courseId: Int = 0
    var name: String? = null
    var order: Int = 0
    var parentChapterId: Int = 0
    var visible: Int = 0

    init {
        id = `in`.readInt()
        courseId = `in`.readInt()
        name = `in`.readString()
        order = `in`.readInt()
        parentChapterId = `in`.readInt()
        visible = `in`.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeInt(id)
        parcel.writeInt(courseId)
        parcel.writeString(name)
        parcel.writeInt(order)
        parcel.writeInt(parentChapterId)
        parcel.writeInt(visible)
    }

    override fun toString(): String {
        return "ProjectCategory{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", name='" + name + '\''.toString() +
                ", order=" + order +
                ", parentChapterId=" + parentChapterId +
                ", visible=" + visible +
                '}'.toString()
    }

    companion object {

        val CREATOR: Parcelable.Creator<ProjectCategory> = object : Parcelable.Creator<ProjectCategory> {
            override fun createFromParcel(`in`: Parcel): ProjectCategory {
                return ProjectCategory(`in`)
            }

            override fun newArray(size: Int): Array<ProjectCategory> {
                return arrayOfNulls(size)
            }
        }
    }
}
