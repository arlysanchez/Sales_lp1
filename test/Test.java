
import Util.ConexionSingleton;
import java.sql.Connection;
import java.sql.SQLException;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author tpp
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Test t = new Test();
        t.testConexion();
        
    }
    
       public void testConexion() {
        ConexionSingleton c = new ConexionSingleton();
        try {
            Connection connection = c.getConnection();
            if (connection != null && !connection.isClosed()) {
                System.out.println("Conexión establecida correctamente.");
                // Puedes realizar otras operaciones con la conexión aquí si lo deseas
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (SQLException e) {
            System.out.println("Error al intentar establecer la conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
}
