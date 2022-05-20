package genericclass;


public class ArraySearch {

    public DataPair<String> getFirstAndLastWord(String[] words){
        if (words == null) {
            throw new NullPointerException("Array should not be null");
        }
        if (words.length == 0){
            throw new IllegalArgumentException("Array should not be empty");
        }

        return new DataPair<>(words[0], words[words.length - 1]);
    }
}
