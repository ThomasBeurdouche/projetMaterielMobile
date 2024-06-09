package epf.min2.projet_materiel_mobile

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class DetailPaysActivity :AppCompatActivity() {

    private lateinit var favoriteManager: FavoriteManager
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_pays_layout)
        favoriteManager = FavoriteManager(this)
        val pays = intent.getSerializableExtra("pays") as Pays

        val imageView = findViewById<ImageView>(R.id.pays_detail_imageView)
        val nameTextView = findViewById<TextView>(R.id.pays_detail_nameTextView)
        val capitalTextView = findViewById<TextView>(R.id.pays_detail_capitalTextView)
        val flagTextView = findViewById<TextView>(R.id.pays_detail_flagTextView)

        val continentTextView = findViewById<TextView>(R.id.pays_detail_continentTextView)
        val languageTextView = findViewById<TextView>(R.id.pays_detail_languagesTextView)
        val addToFavoritesButton = findViewById<Button>(R.id.addToFavoritesButton)

        Picasso.get()
            .load(pays.flags.png)
            .into(imageView)

        nameTextView.text = pays.name.official
        capitalTextView.text = pays.capital.get(0)
        flagTextView.text = pays.flags.alt
        capitalTextView.text = "Capitale: ${pays.capital.joinToString(", ")}"
        continentTextView.text = "Continent: ${pays.continents.joinToString(", ")}"


        addToFavoritesButton.setOnClickListener {
            favoriteManager.addFavorite(pays)

        }}}

//
//        addToFavoritesButton.setOnClickListener {
//            val sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE)
//            val editor = sharedPreferences.edit()
//
//            val favoritesJson = Gson().toJson(pays)
//            editor.putString("favorites", favoritesJson)
//
//            editor.apply()
//
//        }

//

