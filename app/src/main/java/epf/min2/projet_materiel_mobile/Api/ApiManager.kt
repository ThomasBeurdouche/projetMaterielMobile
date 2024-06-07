package epf.min2.projet_materiel_mobile.Api

import androidx.recyclerview.widget.LinearLayoutManager
import epf.min2.projet_materiel_mobile.Pays
import epf.min2.projet_materiel_mobile.PaysAdapter
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


class ApiManager() {


    private val apiService: ApiService

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(45, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getPays():Response<List<Pays>>{
        return apiService.getPays()
    }

    suspend fun getPaysByName(query:String?):List<Pays>{
        var listPays : List<Pays> = listOf()
        try {
            var response1: Response<List<Pays>>
            var response2: Response<List<Pays>>
            var success = false
            var attempts = 0
            val maxAttempts = 5

            while (!success && attempts < maxAttempts) {
                try {
                    response1 = apiService.getPaysByNameOfPays(query?.trimEnd())
                    if (response1.isSuccessful && response1.body() != null) {
                        success = true
                        listPays = response1.body()!!
                    } else {
                        println("Error: ${response1.errorBody()}")
                        Thread.sleep(1000)
                    }
                } catch (e: Exception) {
                    attempts++
                    println("Exception: $e")
                }
            }
            success=false
            attempts = 0

            while (!success && attempts < maxAttempts) {
                try {
                    response2 = apiService.getPaysByNameOfCapital(query?.trimEnd())
                    if (response2.isSuccessful && response2.body() != null) {
                        success = true
                        listPays += response2.body()!!
                    } else {
                        println("Error: ${response2.errorBody()}")
                        Thread.sleep(1000)
                    }
                } catch (e: Exception) {
                    attempts++
                    println("Exception: $e")
                }
            }

        } catch (e: Exception) {
            println("Exception: $e")
        }

        return listPays

    }

    /*
    suspend fun modifyUser(user:User):Response<Unit>{
        return apiService.modifyUser(user.id,user)
    }

    suspend fun createUser(newUser: NewUser):Response<Unit>{
        return apiService.createUser(newUser)
    }

    suspend fun getUsers():Response<List<User>> {
        return apiService.getUsers()
    }

    suspend fun getUser(idUser : String):Response<User>{
        return apiService.getUser(idUser)
    }
    */


}
