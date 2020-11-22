package com.example.movie

data class ResponseCreateToken(
        var expires_at:String,
        var  request_token:String,
        var success:Boolean
)