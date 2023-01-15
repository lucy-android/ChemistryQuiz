package by.budanitskaya.l.chemistryquiz.di

import by.budanitskaya.l.chemistryquiz.games.GamePrinter
import by.budanitskaya.l.chemistryquiz.games.GamesComponent
import dagger.Module

@Module(subcomponents = [GamesComponent::class])
class AppModule {

    fun provideGamesPrinter() = GamePrinter()


}