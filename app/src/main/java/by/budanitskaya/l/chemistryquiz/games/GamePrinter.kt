package by.budanitskaya.l.chemistryquiz.games

import javax.inject.Inject

class GamePrinter @Inject constructor() {

    fun getText(): String = "Hello Dagger Games!"
}