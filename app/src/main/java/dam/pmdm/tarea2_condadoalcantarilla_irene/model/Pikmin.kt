package dam.pmdm.tarea2_condadoalcantarilla_irene.model

import java.io.Serializable

/**
 * Modelo de datos que representa un Pikmin.
 *
 * Esta clase se utiliza para almacenar la información básica
 * de cada Pikmin y transferirla entre Activities mediante Intent,
 * gracias a que implementa Serializable.
 *
 * @property id Identificador único del Pikmin.
 * @property nombre Nombre del Pikmin.
 * @property descripcion Descripción detallada del Pikmin.
 * @property imageResId Identificador del recurso de imagen asociado.
 *
 * @author Irene Condado Alcantarilla
 * @version 1.0
 */
data class Pikmin(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val imageResId: Int  // Recurso de imagen
) : Serializable