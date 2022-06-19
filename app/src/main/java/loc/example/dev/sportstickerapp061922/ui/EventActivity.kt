package loc.example.dev.sportstickerapp061922.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import loc.example.dev.sportstickerapp061922.databinding.ActivityEventBinding
import loc.example.dev.sportstickerapp061922.model.ViewState
import loc.example.dev.sportstickerapp061922.viewmodel.TickerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventBinding
    private val viewModel by viewModel<TickerViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_event)
        ActivityEventBinding.inflate(layoutInflater).also {
            binding = it
            setContentView(it.root)
        }

        viewModel.tickerLiveData.observe(this) {
            it?.let {
                when (it) {
                    is ViewState.Error -> {}
                    ViewState.Loading -> {}
                    is ViewState.Loaded -> binding.tvTicker.text =
                        "[${it.data.id}] - ${it.data.name}"
                }
            }
        }
    }
}