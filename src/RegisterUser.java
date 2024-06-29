import javax.swing.JOptionPane; //JOptionPane used to take user input and display messages
import java.util.Objects;

public class RegisterUser {
    String name;
    String surname;
    String username;
    String password;


    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void UserRegMethod()
    {

        boolean checkUserName;
        do
        {

            //Ask user for their name
            name = JOptionPane.showInputDialog("Enter your first name:");
            //Ask user for their username
            surname = JOptionPane.showInputDialog("Enter your last name:");


            username = JOptionPane.showInputDialog("Create username that contains: \nAn underscore_ \n5 characters");
            int userSize = username.length(); //get user length

            boolean checkUserLength = userSize >= 5; //make sure user is 5 characters long
            boolean checkUserSpecial = false; //set the character is _ condition to false so the base state is false

            for (char UsernameCharacters : username.toCharArray()) //goes over each character in the username
            {
                if ("_".contains(String.valueOf(UsernameCharacters))) //checks if the character in the username is an _ by converting each character to a string
                {
                    checkUserSpecial = true;
                    break;
                }
            }

            checkUserName = checkUserLength && checkUserSpecial;


            if (checkUserName)
            {
                String registerUser = "Username successfully captured";
                //When the username is right then say success message
                JOptionPane.showMessageDialog(null, registerUser);
            }
            else
            {
                String registerUser = "Username is not correctly formatted.\nPlease ensure that your username contains: \nAn underscore \n5 characters";
                //When the username isn't right then say fail message
                JOptionPane.showMessageDialog(null, registerUser);
            }
        }
        while (!checkUserName);



        boolean checkPasswordComplexity;

        do
        {

            password = JOptionPane.showInputDialog("""
                    Create password, your password must be:
                    8 characters
                    a capital letter
                    a number
                    a special character.""");
            int passwordSize = password.length();

            boolean checkPasswordLength = passwordSize >= 8; //checks password length
            boolean checkPasswordSpecial = false;
            boolean checkPasswordCapital = false;
            boolean checkPasswordNumber = false;

            for (char PasswordCharacters : password.toCharArray())
            {
                if ("!@#$%^&*<>?".contains(String.valueOf(PasswordCharacters)))
                { //check for special characters that can be typed by a computer keyboard
                    checkPasswordSpecial = true;
                }
                else if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(String.valueOf(PasswordCharacters)))
                { //check for capital letter
                    checkPasswordCapital = true;
                }
                else if ("1234567890".contains(String.valueOf(PasswordCharacters)))
                { //check for number
                    checkPasswordNumber = true;
                }
            }

            checkPasswordComplexity = checkPasswordLength && checkPasswordSpecial && checkPasswordCapital && checkPasswordNumber;

            if (checkPasswordComplexity)
            {
                String registerUser = "Password successfully captured";
                //if password is good show success message
                JOptionPane.showMessageDialog(null, registerUser);
            }
            else
            {
                String registerUser = """
                        Password is not correctly formatted,\s
                        please ensure that the password contains at least:
                        8 characters
                        a capital letter
                        a number
                        a special character.""";
                //if password isn't good show fail message
                JOptionPane.showMessageDialog(null, registerUser);
            }
        }
        while (!checkPasswordComplexity);


    }
}

