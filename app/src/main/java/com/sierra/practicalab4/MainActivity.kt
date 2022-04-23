package com.sierra.practicalab4

import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sierra.practicalab4.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail.*

const val ACTIVITY_A_REQUEST = 991
const val ACTIVITY_B_REQUEST = 992


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

    }
    fun sendExplicit(view: android.view.View) {
        val name = binding.tvName.text.toString()
        val email = binding.tvEmail.text.toString()
        val direction = binding.tvDirection.text.toString()
        val number = binding.tvNumber.text.toString()

        validateInputFields(name, email, direction, number)
        goDetailActivity(name, email, direction, number)
    }

    private fun goDetailActivity(name: String, email: String,
                                 direction: String, number: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(PARAMETER_EXTRA_NAME, name)
        intent.putExtra(PARAMETER_EXTRA_EMAIL, email)
        intent.putExtra(PARAMETER_EXTRA_DIRECTION, direction)
        intent.putExtra(PARAMETER_EXTRA_NUMBER, number)
        startActivityForResult(intent, ACTIVITY_B_REQUEST)
    }
    private fun validateInputFields(name: String, email: String,
                                    direction: String, number: String) {
        if (name.isBlank() && email.isBlank() && direction.isBlank()
            && number.isBlank()) return
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(ContentValues.TAG, "requestCode:$requestCode")
        Log.d(ContentValues.TAG, "resultCode:$resultCode")
        Log.d(ContentValues.TAG, "data:" + android.R.attr.data)

        when (requestCode) {
            ACTIVITY_A_REQUEST -> Toast.makeText(this, "Regresamos del Activity A",
                Toast.LENGTH_SHORT).show()
            ACTIVITY_B_REQUEST -> {
                Log.d(ContentValues.TAG, "Regresamos del Activity B")
                if (resultCode === RESULT_OK) {
                    val extras = data?.extras

                    if (extras != null) {
                        if (extras.get(PARAMETER_EXTRA_NAME) != null) {
                            binding.tvName.text = extras.getString(PARAMETER_EXTRA_NAME)
                        }

                        if (extras.get(PARAMETER_EXTRA_EMAIL) != null) {
                            binding.tvEmail.text = extras.getString(PARAMETER_EXTRA_EMAIL)
                        }

                        if (extras.get(PARAMETER_EXTRA_DIRECTION) != null) {
                            binding.tvDirection.text = extras.getString(PARAMETER_EXTRA_DIRECTION)
                        }

                        if (extras.get(PARAMETER_EXTRA_NUMBER) != null) {
                            binding.tvNumber.text = extras.getString(PARAMETER_EXTRA_NUMBER)
                        }
                    }
                }
            }
        }

    }

    fun callPhone(view: View) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" +
                binding.tvNumber.text.toString()))
        startActivity(intent)
    }

    fun enviarSMS(view: View) {
        val intentMsg = Intent(Intent.ACTION_VIEW, Uri.parse("sms:" +
                binding.tvNumber.text.toString()))
        intentMsg.putExtra("sms_body", binding.tvName.text.toString()+
                "\n"+binding.tvEmail.text.toString()+ "\n"+binding.tvDirection.text.toString())
        
        startActivity(intentMsg)
    }


    fun enviarWsp(view: View){

        // Creating intent with action send
        val numero =tvNumber.text.toString()
        val intentwsp = Intent(Intent.ACTION_SEND)

        // Setting Intent type
        intentwsp.putExtra("sms_body", tvName.text.toString()+
        "\n"+tvEmail.text.toString()+ "\n"+tvDirection.text.toString())

        // Setting whatsapp package name
        intentwsp.setPackage("com.whatsapp")

        if (intentwsp.resolveActivity(packageManager) == null) {
            Toast.makeText(this,
                "Porfavor instala Whats app.",
                Toast.LENGTH_SHORT).show()
            return
        }

        startActivity(intentwsp)
    }
}