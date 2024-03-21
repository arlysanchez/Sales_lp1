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
public class ProductDao {
     private Connection cn;

   
    //crud producto
    
    public List<Product> listarProducts() {
        List<Product> lista = null;
        Product pr;
        PreparedStatement st;
        ResultSet rs;
        String query = null;
        try {
            query = "SELECT * FROM product WHERE status = 1; ";
            lista = new ArrayList<>();
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            rs = st.executeQuery();
            //este while recorre todos los registro que trae el Objeto resultset
            while (rs.next()) {
                pr = new Product();
                pr.setIdproduct(rs.getInt("idproduct"));
                pr.setName_product(rs.getString("name_product"));
                pr.setPrice(rs.getBigDecimal("price"));
                pr.setStock(rs.getString("stocks"));
                pr.setStatus(rs.getString("status"));
                lista.add(pr);
            }

        } catch (Exception e) {
            System.out.println("Error al Listar Productos: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("Erro. No se pudo listar al productos");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return lista;
    
    }
    
   public boolean addProducts(Product pr) {
        boolean flag = false;
        PreparedStatement st;
        String query = null;

        try {
            query = "INSERT INTO product (name_product, price, stocks, status) VALUES (?, ?, ?, ?)";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, pr.getName_product());
            st.setBigDecimal(2, pr.getPrice());
            st.setString(3, pr.getStock());
            st.setString(4, "1");
            st.executeUpdate();
            //cn.commit();
            flag = true;
        } catch (Exception e) {
            System.out.println("Error Agregar  Productos: " + e.getMessage());
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
    public Product searchProduct(int id) {
        Product pr = null;
        PreparedStatement st;
        ResultSet rs;
        String query = null;
        try {
            query = "SELECT * FROM product WHERE idproduct = ?";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                pr = new Product();
                pr.setIdproduct(rs.getInt("idproduct"));
                pr.setName_product(rs.getString("name_product"));
                pr.setPrice(rs.getBigDecimal("price"));
                pr.setStock(rs.getString("stocks"));
                pr.setStatus(rs.getString("status"));
            }
        } catch (Exception e) {
            System.out.println("Error buscar al producto ID: " + e.getMessage());
            try {
                cn.rollback();
            } catch (Exception ex) {
            }
            System.out.println("Erro. No se pudo buscar produto por ID");
        } finally {
            if (cn != null) {
                try {
                } catch (Exception ex) {
                }
            }
        }
        return pr;
    }
     public boolean editProdut(Product pr) {
        boolean flag = false;
        PreparedStatement st;
        String query = null;

        try {
            query = "UPDATE product SET name_product = ?, price = ?, stocks = ? WHERE idproduct = ?";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setString(1, pr.getName_product());
            st.setBigDecimal(2, pr.getPrice());
            st.setString(3, pr.getStock());
            st.setInt(4, pr.getIdproduct());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error Actualizar Product: " + e.getMessage());
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

      public boolean deleteProduct(int id) {
        boolean flag = false;
        PreparedStatement st;
        String query = null;
        try {
            query = "UPDATE product SET status = 0 WHERE idproduct = ? ";
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
      
      public boolean updateStock(int id, int stock){
       boolean flag = false;
        PreparedStatement st;
        String query = null;

        try {
            query = "UPDATE product SET stocks = ? WHERE idproduct = ?";
            cn = ConexionSingleton.getConnection();
            st = cn.prepareStatement(query);
            st.setInt(1, stock);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error Actualizar stock del Product: " + e.getMessage());
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
      
      
}
