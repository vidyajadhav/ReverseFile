package main.java.com.vidya.reverse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import static java.util.Objects.requireNonNull;

public class ReverseFileContent {

    public static void main(String... args) throws IOException {
        File file = new File("src/main/resources/file.txt");

        reverseContent(file);
    }

    public static void reverseContent(File file) throws IOException {
        Reader input = null;
        RandomAccessFile output = null;
        long length = file.length();
        String encoding = "UTF-8";
        InputStream content = null;
        try {

            content = new FileInputStream(file);

            input = new InputStreamReader(content, encoding);

            output = new RandomAccessFile(new File("src/main/resources/filereversed.txt"), "rwd");

            CharsetEncoder encoder = Charset.forName(encoding).newEncoder();
            for (int data; (data = input.read()) != -1; ) {
                ByteBuffer bytes = null;
                bytes = encoder.encode(CharBuffer.wrap(new char[]{(char) data}));
                length -= bytes.limit();
                output.seek(length);
                output.write(bytes.array());
            }
        } catch (FileNotFoundException | CharacterCodingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally{
            requireNonNull(input).close();
            requireNonNull(output).close();
        }
    }

}
