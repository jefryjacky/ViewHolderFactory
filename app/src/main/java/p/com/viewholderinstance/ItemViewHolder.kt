package p.com.viewholderinstance

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*
import p.com.viewholderinstanceannotation.ViewHolderAnnotation


@ViewHolderAnnotation("item_list")
class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(value: String){
        itemView.apply {
            tv_label.text = value
        }
    }
}