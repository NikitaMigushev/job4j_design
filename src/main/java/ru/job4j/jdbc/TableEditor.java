package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
            Class.forName(properties.getProperty("driver_class"));
            String url = properties.getProperty("url");
            String login = properties.getProperty("login");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, login, password);
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println("Connection is successful!");
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String query = String.format("CREATE TABLE %s (id SERIAL PRIMARY KEY)", tableName);
            statement.executeUpdate(query);
            System.out.println(String.format("Table %s has been created", tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String query = String.format("DROP TABLE %s", tableName);
            statement.executeUpdate(query);
            System.out.println(String.format("Table %s has been deleted", tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String query = String.format("ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type);
            statement.executeUpdate(query);
            System.out.println(String.format("Column %s has been added into table %n", columnName, tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String query = String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName);
            statement.executeUpdate(query);
            System.out.println(String.format("Column %s has been removed from table %s", columnName, tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String query = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s", tableName, columnName, newColumnName);
            statement.executeUpdate(query);
            System.out.println(String.format("Column %s has been renamed to %s in table %s", columnName, newColumnName, tableName));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) {
        TableEditor tableEditor = new TableEditor(new Properties());
        tableEditor.createTable("testtable");
        tableEditor.addColumn("testtable", "name", "VARCHAR(255)");
        tableEditor.renameColumn("testtable", "name", "fullname");
        tableEditor.dropColumn("testtable", "fullname");
        tableEditor.dropTable("testtable");
    }
}
