package com.example.lamquen_2415053122347

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lamquen_2415053122347.databinding.ActivityMainBinding

object Try20Logic {
    fun ghiNhan(daNhan: Int, soLuong: Int): Int? {
        return if (daNhan in 0..9 &&
            soLuong in 1..9 &&
            daNhan + soLuong <= 9
        ) {
            daNhan + soLuong
        } else {
            null
        }
    }
}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var Ten = ""
    private var soluong = 0
    private var tongDaNhan = 0

    fun ktradulieu(): Boolean {

        if (Ten.isBlank()) {
            binding.ten.error = "Vui lòng nhập tên hợp lệ"
            return false
        }

        if (soluong !in 1..9) {
            binding.soLuong.error = "Số lượng phải từ 1 đến 9"
            return false
        }

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        // Tổng ban đầu
        binding.tvKetQua.text =
            "Tổng đã nhận: 0\nChỗ còn lại: 9"

        binding.btnKiemTra.setOnClickListener {

            Ten = binding.ten.text.toString().trim()
            soluong = binding.soLuong.text.toString()
                .trim().toIntOrNull() ?: -1

            binding.ten.error = null
            binding.soLuong.error = null

            if (!ktradulieu()) {
                binding.tvKetQua.text =
                    "Dữ liệu không hợp lệ!\n" +
                            "Tổng đã nhận: $tongDaNhan\n" +
                            "Chỗ còn lại: ${9 - tongDaNhan}"
                return@setOnClickListener
            }

            val tongMoi = Try20Logic.ghiNhan(
                tongDaNhan,
                soluong
            )

            if (tongMoi == null) {
                binding.soLuong.error =
                    "Vượt quá sức chứa 9 người"

                binding.tvKetQua.text =
                    "Không đủ chỗ để ghi nhận!\n" +
                            "Tổng đã nhận: $tongDaNhan\n" +
                            "Chỗ còn lại: ${9 - tongDaNhan}"

                return@setOnClickListener
            }
            tongDaNhan = tongMoi

            binding.tvKetQua.text =
                "Xin chào $Ten, số lượng: $soluong\n" +
                        "Tổng đã nhận: $tongDaNhan\n" +
                        "Chỗ còn lại: ${9 - tongDaNhan}"

            Toast.makeText(
                this,
                "Ghi nhận thành công",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.btnXoa.setOnClickListener {

            binding.ten.setText("")
            binding.soLuong.setText("")

            binding.ten.error = null
            binding.soLuong.error = null

            tongDaNhan = 0
            Ten = ""
            soluong = 0

            binding.tvKetQua.text =
                "Tổng đã nhận: 0\nChỗ còn lại: 9"

            binding.ten.requestFocus()
        }
    }
}