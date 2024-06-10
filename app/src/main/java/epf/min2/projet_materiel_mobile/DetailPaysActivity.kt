package epf.min2.projet_materiel_mobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle 
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class DetailPaysActivity :AppCompatActivity() {

    private lateinit var favoriteManager: FavoriteManager
    private var previousActivity: String? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_pays_layout)
        favoriteManager = FavoriteManager(this)
//        val pays = intent.getSerializableExtra("pays") as Pays
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        previousActivity = intent.getStringExtra("previousActivity")
        val paysJson = intent.getStringExtra("pays")
        val pays = Gson().fromJson(paysJson, Pays::class.java)

        val imageView = findViewById<ImageView>(R.id.pays_detail_imageView)
        val nameTextView = findViewById<TextView>(R.id.pays_detail_nameTextView)
        val capitalTextView = findViewById<TextView>(R.id.pays_detail_capitalTextView)
        val flagTextView = findViewById<TextView>(R.id.pays_detail_flagTextView)

        val continentTextView = findViewById<TextView>(R.id.pays_detail_continentTextView)
        val languageTextView = findViewById<TextView>(R.id.pays_detail_languagesTextView)
        val addToFavoritesButton = findViewById<Button>(R.id.addToFavoritesButton)
        val removeFromFavoritesButton = findViewById<Button>(R.id.removeFromFavoritesButton)



        Picasso.get()
            .load(pays.flags.png)
            .into(imageView)

        nameTextView.text = pays.name.official
        capitalTextView.text = pays.capital.get(0)
        flagTextView.text = pays.flags.alt
        capitalTextView.text = "Capitale: ${pays.capital.joinToString(", ")}"
        continentTextView.text = "Continent: ${pays.continents.joinToString(", ")}"

        if (favoriteManager.isFavorite(pays)) {
            addToFavoritesButton.visibility = View.GONE
            removeFromFavoritesButton.visibility = View.VISIBLE
        } else {
            addToFavoritesButton.visibility = View.VISIBLE
            removeFromFavoritesButton.visibility = View.GONE
        }


        addToFavoritesButton.setOnClickListener {
            favoriteManager.addFavorite(pays)

        }
        removeFromFavoritesButton.setOnClickListener {
            favoriteManager.removeFavorite(pays)
            addToFavoritesButton.visibility = View.VISIBLE
            removeFromFavoritesButton.visibility = View.GONE
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

    override fun onBackPressed() {
        previousActivity?.let {
            val intent = when (it) {
                "PaysListActivity" -> Intent(this, PaysListActivity::class.java)
                "PaysFavorisActivity" -> Intent(this, PaysFavorisActivity::class.java)
                else -> null
            }
            intent?.let {
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
                startActivity(it)
            }
        } ?: super.onBackPressed()
    }



}



