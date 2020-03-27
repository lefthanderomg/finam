package andrey.murzin.finam.presentation

import andrey.murzin.finam.di.providers.MainProvider

interface MainComponentProvider {
    fun getMainComponent(): MainProvider
}