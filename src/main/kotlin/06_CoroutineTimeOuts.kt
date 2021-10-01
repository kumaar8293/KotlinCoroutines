import kotlinx.coroutines.*

/** ======= Timeouts =========
 * withTimeout()
 * withTimeoutOrNull()
 */

fun main() = runBlocking {
    println("Main function starts: $threadName")

    /** ====== Practice 1 -> withTimeout =>  TimeoutCancellationException  without try catch=====
     * If we run the following code without try/catch then program will crash
     * **/
    /*withTimeout(2000) {
        for (i in 0..500) {
            print("$i,")
            delay(500)
        }
    }*/

    /** ====== Practice 2 -> withTimeout => TimeoutCancellationException with try catch **/
    /* withTimeout(2000) {
         try {
             for (i in 0..500) {
                 print("$i,")
                 delay(500)
             }
         } catch (ex: TimeoutCancellationException) {
             // code..
         } finally {
             //code..
         }
     }*/

    /** ====== Practice 3 -> withTimeoutOrNull ==========
     * If we don't want to use try and catch block, or we want to return some value.
     *
     * NOTE : If Coroutine did not complete within the given time then it will return null
     * **/

    val data: String? = withTimeoutOrNull(2000) {
        for (i in 0..500) {
            print("$i,")
            delay(500)
        }
        "You can return anything"
    }
    println("\nReturned value is $data")

    println("Main function ends: $threadName")
}