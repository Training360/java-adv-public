package ioreaderclasspath.countries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CountryStatistics {

    private List<Country> countries = new ArrayList<>();

    public void readFromFile(String fileName){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(
                CountryStatistics.class.getResourceAsStream(fileName)))){

            String line;

            while((line=reader.readLine())!=null){
                String[] temp = line.split(" ");
                Country c = new Country(temp[0],Integer.parseInt(temp[1]));
                countries.add(c);
            }

        } catch (IOException e) {
            throw new IllegalStateException("Can not read file", e);
        }
    }

    public int numberOFCountries(){
        return countries.size();
    }

    public Country mostBorderCountries(){
        Country max = countries.get(0);
        for (Country c : countries){
            if(c.getBorderCountries()>max.getBorderCountries()){
                max=c;
            }
        }
        return max;
    }


    public List<Country> getCountries() {
        return new ArrayList<>(countries);
    }
}
