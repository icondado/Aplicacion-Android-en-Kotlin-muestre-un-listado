package dam.pmdm.tarea2_condadoalcantarilla_irene

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import dam.pmdm.tarea2_condadoalcantarilla_irene.model.Pikmin
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

/**
 * Actividad que muestra los detalles completos de un Pikmin seleccionado.
 *
 * Esta actividad recibe un objeto Pikmin mediante un Intent y muestra
 * su imagen grande, nombre y descripción detallada de habilidades.
 * Incluye un menú contextual con opciones de "Acerca de" y "Ajustes".
 * También muestra un Toast al abrir indicando qué Pikmin se ha seleccionado.
 *
 * Componentes visuales:
 * - ImageView: Imagen grande del Pikmin seleccionado
 * - TextView: Nombre del tipo de Pikmin
 * - TextView: Descripción de habilidades y características
 * - Toolbar: Con título dinámico y botón de retroceso
 *
 * @author Irene Condado Alcantarilla
 * @version 1.0
 */
class DetalleActivity : AppCompatActivity() {

    /**
     * Sobrescribe el contexto base para aplicar el idioma guardado en preferencias.
     *
     * Este metodo se llama antes de onCreate() y permite aplicar la configuración
     * de idioma personalizada del usuario antes de inflar las vistas.
     *
     * @param newBase Contexto base de la actividad
     */
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase, LocaleHelper.getLanguage(newBase)))
    }

    /**
     * Inicializa la actividad y muestra los detalles del Pikmin seleccionado.
     *
     * Realiza las siguientes operaciones:
     * 1. Aplica el tema configurado (claro/oscuro)
     * 2. Configura la toolbar con botón de retroceso
     * 3. Recupera el objeto Pikmin del Intent
     * 4. Muestra imagen, nombre y descripción del Pikmin
     * 5. Muestra un Toast indicando el Pikmin seleccionado
     *
     * @param savedInstanceState Estado guardado de la instancia anterior, o null si es nueva
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.applyTheme(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val toolbar: Toolbar = findViewById(R.id.toolbarDetalle)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val pikmin = IntentCompat.getSerializableExtra(intent, "PIKMIN_DATA", Pikmin::class.java)

        pikmin?.let {
            supportActionBar?.title = it.nombre

            val imagePikmin: ImageView = findViewById(R.id.imageDetallePikmin)
            val textNombre: TextView = findViewById(R.id.textDetalleNombre)
            val textDescripcion: TextView = findViewById(R.id.textDetalleDescripcion)

            imagePikmin.setImageResource(it.imageResId)
            textNombre.text = it.nombre
            textDescripcion.text = it.descripcion

            // Mostrar Toast con el nombre del Pikmin seleccionado
            Toast.makeText(
                this,
                getString(R.string.toast_seleccion, it.nombre),
                Toast.LENGTH_SHORT
            ).show()

        }
    }

    /**
     * Maneja el evento de navegación hacia atrás desde la toolbar.
     *
     * Cuando el usuario pulsa el botón de retroceso en la toolbar,
     * se invoca el comportamiento del botón atrás del sistema.
     *
     * @return true si la navegación se procesó correctamente
     */
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    /**
     * Crea el menú de opciones en la toolbar.
     *
     * Infla el layout del menú contextual que contiene las opciones
     * "Acerca de" y "Ajustes".
     *
     * @param menu Menú donde se inflarán los items
     * @return true para mostrar el menú
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    /**
     * Maneja la selección de items del menú contextual.
     *
     * Procesa los clics del usuario en las opciones del menú:
     * - "Acerca de": Muestra un diálogo con información de la app
     * - "Ajustes": Abre la pantalla de configuración
     *
     * @param item Item del menú seleccionado por el usuario
     * @return true si el evento se procesó, false en caso contrario
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_acerca -> {
                mostrarDialogAcerca()
                return true
            }

            R.id.menu_ajustes -> {
                startActivity(Intent(this, AjustesActivity::class.java))
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Muestra un diálogo con información "Acerca de" la aplicación.
     *
     * Presenta un AlertDialog modal con:
     * - Título: "Acerca de"
     * - Icono: Logo de la aplicación
     * - Mensaje: Información del desarrollador y versión
     * - Botón: "OK" para cerrar el diálogo
     */
    private fun mostrarDialogAcerca() {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle(getString(R.string.acerca_de))
            .setIcon(R.mipmap.ic_launcher)
            .setMessage(getString(R.string.acerca_mensaje))
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }
}