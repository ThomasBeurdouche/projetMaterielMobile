package epf.min2.projet_materiel_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projet_materiel_mobile.Api.ApiManager
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait
import retrofit2.Response

class PaysListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pays_list_layout)

        val recyclerView = findViewById<RecyclerView>(R.id.pays_recyclerview)

        val apiManager = ApiManager()

        runBlocking {
            var response: Response<List<Pays>> = apiManager.getPays()
            while (response.isSuccessful){
                response = apiManager.getPays()
                Thread.sleep(1000)
            }
            if (response.isSuccessful){
                val pays : List<Pays> = response.body()!!
                recyclerView.layoutManager = LinearLayoutManager(this@PaysListActivity, LinearLayoutManager.VERTICAL, false)
                recyclerView.adapter = PaysAdapter(pays)
            } else {
                println(response.errorBody())
            }

        }

    }


}
