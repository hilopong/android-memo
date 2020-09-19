package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_memo")
data class MemoEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long?
    ,var memo:String)