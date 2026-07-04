package dam.pmdm.tarea2_condadoalcantarilla_irene

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

/**
 * Activity encargada de gestionar los ajustes de la aplicación.
 *
 * Permite al usuario:
 * - Cambiar el idioma de la aplicación.
 * - Cambiar el tema visual (claro u oscuro).
 *
 * @author Irene Condado Alcantarilla
 * @version 1.0
 */
class AjustesActivity : AppCompatActivity() {

    /**
     * Indica si la Activity se encuentra en su primera carga.
     * Se usa para evitar ejecutar acciones al inicializar los Spinners.
     */
    private var isFirstLoad = true

    /**
     * Aplica el idioma seleccionado antes de crear la Activity.
     *
     * @param newBase Contexto base de la aplicación.
     */
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase, LocaleHelper.getLanguage(newBase)))
    }

    /**
     * Inicializa la Activity, el tema, la vista y los componentes UI.
     *
     * @param savedInstanceState Estado guardado de la Activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.applyTheme(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajustes)

        val toolbar = findViewById<Toolbar>(R.id.toolBarAjustes)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = getString(R.string.ajustes)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        configurarSpinnerIdioma()
        configurarSpinnerTema()

        // Después de configurar los spinners, permite los cambios
        isFirstLoad = false
    }

    /**
     * Configura el Spinner de selección de idioma.
     *
     * Permite cambiar entre español e inglés.
     * Al cambiar el idioma, la aplicación se reinicia.
     */
    private fun configurarSpinnerIdioma() {
        val spinnerIdioma = findViewById<Spinner>(R.id.spinnerIdioma)

        val idiomas = arrayOf(
            getString(R.string.espanol),
            getString(R.string.ingles)
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, idiomas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerIdioma.adapter = adapter

        // Seleccionar idioma actual
        val idiomaActual = LocaleHelper.getLanguage(this)
        spinnerIdioma.setSelection(if (idiomaActual == "es") 0 else 1, false)

        spinnerIdioma.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!isFirstLoad) {
                    val nuevoIdioma = if (position == 0) "es" else "en"
                    if (nuevoIdioma != LocaleHelper.getLanguage(this@AjustesActivity)) {
                        LocaleHelper.setLocale(this@AjustesActivity, nuevoIdioma)
                        reiniciarApp()
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    /**
     * Configura el Spinner de selección de tema.
     *
     * Permite alternar entre tema claro y oscuro.
     */
    private fun configurarSpinnerTema() {
        val spinnerTema = findViewById<Spinner>(R.id.spinnerTema)

        val temas = arrayOf(
            getString(R.string.claro),
            getString(R.string.oscuro)
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, temas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTema.adapter = adapter

        // Seleccionar tema actual
        val temaActual = ThemeHelper.getTheme(this)
        spinnerTema.setSelection(if (temaActual == "claro") 0 else 1, false)

        spinnerTema.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (!isFirstLoad) {
                    val nuevoTema = if (position == 0) "claro" else "oscuro"
                    if (nuevoTema != ThemeHelper.getTheme(this@AjustesActivity)) {
                        ThemeHelper.saveTheme(this@AjustesActivity, nuevoTema)
                        // El tema se aplica automáticamente, no hace falta reiniciar
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    /**
     * Reinicia la aplicación para aplicar cambios.
     */
    private fun reiniciarApp() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    /**
     * Gestiona la acción del botón "volver" de la Toolbar.
     *
     * @return true si la acción se ha manejado correctamente.
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}