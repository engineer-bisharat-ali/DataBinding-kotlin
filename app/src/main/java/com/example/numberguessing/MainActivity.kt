package com.example.numberguessing

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.numberguessing.databinding.ActivityMainBinding
import com.example.numberguessing.databinding.LoserDialogBinding
import com.example.numberguessing.databinding.WinDialogBinding
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var randomNumber: Int = 0
    private var currentNumber: Int = 7
    private var msgHighHint: String = "Your Guess is High"
    private var msgLowHint: String = "Your Guess is Low"

    //    private var msgLose: String = "Your Lose"
//    private var msgWin: String = "Your Win"
//    private var msgWin: String = "Your Win"
    private lateinit var dialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        randomNumber()
        binding.guessModel2 = GuessModelClass(remainingNo = currentNumber.toString())


        binding.submitButton.setOnClickListener {
            val edittextInputNo: Int = binding.editText.text.toString().toInt()
            if (edittextInputNo == randomNumber) {
//                Toast.makeText(this, "You win", Toast.LENGTH_SHORT).show()
                showWinDialog()
//                GuessModelClass(hint = msgWin)

            } else if (edittextInputNo < randomNumber) {
//                Toast.makeText(this, "your guess is low ", Toast.LENGTH_SHORT).show()
                binding.guessModel = GuessModelClass(hint = msgLowHint)

            } else {
                binding.guessModel = GuessModelClass(hint = msgHighHint)
            }


            // when click on button clear the text
            binding.editText.text?.clear()






            if (currentNumber > 1) {
                currentNumber--
                binding.guessModel2 = GuessModelClass(remainingNo = currentNumber.toString())
            } else {
//                Toast.makeText(this, "your  lose ", Toast.LENGTH_SHORT).show()
                showLoseDialog()

            }
        }
    }


    private fun randomNumber(): Int {
        randomNumber = Random().nextInt(100) + 1
        return randomNumber
    }

//    private fun showWinDialog() {
//        dialog = Dialog(this)
//        val binding: WinDialogBinding = DataBindingUtil.inflate(layoutInflater, R.layout.win_dialog, null, false)
//        dialog.setContentView(R.layout.win_dialog)

//        binding.clickListener = this // Pass MainActivity as click listener
//        dialog.show()
//    }

    private fun showWinDialog() {
        dialog = Dialog(this)
        val binding: WinDialogBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.win_dialog, null, false)
        val winnerMsg = "The number is $randomNumber !"
        binding.winnerMessage = winnerMsg
        binding.clickListener = this // Pass MainActivity as click listener
        dialog.setContentView(binding.root)
        dialog.show()
    }

    private fun showLoseDialog() {
        dialog = Dialog(this)
        val binding: LoserDialogBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.loser_dialog, null, false)
        val loserMsg = "Determined number is $randomNumber"
        binding.loserMessage = loserMsg
        binding.clickListener = this // Pass MainActivity as click listener
        dialog.setContentView(binding.root)
        dialog.show()
    }


    fun onPlayAgainClicked() {
        // Reset the game here
        randomNumber()
        currentNumber = 7
        binding.guessModel2 = GuessModelClass(remainingNo = currentNumber.toString())
        binding.guessModel = GuessModelClass() // Clear the previous guess result
        dialog.dismiss() // Dismiss the dialog if it's showing
    }
}
