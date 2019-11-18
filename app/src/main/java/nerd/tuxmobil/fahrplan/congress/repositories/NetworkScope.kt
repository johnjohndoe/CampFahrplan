package nerd.tuxmobil.fahrplan.congress.repositories

import kotlinx.coroutines.*

class NetworkScope(

        executionContext: ExecutionContext,
        parentJob: Job,
        exceptionHandler: CoroutineExceptionHandler

) {

    private val scope = CoroutineScope(executionContext.network + parentJob + exceptionHandler)

    fun launchNamed(name: String, block: suspend CoroutineScope.() -> Unit): Job {
        return scope.launch(context = CoroutineName(name), block = block)
    }

}
