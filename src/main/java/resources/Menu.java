package resources;

import dao.CocheDAO;
import dao.PasajeroDAO;
import model.Coche;
import model.Pasajero;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    Scanner leer = new Scanner(System.in);
    CocheDAO cocheDAO = new CocheDAO();
    PasajeroDAO pasajeroDAO = new PasajeroDAO();

    int opcion;
    int id;
    String matricula;
    String marca;
    String modelo;
    String color;
    String nombre;
    int edad,peso,coche_id;

    public void mostrarMenu()

    {

        do {

            System.out.println("\n--- Menú ---");
            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por ID");
            System.out.println("3. Consultar coche por ID");
            System.out.println("4. Modificar coche por ID");
            System.out.println("5. Listado de coches");
            System.out.println("6. Gestionar pasajeros");
            System.out.println("7. Terminar el programa");
            System.out.print("Selecciona una opción: ");

            opcion = leer.nextInt();

            switch (opcion) {

                case 1:
                    leer.nextLine();
                    System.out.println("Matricula");
                    matricula = leer.nextLine();
                    System.out.println("Marca");
                    marca = leer.nextLine();
                    System.out.println("Modelo");
                    modelo= leer.nextLine();
                    System.out.println("Color");
                    color = leer.nextLine();

                    try {
                        cocheDAO.añadirCoche(new Coche(matricula,marca,modelo,color));
                        System.out.println("El coche fue añadido!");
                    } catch (SQLException e) {
                        System.out.println("El coche no se pudo insertar");
                    }
                    break;

                case 2:
                    System.out.println("id del coche");
                    id = leer.nextInt();
                    try {
                        cocheDAO.borrarCoche(id);
                        System.out.println("coche borrado con exito");
                    } catch (SQLException e) {
                        System.out.println("el coche no se pudo borrar");
                    }
                    break;

                case 3:
                    System.out.println("id del cohe");
                    id = leer.nextInt();
                    try {
                        cocheDAO.consultarCoche(id);
                    } catch (SQLException e) {
                        System.out.println("No se encontro el coche");
                    }
                    break;

                case 4:
                    System.out.println("Dame el id del coche que quieres modificar");
                    id = leer.nextInt();
                    leer.nextLine();
                    System.out.println("Nueva matricula");
                    matricula = leer.nextLine();
                    System.out.println("Nueva marca");
                    marca = leer.nextLine();
                    System.out.println("Nuevo modelor");
                    modelo = leer.nextLine();
                    System.out.println("Nuevo color");
                    color = leer.nextLine();

                    Coche coche = new Coche(id,matricula,modelo,marca,color);
                    try {
                        cocheDAO.modificarCoche(coche);
                        System.out.println("Coche modificado con exito");
                    } catch (SQLException e) {
                        System.out.println("No se pudo modificar el coche");
                    }
                    break;

                case 5:
                    try {
                        cocheDAO.listadoCoches();
                    } catch (SQLException e) {
                        System.out.println("No se encontraron coches");
                    }
                    break;

                case 6:

                    gestionarPasajeros();

                    break;

                case 7:
                    System.out.println("Fin de programa ! :)");
                    break;

                default:
                    System.out.println("Opcion incorrecta, porfavor pruebe de nuevo");
                    break;


            }
        } while (opcion != 7);
    }
    public void gestionarPasajeros(){

        int opcionPasajero;

            System.out.println("\n--- Menú ---");
            System.out.println("1. Añadir nuevo pasajero");
            System.out.println("2. Borrar pasajero por ID");
            System.out.println("3. Consulta pasajero por ID");
            System.out.println("4. Listar todos los pasajeros");
            System.out.println("5. Añadir pasajero a coche");
            System.out.println("6. Eliminar pasajero de coche");
            System.out.println("7. Listar todos los pasajeros de un coche");

            opcionPasajero = leer.nextInt();
            leer.nextLine();

            switch (opcionPasajero){

                case 1:
                    System.out.println("Nombre");
                    nombre = leer.nextLine();
                    System.out.println("Edad");
                    edad=leer.nextInt();
                    System.out.println("Peso");
                    peso = leer.nextInt();


                    Pasajero pasajero = new Pasajero(nombre,edad,peso,coche_id);
                    try {
                        pasajeroDAO.añadirPasajero(pasajero);
                        System.out.println("Pasajero añadido con exito");
                    } catch (SQLException e) {
                        System.out.println("No se pudo añadir el pasajero");
                    }
                    break;

                case 2:

                    System.out.println("Dame el id del pasajero que quieres borrar");
                    id = leer.nextInt();
                    try {
                        pasajeroDAO.borrarPasajero(id);
                        System.out.println("Pasajero borrado con exito");
                    } catch (SQLException e) {
                        System.out.println("No se pudo borrar el pasajero");
                    }
                    break;

                case 3:

                    System.out.println("Dame el id del pasajero");
                    int id = leer.nextInt();
                    try {
                        pasajeroDAO.consultarPasajero(id);
                    } catch (SQLException e) {
                        System.out.println("Pasajero no encontrado");
                    }
                    break;

                case 4:

                    try {
                        pasajeroDAO.listarPasajeros();
                    } catch (SQLException e) {
                        System.out.println("No se encontraron pasajeros");
                    }
                    break;

                case 5:
                    try {
                        pasajeroDAO.mostrarCochesDisponibles();
                        System.out.println("Dame el id del coche");
                        int coche_id = leer.nextInt();
                        System.out.println("Dame el id del pasajero");
                        int pasajero_id = leer.nextInt();
                        pasajeroDAO.añadirPasajeroCoche(coche_id,pasajero_id);
                    } catch (SQLException e) {
                        System.out.println("No se encontraron coches disponibles");
                    }
                    break;

                case 6:

                    System.out.println("Dame el id del coche");
                    int coche_id = leer.nextInt();
                    System.out.println("Dame el id del pasajero");
                    int pasajero_id = leer.nextInt();
                    try {
                        pasajeroDAO.eliminarPasajeroCoche(coche_id,pasajero_id);
                        System.out.println("Pasajero eliminado del coche con éxito");
                    } catch (SQLException e) {
                        System.out.println("No se pudo eliminar al pasajero del coche");
                    }
                    break;

                case 7:

                    System.out.println("Dame el id del coche");
                    int coche_id1 = leer.nextInt();
                    try {
                        pasajeroDAO.listarPasajerosPorCoche(coche_id1);
                    } catch (SQLException e) {
                        System.out.println("No se encontraron pasajeros");
                    }
            }



    }
}
