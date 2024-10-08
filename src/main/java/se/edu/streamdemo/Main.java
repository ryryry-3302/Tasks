package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
        printAllData(tasksData);
        printDataWithStreams(tasksData);
        printDeadlineWithStream(tasksData);
        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("sorted");
        printDeadlinesUsingStream(tasksData);
        System.out.println("filter by stream");
        ArrayList<Task> filteredlist = filterTaskByString(tasksData,"11");
        for (Task task : filteredlist) {
            System.out.println(task);
        }
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Printing all data with iteration");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataWithStreams(ArrayList<Task> tasks) {
        System.out.println("Printing all data with stream");
        tasks.stream()                              //Creates stream
                .forEach(System.out::println);      //Terminal Operator
    }

    public static void printDeadlineWithStream(ArrayList<Task> tasks) {
        System.out.println("Printing deadline with stream");

        tasks.stream()
                .filter((t) -> t instanceof Deadline)
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasksData) {
        tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .sorted((t1,t2)-> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTaskByString (ArrayList<Task> tasksData, String filterString) {
        return (ArrayList<Task>) tasksData.stream()
                                    .filter((t) -> t.getDescription().contains(filterString))
                                    .collect(toList());
    }
}
