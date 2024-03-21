/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Util.ConexionSingleton;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JAST
 */
public class PersonDao {

    private Connection cn;

    public Person validate(String user, String passw) {
        Person p = new Person();
        PreparedStatement st;
        ResultSet rs;
        String query = null;

        try {
            String hashedPassword = hashPassword(passw);

            query = "SELECT * FROM person WHERE user = ? and password = ?; ";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, user);
            st.setString(2, hashedPassword);
            rs = st.executeQuery();
            while (rs.next()) {
                p.setIdperson(rs.getInt("idperson"));
                p.setFullname(rs.getString("fullname"));
                p.setUser(rs.getString("user"));
            }

        } catch (Exception e) {
            System.out.println("Error al validar el usuario: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("Erro. No se pudo validar el usuario");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return p;

    }
    //crud

    public List<Person> listarUsers() {
        List<Person> lista = null;
        Person us;
        PreparedStatement st;
        ResultSet rs;
        String query = null;
        try {
            query = "SELECT * FROM person WHERE idTypePerson = 1; ";
            lista = new ArrayList<>();
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            rs = st.executeQuery();
            //este while recorre todos los registro que trae el Objeto resultset
            while (rs.next()) {
                us = new Person();
                us.setIdperson(rs.getInt("idperson"));
                us.setFullname(rs.getString("fullname"));
                us.setNum_document(rs.getString("num_document"));
                us.setUser(rs.getString("user"));
                us.setPassword(rs.getString("password"));
                us.setStatus(rs.getString("status"));
                lista.add(us);
            }

        } catch (Exception e) {
            System.out.println("Error al Listar Usuarios: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("Erro. No se pudo listar al usuario");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return lista;

    }

    public boolean addUsers(Person p) {
        boolean flag = false;
        PreparedStatement st;
        String query = null;

        try {
            // Encripta la contraseña antes de guardarla en la base de datos
            String hashedPassword = hashPassword(p.getPassword());
            query = "INSERT INTO person (fullname, num_document, user, password, status, idTypePerson) VALUES (?, ?, ?, ?, ?, ?)";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, p.getFullname());
            st.setString(2, p.getNum_document());
            st.setString(3, p.getUser());
            st.setString(4, hashedPassword);
            st.setString(5, p.getStatus());
            st.setString(6, p.getIdTypePerson());
            st.executeUpdate();
            //cn.commit();
            flag = true;
        } catch (Exception e) {
            System.out.println("Error Agregar  Usuario: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            flag = false;
            System.out.println("Erro. No se pudo Agregar");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return flag;

    }

    public Person searchUsers(int id) {
        Person user = null;
        PreparedStatement st;
        ResultSet rs;
        String query = null;
        try {
            query = "SELECT * FROM person WHERE idPerson = ?";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                user = new Person();
                user.setIdperson(rs.getInt("idperson"));
                user.setFullname(rs.getString("fullname"));
                user.setNum_document(rs.getString("num_document"));
                user.setUser(rs.getString("user"));
                user.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            System.out.println("Error buscar al usuario ID: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("Erro. No se pudo buscar usuario por ID");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return user;
    }

    public boolean editUsers(Person per) {
        boolean flag = false;
        PreparedStatement st;
        String query = null;

        try {
            query = "UPDATE person SET fullname = ?, num_document = ?, user = ?,password = ?, status = ?,idTypePerson = ? WHERE idPerson = ?";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, per.getFullname());
            st.setString(2, per.getNum_document());
            st.setString(3, per.getUser());
            st.setString(4, per.getPassword());
            st.setString(5, per.getStatus());
            st.setString(6, per.getIdTypePerson());
            st.setInt(7, per.getIdperson());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error Actualizar Usuario: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            flag = false;
            System.out.println("Erro. No se pudo Actualizar");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return flag;
    }

    public boolean deleteUsers(int id) {
        boolean flag = false;
        PreparedStatement st;
        String query = null;
        try {
            query = "DELETE FROM person WHERE idperson = ? ";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error Eliminar: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            flag = false;
            System.out.println("Erro. No se pudo Eliminar");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return flag;

    }
    // Método para generar un hash de una cadena utilizando SHA-256

    public String hashPassword(String password) {
        try {
            // Crea una instancia de MessageDigest con el algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Aplica el algoritmo de hashing a la contraseña
            byte[] hash = digest.digest(password.getBytes());
            // Convierte el hash a una cadena hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Manejo de la excepción, por ejemplo, lanzar una RuntimeException
            throw new RuntimeException("Error al generar el hash de la contraseña", e);
        }
    }

}
