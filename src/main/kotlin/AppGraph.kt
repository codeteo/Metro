import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

@DependencyGraph(AppScope::class)
interface AppGraph {

    @Provides
    fun providesJson(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    fun providesRetrofit(
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

    @Provides
    fun providesService(
        retrofit: Retrofit
    ): ApiService = retrofit.create(ApiService::class.java)

}