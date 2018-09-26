package id.co.metrasat.footballApp.presenter

import com.google.gson.Gson
import id.co.metrasat.footballApp.helper.ApiRepository
import id.co.metrasat.footballApp.helper.MainView
import id.co.metrasat.footballApp.helper.TheSportDBApi
import id.co.metrasat.footballApp.model.EventsItem
import id.co.metrasat.footballApp.model.EventsResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class EventDetailsPresenterTest {
    @Mock
    private lateinit var view: MainView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository


    private lateinit var presenter: EventDetailsPresenter
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = EventDetailsPresenter(view, apiRepository, gson, TestContextProvider())
    }


    @Test
    fun getTeamDetail() {
        val events : MutableList<EventsItem> = mutableListOf()
        val response = EventsResponse(events)
        val league  = "441613"

        `when` (gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getLookUpEvent(league)),
                EventsResponse::class.java)).thenReturn(response)
        presenter.getTeamDetail(league)

        verify(view).showLoading()
        verify(view).showEventList(events)
        verify(view).hideLoading()

    }


}
