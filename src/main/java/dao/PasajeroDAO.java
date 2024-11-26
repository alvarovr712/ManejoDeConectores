package dao;

import database.DBConnection;
import database.DBScheme;
import model.Coche;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasajeroDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public  PasajeroDAO(){
        connection = new DBConnection().getConnection();
    }

    public void añadirPasajero(Pasajero pasajero) throws SQLException {

        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUES(?,?,?)",
                DBScheme.TAB_PASAJ,
                DBScheme.TAB_PASAJ_COL_NOMBRE,DBScheme.TAB_PASAJ_COL_EDAD,DBScheme.TAB_PASAJ_COL_PESO);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,pasajero.getNombre());
        preparedStatement.setInt(2,pasajero.getEdad());
        preparedStatement.setInt(3,pasajero.getPeso());
        preparedStatement.execute();


    }
    public void borrarPasajero(int id) throws SQLException {

        String query = String.format("DELETE FROM %s WHERE %s = ?",
                DBScheme.TAB_PASAJ,DBScheme.TAB_PASAJ_COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }
    public void consultarPasajero(int id) throws SQLException {

        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                DBScheme.TAB_PASAJ,DBScheme.TAB_PASAJ_COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            int ids = resultSet.getInt(DBScheme.TAB_PASAJ_COL_ID);
            String nombre = resultSet.getString(DBScheme.TAB_PASAJ_COL_NOMBRE);
            int edad = resultSet.getInt(DBScheme.TAB_PASAJ_COL_EDAD);
            int peso = resultSet.getInt(DBScheme.TAB_PASAJ_COL_PESO);
            int coche_id = resultSet.getInt(DBScheme.TAB_PASAJ_COL_COCHE_ID_FK);

            Pasajero pasajero1 = new Pasajero(ids,nombre,edad,peso,coche_id);
            pasajero1.mostrarDatos();
        }

    }
    public  void listarPasajeros() throws SQLException {

        String query = String.format("SELECT * FROM %s",DBScheme.TAB_PASAJ);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){

            int ids = resultSet.getInt(DBScheme.TAB_PASAJ_COL_ID);
            String nombre = resultSet.getString(DBScheme.TAB_PASAJ_COL_NOMBRE);
            int edad = resultSet.getInt(DBScheme.TAB_PASAJ_COL_EDAD);
            int peso = resultSet.getInt(DBScheme.TAB_PASAJ_COL_PESO);
            int coche_id = resultSet.getInt(DBScheme.TAB_PASAJ_COL_COCHE_ID_FK);

            Pasajero pasajero2 = new Pasajero(ids,nombre,edad,peso,coche_id);
            pasajero2.mostrarDatos();

        }

    }
    public  void mostrarCochesDisponibles() throws SQLException {

        String query = String.format("SELECT * FROM %s " +
                        "             LEFT JOIN %s ON %s.%s = %s.%s" +
                        "             GROUP BY %s.%s" +
                        "             HAVING COUNT(%s.%s)<=4 ",
                DBScheme.TAB_COCHES,
                DBScheme.TAB_PASAJ,DBScheme.TAB_COCHES,DBScheme.TAB_COCHES_COL_ID,DBScheme.TAB_PASAJ,DBScheme.TAB_PASAJ_COL_COCHE_ID_FK,
                DBScheme.TAB_COCHES,DBScheme.TAB_COCHES_COL_ID,
                DBScheme.TAB_PASAJ,DBScheme.TAB_PASAJ_COL_ID);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        System.out.println("Coches disponibles:");
        while(resultSet.next()){
            int id = resultSet.getInt(DBScheme.TAB_COCHES_COL_ID);
            String matricula = resultSet.getString(DBScheme.TAB_COCHES_COL_MATRICULA);
            String marca = resultSet.getString(DBScheme.TAB_COCHES_COL_MARCA);
            String modelo = resultSet.getString(DBScheme.TAB_COCHES_COL_MODELO);
            String color = resultSet.getString(DBScheme.TAB_COCHES_COL_COLOR);

            Coche coche = new Coche(id,matricula,marca,modelo,color);
            coche.mostrarDatos();

        }
    }
    public  void añadirPasajeroCoche(int coche_id,int pasajero_id) throws SQLException {



        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?",
                DBScheme.TAB_PASAJ,DBScheme.TAB_PASAJ_COL_COCHE_ID_FK,DBScheme.TAB_PASAJ_COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,coche_id);
        preparedStatement.setInt(2,pasajero_id);
        preparedStatement.executeUpdate();
    }

    public void eliminarPasajeroCoche(int coche_id, int pasajero_id) throws SQLException {

        String query = String.format("UPDATE %s SET %s = NULL WHERE %s = ? AND %s = ?",
                DBScheme.TAB_PASAJ,DBScheme.TAB_PASAJ_COL_COCHE_ID_FK,DBScheme.TAB_PASAJ_COL_COCHE_ID_FK,DBScheme.TAB_PASAJ_COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,coche_id);
        preparedStatement.setInt(2, pasajero_id);
        preparedStatement.execute();

    }
    public void listarPasajerosPorCoche ( int coche_id) throws SQLException {

        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                DBScheme.TAB_PASAJ,DBScheme.TAB_PASAJ_COL_COCHE_ID_FK);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, coche_id);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){

            int id = resultSet.getInt(DBScheme.TAB_PASAJ_COL_ID);
            String nombre = resultSet.getString(DBScheme.TAB_PASAJ_COL_NOMBRE);
            int edad = resultSet.getInt(DBScheme.TAB_PASAJ_COL_EDAD);
            int peso = resultSet.getInt(DBScheme.TAB_PASAJ_COL_PESO);

            Pasajero pasajero = new Pasajero(id,nombre,edad,peso);
            pasajero.mostrarDatos();

        }

    }
}
