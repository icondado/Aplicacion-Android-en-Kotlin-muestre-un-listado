package dam.pmdm.tarea2_condadoalcantarilla_irene.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import dam.pmdm.tarea2_condadoalcantarilla_irene.R
import dam.pmdm.tarea2_condadoalcantarilla_irene.model.Pikmin

/**
 * Adapter encargado de gestionar y enlazar una lista de objetos Pikmin
 * con un RecyclerView que los muestra en forma de cuadrícula.
 *
 * Este Adapter:
 * - Crea las vistas necesarias para cada elemento de la lista.
 * - Asocia los datos de cada Pikmin con su vista correspondiente.
 * - Gestiona los eventos de clic sobre cada elemento.
 *
 * @param pikminList Lista de objetos Pikmin que se mostrarán en el RecyclerView.
 * @param onPikminClick Función lambda que se ejecuta cuando el usuario
 * hace clic sobre un Pikmin. Recibe el Pikmin seleccionado como parámetro.
 *
 * @author Irene Condado Alcantarilla
 * @version 1.0
 */
class PikminAdapter(
    private val pikminList: List<Pikmin>,
    private val onPikminClick: (Pikmin) -> Unit
) : RecyclerView.Adapter<PikminAdapter.PikminViewHolder>() {

    /**
     * ViewHolder que representa cada elemento individual del RecyclerView.
     *
     * Se encarga de mantener las referencias a las vistas del layout
     * item_pikmin para evitar llamadas repetidas a findViewById.
     *
     * @param itemView Vista inflada correspondiente a un elemento de la lista.
     */
    inner class PikminViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /** CardView que contiene el elemento del Pikmin **/
        val cardView: CardView = itemView.findViewById(R.id.cardViewPikmin)

        /** ImageView que muestra la imagen del Pikmin */
        val imagePikmin: ImageView = itemView.findViewById(R.id.imagePikmin)

        /** TextView que muestra el nombre del Pikmin */
        val textNombre: TextView = itemView.findViewById(R.id.textNombrePikmin)
    }

    /**
     * Crea y devuelve un nuevo ViewHolder cuando el RecyclerView lo necesita.
     *
     * Este metodo infla el layout XML correspondiente a cada elemento
     * de la cuadrícula.
     *
     * @param parent ViewGroup al que se añadirá la nueva vista.
     * @param viewType Tipo de vista (no se usa en este caso).
     * @return Una nueva instancia de PikminViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PikminViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pikmin, parent, false)
        return PikminViewHolder(view)
    }

    /**
     * Asocia los datos de un objeto Pikmin con las vistas del ViewHolder.
     *
     * También configura el evento de clic sobre la cardView del Pikmin.
     *
     * @param holder ViewHolder que contiene las vistas del elemento.
     * @param position Posición del elemento dentro de la lista.
     */
    override fun onBindViewHolder(holder: PikminViewHolder, position: Int) {
        val pikmin = pikminList[position]

        // Asignar imagen y nombre del Pikmin
        holder.imagePikmin.setImageResource(pikmin.imageResId)
        holder.textNombre.text = pikmin.nombre

        // Configurar el evento de clic
        holder.cardView.setOnClickListener {
            onPikminClick(pikmin)
        }
    }

    /**
     * Devuelve el número total de elementos que contiene el Adapter.
     *
     * @return Tamaño de la lista de Pikmin.
     */
    override fun getItemCount(): Int = pikminList.size
}