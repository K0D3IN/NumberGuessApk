package com.byapps.deneme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.byapps.deneme.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var randomNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button1.setOnClickListener {
            checkGuess()
        }
        //sıfırlama butonunu aktifleştiriyorum
        binding.sifirlaButton.setOnClickListener {
            resetGame()
        }

        generateRandomNumber()

        // Easter egg: Loglara sayıyı yerleştiriyorum
        Log.d("EasterEgg", "sayi: $randomNumber")
    }

    private fun generateRandomNumber() {
        randomNumber = Random.nextInt(1, 101)
    }

    private fun checkGuess() {
        val userGuess = binding.textnumero.text.toString().toIntOrNull()

        if (userGuess != null) {
            //küçük bir easter egg daha
            if (userGuess == 31) {
                binding.cevaptext.text = getString(R.string.gulmek)
            } else {
                when {
                    userGuess < randomNumber -> binding.cevaptext.text = getString(R.string.EBG)
                    userGuess > randomNumber -> binding.cevaptext.text = getString(R.string.ESN)
                    else -> binding.cevaptext.text = getString(R.string.cong)
                }
            }
        } else {
            binding.cevaptext.text = getString(R.string.EVN)
        }
    }

    private fun resetGame() {
        //sıfırlama butonu fonksiyonu
        generateRandomNumber()
        binding.textnumero.text.clear()
        binding.cevaptext.text = ""
    }
}
