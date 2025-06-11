package com.example.dbtafeprojects12025

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import com.example.dbtafeprojects12025.databinding.AddeditBinding

class AddEdit: Activity(), View.OnClickListener {
    private lateinit var binding: AddeditBinding
    //
    val dbh = DBHandler(this,null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddeditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //
        binding.btnSave.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
        //
        val extras = intent.extras
        if(extras!=null){
            val id:Int = extras.getInt("ID")
            //
            val employee = dbh.getEmployee(id)
            //
            binding.etID.setText(employee.id.toString())
            binding.etName.setText(employee.name.toString())
            binding.etAddress.setText(employee.address.toString())
            binding.etMobile.setText(employee.mobile.toString())
            binding.etEmail.setText(employee.email.toString())
            binding.ivImageFile.setImageResource(this.resources.getIdentifier(
                employee.imageFile, "drawable", "com.example.dbtafeprojects12025"
            ))
        }else{
            binding.etID.setText("")
            binding.etName.setText("")
            binding.etAddress.setText("")
            binding.etMobile.setText("")
            binding.etEmail.setText("")
            binding.ivImageFile.setImageResource(this.resources.getIdentifier(
                "", "drawable", "com.example.dbtafeprojects12025"
            ))
        }
    }

    override fun onClick(btn: View) {
        when(btn.id){
            R.id.btnSave->{
                val cid:Int = binding.etID.text.toString().toIntOrNull()?:0
                if(cid==0){
                    addEmployee()
                }else{
                    editEmployee(cid)
                }
            }
            R.id.btnCancel->{
                goBack()
            }
        }
    }

    private fun goBack() {
        //
        val mainact = Intent(this,MainActivity::class.java)
        startActivity(mainact)
    }

    private fun editEmployee(cid: Int) {
        //
        val employee = Person()
        employee.id = binding.etID.text.toString().toInt()
        employee.name=binding.etName.text.toString()
        employee.address=binding.etAddress.text.toString()
        employee.mobile=binding.etMobile.text.toString()
        employee.email=binding.etEmail.text.toString()
        employee.imageFile=binding.etImageFile.text.toString()
        //
        dbh.updateEmployee(employee)
        //
        Toast.makeText(this,"Employee details updated", Toast.LENGTH_LONG).show()
        goBack()
    }

    private fun addEmployee() {
        //
        val employee = Person()
        employee.name = binding.etName.text.toString()
        employee.address = binding.etAddress.text.toString()
        employee.mobile = binding.etMobile.text.toString()
        employee.email = binding.etEmail.text.toString()
        employee.imageFile = binding.etImageFile.text.toString()
        dbh.addEmpolyee(employee)
        Toast.makeText(this,"Employee has been added",Toast.LENGTH_LONG).show()
        goBack()
    }
}