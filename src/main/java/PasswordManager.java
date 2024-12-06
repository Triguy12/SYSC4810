import org.mindrot.jbcrypt.BCrypt;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.security.SecureRandom;

public class PasswordManager {
    List<String> weakPasswords;
    List<String> validSpecialCharacters;

    public PasswordManager() {

        weakPasswords = new ArrayList<String>() {{
            add("password");
            add("qwerty");
            add("123456789");
            add("Qazwsx123&");
            add("Uiop321&");
            add("123456");
            add("123456789");
            add("12345");
            add("12345678");
            add("111111");
            add("123123");
            add("1234567890");
            add("1234567");
            add("qwerty123");
            add("000000");
            add("1q2w3e");
            add("aa12345678");
            add("abc123");
            add("password1");
            add("1234");
            add("qwertyuiop");
            add("123321");
            add("password123");
        } };

        validSpecialCharacters = new ArrayList<String>() {{
            add("!");
            add("@");
            add("#");
            add("$");
            add("%");
            add("*");
            add("&");
        } };
    }

    public boolean checkIfValid(String username, String password)
    {
        if(username == password){
            System.out.println("Username and Password can not be the same.");
            return false;
        }

        if(weakPasswords.contains(password)){
            System.out.println("Password was found on list ok weak passwords.");
            return false;
        }

        if(password.length() < 8 || password.length() > 12 )
        {
            System.out.println("Password must be between 8 and 12 characters long");
            return false;
        }

        boolean upperCaseFlag = false;
        boolean lowerCaseFlag = false;
        boolean numericDigitFlag = false;
        boolean specialCharacterFlag = false;

        for (Character c : password.toCharArray()) {
            if(Character.isUpperCase(c))
            {
                upperCaseFlag = true;
            }

            if(Character.isLowerCase(c))
            {
                lowerCaseFlag = true;
            }

            if(c >= '0' && c <= '9')
            {
                numericDigitFlag = true;
            }
        }

        for (String vsc : validSpecialCharacters) {
            if(password.contains(vsc))
            {
                specialCharacterFlag = true;
            }
        }

        if(!upperCaseFlag){
            System.out.println("Password must contain at least one upper-case letter.");
            return false;
        }

        if(!lowerCaseFlag){
            System.out.println("Password must contain at least one lower-case letter.");
            return false;
        }

        if(!numericDigitFlag){
            System.out.println("Password must contain at least numerical digit.");
            return false;
        }

        if(!specialCharacterFlag){
            System.out.println("Password must contain at least one special character from the following: !, @, #, $, %, *, &");
            return false;
        }

        return true;
    }

    private String generateHashBcrypt(String password, String salt)
    {
        int costFactor = 12;
        if(salt.equals(""))
        {
            salt = BCrypt.gensalt(costFactor);
        }
        String hash = BCrypt.hashpw(password, salt);
        return hash;
    }

    private String generatePasswordHash(String username, String password)
    {
        if(!checkIfValid(username, password))
        {
            System.out.println("Invalid Password");
            return "";
        }

        String salt = "";
        String hash = generateHashBcrypt(password, salt);
        return username + " " + hash;
    }

    public boolean addUsernamePasswordPair(String fileName, String username, String password)
    {
        try{
            FileWriter fileWriter = new FileWriter(fileName, true);
            String out = generatePasswordHash(username, password);
            if(out.equals(""))
            {
                return false;
            }
            fileWriter.write(out + "\n");
            fileWriter.close();
            return true;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean verifyUsernamePasswordPair(String username, String password)
    {
        String[] storedString = getPasswordHash(username, "passwd.txt");
        String storedHash = storedString[1];
        String salt = storedHash.substring(0, 29);
        String hashToVerify = generateHashBcrypt(password, salt);

        if(storedHash.equals(hashToVerify))
        {
            return true; // user can log in
        }

        return false; //user can not log in
    }

    private String[] getPasswordHash(String username, String fileName)
    {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String currentLine;
            while ((currentLine = reader.readLine()) != null)
            {
                String[] tokens = currentLine.split(" ");
                if(tokens[0].equals(username))
                {
                    return tokens;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }


}
