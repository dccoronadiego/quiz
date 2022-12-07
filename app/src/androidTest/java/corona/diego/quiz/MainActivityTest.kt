package corona.diego.quiz

import androidx.annotation.StringRes
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    private lateinit var scenario:ActivityScenario<MainActivity>
    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }
    @Test
    fun verificarSiSeMuestraPrimerPreguntaOnLaunch(){
        Espresso.onView(ViewMatchers.withId(R.id.question_text))
            .check(matches(withText(R.string.pregunta_dios)))
    }
    @Test
    fun seMuestraLaSegundaPreguntaAlDarClick(){
        onView(withId(R.id.next_button)).perform(click())
        onView(withId(R.id.question_text))
            .check(matches(withText(R.string.pregunta_trinidad)))
    }
    

    @Test
    fun seMantieneElEstadodeLaUI(){
        onView(withId(R.id.next_button)).perform(click())
        scenario.recreate()
        onView(withId(R.id.question_text))
            .check(matches(withText(R.string.pregunta_trinidad)))
    }
}
