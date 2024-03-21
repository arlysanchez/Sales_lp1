/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author JAST
 */
public class GenerateSerie {

    int dato;

   public String SerialNumber(int dato) {
    // Incrementa el valor de dato en 1
    this.dato = dato + 1;

    // Convierte el valor a cadena
    String datoStr = String.valueOf(this.dato);

    // Calcula la longitud de la cadena
    int longitud = datoStr.length();

    // Rellena con ceros a la izquierda si es necesario
    int cerosFaltantes = 8 - longitud;
    StringBuilder serialNumber = new StringBuilder();

    for (int i = 0; i < cerosFaltantes; i++) {
        serialNumber.append('0');
    }

    serialNumber.append(datoStr);

    return serialNumber.toString();
}
}
