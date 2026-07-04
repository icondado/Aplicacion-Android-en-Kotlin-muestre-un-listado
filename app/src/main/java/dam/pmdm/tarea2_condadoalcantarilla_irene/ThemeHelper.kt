package dam.pmdm.tarea2_condadoalcantarilla_irene

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit

/**
 * Clase auxiliar (Helper) para gestionar el tema visual de la aplicación.
 *
 * Esta clase proporciona métodos estáticos para cambiar, guardar
 * y aplicar el tema (claro/oscuro) configurado por el usuario. Los cambios
 * se persisten en SharedPreferences para mantener la preferencia del usuario
 * entre sesiones de la aplicación.
 *
 * Implementación:
 * - Utiliza AppCompatDelegate para gestionar el modo claro/oscuro
 * - Guarda las preferencias en SharedPreferences con la clave "MisPreferencias"
 * - Soporta tres modos: claro, oscuro, y seguir la configuración del sistema (predeterminado)
 *
 * @author Irene Condado Alcantarilla
 * @version 1.0
 */
object ThemeHelper {

    /**
     * Clave utilizada para almacenar y recuperar el tema seleccionado
     * en SharedPreferences. Valor: "tema"
     */
    private const val SELECTED_THEME = "tema"

    /**
     * Aplica el tema guardado en las preferencias del usuario.
     *
     * @param context Contexto de la aplicación necesario para acceder a SharedPreferences
     */
    fun applyTheme(context: Context) {
        val theme = getTheme(context)
        setTheme(theme)
    }

    /**
     * Establece el modo de tema de la aplicación.
     *
     * Utiliza AppCompatDelegate.setDefaultNightMode() para cambiar entre
     * modo claro, oscuro o seguir la configuración del sistema. Los cambios
     * se aplican inmediatamente a toda la aplicación.
     *
     * @param theme Nombre del tema a aplicar ("claro", "oscuro", u otro para modo sistema)
     */
    fun setTheme(theme: String) {
        when (theme) {
            "oscuro" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "claro" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    /**
     * Guarda el tema seleccionado en SharedPreferences y lo aplica inmediatamente.
     * El tema se guarda de forma persistente, por lo que se mantendrá
     * entre reinicios de la aplicación hasta que el usuario lo cambie
     * nuevamente.
     * @param context Contexto de la aplicación necesario para acceder a SharedPreferences
     * @param theme Nombre del tema a guardar y aplicar ("claro" o "oscuro")
     */
    fun saveTheme(context: Context, theme: String) {
        val prefs = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        prefs.edit { putString(SELECTED_THEME, theme) }  // Usando KTX
        setTheme(theme)
    }

    /**
     * Obtiene el tema guardado en las preferencias del usuario.
     *
     * Lee el valor guardado en SharedPreferences bajo la clave SELECTED_THEME.
     * Si no existe ningún valor guardado (primera ejecución de la app),
     * devuelve "claro" como valor predeterminado.
     *
     * @param context Contexto de la aplicación necesario para acceder a SharedPreferences
     * @return Nombre del tema guardado ("claro" o "oscuro"). Por defecto devuelve "claro"
     */
    fun getTheme(context: Context): String {
        val prefs = context.getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        return prefs.getString(SELECTED_THEME, "claro") ?: "claro"
    }
}