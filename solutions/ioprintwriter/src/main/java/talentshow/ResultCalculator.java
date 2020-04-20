package talentshow;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ResultCalculator {

    private List<Production> productions = new ArrayList<>();
    private List<Vote> votes = new ArrayList<>();
    private double votesNumber = 0;
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");


    public void readTalents() {
        Path file = Path.of("src/main/resources/talents.txt");
        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(" ");
                Production p = new Production(Long.parseLong(temp[0]), temp[1]);
                productions.add(p);
            }

        } catch (IOException e) {
            throw new IllegalStateException("Can't read file!", e);
        }
    }


    public void calculateVotes() {
        Path file = Path.of("src/main/resources/votes.txt");
        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            while ((line = br.readLine()) != null) {
                votesNumber++;
                int actVote = Integer.parseInt(line);
                voteAdder(actVote);
            }

        } catch (IOException e) {
            throw new IllegalStateException("Can't read file!", e);
        }
    }


    public void writeResultToFile() {
        Path file = Path.of("src/main/resources/result.txt");

        try (PrintWriter pw = new PrintWriter(Files.newBufferedWriter(file))) {

            for (Vote v : votes) {

                Production p = findProductionById(v.getId());
                pw.print(v.getId() + " ");
                pw.print(p.getName() + " ");
                double percent = (v.getNumber() / votesNumber) * 100;
                pw.print(decimalFormat.format(percent));
                pw.println("%");
            }

            pw.print("Winner: " + findWinner().getName());

        } catch (IOException e) {
            throw new IllegalStateException("Can't open file", e);
        }
    }


    private Production findWinner() {
        int max = votes.get(0).getNumber();
        long maxId = votes.get(0).getId();
        for (Vote v : votes) {
            if (v.getNumber() > max) {
                max = v.getNumber();
                maxId = v.getId();
            }
        }

        for (Production p : productions) {
            if (maxId == p.getId()) {
                return p;
            }
        }
        return null;

    }


    private Production findProductionById(long id) {
        for (Production p : productions) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private void voteAdder(int id) {
        boolean exist = false;
        for (Vote v : votes) {
            if (v.getId() == id) {
                v.incNum();
                exist = true;
            }
        }
        if (!exist) {
            votes.add(new Vote(id, 1));
        }


    }

    public List<Production> getProductions() {
        return new ArrayList<>(productions);
    }

    public List<Vote> getVotes() {
        return new ArrayList<>(votes);
    }
}
