package ex01;

import java.io.*;
import java.util.*;

public class Program {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Program <file1> <file2>");
            return;
        }

        try {
            // Считываем тексты из файлов
            String text1 = readFile(args[0]);
            String text2 = readFile(args[1]);

            // Создаем словарь на основе текстов и записываем его в файл
            createDictionary(text1, text2);

            // Создаем файл dictionary.txt, если он не существует
            File resultFile = new File("dictionary.txt");
            if (!resultFile.exists()) {
                try {
                    resultFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Считываем словарь из файла
            Map<String, Integer> dictionary = readDictionary("dictionary.txt");

            // Создаем векторы на основе текстов и словаря
            double[] vector1 = createVector(text1, dictionary);
            double[] vector2 = createVector(text2, dictionary);

            // Вычисляем сходство между векторами
            double similarity = computeSimilarity(vector1, vector2);
            // Округляем к нижней границе
            double roundedNumber = Math.floor(similarity * 100) / 100;
            // Выводим результат
            System.out.println("Similarity = " + roundedNumber);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static String readFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    public static Map<String, Integer> readDictionary(String fileName) {
        Map<String, Integer> dictionary = new HashMap<>();
        int index = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.put(line, index);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public static void createDictionary(String text1, String text2) {
        // Создаем словарь на основе двух текстов
        Set<String> words = new HashSet<>();
        words.addAll(Arrays.asList(text1.split("\\s+")));
        words.addAll(Arrays.asList(text2.split("\\s+")));

        // Записываем словарь в файл dictionary.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("dictionary.txt"))) {
            for (String word : words) {
                writer.println(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double[] createVector(String text, Map<String, Integer> dictionary) {
        // Создаем вектор на основе текста и словаря
        double[] vector = new double[dictionary.size()];
        String[] words = text.split("\\s+");
        for (String word : words) {
            Integer index = dictionary.get(word);
            if (index != null) {
                vector[index] += 1;
            }
        }
        return vector;
    }

    public static double computeSimilarity(double[] vector1, double[] vector2) {
        // Вычисляем сходство между двумя векторами
        double dotProduct = 0;
        double norm1 = 0;
        double norm2 = 0;
        for (int i = 0; i < vector1.length; i++) {
            dotProduct += vector1[i] * vector2[i];
            norm1 += vector1[i] * vector1[i];
            norm2 += vector2[i] * vector2[i];
        }
        double similarity = dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
        return similarity;
    }
}