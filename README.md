## Kotlin Coroutines Tutorials/Programs
### About this project:
* If you want to get started with Kotlin Coroutines, then may be this project is for you. Learn Kotlin Coroutines, its basics and Fundamentals from scratch. 
## Topics to be covered
##1. What are Coroutines?
   - Light-weight Threads but **`Coroutines != Thread.`**
   - Like Thread Coroutines can run in parallel/Concurrent, wait for each other, and communicate with each other.
   - Coroutines are very, very cheap - almost free, Creates thousands of them without any memory issue.
   - It is best for modern application.

**2. How are they related to Threads?**
   - In case of Thread, Application will wait to finish until all the threads finish.
   - Un-like threads, for coroutines the application by default doesn't wait for it to finish its execution.(Means, application will not wait for coroutines to finish, it will finish the program automatically).

**3. Coroutines Builders?** 
   - Coroutines Builder are used to create Coroutines.

**4. Types of Coroutines Builders?**

     A. launch [Fire and Forget]
   - Launches a new Coroutine **_without blocking the current thread_**.
   - Inherits the thread and coroutine scope of immediate parent coroutine.
   - Return the reference of **Job object**.
   - Using Job object you can cancel or wait for the coroutine to finish.

    B. async
   - Launches a new Coroutine without blocking the current thread.
   - Inherits the thread and coroutine scope of immediate parent coroutine.
   - Return the reference of **`Deferred<T>`** object, Which is subclass of Job object.
   - Using DeferredObject you can cancel or wait for the coroutine to finish.
   - We can use that return value `[val deferredValue = deferredJob.await()]`



## Author

* **Mukesh Kumar Patel** 