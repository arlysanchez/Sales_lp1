/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Util.ConexionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author JAST
 */
public class SalesDao {
    private Connection cn;
    
    public String GenerateSerieNumber(){
          String serialNumber = "";
        PreparedStatement st;
        ResultSet rs;
        String query = null;
        try {
            query = "SELECT max(serial_number) FROM sale";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            rs = st.executeQuery();
            if (rs.next()) {
                serialNumber = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("Error buscar al serial number: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("Erro. No se pudo buscar al max serial number");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return serialNumber;
    }
    public String MaxIdSales(){
          String IdSales = "";
        PreparedStatement st;
        ResultSet rs;
        String query = null;
        try {
            query = "SELECT max(idsale) FROM sale";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            rs = st.executeQuery();
            if (rs.next()) {
                IdSales = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("Error buscar al serial number: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("Erro. No se pudo buscar al max serial number");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return IdSales;
    }
    public boolean addSale(Sales s) {
        boolean flag = false;
        PreparedStatement st;
        String query = null;
        // Obtén la fecha actual del sistema
        Date currentDate = new Date(System.currentTimeMillis());


        try {
            query = "INSERT INTO sale (idCustomer, serial_number, sale_date, cost,idUser, status) VALUES (?, ?, ?, ?, ?, ?)";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setInt(1, s.getIdcustomer());
            st.setString(2, s.getSerialNumber());
            st.setString(3, s.getSale_date());
            st.setDouble(4, s.getPrice());
            st.setInt(5, s.getIduser());
            st.setString(6, s.getStatus());
            st.executeUpdate();
            //cn.commit();
            flag = true;
        } catch (Exception e) {
            System.out.println("Error Agregar  Venta: " + e.getMessage());
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
    public boolean addSaleDetail(Sales s) {
        boolean flag = false;
        PreparedStatement st;
        String query = null;
        try {
            query = "INSERT INTO detailsale (idsale, idproduct, amount, price) VALUES (?, ?, ?, ?)";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setInt(1, s.getIdsales());
            st.setInt(2, s.getIdproduct());
            st.setInt(3, s.getCantidad());
            st.setDouble(4, s.getPrice());
            st.executeUpdate();
            //cn.commit();
            flag = true;
        } catch (Exception e) {
            System.out.println("Error Agregar  DetalleVenta: " + e.getMessage());
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
}
