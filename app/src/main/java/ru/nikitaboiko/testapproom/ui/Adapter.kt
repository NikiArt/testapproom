package ru.nikitaboiko.testapproom.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import ru.nikitaboiko.testapproom.R
import ru.nikitaboiko.testapproom.presenters.AdapterPresenter

class Adapter(private val adapterPresenter: AdapterPresenter) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return adapterPresenter.getCount()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.pos = position
        adapterPresenter.bind(holder)
    }

    public class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var pos = 0

        val model = view.item_model
        val submodel = view.item_submodel
        val number = view.item_number
        val age = view.item_age
        val color = view.item_color

        fun getCurrentPos(): Int {
            return pos
        }

        fun setValuesOnView(
            model: String,
            submodel: String,
            number: String,
            age: String,
            color: String
        ) {
            this.model.text = model
            this.submodel.text = submodel
            this.number.text = number
            this.age.text = age
            this.color.text = color

        }

    }
}

