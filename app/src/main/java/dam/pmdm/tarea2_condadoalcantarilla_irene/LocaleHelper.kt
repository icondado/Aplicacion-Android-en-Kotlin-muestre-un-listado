package dam.pmdm.tarea2_condadoalcantarilla_irene

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.Locale
import androidx.core.content.edit

/**
 * Clase auxiliar para gestionar el idioma de la aplicación.
 *
 * Proporciona métodos para cambiar, guardar y recuperar el idioma
 * configurado por el usuario. Los cambios se persisten en SharedPreferences
 * y se aplican a nivel de configuración del sistema.
 *
 * @author Irene Condado Alcantarilla
 * @version 1.0
 */
object LocaleHelper {

    /** Clave para almacenar el idioma seleccionado en SharedPreferences */
    private const val SELECTED_LANGUAGE = "idioma"

    /**
     * Cambia el idioma de la aplicación y actualiza el contexto.
     *
     * Guarda el nuevo idioma en SharedPreferences y actualiza los recursos
     * de la aplicación para reflejar el cambio.
     *
     * @param context Contexto de la aplicación
     * @param language Código del idioma ("es" para español, "en" para inglés)
     * @return Contexto actualizado con el nuevo idioma
     */
    fun setLocale(context: Context, language: String): Context {
        persist(context, language)
        return updateResources(context, language)
    }

    /**
     * Obtiene el idioma guardado en las preferencias.
     *
     * @param context Contexto de la aplicación
     * @return Código del idioma guardado ("es" por defecto)
     */
    fun getLanguage(context: Context): String {
        val prefs = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        return prefs.getString(SELECTED_LANGUAGE, "es") ?: "es"
    }

    /**
     * Guarda el idioma seleccionado en SharedPreferences.
     *
     * @param context Contexto de la aplicación
     * @param language Código del idioma a guardar
     */
    private fun persist(context: Context, language: String) {
        val prefs = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        prefs.edit { putString(SELECTED_LANGUAGE, language) }
    }

    /**
     * Actualiza los recursos de la aplicación con el nuevo idioma.
     *
     * Crea una nueva configuración con el locale especificado y la aplica
     * al contexto. El metodo de aplicación varía según la versión de Android.
     *
     * @param context Contexto de la aplicación
     * @param language Código del idioma a aplicar
     * @return Contexto con los recursos actualizados
     */
    @SuppressLint("ObsoleteSdkInt")
    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale.Builder().setLanguage(language).build()
        Locale.setDefault(locale)

        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(configuration)
        } else {
            @Suppress("DEPRECATION")
            context.resources.updateConfiguration(configuration, context.resources.displayMetrics)
            context
        }
    }
}