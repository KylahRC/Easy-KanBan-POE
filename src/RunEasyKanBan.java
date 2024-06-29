import javax.swing.JOptionPane; //JOptionPane used to take user input and display messages
import java.util.Objects;

public class RunEasyKanBan {
    public static void main(String[] args) {

        RegisterUser newUser = new RegisterUser();
        newUser.UserRegMethod(); // Call the method to register user details

        LogInUser userLogin = new LogInUser(newUser); // Pass the newUser object to LogInUser
        userLogin.LogInMethod(); // Now you can call LogInMethod without getting a NullPointerException

        CreateTasks taskMaker = new CreateTasks();
        taskMaker.createTasksMethod(); // Call the method to register user details

    }
}

