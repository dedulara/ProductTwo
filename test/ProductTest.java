import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    Product p1;

    @BeforeEach
    void setUp() {
        p1 = new Product("000001", "Pipeweed", "Long Bottom Leaf", 600.0);
    }

    @Test
    void setCostDouble()
    {
        p1.setCostDouble(600.0);
        assertEquals(600.0, p1.getCostDouble());                         //Success
    }

    @Test
    void toCSVDataRecord()                                                          //Success
    {
        p1.toCSVDataRecord();
        assertEquals("000001, Pipeweed, Long Bottom Leaf, 600.0", p1.toCSVDataRecord());
    }
}