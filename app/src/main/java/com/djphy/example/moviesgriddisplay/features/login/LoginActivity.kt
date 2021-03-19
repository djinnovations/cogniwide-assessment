package com.djphy.example.moviesgriddisplay.features.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.djphy.example.moviesgriddisplay.R
import com.djphy.example.moviesgriddisplay.databinding.ActivityLoginBinding
import com.djphy.example.moviesgriddisplay.features.home.MainActivity
import com.djphy.example.moviesgriddisplay.isValid
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var mDataBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mDataBinding.state = LoginActivityState(false)
        btnLogin.setOnClickListener(this)
        setTextWatcherLister()
    }

    private fun checkLoginStat(){
        if (isValidEmail(etEmailId.text.toString()) && isValidPassword(etPassword.text.toString())){
            mDataBinding.state = LoginActivityState(true)
        } else{
            mDataBinding.state = LoginActivityState(false)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return email.isValid() && Patterns.EMAIL_ADDRESS.matcher(email)
            .matches()
    }

    private fun isValidPassword(password: String): Boolean {
        return password.isValid() && password.length >= 6 && password.length <= 12
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnLogin ->{
                Intent(applicationContext, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(this)
                }
            }
        }
    }











    private fun setTextWatcherLister() {
        etEmailId.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                checkLoginStat()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(isValidEmail(s.toString())) {
                    input_layout_emailId.isErrorEnabled = false
                } else{
                    input_layout_emailId.error = "Invalid Email address"
                }
            }
        })

        etPassword.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                checkLoginStat()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(isValidPassword(s.toString())) {
                    input_layout_password.isErrorEnabled = false
                } else{
                    input_layout_password.error = "Password minimum length is 6"
                }
            }
        })
    }

}