import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@DependencyGraph(AppScope::class)
interface AppGraph {
    val repository: Repository

    @Provides
    fun providesInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = Level.BODY
    }

    @Provides
    fun providesOkHttpClient(
        logger: HttpLoggingInterceptor
    ): okhttp3.OkHttpClient =
        okhttp3.OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

    @Provides
    fun providesJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    fun providesRetrofit(
        json: Json,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    fun providesService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

    @Binds val RepositoryImpl.bind: Repository

}