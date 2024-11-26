package database;

public interface DBScheme {

    String HOST = "127.0.0.1";
    String PORT ="3306";
    String DATABASE = "conectores";

    //Coches (Tabla y columnas)

    String TAB_COCHES = "coches";
    String TAB_COCHES_COL_ID = "id";
    String TAB_COCHES_COL_MATRICULA= "matricula";
    String TAB_COCHES_COL_MARCA = "marca";
    String TAB_COCHES_COL_MODELO = "modelo";
    String TAB_COCHES_COL_COLOR = "color";

    //Pasajeros(Tabla y columnas)

    String TAB_PASAJ = "pasajeros";
    String TAB_PASAJ_COL_ID = "id";
    String TAB_PASAJ_COL_NOMBRE = "nombre";
    String TAB_PASAJ_COL_EDAD = "edad";
    String TAB_PASAJ_COL_PESO = "peso";
    String TAB_PASAJ_COL_COCHE_ID_FK = "coche_id";


}
