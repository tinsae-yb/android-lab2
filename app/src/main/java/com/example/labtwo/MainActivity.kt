package com.example.labtwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.labtwo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val chemicals = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.addChemicalButton.setOnClickListener {
            val chemicalName : String = binding.addChemicalTextEdit.text.toString().trim()

            if(chemicalName.isEmpty()){
                toaster("Chemical name is empty")
            }else{
               if (chemicals.contains(chemicalName) ) {
                   binding.statusText.text = "Chemical $chemicalName is already available"

               } else {

                   chemicals.add(chemicalName)
                   binding.statusText.text = "Chemical $chemicalName added successfully"
                   binding.addChemicalTextEdit.text.clear()
               }
            }
        }

        binding.guessButton.setOnClickListener {
            val chemicalName : String = binding.guessTextEdit.text.toString().trim()

            if(chemicalName.isEmpty()){
                toaster("Chemical name is empty")
            }else{

                if(chemicals.isEmpty()){
                    toaster("Add chemicals first")
                    return@setOnClickListener
                }
                val randChemical = chemicals[ (chemicals.size *  Math.random()).toInt() ]
                if(chemicalName.lowercase()==randChemical.lowercase()){
                    binding.statusText.text = "Congratulations! You guessed it right. It was $randChemical"
                    binding.guessTextEdit.text.clear()
                }else{
                    binding.statusText.text = "Sorry, Wrong guess. The correct answer was $randChemical"
                }


            }
        }


        setContentView(binding.root)
    }

    fun toaster(message : String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

    }
}