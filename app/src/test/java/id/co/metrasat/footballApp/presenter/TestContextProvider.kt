package id.co.metrasat.footballApp.presenter

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestContextProvider: EventDetailsPresenter.CoroutineContextProvider(){
    override val main : CoroutineContext = Unconfined
}