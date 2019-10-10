package com.improving.tagcliredo.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class OldShoolDatabaseClient {

    private static final Logger logger = LoggerFactory.getLogger(OldShoolDatabaseClient.class);

    public void insertRecordIntoDatabase() throws SQLException {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){
            logger.info("Connection + Statement made");

            int rowsAffected = statement.executeUpdate("INSERT INTO weapon (Name, Area, ItemType) "
                + "VALUES ('Test Dagger', 'Test Area', 'Weapon')");

            if (rowsAffected > 0) {
                logger.info("Committing....");
                connection.commit();
            }
        } catch (SQLException e){
            logger.error(e.getMessage(), e);
        }

    }

    public void readRecordIntoDatabase() throws SQLException {

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){
            logger.info("Connection + Statement made");

            ResultSet resultSet = statement.executeQuery("select * from weapon limit 10");

            String columns = "";
            ResultSetMetaData metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columns = columns + "(" + i + ")" + metaData.getColumnName(i) + ", ";
            }
            logger.info("Table Columns: " + columns);

            resultSet.beforeFirst();
            // Next returns boolean that says, "Am I at the end yet?"
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(3);
                String area = resultSet.getString(17);
                logger.info("Id: {}, Name: {}, Area: {}", id, name, area);
            }

            resultSet.close();
        } catch (SQLException e){
            logger.error(e.getMessage(), e);
        }

    }

    private Connection getConnection() throws SQLException {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/tag?serverTimezone=UTC",
                        "Ethan", "1418");
        connection.setAutoCommit(false);

        return connection;
    }
}
