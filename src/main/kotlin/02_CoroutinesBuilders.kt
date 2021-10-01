import kotlinx.coroutines.*

/**
 * Coroutines Builder are used to create Coroutines
 *  ===== Types of Coroutines Builders? =====
 *   > launch, async, runBlocking
 */


fun main() {
    println("Main program start $threadName")

    /** ====== launch [Fire_And_Forget]======
     * Launches a new Coroutine without blocking the current thread
     *    => Inherits the thread and coroutine scope of immediate parent coroutine.
     * Return the reference og Job object
     * Using Job object you can cancel or wait for the coroutine to finish.
     * We can also check if its active, isCancelled, isCompleted
     *  GlobalScope.launch {  }  => Global launch
     */
    /* runBlocking {
         val job: Job = launch { // Thread: Main [Inherit the scope from parent Coroutines]
             println("Fake work start $threadName") //Thread: Main
             delay(1000) // Coroutine is suspended but Thread main is free (Not Blocked)
             println("Fake work 2 end $threadName") //Either main thread or some other thread
         }
         job.join() //join() will wait to finish the coroutines, only after that next statement will be executed
     }
 */

    /** ====== async ======
     * Launches a new Coroutine without blocking the current thread
     *    => Inherits the thread and coroutine scope of immediate parent coroutine.
     * Return the reference og Deferred<T> object, Which is subclass of Job object
     * Using DeferredObject you can cancel or wait for the coroutine to finish.
     * We can also check if its active, isCancelled, isCompleted
     * GlobalScope.async {  }  => launch at global level
     *
     * DeferredObject means it contains some value [Deferred<T>] depending on the return type of coroutine
     * We can use that  return value [val deferredValue = deferredJob.await()]
     */

    runBlocking {
        val deferredJob: Deferred<Int> = async {
            println("Fake work start $threadName")
            delay(1000)
            println("Fake work end $threadName")
            10001
        }

        /**
         * If you don't want to use the return type then use join() to wait
         * But of you want to use return type then use await() instead of join()
         */
        // deferredJob.join()
        val deferredValue = deferredJob.await()
        println("Deferred object value $deferredValue")
    }

    println("Main program ends $threadName")
}

/** ===== runBlocking()
 * runBlocking() is generally used to write test cases to test the suspending functions
 * NOTE :: launch and async are non-blocking in nature but runBlocking() blocks the thread
 */