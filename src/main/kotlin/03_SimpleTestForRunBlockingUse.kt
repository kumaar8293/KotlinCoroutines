import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class `03_SimpleTestForRunBlockingUse` {

    @Test
    fun myFistTest() {
        Assert.assertEquals(10, 1 + 9)
    }

    @Test
    fun mySuspendingFunctionTest() {
        runBlocking {
            mySuspendingFunction(100)
            Assert.assertEquals(10, 1 + 9)
        }
    }
}