import com.example.searchviewkotlin.DemoData
import com.example.searchviewkotlin.MyApi
import com.example.searchviewkotlin.LanguageData
import com.example.searchviewkotlin.MainActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityTest {
    @Mock
    private lateinit var api: MyApi
    private lateinit var mockWebServer: MockWebServer
    private lateinit var mainActivity: MainActivity
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(MyApi::class.java)
        mainActivity = MainActivity()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetAllDataSuccess() {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("Success")
        mockWebServer.enqueue(mockResponse)
        mainActivity.getAllData()
    }

    @Test
    fun testGetAllDataFailure() {
        val mockResponse = MockResponse()
            .setResponseCode(404)
            .setBody("Failure")
        mockWebServer.enqueue(mockResponse)
        mainActivity.getAllData()
    }
}
