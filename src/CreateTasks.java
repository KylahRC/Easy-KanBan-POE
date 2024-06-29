import javax.swing.JOptionPane;
import java.util.Objects;

class TaskDisplay // Parallel arrays used for storage of task data
{
    private static final int MAX_TASKS = 10;
    // Maximum number of tasks a user can store at once

    private String[] developerNames = new String[MAX_TASKS];
    //stores developer name, has both name and surname

    private String[] taskNames = new String[MAX_TASKS];
    //stores the name users give to tasks

    private String[] taskIDs = new String[MAX_TASKS];
    //stores the task id of each task

    private int[] taskDurations = new int[MAX_TASKS];
    //stores the duration set by the user for each task

    private String[] taskStatuses = new String[MAX_TASKS];
    //stores the status of each task

    private int totalDuration = 0;
    //counter for the total duration of all tasks (totalDuration += taskDuration)

    private int taskCount = 0;
    // Counter for the number of tasks the user has added so far

    // Method to autogenerate a task ID for the tasks the user makes
    public String generateTaskID(String taskName, String developerName, int taskNumber)
    //in brackets are all the variables needed in this method
    {
        return taskName.substring(0, 2) + ":" + (taskNumber + 1) + ":" + developerName.substring(developerName.length() - 3).toUpperCase();
    }

    // Method to add a tasks kept separate for good coding practices
    public void addTask(String developerName, String developerSurname, String taskName, int taskDuration, String taskStatus)
    {
        if (taskCount < MAX_TASKS)
        {
            developerNames[taskCount] = developerName + " " + developerSurname;
            taskNames[taskCount] = taskName;
            taskIDs[taskCount] = generateTaskID(taskName, developerName, taskCount);
            taskDurations[taskCount] = taskDuration;
            taskStatuses[taskCount] = taskStatus;
            totalDuration += taskDuration;
            taskCount++;
        }

        else
        {
            JOptionPane.showMessageDialog(null, "Maximum number of tasks reached.");
        }
    }



    // Method to delete a task
    public boolean deleteTask(String taskName)
    {
        for (int i = 0; i < taskCount; i++)
        {
            if (this.taskNames[i].equalsIgnoreCase(taskName))
            {
                // Shift all elements to the left to fill the gap
                for (int j = i; j < taskCount - 1; j++)
                {
                    developerNames[j] = developerNames[j + 1];
                    taskNames[j] = taskNames[j + 1];
                    taskIDs[j] = taskIDs[j + 1];
                    taskDurations[j] = taskDurations[j + 1];
                    taskStatuses[j] = taskStatuses[j + 1];
                }
                // Decrement taskCount and totalDuration
                taskCount--;
                totalDuration -= taskDurations[i];
                // Nullify the last element since it's now a duplicate
                developerNames[taskCount] = null;
                taskNames[taskCount] = null;
                taskIDs[taskCount] = null;
                taskDurations[taskCount] = 0;
                taskStatuses[taskCount] = null;
                return true;
            }
        }
        return false;
    }

    // Getters for each array
    public String[] getDeveloperNames()
    {
        return developerNames;
    }

    public String[] getTaskNames()
    {
        return taskNames;
    }

    public String[] getTaskIDs()
    {
        return taskIDs;
    }

    public int[] getTaskDurations()
    {
        return taskDurations;
    }

    public String[] getTaskStatuses()
    {
        return taskStatuses;
    }

    public int getTotalDuration()
    {
        return totalDuration;
    }

    public int getTaskCount()
    {
        return taskCount;
    }

}

class CreateTasks
{
    private TaskDisplay taskDisplay = new TaskDisplay();
    //import methods and variables from above class
    private int taskCounter = 1;
    //counts tasks so that they never exceed 10 stored

