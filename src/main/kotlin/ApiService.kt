import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") id: Int): Post
}


@Serializable
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)