package by.budanitskaya.l.chemistryquiz.di

import android.content.Context
import by.budanitskaya.l.chemistryquiz.MainActivity
import by.budanitskaya.l.chemistryquiz.games.GamesComponent
import by.budanitskaya.l.chemistryquiz.home.HomeFragment
import by.budanitskaya.l.chemistryquiz.notifications.NotificationsFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
interface ChemistryAppComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(homeFragment: HomeFragment)
    fun inject(notificationsFragment: NotificationsFragment)

    @Component.Builder
    interface AppCompBuilder {
        fun buildAppComp(): ChemistryAppComponent

        @BindsInstance
        fun injectContext(context: Context): AppCompBuilder
    }

    fun provideGamesBuilder(): GamesComponent.GamesBuilder

}