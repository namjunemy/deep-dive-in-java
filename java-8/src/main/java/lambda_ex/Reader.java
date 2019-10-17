package lambda_ex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    public static String processFile(BufferedReaderProcessor p) throws IOException {
        String fileName = Reader.class.getResource("/test.txt").getFile();

        // try with resource 자원 해제
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return p.process(br);
        }
    }
}