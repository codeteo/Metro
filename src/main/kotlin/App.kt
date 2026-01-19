import dev.zacsweers.metro.createGraph

suspend fun main() {
    println("Hello, Kotlin!")

    val appGraph = createGraph<AppGraph>()
    val repository = appGraph.repository

    repository.getPost(1)
}