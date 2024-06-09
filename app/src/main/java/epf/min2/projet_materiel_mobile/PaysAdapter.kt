package epf.min2.projet_materiel_mobile

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class PaysViewHolder(view : View) : RecyclerView.ViewHolder(view)

class PaysAdapter(val pays: List<Pays>) : RecyclerView.Adapter<PaysViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaysViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.pays_view,parent,false)
        return PaysViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pays.size
    }

    override fun onBindViewHolder(holder: PaysViewHolder, position: Int) {
        val pays: Pays = pays[position]
        val view : View = holder.itemView
        val paysTextView = view.findViewById<TextView>(R.id.pays_name_textview)
        paysTextView.text = pays.name.common

        val imageView1 = view.findViewById<ImageView>(R.id.pays_image_imageView1)
//        val imageView2 = view.findViewById<ImageView>(R.id.pays_image_imageView2)
        Picasso.get()
            .load(pays.flags.png)
            .into(imageView1)
//        Picasso.get()
//            .load(pays.flags.png)
//            .into(imageView2)

        val cardVIew = view.findViewById<CardView>(R.id.pays_view_cardview)
        cardVIew.setOnClickListener() {
            with(it.context){
                val intent = Intent(this, DetailPaysActivity::class.java)
                intent.putExtra("pays",Gson().toJson(pays))
                startActivity(intent)
            }
        }
    }
}