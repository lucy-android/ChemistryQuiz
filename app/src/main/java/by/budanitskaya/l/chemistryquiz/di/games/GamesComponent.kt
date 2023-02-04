package by.budanitskaya.l.chemistryquiz.di.games

import android.content.Context
import by.budanitskaya.l.chemistryquiz.games.GamePrinter
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent
interface GamesComponent {

    @Subcomponent.Builder
    interface GamesBuilder {

        fun gamesComponent(): GamesComponent

        @BindsInstance
        fun addContext(context: Context): GamesBuilder
    }

    fun getPrinter(): GamePrinter

}