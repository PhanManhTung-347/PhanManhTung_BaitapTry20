package com.example.lamquen_2415053122347

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lamquen_2415053122347.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var ten347 = ""
    private var soLuong347=0
    fun ktradulieu(): Boolean{
        if (ten347.isBlank()){
            binding.ten.error ="Vui long nhap ten hop le!"
            return false
        }
        if (soLuong347 == null || soLuong347 <=0)
        {
            binding.soLuong.error == "Vui long nhap so luong hop le!"
            return false
        }
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Phan Mạnh Tùng 2415053122347"
        binding.btnKiemTra.setOnClickListener {
            ten347= binding.ten.text.toString().trim()
            soLuong347 = binding.soLuong.text.trim().toString().toIntOrNull()?:-1
            if (ktradulieu()== false){
                Toast.makeText(this, "Vui logn kiem tra du lieu hop le", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Du lieu dung", Toast.LENGTH_SHORT).show()
                binding.tvKetQua.text = "Kết quả: ten-${ten347} || so luong-${soLuong347}"
            }
        }
        binding.btnXoa.setOnClickListener {
                binding.ten.error = null
                binding.soLuong.error = null
            binding.ten.setText("")
            binding.soLuong.setText("")
        }

    }
}