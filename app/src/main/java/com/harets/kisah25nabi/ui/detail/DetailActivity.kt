package com.harets.kisah25nabi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.harets.kisah25nabi.R
import com.harets.kisah25nabi.data.KisahResponse
import com.harets.kisah25nabi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private var _binding : ActivityDetailBinding? = null
    private val binding get() = _binding as ActivityDetailBinding

    companion object {
        const val EXTRA_DATA = "extra data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(binding.root)


        val data = intent.getParcelableExtra<KisahResponse>(EXTRA_DATA) as KisahResponse

        binding.apply {
            Glide.with(this@DetailActivity).load(data.imageUrl).into(detailImage)
            detailNama.text = data.name
            detailTempat.text = "Tempat : " + data.tmp
            detailUsia.text = "Usia : " + data.usia
            detailDesk.text = data.description
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}