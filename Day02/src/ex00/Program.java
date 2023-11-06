package ex00;

import java.io.*;
import java.util.*;

public class Program {
    public static void main(String[] args) {
        // Создаем файл result.txt, если он не существует
        File resultFile = new File("result.txt");
        if (!resultFile.exists()) {
            try {
                resultFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Считываем сигнатуры из файла signatures.txt
        Map<String, List<Integer>> signatures = readSignaturesFromFile("./ex00/signatures.txt");

        // Запрашиваем у пользователя путь к файлу
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String filePath = scanner.nextLine();

        // Проверяем сигнатуру файла
        String fileType = checkFileType(filePath, signatures);

        // Записываем результат в файл result.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("result.txt", true))) {
            if (fileType != "UNDEFINED") {
                writer.println(fileType);
                System.out.println("PROCESSED");
            } else {
                System.out.println("UNDEFINED");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();

    }

    // Метод для чтения сигнатур из файла
    private static Map<String, List<Integer>> readSignaturesFromFile(String fileName) {
        Map<String, List<Integer>> signatures = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String fileType = parts[0];
                String[] signatureHexStrings = parts[1].split(" ");
                List<Integer> signature = new ArrayList<>();
                for (String hexString : signatureHexStrings) {
                    signature.add(Integer.parseInt(hexString, 16));
                }
                signatures.put(fileType, signature);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return signatures;
    }

    // Метод для проверки сигнатуры файла
    private static String checkFileType(String filePath, Map<String, List<Integer>> signatures) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[8];
            int bytesRead = inputStream.read(buffer);
            if (bytesRead != 8) {
                return "UNDEFINED";
            }
            for (Map.Entry<String, List<Integer>> entry : signatures.entrySet()) {
                String fileType = entry.getKey();
                List<Integer> signature = entry.getValue();
                boolean matches = true;
                for (int i = 0; i < signature.size(); i++) {
                    if (signature.get(i) != (buffer[i] & 0xFF)) {
                        matches = false;
                        break;
                    }
                }
                if (matches) {
                    return fileType;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "UNDEFINED";
    }
}