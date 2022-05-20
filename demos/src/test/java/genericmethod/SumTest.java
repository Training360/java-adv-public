package genericmethod;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumTest {

    @Test
    public void testSum() {
        List<Integer> l = Arrays.asList(1, 3, 5, 6, 8);
        int sum = Summarizer.sum(l, new ValueExtractor<Integer>() {
            @Override
            public int extract(Integer o) {
                return o.intValue();
            }
        });
        assertEquals(23, sum);

        List<String> s = Arrays.asList("x", "xxx", "xxxxx");
        int sum2 = Summarizer.sum(s, new ValueExtractor<String>() {
            @Override
            public int extract(String o) {
                return o.length();
            }
        });
        assertEquals(9, sum2);

        int sum3 = Summarizer.sum(s, new ValueExtractor<String>() {
            @Override
            public int extract(String o) {
                return o.charAt(0);
            }
        });
        assertEquals(360, sum3);
    }
}
