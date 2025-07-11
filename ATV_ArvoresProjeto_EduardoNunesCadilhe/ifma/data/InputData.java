package ifma.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InputData {
    public static List<Integer> readFromFile(String filename) {
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./ifma/data/" + filename))) {
            String line = br.readLine();
            if (line != null) {
                String[] values = line.split(";");
                for (String value : values) {
                    numbers.add(Integer.parseInt(value.trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return numbers;
    }
} 