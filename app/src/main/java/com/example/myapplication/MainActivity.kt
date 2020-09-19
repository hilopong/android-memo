package com.example.myapplication

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

@SuppressLint("StaticFieldLeak")
class MainActivity : AppCompatActivity(),OnDeleteListener {


    lateinit var db:MemoDatabase
    var memoList= listOf<MemoEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db= MemoDatabase.getInstance(this)!!

        btn_add.setOnClickListener{
            var memo=MemoEntity(null,editview.text.toString())
            editview.setText("")
            insert(memo)
        }

        recycleview.layoutManager=LinearLayoutManager(this)

        select()
    }

    fun select(){
        var selectTask=object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                memoList = db.memoDAO().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecycleview(memoList)
            }
        }

        selectTask.execute()
    }

    fun insert(memo : MemoEntity){

        var insertTask=
        object :AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                db.memoDAO().insert(memo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                select()
            }
        }

        insertTask.execute()


    }

    fun delete(memo: MemoEntity){
        var deleteTask=object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg p0: Unit?) {
                db.memoDAO().delete(memo)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)

                select()
            }
        }

        deleteTask.execute()
    }

    fun setRecycleview(memoList : List<MemoEntity>){
        recycleview.adapter=MyAdapter(this,memoList,this)
    }

    override fun OnDeleteListener(memo: MemoEntity){
        delete(memo)
    }
}