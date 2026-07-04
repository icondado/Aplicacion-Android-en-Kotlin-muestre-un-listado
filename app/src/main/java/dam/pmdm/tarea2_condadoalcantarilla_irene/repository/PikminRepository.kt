package dam.pmdm.tarea2_condadoalcantarilla_irene.repository

import android.content.Context
import dam.pmdm.tarea2_condadoalcantarilla_irene.R
import dam.pmdm.tarea2_condadoalcantarilla_irene.model.Pikmin

/**
 * Repositorio encargado de proporcionar los datos de Pikmin.
 *
 * Centraliza la creación de la lista de Pikmin y desacopla
 * la fuente de datos del resto de la aplicación.
 *
 * @author Irene Condado Alcantarilla
 * @version 1.0
 */
object PikminRepository {

    /**
     * Obtiene la lista completa de Pikmin disponibles.
     *
     * Las descripciones se cargan desde los recursos string
     * para permitir la localización en distintos idiomas.
     *
     * @param context Contexto necesario para acceder a los recursos.
     * @return Lista de objetos Pikmin.
     */
    fun getPikminList(context: Context): List<Pikmin> {
        return listOf(
            Pikmin(
                id = 1,
                nombre = "Pikmin Rojo",
                descripcion = context.getString(R.string.desc_pikmin_rojo),
                imageResId = R.drawable.red_pikmin
            ),
            Pikmin(
                id = 2,
                nombre = "Pikmin Amarillo",
                descripcion = context.getString(R.string.desc_pikmin_amarillo),
                imageResId = R.drawable.yellow_pikmin
            ),
            Pikmin(
                id = 3,
                nombre = "Pikmin Azul",
                descripcion = context.getString(R.string.desc_pikmin_azul),
                imageResId = R.drawable.blue_pikmin
            ),
            Pikmin(
                id = 4,
                nombre = "Pikmin Morado",
                descripcion = context.getString(R.string.desc_pikmin_morado),
                imageResId = R.drawable.purple_pikmin
            ),
            Pikmin(
                id = 5,
                nombre = "Pikmin Blanco",
                descripcion = context.getString(R.string.desc_pikmin_blanco),
                imageResId = R.drawable.white_pikmin
            ),
            Pikmin(
                id = 6,
                nombre = "Pikmin Pétreo",
                descripcion = context.getString(R.string.desc_pikmin_petreo),
                imageResId = R.drawable.rock_pikmin
            ),
            Pikmin(
                id = 7,
                nombre = "Pikmin Alado",
                descripcion = context.getString(R.string.desc_pikmin_alado),
                imageResId = R.drawable.winged_pikmin
            ),
            Pikmin(
                id = 8,
                nombre = "Pikmin Gélido",
                descripcion = context.getString(R.string.desc_pikmin_gelido),
                imageResId = R.drawable.ice_pikmin
            ),
            Pikmin(
                id = 9,
                nombre = "Pikmin Luminoso",
                descripcion = context.getString(R.string.desc_pikmin_luminoso),
                imageResId = R.drawable.glow_pikmin
            )
        )
    }
}