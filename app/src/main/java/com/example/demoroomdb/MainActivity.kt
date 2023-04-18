package com.example.demoroomdb

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demoroomdb.adaptor.MyAdapter
import com.example.demoroomdb.databinding.ActivityMainBinding
import com.example.demoroomdb.roomdb.Contact
import com.example.demoroomdb.roomdb.ContactDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class MainActivity : AppCompatActivity(), MyAdapter.MyClickListener {

    //declare dataBase var
    private lateinit var dataBase: ContactDataBase
    private lateinit var mBinding: ActivityMainBinding
    private lateinit var item: Contact
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
                        //empty the name and phone
                        mBinding.edName.text = null
                        mBinding.edPhone.text = null
                        mBinding.textLayoutName.requestFocus()
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
        mBinding.btnUpdate.setOnClickListener {
            val name = mBinding.edName.text.toString()

            if (name.isNotEmpty()) {
                val phone = mBinding.edPhone.text.toString()
                if (phone.isNotEmpty()) {
                    if (phone.length == 10) {
                        mBinding.textLayoutPhone.error = null
                        //empty the name and phone
                        mBinding.edName.text = null
                        mBinding.edPhone.text = null
                        mBinding.textLayoutName.requestFocus()
                        updateUser(name, phone)
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
        showRecyclerView();
    }

    private fun showRecyclerView() {

        dataBase.contactDao().getContactDesc().observe(this) {
            val adapter = MyAdapter(it, this)
            mBinding.recyclerView.adapter = adapter
        }

    }

    private fun insertUser(name: String, phone: String) {
        //BackGround Thread running
        GlobalScope.launch {
            val insert = dataBase.contactDao().insertData(Contact(0, name, phone, Date()))

            if (insert > 0) {
                //when we run background that time we use withContext(dispatcher)
                withContext(Dispatchers.Main) {
                    Utils().showToast(applicationContext, "Insert One Record Successful")
                }
            } else {
                withContext(Dispatchers.Main) {
                    Utils().showToast(applicationContext, "Already present")
                }
            }
        }
    }

    private fun updateUser(name: String, phone: String) {

        //BackGround Thread running
        GlobalScope.launch {
            val update =
                dataBase.contactDao().updateData(Contact(item.id, name, phone, item.createdDate))

            if (update > 0) {
                //when we run background that time we use withContext(dispatcher)
                withContext(Dispatchers.Main) {
                    Utils().showToast(applicationContext, "One Record Update Successful")
                    mBinding.btnUpdate.visibility = View.GONE
                    mBinding.btnSave.visibility = View.VISIBLE
                }
            } else {
                withContext(Dispatchers.Main) {
                    Utils().showToast(applicationContext, "Not Update")
                }
            }
        }

    }


    override fun onUpdateItemClick(item: Contact) {
        mBinding.btnUpdate.visibility = View.VISIBLE
        mBinding.btnSave.visibility = View.GONE

        this.item = item
        //set the value on editTextView
        mBinding.edName.setText(item.name)
        mBinding.edPhone.setText(item.phone)

    }

    override fun onDeleteItemClick(item: Contact) {
        GlobalScope.launch {
            val delete = dataBase.contactDao().deleteData(item)
            if (delete > 0) {
                //when we run background that time we use withContext(dispatcher)
                withContext(Dispatchers.Main) {
                    Utils().showToast(applicationContext, "One Record Deleted")
                }
            } else {
                withContext(Dispatchers.Main) {
                    Utils().showToast(applicationContext, "Not present")
                }
            }
        }

    }


}