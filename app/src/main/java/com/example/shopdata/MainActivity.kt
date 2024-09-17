package com.example.shopdata

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopdata.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    private lateinit  var shopDataList: ArrayList<ShopDetails>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.shopRegistrationFloatingButton.setOnClickListener{
            startActivity(Intent(this,ShopRegistration::class.java))
        }
//        shopDataList = arrayListOf<ShopDetails>()

        shopDataList = ArrayList()
        binding.shopRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.shopRecyclerView.setHasFixedSize(true) // Use setHasFixedSize
        getItemData()

    }

    private fun getItemData() {
        databaseReference = FirebaseDatabase.getInstance().getReference("shopDetails")
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (itemSnap in snapshot.children){
                        val item = itemSnap.getValue(ShopDetails::class.java)
                        shopDataList.add(item!!)
                    }
                    binding.shopRecyclerView.adapter = ShopAdapter(shopDataList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}





