package chapter_III;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author: FangyiXu
 * @Date: 2019-05-28 14:49
 */
public class FileUtils {
    public interface Functional {
        String process(BufferedReader bufferedReader) throws IOException;
    }

    public static String processFile(Functional functional, String path) throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            return functional.process(bufferedReader);
        }

    }
}
