import com.sun.tools.javac.Main;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class testJustInvestSystem {

    private final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    private final PrintStream originalSystemOut = System.out;
    private final InputStream originalSystemIn = System.in;

    @Before
    public void setUp()
    {
        //before
        //System.setOut(new PrintStream(byteArrayOutputStream));

        //after
        //System.setOut(originalSystemOut);
        //System.setIn(originalSystemIn);
    }

    @Test
    public void testingLogout()
    {
        System.setOut(new PrintStream(byteArrayOutputStream));

        String input = "3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String[] args = {};
        justInvestSystem.main(args);

        String output = byteArrayOutputStream.toString();
        System.out.println(output);
        assertTrue(output.contains("Exiting System. Bye for now ;~;"));

        System.setOut(originalSystemOut);
        System.setIn(originalSystemIn);
    }

    @Test
    public void testingSystemEntry()
    {
        System.setOut(new PrintStream(byteArrayOutputStream));

        String fullInput = "personName\nQazwsx12&\n1\n" + "personName\nQazwsx12&\n" + "8\n" + "3\n";

        System.setIn(new ByteArrayInputStream(fullInput.getBytes()));

        String[] args = {};
        justInvestSystem.main(args);

        String output = byteArrayOutputStream.toString();

        assertTrue(output.contains("Registration Complete, returning to home page."));
        assertTrue(output.contains("Enter username:"));
        assertTrue(output.contains("Enter password:"));
        assertTrue(output.contains("Enter Role:"));


        System.setOut(originalSystemOut);
        System.setIn(originalSystemIn);
    }
}
