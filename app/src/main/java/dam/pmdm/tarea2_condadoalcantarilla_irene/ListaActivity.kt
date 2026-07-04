package dam.pmdm.tarea2_condadoalcantarilla_irene

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dam.pmdm.tarea2_condadoalcantarilla_irene.adapter.PikminAdapter
import dam.pmdm.tarea2_condadoalcantarilla_irene.repository.PikminRepository
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.snackbar.Snackbar

/**
 *  Actividad para Lista de Pikmin
 *  Esta actividad se ejecuta después de la pantalla Splash Screen y muestra un
 *  listado de 9 tipos de Pikmin utilizando componentes:
 *   -RecyclerView configurado en GridLayoutManager para mostrar la cuadrícula.
 *   -CardView para cada elemento del RecyclerView.
 *  Incluye una Toolbar con el título “Pikmin” con menú contextual con opciones
 *  de "Acerca de" y "Ajustes".
 *
 *  @author Irene Condado Alcantarilla
 *  @version 1.0
 */
class ListaActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pikminAdapter: PikminAdapter
    private lateinit var toolbar: Toolbar

    /**
     * Sobrescribe el contexto base para aplicar el idioma guardado en preferencias.
     *
     * @param newBase Contexto base de la actividad
     * @return Contexto actualizado con el idioma configurado
     */
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.setLocale(newBase, LocaleHelper.getLanguage(newBase)))
    }

    /**
     * Inicializa la actividad, configura la interfaz de usuario y carga la lista de Pikmin.
     *
     * Configura el tema, la toolbar, el RecyclerView en modo cuadrícula (2 columnas)
     * y muestra un mensaje de bienvenida cuando se accede a esta actividad(vista).
     *
     * @param savedInstanceState Estado guardado de la instancia
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeHelper.applyTheme(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = getString(R.string.pikmin)
        }

        recyclerView = findViewById(R.id.recyclerViewPikmin)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        val pikminList = PikminRepository.getPikminList(this)

        pikminAdapter = PikminAdapter(pikminList) { pikmin ->
            val intent = Intent(this, DetalleActivity::class.java)
            intent.putExtra("PIKMIN_DATA", pikmin)
            startActivity(intent)
        }

        recyclerView.adapter = pikminAdapter

        // Mostrar Snackbar de bienvenida
        mostrarSnackbarBienvenida()
    }

    /**
     * Muestra un Snackbar de bienvenida cuando se abre la actividad.
     */
    private fun mostrarSnackbarBienvenida() {
        // Usar el RecyclerView para mostrar el Snackbar
        Snackbar.make(recyclerView, getString(R.string.Snackbar), Snackbar.LENGTH_LONG).show()
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