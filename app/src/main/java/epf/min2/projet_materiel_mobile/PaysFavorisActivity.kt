package epf.min2.projet_materiel_mobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
class PaysFavorisActivity : AppCompatActivity(){
    private lateinit var favoriteManager: FavoriteManager
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.pays_favoris_layout)
        favoriteManager = FavoriteManager(this)
        val listPays = favoriteManager.getFavorites()

        if (listPays.isNotEmpty()) {
            val listView = findViewById<ListView>(R.id.lvFavoris)
            listView.adapter = object : ArrayAdapter<Pays>(this, android.R.layout.simple_list_item_1, listPays) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    val textView = view.findViewById<TextView>(android.R.id.text1)
                    textView.text = listPays[position].name.official
                    return view
                }
            }
            listView.setOnItemClickListener { _, _, position, _ ->
                val selectedPays = listPays[position]
                val intent = Intent(this, DetailPaysActivity::class.java)
                intent.putExtra("pays", Gson().toJson(selectedPays))
                startActivity(intent)
            }
        } else {
            val tvFavorisMessage = findViewById<TextView>(R.id.tvFavorisMessage)
            tvFavorisMessage.text = "Aucun pays n'a été ajouté aux favoris."
        }
    }

    }
