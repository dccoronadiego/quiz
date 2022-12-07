package corona.diego.quiz

import android.app.Activity
import android.content.ClipData.newIntent
import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import corona.diego.quiz.databinding.ActivityMainBinding

 private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val quizViewModel:QuizViewModel by viewModels()
    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        //Handle the result
        if (result.resultCode == Activity.RESULT_OK) {
            quizViewModel.isCheater =
                result.data?.getBooleanExtra(EXTRA_ANSWER_SHOWN, false) ?: false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        //binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "tengo un viewModel: ${quizViewModel}")


        binding.trueButton.setOnClickListener { view: View->
            checkAnswer(true,view)

        }
        binding.falseButton.setOnClickListener { view: View->
            checkAnswer(false,view)
        }
        binding.nextButton.setOnClickListener {
            quizViewModel.siguientePregunta()
            updateQuestion()
        }
        binding.cheatButton?.setOnClickListener {
        // Start CheatActivity
        }

        updateQuestion()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            blurCheatButton()
        }

        binding.cheatButton?.setOnClickListener {
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            cheatLauncher.launch(intent)
        }
        val intent = Intent(this, CheatActivity::class.java)
        cheatLauncher.launch(intent)
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "En el onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "En el onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "En el onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "En el onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "En el onDestroy")
    }
    private fun updateQuestion() {
        val preguntaTextResId = quizViewModel.currentQuestionText
        binding.questionText.setText(preguntaTextResId)
    }
    private fun checkAnswer(userAnswer:Boolean, view: View){
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when {
            quizViewModel.isCheater -> R.string.judgment_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }

        val colorBackground = if(userAnswer ==correctAnswer){
            R.color.verde
        }else{
            R.color.rojo
        }
        val mySnack = Snackbar.make(view, messageResId, Snackbar.LENGTH_LONG)
        mySnack.setBackgroundTint(resources.getColor(R.color.green))
        mySnack.show()
    }
    @RequiresApi(Build.VERSION_CODES.S)
    private fun blurCheatButton() {
        val effect = RenderEffect.createBlurEffect(
            10.0f,
            10.0f,
            Shader.TileMode.CLAMP
        )
        binding.cheatButton?.setRenderEffect(effect)
    }
}
