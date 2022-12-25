package by.budanitskaya.l.chemistryquiz.di

import by.budanitskaya.l.chemistryquiz.MainActivity
import dagger.Component

@Component
interface ChemistryAppComponent {

    fun inject(mainActivity: MainActivity)
}