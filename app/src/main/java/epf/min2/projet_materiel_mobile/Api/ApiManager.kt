package epf.min2.projet_materiel_mobile.Api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager() {


    private val apiService: ApiService

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        apiService = retrofit.create(ApiService::class.java)
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
