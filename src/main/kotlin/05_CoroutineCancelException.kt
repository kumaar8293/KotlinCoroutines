import kotlinx.coroutines.*

/** ===== Handling Exceptions =======
 * Cancellable suspending functions such as yield(), delay() etc. throw
 *  CancellationException on the coroutine cancellation
 *
 *  We can't run suspending function from Finally block, because the coroutine running this code is already cancelled.
 *
 *  If we want to execute a suspending function from a finally block then wrap the
 *  code  within the withContext(NonCancellable) function
 */

fun main() = runBlocking {

    println("Main function start $threadName")

    /** ======== Program 1 =========
     * **/
    /*val job = launch(Dispatchers.Default) {

        try {
            for (i in 0..500) {
                print("$i,")
                delay(5) // we can use yield() as well
            }
        } catch (cancellation: CancellationException) {
            print("\n Exception caught safely")
        } finally {
            print("\n Close resource in finally")
        }
    }
    delay(10)
    job.cancelAndJoin()*/

    /** ======= Program 2, delay() inside finally ======
     * We can't run suspending function from Finally block, because the coroutine running this code is already cancelled.
     * If we want to execute a suspending function from a finally block then wrap the
     *  code  within the withContext(NonCancellable) function
     */

    /* val job = launch(Dispatchers.Default) {

         try {
             for (i in 0..500) {
                 print("$i,")
                 delay(5) // we can use yield() as well
             }
         } catch (cancellation: CancellationException) {
             print("\n Exception caught safely")
         } finally {

 //            delay(1000)
             */
    /** If we run like this then it won't print the below line,
     * because it's a suspending function and Main thread won't wait for it to get executed.
     *
     * We can use withContext() here, it is a CoroutineBuilder
     *
     * NOTE : We should not use suspending function inside finally block.
     *//*
            withContext(NonCancellable) {
                delay(100) // Now main thread will wait
            }
            print("\n Close resource in finally")
        }
    }
    delay(10)
    job.cancelAndJoin()*/

    /** ======= Program 3, Custom cancellation message =========
     *  **/
    val job = launch(Dispatchers.Default) {

        try {
            for (i in 0..500) {
                print("$i,")
                delay(5) // we can use yield() as well
            }
        } catch (ex: CancellationException) {
            print("\n Exception caught safely: ${ex.message}")
        } finally {
            print("\n Close resource in finally")
        }
    }
    delay(10)
//    job.cancel("My Own crash message")
    job.cancel(CancellationException("My Own crash message"))
    job.join()
    println("\nMain function end $threadName")

}