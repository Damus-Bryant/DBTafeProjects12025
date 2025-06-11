package com.example.dbtafeprojects12025

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomerAdapter(context: Context,value:Int,arrayList:ArrayList<Person>)
    :ArrayAdapter<Person>(context,0,arrayList) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val convertView =
                LayoutInflater.from(context).inflate(R.layout.row_item, parent,false)
            val tvName = convertView.findViewById<TextView>(R.id.tvName)
            val tvAddress = convertView.findViewById<TextView>(R.id.tvAddress)
            val tvMobile = convertView.findViewById<TextView>(R.id.tvMobile)
            val tvEmail = convertView.findViewById<TextView>(R.id.tvEmail)
            val ivImageFile = convertView.findViewById<ImageView>(R.id.ivImageFile)

            // assign values from arraylist
            tvName.text = getItem(position)?.name
            tvAddress.text = getItem(position)?.address
            tvMobile.text = getItem(position)?.mobile
            tvEmail.text = getItem(position)?.email

            ivImageFile.setImageResource(context.resources.
            getIdentifier(getItem(position)?.imageFile,
                "drawable","com.example.dbtafeprojects12025"))
        return convertView
            //return super.getView(position, convertView, parent)
        }
}