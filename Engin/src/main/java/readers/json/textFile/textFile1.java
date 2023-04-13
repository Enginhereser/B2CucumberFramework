package readers.json.textFile;

import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class textFile1 {
    public static String ENTER="\n";
    @Test
    public void textCreateFile() throws IOException {
        String file="src/test/resources/datafiles/myFile.txt";

        FileWriter fileWriter=new FileWriter(file);
        fileWriter.write("engin"+ENTER);
        fileWriter.write("Hereser"+ENTER);
        fileWriter.close();
    }
    @Test
    public void textCreateFile2() throws IOException {
        String file="src/test/resources/datafiles/myFile.txt";

        FileWriter fileWriter=new FileWriter(file,true);
        fileWriter.write("Metin Asim"+ENTER);
        fileWriter.write("Hereser"+ENTER);
        fileWriter.close();
    }
    @Test
    public void textReadFile() throws IOException {
        String file = "src/test/resources/datafiles/myFile.txt";
        Scanner sc=new Scanner(new File(file));
        while (sc.hasNext()){
            System.out.println(sc.nextLine());
        }
    }
}
