package com.example.myapplication

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface memoDAO {
    @Insert(onConflict = REPLACE)
    fun insert(memo:MemoEntity)

    @Query("select * from TB_memo")
    fun getAll() : List<MemoEntity>

    @Delete
    fun delete(memo:MemoEntity)

    @Update
    fun update(memo: MemoEntity)

}