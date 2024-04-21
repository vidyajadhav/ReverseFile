package test.java.com.vidya.reverse;

import main.java.com.vidya.reverse.ReverseFileRandom;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ReverseFileContentTest {

    File file1, file2;

    @Before
    public void setUp() {
        try {
            file1 = new File("src/main/resources/file.txt");
            file2 = new File("src/main/resources/filereversed.txt");
            //write out data to the test files
            FileWriter fw1 = new FileWriter( file1 );
            BufferedWriter bw1 = new BufferedWriter( fw1 );
            bw1.write( "content for file1");
            bw1.close();
        }
        catch( IOException ioe ) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName() );
        }
    }
    /**
     *  Write to the two temporary files and run some asserts.
     */
    @Test
    public void test2TempFiles() {
        assertTrue( file1.exists() );
        assertTrue( file2.isFile() );

    }
    @Test
    public void testAddPass() throws IOException {
        BufferedReader readerContent = new BufferedReader(new FileReader(file1));
        String currentLine = readerContent.readLine();
        readerContent.close();
        assertEquals("content for file1", currentLine);

        ReverseFileContent.reverseContent(file1);

        BufferedReader reverseContents = new BufferedReader(new FileReader(file2));
        String reverselines = reverseContents.readLine();
        reverseContents.close();
        assertEquals("1elif rof tnetnoc", reverselines);
    }

}
