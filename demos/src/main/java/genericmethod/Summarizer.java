package genericmethod;

import java.util.List;

public class Summarizer {

    public static <T> int sum(List<T> l, ValueExtractor<T> extractor) {
        int sum = 0;
        for (T o: l) {
            int value = extractor.extract(o);
            sum += value;
        }
        return sum;
    }
}
