package com.example.movie



import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import kotlinx.android.synthetic.main.activity_second.*
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SecondActivity : AppCompatActivity() {
    val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/3/")
            .build()

    val reqTokenClient = retrofit.create(AuthorizedApi::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        lateinit var token:String

        reqTokenClient.createToken().enqueue(object : Callback<ResponseCreateToken> {
            override fun onResponse(call: Call<ResponseCreateToken>, response: Response<ResponseCreateToken>) {
                if (response != null) {
                    token = response.body()!!.request_token
                    testone.text = response.body()?.request_token
                }
            }
            override fun onFailure(call: Call<ResponseCreateToken>, t: Throwable) {
            }
        })
        inputLogin.addTextChangedListener { editableText: Editable? ->
            if (editableText != null)
            {
                var login = editableText.toString()
            }
        }
        inputPassword.addTextChangedListener{
            if (it!=null){
                var pass = it.toString()
            }
        }

        var userOne = User(loginU = inputLogin.toString(),passU = inputPassword.toString(),token)

        lateinit var tokenTwo:String

        reqTokenClient.postLiP(API_KEY,userOne).enqueue(object :Callback<ResponseCreateToken>{
            override fun onResponse(call: Call<ResponseCreateToken>, response: Response<ResponseCreateToken>) {
                if(response!=null)
                tokenTwo = response.body()!!.request_token
            }
            override fun onFailure(call: Call<ResponseCreateToken>, t: Throwable) {

            }
        })

        reqTokenClient.sesionID(API_KEY,tokenTwo).enqueue(object :Callback<LastPostData>{
            override fun onResponse(call: Call<LastPostData>, response: Response<LastPostData>) {
                if(response!=null){
                    SesionId.id=response.body()!!.session_id
                }
            }
            override fun onFailure(call: Call<LastPostData>, t: Throwable) {

            }

        })
        buttonInput2.setOnClickListener {
            var intent = Intent(this, treriyActiv::class.java)
            startActivity(intent)
        }








    }
}