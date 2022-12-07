package corona.diego.quiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedSateHandle:SavedStateHandle) : ViewModel(){
    private val bancoPreguntas = listOf(
        Pregunta(R.string.pregunta_dios, true),
        Pregunta(R.string.pregunta_trinidad, true),
        Pregunta(R.string.pregunta_pedro, false),
        Pregunta(R.string.pregunta_judas, true),
        Pregunta(R.string.pregunta_elias, false),
        Pregunta(R.string.pregunta_hijo, true)

    )
    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    private var indice:Int
    get() = savedSateHandle.get(CURRENT_INDEX_KEY) ?: 0
    set(value) = savedSateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = bancoPreguntas[indice].respuesta
    val currentQuestionText: Int
        get() = bancoPreguntas[indice].textoPregunta
    fun siguientePregunta(){
        indice = (indice+1)%bancoPreguntas.size
    }

}