package epf.min2.projet_materiel_mobile

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
class PaysFavorisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pays_favoris_layout)

        val sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE)
        val favoritesJson = sharedPreferences.getString("favorites", "")

        if (favoritesJson!= "") {
           val listType: Type = object : TypeToken<ArrayList<Pays>>() {}.type

            val favoritesList:ArrayList<Pays> = Gson().fromJson(favoritesJson, listType)


            val listView = findViewById<ListView>(R.id.lvFavoris)
            listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, favoritesList.map { it.name.official })
        } else {
            val tvFavorisMessage = findViewById<TextView>(R.id.tvFavorisMessage)
            tvFavorisMessage.text = "Aucun pays n'a été ajouté aux favoris."
        }
    }
}