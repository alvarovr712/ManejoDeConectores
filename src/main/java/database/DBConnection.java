package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public void createConnection(){

        String url = String.format("jdbc:mysql://%s:%s/%s",DBScheme.HOST,DBScheme.PORT,DBScheme.DATABASE);

        try {
            connection = DriverManager.getConnection(url,"root","");
            System.out.println("Conexion a la base de datos establecida!");
        } catch (SQLException e) {
            System.out.println("Fallo en la conexión a la Base de Datos");
        }
    }

    public Connection getConnection(){

        if(connection == null){
            createConnection();
        }

        return connection;
    }



}
