package epf.min2.projet_materiel_mobile

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Type
class PaysFavorisActivity : AppCompatActivity(){
    private lateinit var favoriteManager: FavoriteManager
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.pays_favoris_layout)
        favoriteManager = FavoriteManager(this)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val listPays = favoriteManager.getFavorites()

        if (listPays.isNotEmpty()) {

            val recyclerView = findViewById<RecyclerView>(R.id.pays_favoris_recyclerview)
            recyclerView.layoutManager = LinearLayoutManager(this@PaysFavorisActivity, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = PaysAdapter(listPays)

        } else {
            val tvFavorisMessage = findViewById<TextView>(R.id.tvFavorisMessage)
            tvFavorisMessage.text = "Aucun pays n'a été ajouté aux favoris."
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    }
