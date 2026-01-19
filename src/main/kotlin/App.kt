import dev.zacsweers.metro.createGraph
import kotlin.system.exitProcess

suspend fun main() {
    println("Hello, Kotlin!")

    val appGraph = createGraph<AppGraph>()
    val repository = appGraph.repository

    val res = repository.getPost(1)
    println("MESA ME ${res.title}")

    exitProcess(0)
}