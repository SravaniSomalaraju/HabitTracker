package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Habit;

public class HabitDAO {

    public void addHabit(Habit habit) {
        String sql = "INSERT INTO habits (habit_name, created_on) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, habit.getName());
            ps.setDate(2, Date.valueOf(habit.getCreatedOn()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Habit> getAllHabits() {
        List<Habit> habits = new ArrayList<>();
        String sql = "SELECT habit_id, habit_name, created_on FROM habits";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Habit habit = new Habit();
                habit.setId(rs.getInt("habit_id"));
                habit.setName(rs.getString("habit_name"));
                habit.setCreatedOn(rs.getDate("created_on").toLocalDate());
                habits.add(habit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return habits;
    }

    public void deleteHabit(int habitId) {
        String deleteLogSql = "DELETE FROM habit_log WHERE habit_id = ?";
        String deleteHabitSql = "DELETE FROM habits WHERE habit_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psLog = conn.prepareStatement(deleteLogSql);
             PreparedStatement psHabit = conn.prepareStatement(deleteHabitSql)) {

            conn.setAutoCommit(false);

            psLog.setInt(1, habitId);
            psLog.executeUpdate();

            psHabit.setInt(1, habitId);
            psHabit.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void markHabitForDate(int habitId, LocalDate date, boolean status) {
        String sqlCheck = "SELECT log_id FROM habit_log WHERE habit_id = ? AND log_date = ?";
        String sqlInsert = "INSERT INTO habit_log (habit_id, log_date, status) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE habit_log SET status = ? WHERE log_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psCheck = conn.prepareStatement(sqlCheck)) {

            psCheck.setInt(1, habitId);
            psCheck.setDate(2, Date.valueOf(date));

            try (ResultSet rs = psCheck.executeQuery()) {
                if (rs.next()) {
                    int logId = rs.getInt("log_id");
                    try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
                        psUpdate.setBoolean(1, status);
                        psUpdate.setInt(2, logId);
                        psUpdate.executeUpdate();
                    }
                } else {
                    try (PreparedStatement psInsert = conn.prepareStatement(sqlInsert)) {
                        psInsert.setInt(1, habitId);
                        psInsert.setDate(2, Date.valueOf(date));
                        psInsert.setBoolean(3, status);
                        psInsert.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getTotalHabitCount() {
        String sql = "SELECT COUNT(*) AS total FROM habits";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getCompletedHabitsOnDate(LocalDate date) {
        String sql = "SELECT COUNT(*) AS completed " +
                     "FROM habit_log WHERE log_date = ? AND status = true";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(date));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("completed");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

