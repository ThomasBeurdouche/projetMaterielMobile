package epf.min2.projet_materiel_mobile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlin.random.Random

class QuizActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_layout)

        val paysList = intent.getSerializableExtra("PAYS_LIST") as List<Pays>
        var paysChoisie = paysList[Random.nextInt(0, paysList.size)]

        val layout = findViewById<LinearLayout>(R.id.linearLayout_quiz)
        val imageViewDrapeau = findViewById<ImageView>(R.id.imageView_drapeau_quiz)
        val Reponse = findViewById<EditText>(R.id.editText_reponse_quiz)
        val buttonVerifier = findViewById<Button>(R.id.button_vérifier_quiz)
        val buttonRetour = findViewById<Button>(R.id.button_retour_quiz)



        Picasso.get()
            .load(paysChoisie.flags.png)
            .into(imageViewDrapeau)

        buttonVerifier.setOnClickListener {
            if (paysChoisie.name.common.equals(Reponse.text.toString().trim()) || paysChoisie.name.official.equals(Reponse.text.toString().trim())) {
                Picasso.get()
                    .load(R.drawable.correct)
                    .into(imageViewDrapeau)
                Handler().postDelayed({
                    paysChoisie = paysList[Random.nextInt(0, paysList.size)]
                    Picasso.get()
                        .load(paysChoisie.flags.png)
                        .into(imageViewDrapeau)
                    Reponse.setText("")
                }, 4000)

            } else {
                Picasso.get()
                    .load(R.drawable.incorrect)
                    .into(imageViewDrapeau)
                Handler().postDelayed({
                    paysChoisie = paysList[Random.nextInt(0, paysList.size)]
                    Picasso.get()
                        .load(paysChoisie.flags.png)
                        .into(imageViewDrapeau)
                    Reponse.setText("")
                }, 4000)

            }
        }

        layout.setOnClickListener{
            Reponse.isEnabled=false
            Reponse.isEnabled=true
        }

        buttonRetour.setOnClickListener{
            finish()
        }

    }

}