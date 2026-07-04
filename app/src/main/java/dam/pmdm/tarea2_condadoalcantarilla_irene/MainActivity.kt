package dam.pmdm.tarea2_condadoalcantarilla_irene

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Pantalla de inicio. Esta actividad se ejecuta con Splash Screen nativo y
 * redirige automáticamente al usuario a ListaActivity donde se muestra
 * el listado de Pikmin.
 *
 * @author Irene Condado Alcantarilla
 * @version 1.0
 */
class MainActivity : AppCompatActivity() {


    /**
     * Inicializa la actividad y redirige a la pantalla de listado.
     *
     * Tras el Splash Screen, inicia inmediatamente ListaActivity
     * y finaliza esta actividad para evitar que el usuario pueda
     * volver a ella con el botón atrás.
     *
     * @param savedInstanceState Estado guardado de la instancia (no usado)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Después del Splash, ir directamente al listado Pikmin
        startActivity(Intent(this, ListaActivity::class.java))
        finish()
    }
}