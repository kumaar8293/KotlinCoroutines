import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/** ====== Execution of Coroutines ======
 *  1. Sequential Execution => Function execution are sequential by default.
 *  2. Concurrent Execution => Achieve concurrent execution by async{ }.
 *  3. Lazy Coroutine Execution => Lazily execute code in coroutine.
 */

fun main() = runBlocking {
    println("Main Method starts $threadName")

    /** ======= Program 1: Sequential execution =======
     * The Following code will be running on sequential basis
     * Code execution within coroutine is by default sequential.
     * **/

    /*  val msg1 = getMessageOne()
      val msg2 = getMessageTwo()
      println("The entire message is: $msg1 $msg2") */

    /** We can check time taken for the both suspend function
     * getMessageOne() takes 1000L and getMessageTwo() takes 1000L == Total 2000
     *  **/
    /* val time = measureTimeMillis {
         val msg0 = getMessageOne()
         val msg01 = getMessageTwo()
         println("The entire message is: $msg0 $msg01")
     }
     println("Complete in : $time")
     */

    /** ======= Program 2: Concurrent execution =======
     * In the following code getMessageOne() and getMessageTwo() are being called
     * from the different Coroutine so both will run asynchronous.
     *
     * Using launch{} we will still get Concurrency.[I am not using here because I am expecting some return value]
     * **/

    /* val time = measureTimeMillis {
         val msg0: Deferred<String> =
             async { // Since async is a coroutine builder, it will create a different coroutine on main thread.
                 getMessageOne()
             }
         val msg01: Deferred<String> = async { getMessageTwo() }
         println("The entire message is: ${msg0.await()} ${msg01.await()}")
     }
     println("Complete in : $time")*/

    /** ======= Program 3: Lazily execution =======
     * Lazy
     * **/

    /*  val msg0 = async { getMessageOne() }
      val msg1 = async { getMessageTwo() }*/
    /**
     * If we simply run the above code then it will execute the getMessageOne() && getMessageTwo()
     *  event we are not using the return value of that Coroutine.
     *
     *  With lazy, we can avoid that [If the return value is not being used
     *  then it won't execute the getMessageOne() & getMessageTwo()].
     */
    val msg0 = async(start = CoroutineStart.LAZY) { getMessageOne() }
    val msg1 = async (start = CoroutineStart.LAZY){ getMessageTwo() }
    println("We are using the return value : ${msg0.await()} ${msg1.await()}")

    println("Main Method ends $threadName")
}

private suspend fun getMessageOne(): String {
    delay(1000L)
    println("After getMessageOne() completed ")
    return "Hey"
}

private suspend fun getMessageTwo(): String {
    delay(1000L)
    println("After getMessageOne() completed ")
    return "Buddy"
}