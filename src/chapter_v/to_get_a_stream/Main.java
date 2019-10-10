package chapter_v.to_get_a_stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: FangyiXu
 * @Date: 2019-06-04 17:21
 */
public class Main {
    public static void main(String[] args) {
        //Stream.of
        Stream<String> stringStream = Stream.of("java 8", "In", "actiOn");
        stringStream
                .map(String::toUpperCase)
                .forEach(System.out::println);

        //empty
        Stream<Object> emptyStream = Stream.empty();

        //Arrays.stream
        //...

        //由文件生成流
        long uniqueWords = 0;   //不重复的单词个数
        try(Stream<String> lines = Files.lines(Paths.get("/Users/xufangyi/Downloads/file4u.txt"), Charset.defaultCharset())) {  //lines每行为一个元素
            uniqueWords = lines.flatMap(
                    line -> Arrays.stream(line.split(" "))
            )
            .map(String::toUpperCase)
            .distinct()
            .count();
            System.out.println(uniqueWords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
