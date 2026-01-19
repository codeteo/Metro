interface Repository {
    suspend fun getPost(id: Int): Post
}

class RepositoryImpl(
    private val apiService: ApiService
) : Repository {
    override suspend fun getPost(id: Int): Post {
        return apiService.getPost(id)
    }
}