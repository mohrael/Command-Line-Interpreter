
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class CLITest {

    @Test
    void testPwd()  {
        CLI cl = new CLI();

        // Test case 1: No arguments provided
        String pwd = cl.pwd("");
        String expected = System.getProperty("user.dir");
        System.out.println("Current working directory: " + pwd);
        assertEquals(expected, pwd);

        // Test case 2: Invalid command with arguments
        String invalidResult = cl.pwd("invalid");
        System.out.println("Invalid command result: " + invalidResult);
        assertEquals("Invalid Command!", invalidResult);
    }


    @Test
    void testMkdir()  {
        CLI cl = new CLI();
        // Test case 1: Create a new directory
        String dirName = "hello";
        cl.createDirectory(dirName);
        File dir = new File(System.getProperty("user.dir"), dirName);
        assertTrue(dir.exists());

//        cl.createDirectory(dirName);

        // Test case 2: Attempt to create the same directory again
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        cl.createDirectory(dirName);
        String output=outputStream.toString().trim();

        assertTrue(output.contains("already exists or could not be created"),
                "Expected message about existing directory should be present");

        // Test case 3: Attempt to create a directory without a name
        outputStream.reset();
        cl.createDirectory("");
        output = outputStream.toString().trim();
        assertEquals("Directory name required", output,
                "Expected 'Directory name required' message should be present");
    }

}
