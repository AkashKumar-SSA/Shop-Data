package com.example.shopdata

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.shopdata.databinding.ActivityShopRegistrationBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ShopRegistration : AppCompatActivity() {
    private lateinit var binding: ActivityShopRegistrationBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityShopRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("shopDetails")

        binding.shopDataInputButton.setOnClickListener {
            val shopNameInput: String = binding.shopNameInput.text.toString()
            val shopOwnerName: String = binding.shopOwnerNameInput.text.toString()
            val shopDistance: String = binding.shopDistanceInput.text.toString()
            val shopAddress: String = binding.shopAddressInput.text.toString()
            val shopRating: String = binding.shopRatingInput.text.toString()

            if (shopNameInput.isNotEmpty() && shopOwnerName.isNotEmpty() && shopDistance.isNotEmpty() &&
                shopAddress.isNotEmpty() && shopRating.isNotEmpty()){
                val shopUploadDataList: ArrayList<ShopDetails> = ArrayList()
                shopUploadDataList.add(ShopDetails(R.drawable.shop1,shopNameInput,shopDistance,shopOwnerName,shopAddress,shopRating))
                databaseReference.child(shopUploadDataList[0].shopName).setValue(shopUploadDataList[0])
                    .addOnSuccessListener {
                        Toast.makeText(this,"Uploaded Successfully",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,MainActivity::class.java))

                    }
                    .addOnFailureListener{
                        Toast.makeText(this,"Failed to save",Toast.LENGTH_SHORT).show()
                    }

            }else
                Toast.makeText(this,"Fields can not be Empty",Toast.LENGTH_SHORT).show()

        }
    }
}