package main;


import java.util.List;
import java.util.Scanner;
import service.HabitService;
import dao.HabitDAO;
import model.Habit;

public class HabitTrackerApp {

    public static void main(String[] args) {

        HabitService service = new HabitService();
        HabitDAO habitDAO = new HabitDAO();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== Habit Tracker & Productivity System ===");
            System.out.println("1. Add Habit");
            System.out.println("2. View All Habits");
            System.out.println("3. Mark Habit as Completed (Today)");
            System.out.println("4. Show Today's Productivity Score");
            System.out.println("5. Show Last 7 Days Report");
            System.out.println("6. Delete Habit");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Enter a valid number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter habit name: ");
                    String name = scanner.nextLine();
                    service.addHabit(name);
                    break;
                case 2:
                    service.viewHabits();
                    break;
                case 3:
                    List<Habit> habits = habitDAO.getAllHabits();
                    if (habits.isEmpty()) {
                        System.out.println("No habits available. Add a habit first.");
                        break;
                    }
                    System.out.println("Select habit to mark as completed:");
                    for (Habit h : habits) {
                        System.out.println(h.getId() + " - " + h.getName());
                    }
                    System.out.print("Enter habit id: ");
                    int id = scanner.nextInt();
                    service.markHabitCompletedToday(id);
                    break;
                case 4:
                    service.showTodayProductivity();
                    break;
                case 5:
                    service.showLast7DaysReport();
                    break;
                case 6:
                    System.out.print("Enter habit id to delete: ");
                    int delId = scanner.nextInt();
                    service.deleteHabit(delId);
                    break;
                case 0:
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
