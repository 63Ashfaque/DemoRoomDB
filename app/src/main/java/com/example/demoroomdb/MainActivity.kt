package com.example.demoroomdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demoroomdb.adaptor.MyAdapter
import com.example.demoroomdb.databinding.ActivityMainBinding
import com.example.demoroomdb.model.UserItem
import com.example.demoroomdb.roomdb.Contact
import com.example.demoroomdb.roomdb.ContactDataBase
import kotlinx.coroutines.*
import java.util.*

class MainActivity : AppCompatActivity() {

    //declare dataBase var
    lateinit var dataBase: ContactDataBase
    lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //initialize dataBase var
        dataBase = ContactDataBase.getDataBase(this)

        mBinding.btnSave.setOnClickListener {
            val name = mBinding.edName.text.toString()

            if (name.isNotEmpty()) {
                val phone = mBinding.edPhone.text.toString()
                if (phone.isNotEmpty()) {
                    if (phone.length == 10) {
                        mBinding.textLayoutPhone.error = null
                        insertUser(name, phone)
                    } else {
                        mBinding.textLayoutPhone.error = "Please Enter Valid Phone No"
                        mBinding.textLayoutPhone.requestFocus()

                    }

                } else {
                    mBinding.textLayoutPhone.error = "Please Enter Phone No"
                    mBinding.textLayoutPhone.requestFocus()
                    mBinding.textLayoutName.error = null
                }
            } else {
                mBinding.textLayoutName.error = "Please Enter Name"
                mBinding.textLayoutName.requestFocus()
            }
        }


        val items = listOf(UserItem("name",  "dfd"))
        val adapter = MyAdapter(items)
        mBinding.recyclerView.adapter = adapter
    }

    private fun insertUser(name: String, phone: String) {
        GlobalScope.launch {
            val insert = dataBase.contactDao().insertData(Contact(0, name, phone, Date()))
            showToast(insert)
        }
    }

    private suspend fun showToast(insert: Long) {
        val dispatcher: CoroutineDispatcher = Dispatchers.Main
        if (insert > 0) {
            withContext(dispatcher) {
                Utils().showToast(applicationContext, "Insert One Record Successful")
            }
        } else {
            withContext(dispatcher) {
                Utils().showToast(applicationContext, "Already present")
            }
        }
    }
}