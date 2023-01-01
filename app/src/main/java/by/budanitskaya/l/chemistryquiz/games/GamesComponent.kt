package by.budanitskaya.l.chemistryquiz.games

import android.content.Context
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

    fun inject(gamesFragment: GamesFragment)

}