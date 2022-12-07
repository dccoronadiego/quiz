package corona.diego.quiz

import androidx.lifecycle.SavedStateHandle
import org.junit.Test

import org.junit.Assert.*


class QuizViewModelTest{
    @Test
    fun proveeElTextoDePreguntaEsperado(){
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)
        assertEquals(R.string.pregunta_dios,quizViewModel.currentQuestionText)
        quizViewModel.siguientePregunta()
        assertEquals(R.string.pregunta_trinidad,quizViewModel.currentQuestionText)
    }
@Test
fun funcinaElBancoPreguntas(){
    val savedStateHandle = SavedStateHandle(mapOf(CURRENT_INDEX_KEY to 3))
    val quizViewModel = QuizViewModel(savedStateHandle)
    assertEquals(R.string.pregunta_judas,quizViewModel.currentQuestionText)
    quizViewModel.siguientePregunta()
    assertEquals(R.string.pregunta_dios,quizViewModel.currentQuestionText)
}
}