package com.example.newrautomata.data.interfaces

import com.example.newrautomata.data.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface APIServiceInterface {
    @GET("users")
    suspend fun getUsers(): List<User>
    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int):  User?
}