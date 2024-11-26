package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pasajero {

    private int id;
    private String nombre;
    private int edad;
    private int peso;
    //La fk en la base de datos
    private int coche_id;

    public Pasajero(String nombre, int edad, int peso, int coche_id) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.coche_id = coche_id;
    }

    public void mostrarDatos(){

        System.out.println("id = " + id);
        System.out.println("nombre = " + nombre);
        System.out.println("edad = " + edad);
        System.out.println("peso = " + peso);
        System.out.println("coche_id = " + coche_id);
    }

    public Pasajero(String nombre, int edad, int peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    public Pasajero(int id, String nombre, int edad, int peso) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }
}
