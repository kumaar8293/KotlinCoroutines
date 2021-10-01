import kotlinx.coroutines.*

/** =====  Why Would you cancel a Coroutine? =======
 *  A. Result no longer needed
 *  B. Coroutine taking too long to respond. etc.
 *
 * To cancel a Coroutine, it should be cooperative.
 * job.cancel() => If the coroutine is cooperative then cancel it.
 * job.join() => Wait for the coroutine to finish.
 *
 * job.cancelAndJoin() =>If the coroutine is cooperative then cancel it else Wait for the coroutine to finish.
 */

fun main() = runBlocking {
    println("Main Programs starts $threadName")

    /** ===== Step 1 [Non cooperative coroutine]=======
     * following coroutines is non-cooperative, so we can't cancel it.
     */
    /* val nonCooperativeJob = launch {
         for (i in 0..500){
             print("$i,")
             Thread.sleep(50)
         }
     }

     delay(200) // Lets print few values before cancel it
     nonCooperativeJob.cancel()
     nonCooperativeJob.join()*/

    /** ======= Step 2, What is cooperative? and how to make Coroutine cooperative =======
     *  => Periodically invoke a suspending function that chek for cancellation.
     *  => Only those suspending functions that belongs to kotlinx.coroutines
     *     package will make coroutine cooperative.
     *  => delay(), yield(), withContext(), withTimeOut() etc. are the suspending
     *     functions that belongs to kotlinx.coroutines package.
     */

    /* val cooperativeJob = launch {
         for (i in 0..500) {
             print("$i,")
 //            delay(50)
             yield() //or use delay() or any other suspending function as per your need
         }
     }

     delay(10) // Lets print few values before cancel it
     cooperativeJob.cancelAndJoin() //== cooperativeJob.cancel() + cooperativeJob.join()
 */
    /**There is another way to make coroutine cooperative
     *  => Explicitly check for cancellation status within the coroutine
     *       >> CoroutineScope.isActive boolean flag
     * **/

    val cooperativeJob = launch(Dispatchers.Default) { // Will check dispatchers later
        for (i in 0..500) {
            if (!isActive) { // This will make it cooperative
//                break
                return@launch  //We can break or return to the launch
            }
            print("$i,")
            Thread.sleep(5) //I am not using because it will make cooperative automatically as we have seen before.
        }
    }

    delay(10) // Lets print few values before cancel it
    cooperativeJob.cancelAndJoin() //== cooperativeJob.cancel() + cooperativeJob.join()
    println("\nMain Programs ends $threadName")

}