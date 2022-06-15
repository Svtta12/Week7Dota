package science.example.week7dota.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import coil.transform.CircleCropTransformation
import science.example.week7dota.data.Repository.Companion.PHOTO_URL
import science.example.week7dota.databinding.FragmentItemBinding
import science.example.week7dota.model.HeroDota

class DotaAdapter : ListAdapter<HeroDota, DotaVewHolder>(DiffCallback()) {

    var onItemClickListener: ((HeroDota) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DotaVewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentItemBinding.inflate(layoutInflater, parent, false)
        return DotaVewHolder(binding)
    }

    override fun onBindViewHolder(holder: DotaVewHolder, position: Int) {
        val dotaItem = getItem(position)
        holder.bind(dotaItem)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(dotaItem)
        }
    }
}

class DotaVewHolder(private val binding: FragmentItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: HeroDota) {

        binding.imageHero.load(PHOTO_URL + item.img) {
            transformations(CircleCropTransformation())
        }

        binding.textName.text = item.name
        binding.textLocoName.text = item.localizedName
    }
}

private class DiffCallback : DiffUtil.ItemCallback<HeroDota>() {
    override fun areItemsTheSame(oldItem: HeroDota, newItem: HeroDota): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: HeroDota, newItem: HeroDota): Boolean =
        oldItem.name == newItem.name
}