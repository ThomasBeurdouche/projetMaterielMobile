package epf.min2.projet_materiel_mobile

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.squareup.picasso.Picasso

class DetailPaysActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_pays_layout)
        val pays = intent.getSerializableExtra("pays") as Pays

        val imageView = findViewById<ImageView>(R.id.pays_detail_imageView)
        val nameTextView = findViewById<TextView>(R.id.pays_detail_nameTextView)
        val capitalTextView = findViewById<TextView>(R.id.pays_detail_capitalTextView)

        Picasso.get()
            .load(pays.flags.png)
            .into(imageView)

        nameTextView.text=pays.name.official
        capitalTextView.text=pays.capital.get(0)

    }
}