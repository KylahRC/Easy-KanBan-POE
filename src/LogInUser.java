import javax.swing.JOptionPane; //JOptionPane used to take user input and display messages
import java.util.Objects;
import java.lang.String;

public class LogInUser
{
    RegisterUser registeredUser;

    // Constructor that accepts a RegisterUser object
    public LogInUser(RegisterUser registeredUser)
    {
        this.registeredUser = registeredUser;
    }


    // Constructor that accepts a RegisterUser object
    public void LogInMethod()
    {
        // Use the registeredUser's getters to access the information
        String Username = registeredUser.getUsername();
        String password = registeredUser.getPassword();
        String name = registeredUser.getName();
        String surname = registeredUser.getSurname();
        boolean checkLogIn = false; // Declare checkLogIn before the loop
        do
        {
            String UsernameLogin = JOptionPane.showInputDialog("Welcome! Please enter your username to log in:");
            boolean UsernameCorrect = Objects.equals(UsernameLogin, Username);

            if (!UsernameCorrect)
            {
                String LoginFail = "Your Username is incorrect. Please try again.";
                JOptionPane.showMessageDialog(null, LoginFail);
            }
            else
            {
                String PasswordLogin = JOptionPane.showInputDialog("Welcome! Please enter your password to log in");
                boolean PasswordCorrect = Objects.equals(PasswordLogin, password);

                if (!PasswordCorrect)
                {
                    String LoginFail = "Your password is incorrect. Please try again.";
                    JOptionPane.showMessageDialog(null, LoginFail);
                }

                checkLogIn = UsernameCorrect && PasswordCorrect;
            }
        }

        while (!checkLogIn);

        if (checkLogIn)
        {
            String LoginSuccess = "Welcome " + name + " " + surname + "! Good to see you again!";
            JOptionPane.showMessageDialog(null, LoginSuccess);
        }
    }
}

