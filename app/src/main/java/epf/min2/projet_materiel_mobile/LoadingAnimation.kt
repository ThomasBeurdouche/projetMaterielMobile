package epf.min2.projet_materiel_mobile

import android.app.ProgressDialog
import android.content.Context

class LoadingAnimation(context: Context) {

    private val progressDialog: ProgressDialog = ProgressDialog(context)

    init {
        progressDialog.setCancelable(false)
        progressDialog.setTitle("Chargement")
        progressDialog.setMessage("Veuillez patienter")
    }

    fun show() {
        progressDialog.show()
    }

    fun dismiss() {
        progressDialog.dismiss()
    }
}
