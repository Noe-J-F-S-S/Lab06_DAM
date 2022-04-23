package com.sierra.practicalab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.detail.*

const val PARAMETER_EXTRA_NAME = "name"
const val PARAMETER_EXTRA_EMAIL = "email"
const val PARAMETER_EXTRA_DIRECTION = "direction"
const val PARAMETER_EXTRA_NUMBER = "number"

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)

        val extras = this.intent.extras

        if (extras != null) {
            if (extras.get(PARAMETER_EXTRA_NAME) != null) {
                edtName.setText(extras.getString(PARAMETER_EXTRA_NAME))
            }

            if (extras.get(PARAMETER_EXTRA_EMAIL) != null) {
                edtEmail.setText(extras.getString(PARAMETER_EXTRA_EMAIL))
            }

            if (extras.get(PARAMETER_EXTRA_DIRECTION) != null) {
                edtDirection.setText(extras.getString(PARAMETER_EXTRA_DIRECTION))
            }
            if (extras.get(PARAMETER_EXTRA_NUMBER) != null) {
                edtNumber.setText(extras.getString(PARAMETER_EXTRA_NUMBER))
            }
        }
    }
    fun closeActivity(view: android.view.View) {
        val intent = Intent()
        intent.putExtra(PARAMETER_EXTRA_NAME, edtName.getText().toString())
        intent.putExtra(PARAMETER_EXTRA_EMAIL, edtEmail.getText().toString())
        intent.putExtra(PARAMETER_EXTRA_DIRECTION, edtDirection.getText().toString())
        intent.putExtra(PARAMETER_EXTRA_NUMBER, edtNumber.getText().toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}