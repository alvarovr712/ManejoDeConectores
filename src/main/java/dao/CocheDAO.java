package dao;

import database.DBConnection;
import database.DBScheme;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CocheDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public CocheDAO(){
        connection = new DBConnection().getConnection();
    }

    public void añadirCoche(Coche coche) throws SQLException {

        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES(?,?,?,?)",
                DBScheme.TAB_COCHES,
                DBScheme.TAB_COCHES_COL_MATRICULA,DBScheme.TAB_COCHES_COL_MARCA,DBScheme.TAB_COCHES_COL_MODELO,DBScheme.TAB_COCHES_COL_COLOR);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4,coche.getColor());
        preparedStatement.execute();


    }
    public void borrarCoche (int id) throws SQLException {

        String query = String.format("DELETE FROM %s WHERE %s = ?",
                DBScheme.TAB_COCHES,DBScheme.TAB_COCHES_COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        preparedStatement.execute();

    }
    public void consultarCoche(int id) throws SQLException {

        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                DBScheme.TAB_COCHES,DBScheme.TAB_COCHES_COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            id = resultSet.getInt(DBScheme.TAB_COCHES_COL_ID);
            String matricula = resultSet.getString(DBScheme.TAB_COCHES_COL_MATRICULA);
            String marca = resultSet.getString(DBScheme.TAB_COCHES_COL_MARCA);
            String modelo = resultSet.getString(DBScheme.TAB_COCHES_COL_MODELO);
            String color = resultSet.getString(DBScheme.TAB_COCHES_COL_COLOR);

            Coche coche = new Coche(id,matricula,marca,modelo,color);
            coche.mostrarDatos();
        }
    }

    public void modificarCoche(Coche coche) throws SQLException {

        String query = String.format("UPDATE %s SET %s = ?, %s =?, %s = ?, %s = ? WHERE %s = ?",
                DBScheme.TAB_COCHES,
                DBScheme.TAB_COCHES_COL_MATRICULA,DBScheme.TAB_COCHES_COL_MARCA,DBScheme.TAB_COCHES_COL_MODELO,DBScheme.TAB_COCHES_COL_COLOR,
                DBScheme.TAB_COCHES_COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula() );
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3,coche.getModelo());
        preparedStatement.setString(4,coche.getColor());
        preparedStatement.setInt(5,coche.getId());
        preparedStatement.executeUpdate();
    }
    public  void listadoCoches() throws SQLException {

        String query = String.format("SELECT * FROM %s",
                DBScheme.TAB_COCHES);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){

            int id = resultSet.getInt(DBScheme.TAB_COCHES_COL_ID);
            String matricula = resultSet.getString(DBScheme.TAB_COCHES_COL_MATRICULA);
            String marca = resultSet.getString(DBScheme.TAB_COCHES_COL_MARCA);
            String modelo = resultSet.getString(DBScheme.TAB_COCHES_COL_MODELO);
            String color = resultSet.getString(DBScheme.TAB_COCHES_COL_COLOR);

            Coche coche1 = new Coche(id,matricula,marca,modelo,color);
            coche1.mostrarDatos();

        }
    }


}
