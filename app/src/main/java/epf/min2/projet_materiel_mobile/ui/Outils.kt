package epf.min2.projet_materiel_mobile.ui

import android.view.View

object outils {
    fun clickAilleurs(listeObject : Array<View>){
        for(objet in listeObject){
            objet.isEnabled = false
            objet.isEnabled = true
        }
    }
}