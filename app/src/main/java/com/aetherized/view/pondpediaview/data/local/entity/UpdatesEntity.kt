package com.aetherized.view.pondpediaview.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "updates")
data class UpdatesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    
    @ColumnInfo(name = "pond_id")
    val pondId: Int,

    @ColumnInfo(name = "updates_category")
    val updatesCategory: String,

    @ColumnInfo(name = "updates_name")
    val updatesName: String,

    @ColumnInfo(name = "updates_content")
    val updatesContent: String? = null,

    @ColumnInfo(name = "update_details")
    val updatesDetails: String? = null,

    @ColumnInfo(name = "timestamp")
    val timestamp: String
) : Parcelable
