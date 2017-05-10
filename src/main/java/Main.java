import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        String str;
        File file = new File(args[1]);
        String dot = file.getName().substring(file.getName().lastIndexOf("."), file.getName().length());
        String fileName;
        if (args[0].equals("-e")) {
            try {
                str = new String(FileManager.readFile(file));
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
                return;
            }
            byte[] encodeByte = Base64.encode(str.getBytes());
            fileName = file.getName().substring(0, file.getName().lastIndexOf(".")) + "-Base64" + dot;
            FileManager.writeFile(fileName , encodeByte);
            System.out.println("encode: " + str + " -> ("
                    + new String(encodeByte) + ")");
        }

        if (args[0].equals("-d")) {
            try {
                str = new String(FileManager.readFile(file));
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден");
                return;
            }
            String result = new String(Base64.decode(str.getBytes()));
            System.out.println("decode: " + str + " -> ("
                    + result + ")");
            fileName = file.getName().substring(0, file.getName().lastIndexOf("-")) + "-unBase64" + dot;
            FileManager.writeFile(fileName, result.getBytes());
        }
    }
}

