package loc.example.dev.sportstickerapp061922.widget

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import loc.example.dev.sportstickerapp061922.databinding.ViewHolderItemBinding
import loc.example.dev.sportstickerapp061922.db.entity.Team
import loc.example.dev.sportstickerapp061922.viewmodel.TeamViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class TeamAdapter : ListAdapter<Team, TeamViewHolder>(object : DiffUtil.ItemCallback<Team>() {
    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Team, newItem: Team) = oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            TeamViewHolder(this)
        }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class TeamViewHolder(private val binding: ViewHolderItemBinding) :
    RecyclerView.ViewHolder(binding.root), KoinComponent {
    private val viewModel: TeamViewModel = get()

    init {
        binding.viewModel = viewModel
    }

    fun bind(item: Team) {
        binding.textView.text = item.name
        binding.item = item
    }
}