package posmachine;


import javafx.geometry.Pos;
import org.graalvm.compiler.hotspot.stubs.Stub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.text.CollationKey;

import static org.mockito.Mockito.when;

public class PosMachineTest {


    @Test
    public void should_get_receipt_using_real_price_calculator() {
        //given
        PriceCalculator priceCalculator = new PriceCalculator();
        PosMachine posMachine = new PosMachine(priceCalculator);
        //when
        //then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            posMachine.getReceipt("Coke");
        });
    }

    @Test
    public void should_get_receipt_using_stub_price_calculator() {
        //given
        String receipt = "Name: Coke, Price: 10.0";
        PriceCalculator stubCalculator = new StubPriceCalculator();
        PosMachine posMachine = new PosMachine(stubCalculator);
        Assertions.assertEquals(receipt, posMachine.getReceipt("Coke"));
    }

    @Test
    public void should_get_receipt_using_real_price_calculator_with_mockito() {
        String receipt = "Name: Coke, Price: 10.0";
        PriceCalculator calculator = Mockito.mock(PriceCalculator.class);
        PosMachine posMachine = new PosMachine(calculator);
        final String productName = "Coke";

        when(calculator.calculate(productName)).thenReturn(10.0);

        Assertions.assertEquals(receipt,posMachine.getReceipt(productName));
    }

    private class StubPriceCalculator extends PriceCalculator {
        @Override
        public double calculate(String productName) {
            return (10.0);
        }
    }
}
