package by.budanitskaya.l.chemistryquiz

import android.app.Application
import by.budanitskaya.l.chemistryquiz.di.DaggerChemistryAppComponent

class ChemistryApp : Application() {

    val appComponent = DaggerChemistryAppComponent
        .builder()
        .injectContext(this)
        .buildAppComp()
}