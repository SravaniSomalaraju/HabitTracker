package service;

import java.time.LocalDate;
import dao.HabitDAO;
import model.Habit;
import java.util.List;



public class HabitService {

    private final HabitDAO habitDAO = new HabitDAO();

    public void addHabit(String name) {
        Habit habit = new Habit(name, LocalDate.now());
        habitDAO.addHabit(habit);
        System.out.println("Habit added successfully.");
    }

    public void viewHabits() {
        List<Habit> habits = habitDAO.getAllHabits();
        if (habits.isEmpty()) {
            System.out.println("No habits found.");
            return;
        }
        System.out.println("Your Habits:");
        for (Habit h : habits) {
            System.out.println(h.getId() + " - " + h.getName() + " (Created: " + h.getCreatedOn() + ")");
        }
    }

    public void deleteHabit(int id) {
        habitDAO.deleteHabit(id);
        System.out.println("Habit deleted successfully.");
    }

    public void markHabitCompletedToday(int habitId) {
        LocalDate today = LocalDate.now();
        habitDAO.markHabitForDate(habitId, today, true);
        System.out.println("Habit marked as completed for today.");
    }

    public double calculateProductivity(LocalDate date) {
        int total = habitDAO.getTotalHabitCount();
        if (total == 0) {
            return 0.0;
        }
        int completed = habitDAO.getCompletedHabitsOnDate(date);
        return (completed * 100.0) / total;
    }

    public void showTodayProductivity() {
        LocalDate today = LocalDate.now();
        double score = calculateProductivity(today);
        System.out.printf("Today's productivity score (%s): %.2f%%%n", today, score);
    }

    public void showLast7DaysReport() {
        LocalDate today = LocalDate.now();
        System.out.println("Last 7 days productivity report:");
        for (int i = 6; i >= 0; i--) {
            LocalDate date = today.minusDays(i);
            double score = calculateProductivity(date);
            System.out.printf("%s -> %.2f%%%n", date, score);
        }
    }
}

