package loc.example.dev.sportstickerapp061922.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import loc.example.dev.sportstickerapp061922.databinding.ActivityMainBinding
import loc.example.dev.sportstickerapp061922.model.ViewState
import loc.example.dev.sportstickerapp061922.viewmodel.TickerViewModel
import loc.example.dev.sportstickerapp061922.widget.TeamAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TickerViewModel by viewModel()
    private val teamAdapter by lazy { TeamAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
            binding = it
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = teamAdapter
        }

        binding.editText.addTextChangedListener {
            it?.let { viewModel.observeSearchTerm(it.toString()) }
        }

        viewModel.resultLiveData.observe(this) {
            it?.let { teamAdapter.submitList(it) }
        }

        viewModel.tickerLiveData.observe(this) {
            it?.let {
                when (it) {
                    is ViewState.Error -> {}
                    ViewState.Loading -> {}
                    is ViewState.Loaded -> {
                        binding.ticker.text = "[${it.data.id}] - ${it.data.name}"
                    }
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            with(Intent(this, EventActivity::class.java)) {
                startActivity(this)
            }
        }
    }
}