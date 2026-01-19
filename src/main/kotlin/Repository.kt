import dev.zacsweers.metro.Inject

interface Repository {
    suspend fun getPost(id: Int): Post
}

@Inject
class RepositoryImpl(
    private val apiService: ApiService
) : Repository {
    override suspend fun getPost(id: Int): Post {
        return apiService.getPost(id)
    }
}