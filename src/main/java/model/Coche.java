package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Coche {

    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;


  /**Meto un constructor mas sin el id que va a ser autoincremental**/

    public Coche(String matricula, String marca, String modelo, String color) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
    }



    public void mostrarDatos(){

        System.out.println("id = " + id);
        System.out.println("matricula = " + matricula);
        System.out.println("marca = " + marca);
        System.out.println("modelo = " + modelo);
        System.out.println("color = " + color);
    }
}
