/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Util.ConexionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JAST
 */
public class CustomerDao {
    private Connection cn;

   
    //crud cliente
    
    public List<Person> listarCustomers() {
        List<Person> lista = null;
        Person cl;
        PreparedStatement st;
        ResultSet rs;
        String query = null;
        try {
            query = "SELECT * FROM person WHERE idTypePerson = 2  AND status = 1; ";
            lista = new ArrayList<>();
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            rs = st.executeQuery();
            //este while recorre todos los registro que trae el Objeto resultset
            while (rs.next()) {
                cl = new Person();
                cl.setIdperson(rs.getInt("idperson"));
                cl.setFullname(rs.getString("fullname"));
                cl.setNum_document(rs.getString("num_document"));
                cl.setAddress(rs.getString("address"));
                cl.setTelephone(rs.getString("telephone"));
                cl.setStatus(rs.getString("status"));
                lista.add(cl);
            }

        } catch (Exception e) {
            System.out.println("Error al Listar Clientes: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("Erro. No se pudo listar al cliente");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return lista;
    
    }
    
   public boolean addCustomers(Person p) {
        boolean flag = false;
        PreparedStatement st;
        String query = null;

        try {
            query = "INSERT INTO person (fullname, num_document, address, telephone, status, idTypePerson) VALUES (?, ?, ?, ?, ?, ?)";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, p.getFullname());
            st.setString(2, p.getNum_document());
            st.setString(3, p.getAddress());
            st.setString(4, p.getTelephone());
            st.setString(5, p.getStatus());
            st.setString(6, p.getIdTypePerson());
            st.executeUpdate();
            //cn.commit();
            flag = true;
        } catch (Exception e) {
            System.out.println("Error Agregar  Clientes: " + e.getMessage());
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
    public Person searchCustomer(int id) {
        Person cl = null;
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
                cl = new Person();
                cl.setIdperson(rs.getInt("idperson"));
                cl.setFullname(rs.getString("fullname"));
                cl.setNum_document(rs.getString("num_document"));
                cl.setAddress(rs.getString("address"));
                cl.setTelephone(rs.getString("telephone"));
                cl.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            System.out.println("Error buscar al cliente ID: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("Erro. No se pudo buscar cliente por ID");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return cl;
    }
     public boolean editCustomer(Person per) {
        boolean flag = false;
        PreparedStatement st;
        String query = null;

        try {
            query = "UPDATE person SET fullname = ?, num_document = ?, address = ?,telephone = ?, status = ?,idTypePerson = ? WHERE idPerson = ?";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, per.getFullname());
            st.setString(2, per.getNum_document());
            st.setString(3, per.getAddress());
            st.setString(4, per.getTelephone());
            st.setString(5, per.getStatus());
            st.setString(6, per.getIdTypePerson());
            st.setInt(7, per.getIdperson());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error Actualizar Cliente: " + e.getMessage());
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

      public boolean deleteCustomer(int id) {
        boolean flag = false;
        PreparedStatement st;
        String query = null;
        try {
            query = "UPDATE person SET status = 0 WHERE idperson = ? ";
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
      
      //buscar cliente por DNI
      
      public Person searchCustomerDNI(String dni) {
        Person cl = null;
        PreparedStatement st;
        ResultSet rs;
        String query = null;
        try {
            query = "SELECT * FROM person WHERE num_document = ?";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, dni);
            rs = st.executeQuery();
            if (rs.next()) {
                cl = new Person();
                cl.setIdperson(rs.getInt("idperson"));
                cl.setFullname(rs.getString("fullname"));
                cl.setNum_document(rs.getString("num_document"));
                cl.setAddress(rs.getString("address"));
            }
        } catch (Exception e) {
            System.out.println("Error buscar al cliente DNI: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("Erro. No se pudo buscar cliente por DNI");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return cl;
    }
}
