package p.com.viewholderinstance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(value: String){
        itemView.apply {
            tv_label.text = value
        }
    }

    companion object{
        fun create(viewGroup: ViewGroup): ItemViewHolder{
            val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_list,
                viewGroup, false)
            return ItemViewHolder(view)
        }
    }
}