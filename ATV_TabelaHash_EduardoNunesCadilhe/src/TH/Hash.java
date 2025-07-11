package src.TH;

public class Hash {

    public int of(String frase){
        int hashValue = 0;
        for (int i = 0; i < frase.length(); i++) {
            hashValue = (((int)Math.pow(frase.charAt(i),i) * 27)  + hashValue);
        }
        return Math.abs(hashValue);
    }

}
