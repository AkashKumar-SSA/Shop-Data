package com.example.shopdata

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

    private var shopDataList = arrayListOf<ShopDetails>()

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

        binding.shopRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.shopRecyclerView.setHasFixedSize(true) // Use setHasFixedSize
        getItemData()

    }

    private fun getItemData() {
        val db = FirebaseDatabase.getInstance().getReference("shopDetails")
        db.addValueEventListener(object : ValueEventListener{
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


//        val shopDetails: ArrayList<ShopDetails> = ArrayList()
//        shopDetails.add(ShopDetails(R.drawable.shop2,"Shop 2","5.5 km away","Owner two","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop3,"Shop 3","5.5 km away","Owner three","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop4,"Shop 4","5.5 km away","Owner four","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop5,"Shop 5","5.5 km away","Owner five","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop6,"Shop 6","5.5 km away","Owner six","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop7,"Shop 7","5.5 km away","Owner seven","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop8,"Shop 8","5.5 km away","Owner eight","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop9,"Shop 9","5.5 km away","Owner nine","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop10,"Shop 10","5.5 km away","Owner ten","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop11,"Shop 11","5.5 km away","Owner eleven","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop12,"Shop 12","5.5 km away","Owner twelve","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop13,"Shop 13","5.5 km away","Owner thirteen","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop14,"Shop 14","5.5 km away","Owner fourteen","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop15,"Shop 15","5.5 km away","Owner fifteen","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop16,"Shop 16","5.5 km away","Owner sixteen","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop17,"Shop 17","5.5 km away","Owner seventeen","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop18,"Shop 18","5.5 km away","Owner eighteen","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop19,"Shop 19","5.5 km away","Owner nineteen","Delhi","2.5"))
//        shopDetails.add(ShopDetails(R.drawable.shop20,"Shop 20","5.5 km away","Owner twenty","Delhi","2.5"))
//        databaseReference = FirebaseDatabase.getInstance().getReference("shopDetails")
//        for (i in shopDetails.indices){
//            databaseReference.child(shopDetails[i].shopName).setValue(shopDetails[i])
//        }


//        binding.shopRecyclerView.layoutManager = GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false)
//        binding.shopRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
//        binding.shopRecyclerView.adapter = ShopAdapter(shopDetails)

//        binding.shopRecyclerView.adapter = ShopAdapter(shopDataList)


