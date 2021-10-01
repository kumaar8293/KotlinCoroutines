import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread


fun main() { //Execute in main thread
    println("Main program start $threadName")

    /** ======== Step 1 > About Thread ========
     *  Key points: Application will wait to finish until all the threads finish.
     **/

    /* thread { //Create a background thread (Worker thread)
         println("Fake work1 start $threadName")
         Thread.sleep(2000) // Pretend to do some work
         println("Fake work1 end $threadName")
     }*/

    /**  ======== Step 2 > About GlobalScope.launch ========
     * Key Points :
     * 1 => Un-like threads, for coroutines the application by default
     *        doesn't wait for it to finish its execution.(Means,
     *        application will not wait for coroutines to finish, it will finish the program automatically)
     *
     * 2 => If you want your main thread to wait for coroutines finish then we have to use Thread.sleep() manually
     *
     * 3 => If you want main thread to wait for Coroutines to finish then we have to us join()
     *        keyword but join keyword will work inside a suspend function (Will learn later)
     **/

    /*  GlobalScope.launch { // Create a background Coroutines that runs on a background thread
          println("Fake work 2 start $threadName")
          Thread.sleep(1000) // Pretend to do some work (Blocked the Thread)
          println("Fake work 2 end $threadName")
      }

      //Blocks the current main thread and wait for coroutine to finish(Practically not a right way to wait)
      Thread.sleep(2000)

      */

    /**  ======== Step 3 > About delay() ========
     * Read the Delay.kt file
     **/

    /* GlobalScope.launch { // Suppose thread name is t1
         println("Fake work 2 start $threadName")
         delay(1000) // Coroutine is suspended but Thread T1 is free
                     // (Not Blocked, Other coroutines can run on T1 thread)

         println("Fake work 2 end $threadName") //This line may run on T1 thread or some other thread.
     }
 */

    /** === Suspending function ====
     * A function with a 'suspend' modifier is known as suspending function.
     *
     * The suspending functions are only allowed to be called from another suspending function or a Coroutines.
     *
     * They can't be called from outside a Coroutine.
     * =============================================
     **/


    /**  ======== Step 4 > About runBlocking() ========
     * Read from official runBlocking(Builders.kt)
     * GlobalScope.launch() is non-blocking in nature whereas
     * runBlocking() blocks the thread in which it operates
     * ==>> Both creates a new COROUTINES but completely different
     **/

    /*GlobalScope.launch { // Suppose thread name is t1
        println("Fake work 2 start $threadName")
        delay(1000) // Coroutine is suspended but Thread T1 is free
        // (Not Blocked, Other coroutines can run on T1 thread)

        println("Fake work 2 end $threadName") //This line may run on T1 thread or some other thread.
    }

    runBlocking {  //Creates a coroutines that blocks the current thread (Main Thread)
        delay(2000) // Wait for coroutines to finish(Practically not a right way to wait)
    }*/
    /** Above 2 lines of code is indirectly equals to Thread.sleep(2000) **/

    runBlocking {
        GlobalScope.launch { // Suppose thread name is t1
            println("Fake work 2 start $threadName")
            delay(1000)
            println("Fake work 2 end $threadName")
        }

        //Creates a coroutines that blocks the main thread
        delay(2000)
    }
    println("Main program ends $threadName")
}

var threadName = Thread.currentThread().name

suspend fun mySuspendingFunction(times: Long) {
    delay(times)
}

/** =====  Extra Notes  =====
 *  As we know, One Thread can have multiple Coroutines, we should not use Thread.sleep() inside Coroutines
 *  Because it will block the Thread, and it will pause all the coroutines.
 *  We should always use delay() inside coroutines
 *
 **/