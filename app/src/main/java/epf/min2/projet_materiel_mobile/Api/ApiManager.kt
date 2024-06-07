package epf.min2.projet_materiel_mobile.Api

import epf.min2.projet_materiel_mobile.Pays
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
            .connectTimeout(30, TimeUnit.SECONDS)
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
