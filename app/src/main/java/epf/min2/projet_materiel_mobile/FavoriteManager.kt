package epf.min2.projet_materiel_mobile
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FavoriteManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun addFavorite(country: Pays) {
        val favorites = getFavorites().toMutableList()
        if (!favorites.any { it.name.common == country.name.common }) {
            favorites.add(country)
            saveFavorites(favorites)
        }
    }

    fun removeFavorite(country: Pays) {
        val favorites = getFavorites().toMutableList()
        favorites.removeAll { it.name.common == country.name.common }
        saveFavorites(favorites)
    }

    fun getFavorites(): List<Pays> {
        val json = sharedPreferences.getString("favorite_countries", null) ?: return emptyList()
        val type = object : TypeToken<List<Pays>>() {}.type
        return gson.fromJson(json, type)
    }

    private fun saveFavorites(favorites: List<Pays>) {
        val json = gson.toJson(favorites)
        sharedPreferences.edit().putString("favorite_countries", json).apply()
    }
}
