package epf.min2.projet_materiel_mobile


import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import epf.min2.projet_materiel_mobile.Api.ApiManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PaysListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pays_list_layout)

        val recyclerView = findViewById<RecyclerView>(R.id.pays_recyclerview)
        val barreRecherche = findViewById<SearchView>(R.id.searchViewPaysList)
        val addToFavoritesButton = findViewById<FloatingActionButton>(R.id.buttonFavorites)

        val apiManager = ApiManager()

        val animation = LoadingAnimation(this@PaysListActivity)

        animation.show()

        CoroutineScope(Dispatchers.Main).launch {
            recyclerView.layoutManager = LinearLayoutManager(this@PaysListActivity, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = PaysAdapter(withContext(Dispatchers.IO){apiManager.getPays()} )
            animation.dismiss()
        }


        barreRecherche.setOnClickListener{
            barreRecherche.isIconified = false
        }

        barreRecherche.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                animation.show()

                CoroutineScope(Dispatchers.Main).launch {
                    recyclerView.layoutManager = LinearLayoutManager(this@PaysListActivity, LinearLayoutManager.VERTICAL, false)
                    recyclerView.adapter = PaysAdapter(withContext(Dispatchers.IO){apiManager.getPaysByName(query)})
                    animation.dismiss()
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        addToFavoritesButton.setOnClickListener {
            startActivity(Intent(this, PaysFavorisActivity::class.java))
        }

    }
}