import java.sql.*;

public class BD {
    public static String url = "jdbc:mysql://localhost/cafe";
    public static String user = "root";
    public static String password = "";

    // открыть соединение
    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    //закрыть соединение
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public int findIntByName(String idColumn,String tableName,String nameColumn,String name){
        //idColumn - название столбца с айди; tableName - название таблицы;
        // nameColumn - название столбца со значением для поиска; name - значение для поиска
        int id=-1;
        Connection connection=null;
        try {
            connection=openConnection();
            String query=String.format("SELECT %s FROM %s WHERE %s=?", idColumn,tableName,nameColumn);
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setString(1,name);
            ResultSet resultSet=statement.executeQuery();
            if (resultSet.next()){id=resultSet.getInt(idColumn);}
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("id не получен из "+tableName);
        } finally {
            closeConnection(connection);
        }
        return id;
    }
    public void selectMenu() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT menu.victual, menu.price FROM `menu`";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String cer = resultSet.getString("victual");
                String sur = resultSet.getString("price");
                System.out.println(cer+" "+sur);
            }
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
    public void createUser(String user,String password) {
        Connection connection = null;
        try {
            connection = openConnection();//;
            String query = "CREATE USER '"+user+"'@'localhost' IDENTIFIED WITH mysql_native_password BY '"+password+"';";
            PreparedStatement statement=connection.prepareStatement(query);//CREATE USER '" + user + "'@'localhost';GRANT SELECT ON *.* TO '" + user + "'@'localhost';
            statement.executeUpdate();
            query = "GRANT ALL PRIVILEGES ON *.* TO '"+user+"'@'localhost' WITH GRANT OPTION;";
            statement=connection.prepareStatement(query);
            statement.executeUpdate();
            query="ALTER USER '"+user+"'@'localhost' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0";
            statement= connection.prepareStatement(query);
            statement.executeUpdate();
            closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
