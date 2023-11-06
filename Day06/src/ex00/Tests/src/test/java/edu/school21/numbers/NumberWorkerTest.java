import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {

    private NumberWorker numberWorker;

    @BeforeEach
    public void setUp() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 7, 11})
    public void isPrimeForPrimes(int number) {
        boolean result = numberWorker.isPrime(number);
        Assertions.assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 8, 9, 10})
    public void isPrimeForNotPrimes(int number) {
        boolean result = numberWorker.isPrime(number);
        Assertions.assertFalse(result);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, -1, -5})
    public void isPrimeForIncorrectNumbers(int number) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            numberWorker.isPrime(number);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void digitsSum(int number, int expectedSum) {
        int result = numberWorker.digitsSum(number);
        Assertions.assertEquals(expectedSum, result);
    }
}