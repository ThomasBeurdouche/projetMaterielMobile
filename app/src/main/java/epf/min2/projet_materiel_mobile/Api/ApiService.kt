package epf.min2.projet_materiel_mobile.Api

import epf.min2.projet_materiel_mobile.Pays
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/v3.1/all/")
    suspend fun getPays():Response<List<Pays>>

    @GET("/v3.1/name/{query}/")
    suspend fun getPaysByNameOfPays(@Path("query") query: String?):Response<List<Pays>>

    @GET("/v3.1/capital/{query}/")
    suspend fun getPaysByNameOfCapital(@Path("query") query: String?):Response<List<Pays>>

}
