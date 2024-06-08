package epf.min2.projet_materiel_mobile

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import epf.min2.projet_materiel_mobile.Api.ApiManager
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import java.lang.Exception

class PaysListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pays_list_layout)

        val recyclerView = findViewById<RecyclerView>(R.id.pays_recyclerview)
        val barreRecherche = findViewById<SearchView>(R.id.searchViewPaysList)

        val apiManager = ApiManager()

        runBlocking {
            try {
                var response: Response<List<Pays>>
                var success = false
                var attempts = 0
                val maxAttempts = 5

                while (!success && attempts < maxAttempts) {
                    try {
                        response = apiManager.getPays()
                        if (response.isSuccessful && response.body() != null) {
                            success = true
                            val pays: List<Pays> = response.body()!!
                            recyclerView.layoutManager = LinearLayoutManager(this@PaysListActivity, LinearLayoutManager.VERTICAL, false)
                            recyclerView.adapter = PaysAdapter(pays)
                        } else {
                            println("Error: ${response.errorBody()}")
                            Thread.sleep(1000)
                        }
                    } catch (e: Exception) {
                        attempts++
                        println("Exception: $e")
                        if (attempts >= maxAttempts) {
                        } else {
                            Thread.sleep(1000)
                        }
                    }
                }
            } catch (e: Exception) {
                println("Exception: $e")
            }
        }

        barreRecherche.setOnClickListener{
            barreRecherche.isIconified = false
        }

        barreRecherche.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                runBlocking {
                    recyclerView.layoutManager = LinearLayoutManager(this@PaysListActivity, LinearLayoutManager.VERTICAL, false)
                    recyclerView.adapter = PaysAdapter(apiManager.getPaysByName(query))
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

    }
}