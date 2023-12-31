package edu.school21.chat.app;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;
public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Optional<Message> message;
        MessagesRepositoryJdbcImpl messagesRepositoryJdbc = new MessagesRepositoryJdbcImpl(hikariCPData());

        try{
            System.out.println("Enter ID message:");
            message = messagesRepositoryJdbc.findById(scanner.nextLong());

            if (message.isPresent()) {
                System.out.println(message.get());
            } else {
                System.out.println("Message is not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource hikariCPData() {
        HikariDataSource hikariDataSource = new HikariDataSource();

        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("");

        return hikariDataSource;
    }
}
