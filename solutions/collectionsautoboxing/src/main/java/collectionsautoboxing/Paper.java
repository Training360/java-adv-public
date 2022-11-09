package collectionsautoboxing;

import java.util.HashMap;
import java.util.Map;

public class Paper {

    private Map<String, Integer> paper = new HashMap<>();

    public Map<String, Integer> getPaper() {
        return new HashMap<>(paper);
    }

    public void putFurtherPaper(String className, int kilograms) {
        if (kilograms == 0) {
            return;
        }
        if (!paper.containsKey(className)) {
            paper.put(className, kilograms);
        } else {
            paper.put(className, paper.get(className) + kilograms);
        }
    }

    public String getWinnerClass() {
        String winner = "";
        int maxPaper = Integer.MIN_VALUE;
        for (String s : paper.keySet()) {
            if (paper.get(s) > maxPaper) {
                maxPaper = paper.get(s);
                winner = s;
            }
        }
        return winner;
    }

    public int getTotalWeight() {
        int totalWeight = 0;
        for (int kg : paper.values()) {
            totalWeight += kg;
        }
        return totalWeight;
    }
}