    public void createTasksMethod()
    {
        boolean selectedQuit = false;
        //while the user doesn't want to quit

        while (!selectedQuit)
        {
            String selectedOption = JOptionPane.showInputDialog("Welcome to EasyKanban! Type 1 to Add Tasks. Type 2 to Show Report. Type 3 to Quit");

            if (Objects.equals(selectedOption, "1"))
            {
                JOptionPane.showMessageDialog(null, "Add Task selected");
                String numTasksMaking = JOptionPane.showInputDialog("Please enter how many Tasks you wish to add today");
                int numTasks = Integer.parseInt(numTasksMaking);

                while (numTasks > 0)
                {
                    String taskName = JOptionPane.showInputDialog("Please name your task");
                    String taskDescription = JOptionPane.showInputDialog("Please describe your task in 50 characters or less (optional)");
                    // Description is optional bc of the test data not having it so needed null exception

                    if (taskDescription != null && taskDescription.length() > 50)
                    {
                        JOptionPane.showMessageDialog(null, "Please shorten your description, it may not exceed 50 characters");
                        continue;
                        // Ask for the description again if it's too long
                    }

                    String devName = JOptionPane.showInputDialog("Please enter your name");
                    String devSurname = JOptionPane.showInputDialog("Please enter your surname");
                    String taskDurationString = JOptionPane.showInputDialog("How long will this task take? Please enter the time in hours (no text units or decimals)");
                    int taskDuration = Integer.parseInt(taskDurationString);
                    //convert and store duration as an integer for obvious reasons


                    if (taskDuration < 0)
                    {
                        JOptionPane.showMessageDialog(null, "Duration must be a positive number.");
                        continue;
                    }
                    else if (taskDuration == 0)
                    {
                        JOptionPane.showMessageDialog(null, "Task duration cannot be 0 hours, please round up to the nearest hour");
                        continue;
                    }


                    String taskStatus = JOptionPane.showInputDialog("What is the status of this task?\n1) To do\n2) Done\n3) Doing");
                    String taskStatusOut = switch (taskStatus)
                            //was more compact than an if statement, may change all number menus to this
                    {
                        case "1" -> "To Do";
                        case "2" -> "Done";
                        default -> "Doing";
                    };


                    JOptionPane.showMessageDialog(null, "Here are the details of your task:"
                            + "\nTask status: " + taskStatusOut
                            + "\nDeveloper details: " + devName + " " + devSurname
                            + "\nTask number: " + taskCounter
                            + "\nTask Name: " + taskName
                            + "\nTask Description: " + taskDescription
                            + "\nTask ID: " + taskDisplay.generateTaskID(taskName, devName, taskCounter - 1)
                            + "\nTask duration: " + taskDuration + " hours");

                    taskDisplay.addTask(devName, devSurname, taskName, taskDuration, taskStatusOut);
                    //adds the details to the arrays
                    taskCounter++;
                    // Increment taskCounter after adding the task
                    numTasks--;
                    // Decrement numTasks as one task is added

                    JOptionPane.showMessageDialog(null, "Your total time needed to complete your tasks is: " + taskDisplay.getTotalDuration() + " hours");
                }
            }

            else if (Objects.equals(selectedOption, "2"))
            {
                String taskDisplayOption = JOptionPane.showInputDialog("How would you like to display your tasks? " +
                        "\n1) List all reports" +
                        "\n2) List completed tasks" +
                        "\n3) Show longest task" +
                        "\n4) Search by task name" +
                        "\n5) Search by developer name" +
                        "\n6) Delete a task");

                if (Objects.equals(taskDisplayOption, "1"))
                {
                    StringBuilder report = new StringBuilder();
                    //used to pull and display info from arrays
                    for (int i = 0; i < taskDisplay.getTaskCount(); i++)
                    {
                        report.append("Task Name: ").append(taskDisplay.getTaskNames()[i])
                                .append("\nDeveloper Details: ").append(taskDisplay.getDeveloperNames()[i])
                                .append("\nTask ID: ").append(taskDisplay.getTaskIDs()[i])
                                .append("\nTask Duration: ").append(taskDisplay.getTaskDurations()[i])
                                .append("\nTask Status: ").append(taskDisplay.getTaskStatuses()[i])
                                .append("\n\n");
                    }
                    JOptionPane.showMessageDialog(null, report.toString());
                }

                else if (Objects.equals(taskDisplayOption, "2"))
                {
                    StringBuilder tasksReport = new StringBuilder("Completed Tasks:\n");
                    for (int i = 0; i < taskDisplay.getTaskCount(); i++)
                    {
                        if (taskDisplay.getTaskStatuses()[i].equals("Done"))
                        //only tasks assigned done will be displayed
                        {
                            tasksReport.append("Task Name: ").append(taskDisplay.getTaskNames()[i])
                                    .append("\nDeveloper Details: ").append(taskDisplay.getDeveloperNames()[i])
                                    .append("\nTask ID: ").append(taskDisplay.getTaskIDs()[i])
                                    .append("\nTask Duration: ").append(taskDisplay.getTaskDurations()[i])
                                    .append("\nTask Status: ").append(taskDisplay.getTaskStatuses()[i])
                                    .append("\n\n");
                        }
                    }

                    JOptionPane.showMessageDialog(null, tasksReport.toString());
                }

                else if (Objects.equals(taskDisplayOption, "3"))
                {
                    int longestTaskIndex = -1;
                    //default value used to check if somehow there is no longest task (user hasnt entered a task yet"
                    int maxDuration = 0;
                    //storage for highest duration
                    for (int i = 0; i < taskDisplay.getTaskCount(); i++)
                    {
                        if (taskDisplay.getTaskDurations()[i] > maxDuration)
                        //will check if the next tasks duration is higher than the current highest task stored, kinda like a sorter
                        {
                            maxDuration = taskDisplay.getTaskDurations()[i];
                            longestTaskIndex = i;
                        }
                    }

                    if (longestTaskIndex != -1)
                    {
                        JOptionPane.showMessageDialog(null, "The longest task is:\n" +
                                "Task Name: " + taskDisplay.getTaskNames()[longestTaskIndex] +
                                "\nDeveloper Details: " + taskDisplay.getDeveloperNames()[longestTaskIndex] +
                                "\nTask ID: " + taskDisplay.getTaskIDs()[longestTaskIndex] +
                                "\nTask Duration: " + taskDisplay.getTaskDurations()[longestTaskIndex] +
                                "\nTask Status: " + taskDisplay.getTaskStatuses()[longestTaskIndex]);
                    }

                    else
                    {
                        JOptionPane.showMessageDialog(null, "No tasks found.");
                    }

                }
                else if (Objects.equals(taskDisplayOption, "4"))
                {
                    // Search by task name
                    String searchName = JOptionPane.showInputDialog("Enter the name of the task to search for:");
                    boolean found = false;
                    for (int i = 0; i < taskDisplay.getTaskCount(); i++)
                    {
                        if (taskDisplay.getTaskNames()[i].equalsIgnoreCase(searchName))
                        {
                            JOptionPane.showMessageDialog(null, "Task Found:\n" +
                                    "Task Name: " + taskDisplay.getTaskNames()[i] +
                                    "\nDeveloper Details: " + taskDisplay.getDeveloperNames()[i] +
                                    "\nTask ID: " + taskDisplay.getTaskIDs()[i] +
                                    "\nTask Duration: " + taskDisplay.getTaskDurations()[i] +
                                    "\nTask Status: " + taskDisplay.getTaskStatuses()[i]);
                            found = true;
                            break;
                        }
                    }

                    if (!found)
                    {
                        JOptionPane.showMessageDialog(null, "No task with the name '" + searchName + "' was found.");
                    }
                }

                else if (Objects.equals(taskDisplayOption, "5"))
                {
                    // Search by developer name
                    String searchDeveloper = JOptionPane.showInputDialog("Enter the developer's name to search for their tasks:");
                    StringBuilder devResults = new StringBuilder();
                    for (int i = 0; i < taskDisplay.getTaskCount(); i++)
                    {
                        if (taskDisplay.getDeveloperNames()[i].equalsIgnoreCase(searchDeveloper))
                        {
                            devResults.append("Task Assigned to " + searchDeveloper + ":\n" +
                                    "Task Name: " + taskDisplay.getTaskNames()[i] +
                                    "\nDeveloper Details: " + taskDisplay.getDeveloperNames()[i] +
                                    "\nTask ID: " + taskDisplay.getTaskIDs()[i] +
                                    "\nTask Duration: " + taskDisplay.getTaskDurations()[i] +
                                    "\nTask Status: " + taskDisplay.getTaskStatuses()[i] +
                                    "\n\n");
                        }
                    }

                    if (devResults.length() > 0)
                    {
                        JOptionPane.showMessageDialog(null, devResults.toString());
                    }

                    else
                    {
                        JOptionPane.showMessageDialog(null, "No tasks found for developer '" + searchDeveloper + "'.");
                    }
                }

                else if (Objects.equals(taskDisplayOption, "6"))
                {
                    // Delete a task
                    String deleteTaskName = JOptionPane.showInputDialog("Enter the name of the task you wish to delete:");
                    boolean isDeleted = taskDisplay.deleteTask(deleteTaskName);
                    if (isDeleted)
                    {
                        JOptionPane.showMessageDialog(null, "Task '" + deleteTaskName + "' has been deleted.");
                    }

                    else
                    {
                        JOptionPane.showMessageDialog(null, "No task with the name '" + deleteTaskName + "' was found.");
                    }
                }

            }

            else if (Objects.equals(selectedOption, "3"))
            {
                JOptionPane.showMessageDialog(null, "Goodbye!");
                selectedQuit = true;
            }

        }//while (!selectedQuit)

    }//public void createTasksMethod()

} //class CreateTasks
