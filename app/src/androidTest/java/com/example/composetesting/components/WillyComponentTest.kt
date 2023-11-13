package com.example.composetesting.components

import androidx.compose.ui.test.assertContentDescriptionContains
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.doubleClick
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.longClick
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTextReplacement
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import androidx.compose.ui.test.swipeLeft
import androidx.compose.ui.test.swipeRight
import androidx.compose.ui.test.swipeUp
import org.junit.Rule
import org.junit.Test

class WillyComponentTest {

    // Creamos la configuracion del test
    @get: Rule
    val composeTestRule = createComposeRule()

    // Creamos el test
    @Test
    fun myFirstTest(){
        // Le pasamos el componente a testear
        composeTestRule.setContent {
            WillyComponent()
        }
        // FINDER
        // este es solo 1 componente
        composeTestRule.onNodeWithText("Willy") // accedemos a un componente por su texto
        composeTestRule.onNodeWithTag("component1") // accedemos a un componente por su tag
        composeTestRule.onNodeWithContentDescription("superImage") // accedemos a un componente por su description

        // esto son listados de componentes
        composeTestRule.onAllNodesWithText("w", ignoreCase = true) // accedemos a todos los componentes que contenga esto 'w'
        composeTestRule.onAllNodesWithContentDescription("component1") // accedemos a todos los componentes que su tag sea 'component1'
        composeTestRule.onAllNodesWithContentDescription("visualIcon") // accedemos a todos los componentes que su descripcion sea 'visualIcon'

        // ACTIONS
        // accion de un componente concreto
        composeTestRule.onNodeWithText("Willy").performClick()
        // accion del primer componente del listado
        composeTestRule.onAllNodesWithText("w", ignoreCase = true).onFirst().performClick() // accedemos al primero

        composeTestRule.onNodeWithText("Willy").performTouchInput {
            doubleClick() // doble click
            longClick() // mantener presionado
            swipeDown() // deslizar hacia abajo
            swipeUp() // deslizar hacia arriba
            swipeLeft() // deslizar hacia la izquierda
            swipeRight() // deslizar hacia la derecha
        }
        composeTestRule.onNodeWithText("Willy").performScrollTo() // hace scroll (si nuestro componente tiene scroll)
        composeTestRule.onNodeWithText("Willy").performImeAction() // boton de accion del teclado
        composeTestRule.onNodeWithText("Willy").performTextClearance() // borrar el texto del textfield
        composeTestRule.onNodeWithText("Willy").performTextInput("hola") // esto se lo pone a mi componente
        composeTestRule.onNodeWithText("Willy").performTextReplacement("world") // esto lo reemplaza
        // se pueden anidar acciones
        composeTestRule.onNodeWithText("Willy").performScrollTo().performClick().performTextInput("Yeeee") // el performTextInput no permite seguir anidando,
        // tendriamos que volver a seleccionar el nodo en la siguiente linea definirle las acciones despues del cambio del input
        composeTestRule.onNodeWithText("Willy").performTextReplacement("adios")

        // ASSERTIONS
        composeTestRule.onNodeWithText("Willy").assertExists() // comprueba si existe
        composeTestRule.onNodeWithText("Willy").assertDoesNotExist() // comprueba si no existe
        composeTestRule.onNodeWithText("Willy").assertContentDescriptionEquals("") // comprueba si la descripcion es la misma
        composeTestRule.onNodeWithText("Willy").assertContentDescriptionContains("") // comprueba si la descripcion contiene ''
        composeTestRule.onNodeWithText("Willy").assertIsDisplayed() // comprueba si se muestra (es visible)
        composeTestRule.onNodeWithText("Willy").assertIsNotDisplayed() // comprueba si no se muestra (no es visible)
        composeTestRule.onNodeWithText("Willy").assertIsEnabled() // comprueba si esta habilitado
        composeTestRule.onNodeWithText("Willy").assertIsNotEnabled() // comprueba si esta deshabilitado
        composeTestRule.onNodeWithText("Willy").assertIsSelected() // comprueba si esta seleccionado (radiobutton)
        composeTestRule.onNodeWithText("Willy").assertIsNotSelected() // comprueba si no esta seleccionado (radiobutton)
        composeTestRule.onNodeWithText("Willy").assertIsFocused() // comprueba si esta focus
        composeTestRule.onNodeWithText("Willy").assertIsNotFocused() // comprueba si no esta focus
        composeTestRule.onNodeWithText("Willy").assertIsOn() // comprueba si esta checked (checkbox)
        composeTestRule.onNodeWithText("Willy").assertIsOff() // comprueba si no esta checked (checkbox)
        composeTestRule.onNodeWithText("Willy").assertTextEquals("") // comprueba si el texto es igual a ''
        composeTestRule.onNodeWithText("Willy").assertTextContains("w")  // comprueba si el texto contiene ''
    }

    // Buenas practicas: el nombre del test describa toda la accion del test
    @Test
    fun whenComponentStart_thenVerifyContentIsWilly(){
        composeTestRule.setContent {
            WillyComponent()
        }

        composeTestRule.onNodeWithText("willy", ignoreCase = true).assertExists()
        composeTestRule.onNodeWithTag("textFieldName").assertTextContains("WillY")
    }

    @Test
    fun whenNameIsAdded_thenVerifyTextContainGreeting() {
        composeTestRule.setContent {
            WillyComponent()
        }

        composeTestRule.onNodeWithTag("textFieldName").performTextReplacement("Pepe")
        composeTestRule.onNodeWithTag("textGreeting").assertTextEquals("Te llamas Pepe")
    }
}