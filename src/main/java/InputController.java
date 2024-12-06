import java.util.List;

public class InputController {

    public InputController()
    {

    }

    public boolean isValidInput(String input, List<String> validInputs)
    {
        if(validInputs.contains(input))
        {
            return true;
        }
        return false;
    }

    public boolean sanitizeInput(String input)
    {
        return true;
    }
}
