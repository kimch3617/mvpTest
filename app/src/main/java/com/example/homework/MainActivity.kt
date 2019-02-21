package com.example.homework

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.example.homework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_test.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("testapp://repos/jakewharton"))
            startActivity(intent)
        }
    }
}