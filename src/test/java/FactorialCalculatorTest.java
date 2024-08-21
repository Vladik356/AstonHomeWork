import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {

    @Test
    void testFactorialOfZero() {
        FactorialCalculator calculator = new FactorialCalculator();
        assertEquals(1, calculator.factorial(0));
    }

    @Test
    void testFactorialOfPositiveNumber() {
        FactorialCalculator calculator = new FactorialCalculator();
        assertEquals(120, calculator.factorial(5));
    }

    @Test
    void testFactorialOfNegativeNumber() {
        FactorialCalculator calculator = new FactorialCalculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.factorial(-1));
    }
}
