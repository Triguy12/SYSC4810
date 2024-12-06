import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
public class InputControllerTest {

    @Test
    public void testIsValidCommand()
    {
        InputController controller = new InputController();

        List<String> toTest = Arrays.asList("1", "2", "6", "7", "8");
        String commandWord = "1";
        assertTrue(controller.isValidInput(commandWord, toTest));

        commandWord = "1";
        assertTrue(controller.isValidInput(commandWord, toTest));

        commandWord = "2";
        assertTrue(controller.isValidInput(commandWord, toTest));

        commandWord = "3";
        assertFalse(controller.isValidInput(commandWord, toTest));

        commandWord = "4";
        assertFalse(controller.isValidInput(commandWord, toTest));

        commandWord = "5";
        assertFalse(controller.isValidInput(commandWord, toTest));

        commandWord = "6";
        assertTrue(controller.isValidInput(commandWord, toTest));

        commandWord = "7";
        assertTrue(controller.isValidInput(commandWord, toTest));

        commandWord = "8";
        assertTrue(controller.isValidInput(commandWord, toTest));

        commandWord = "A";
        assertFalse(controller.isValidInput(commandWord, toTest));

        commandWord = "Giberish";
        assertFalse(controller.isValidInput(commandWord, toTest));
    }
}
