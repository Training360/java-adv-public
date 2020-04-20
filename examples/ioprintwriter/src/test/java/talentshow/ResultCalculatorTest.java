package talentshow;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ResultCalculatorTest {

    private ResultCalculator rc = new ResultCalculator();


    @Before
    public void deleteFiles() throws IOException {
        Path file = Path.of("src/main/resources/result.txt");
        if (Files.exists(file)) {
            Files.delete(file);
        }
    }

    @Test
    public void gettersTest() {
        assertEquals(0, rc.getProductions().size());
        assertEquals(0, rc.getVotes().size());

        rc.getProductions().add(new Production(1, "Test"));
        rc.getVotes().add(new Vote(1, 2));

        assertEquals(0, rc.getProductions().size());
        assertEquals(0, rc.getVotes().size());

    }


    @Test
    public void readProductionsTest() {
        rc.readTalents();

        assertEquals(12, rc.getProductions().size());
        assertEquals(6, rc.getProductions().get(5).getId());
        assertEquals("Magician", rc.getProductions().get(5).getName());


    }


    @Test
    public void calculateVotesTest() {
        rc.calculateVotes();
        int testNum = 0;

        for (Vote v : rc.getVotes()) {
            if (v.getId() == 10) {
                testNum = v.getNumber();
            }
        }

        assertEquals(4, testNum);

    }


    @Test
    public void writeResultToFileTest() throws IOException {
        rc.calculateVotes();
        rc.readTalents();
        rc.writeResultToFile();
        Path file = Path.of("src/main/resources/result.txt");

        List<String> results = Files.readAllLines(file);
        for(String s : results){
            if(s.contains("9 Singer")){
                assertTrue(s.contains("5,22%"));
            }
        }


        assertTrue(results.get(results.size() - 1).equals("Winner: Actors_From_Shadow"));
    }
}
