/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.CustomerDao;
import Model.Person;
import Model.PersonDao;
import Model.Product;
import Model.ProductDao;
import Model.Sales;
import Model.SalesDao;
import Util.GenerateSerie;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tpp
 */
public class Controller extends HttpServlet {

    Person u = new Person();
    PersonDao uDao = new PersonDao();
    CustomerDao cDao = new CustomerDao();
    //
    Product p = new Product();
    ProductDao pDao = new ProductDao();

    //
    Sales v = new Sales();
    List<Sales> lista = new ArrayList<>();

    int idproduct;
    int stock;
    String description_product;
    String serialNumber;
    double price;
    int cantidad;
    double subTotal;
    double TotalPay;

    String SerialNumber;
    SalesDao vDao = new SalesDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //para editar
        int idPerson;
        String op = request.getParameter("op");
        String accion = request.getParameter("accion");
        String fullname = request.getParameter("txt_fullname");
        String num_document = request.getParameter("txt_num_document");
        String user = request.getParameter("txt_user");
        String password = request.getParameter("txt_password");
        String status = request.getParameter("txt_status");
        String type_person = request.getParameter("txt_type_person");
        String address = request.getParameter("txt_address");
        String telephone = request.getParameter("txt_telephone");

        //
        int idProduct;
        String name_product = request.getParameter("txt_name_product");
        String stock = request.getParameter("txt_stock");

        if (op.equals("dashboard")) {
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
        if (op.equals("Products")) {
            switch (accion) {
                case "List" -> {
                    request.setAttribute("productos", pDao.listarProducts());
                    break;
                }
                case "Agregar" -> {
                    String priceParameter = request.getParameter("txt_price");
                    BigDecimal price = new BigDecimal(priceParameter);

                    p.setName_product(name_product);
                    p.setPrice(price);
                    p.setStock(stock);
                    pDao.addProducts(p);
                    request.getRequestDispatcher("Controller?op=Products&accion=List").forward(request, response);
                    break;
                }

                case "Search" -> {
                    idProduct = Integer.parseInt(request.getParameter("id"));
                    Product p = pDao.searchProduct(idProduct);
                    request.setAttribute("product_edit", p);
                    request.getRequestDispatcher("Controller?op=Products&accion=List").forward(request, response);
                    break;
                }
                case "Editar" -> {
                    String priceParameter = request.getParameter("txt_price");
                    BigDecimal price = new BigDecimal(priceParameter);
                    int idProductEdit = Integer.parseInt(request.getParameter("txt_id_product"));
                    p.setName_product(name_product);
                    p.setPrice(price);
                    p.setStock(stock);
                    p.setIdproduct(idProductEdit);
                    pDao.editProdut(p);
                    request.getRequestDispatcher("Controller?op=Products&accion=List").forward(request, response);

                    break;
                }

                case "Delete" -> {
                    idProduct = Integer.parseInt(request.getParameter("id"));
                    pDao.deleteProduct(idProduct);
                    request.getRequestDispatcher("Controller?op=Products&accion=List").forward(request, response);

                    break;
                }
                default ->
                    throw new AssertionError();
            }

            request.getRequestDispatcher("product.jsp").forward(request, response);
        }

        if (op.equals("Users")) {
            switch (accion) {
                case "List" -> {
                    request.setAttribute("usuarios", uDao.listarUsers());
                    break;
                }
                case "Agregar" -> {
                    u.setFullname(fullname);
                    u.setNum_document(num_document);
                    u.setUser(user);
                    u.setPassword(password);
                    u.setStatus(status);
                    u.setIdTypePerson(type_person);
                    uDao.addUsers(u);
                    request.getRequestDispatcher("Controller?op=Users&accion=List").forward(request, response);
                    break;
                }
                case "Search" -> {
                    idPerson = Integer.parseInt(request.getParameter("id"));
                    Person us = uDao.searchUsers(idPerson);
                    request.setAttribute("usuarios_edit", us);
                    request.getRequestDispatcher("Controller?op=Users&accion=List").forward(request, response);

                    break;
                }
                case "Editar" -> {
                    int idPersonEdit = Integer.parseInt(request.getParameter("txt_id_persona"));
                    u.setFullname(fullname);
                    u.setNum_document(num_document);
                    u.setUser(user);
                    u.setPassword(password);
                    u.setStatus(status);
                    u.setIdTypePerson(type_person);
                    u.setIdperson(idPersonEdit);
                    uDao.editUsers(u);
                    request.getRequestDispatcher("Controller?op=Users&accion=List").forward(request, response);

                    break;
                }

                case "Delete" -> {
                    idPerson = Integer.parseInt(request.getParameter("id"));
                    uDao.deleteUsers(idPerson);
                    request.getRequestDispatcher("Controller?op=Users&accion=List").forward(request, response);

                    break;
                }
                default ->
                    throw new AssertionError();
            }
            request.getRequestDispatcher("users.jsp").forward(request, response);
        }
        if (op.equals("Customers")) {

            switch (accion) {
                case "List" -> {
                    request.setAttribute("clientes", cDao.listarCustomers());
                    break;
                }
                case "Agregar" -> {
                    u.setFullname(fullname);
                    u.setNum_document(num_document);
                    u.setAddress(address);
                    u.setTelephone(telephone);
                    u.setStatus(status);
                    u.setIdTypePerson(type_person);
                    cDao.addCustomers(u);
                    request.getRequestDispatcher("Controller?op=Customers&accion=List").forward(request, response);
                    break;
                }
                case "Search" -> {
                    idPerson = Integer.parseInt(request.getParameter("id"));
                    Person cl = cDao.searchCustomer(idPerson);
                    request.setAttribute("cliente_edit", cl);
                    request.getRequestDispatcher("Controller?op=Customers&accion=List").forward(request, response);

                    break;
                }
                case "Editar" -> {
                    int idCustomerEdit = Integer.parseInt(request.getParameter("txt_id_persona"));
                    u.setFullname(fullname);
                    u.setNum_document(num_document);
                    u.setAddress(address);
                    u.setTelephone(telephone);
                    u.setStatus(status);
                    u.setIdTypePerson(type_person);
                    u.setIdperson(idCustomerEdit);
                    cDao.editCustomer(u);
                    request.getRequestDispatcher("Controller?op=Customers&accion=List").forward(request, response);

                    break;
                }

                case "Delete" -> {
                    idPerson = Integer.parseInt(request.getParameter("id"));
                    cDao.deleteCustomer(idPerson);
                    request.getRequestDispatcher("Controller?op=Customers&accion=List").forward(request, response);

                    break;
                }
                default ->
                    throw new AssertionError();
            }

            request.getRequestDispatcher("customers.jsp").forward(request, response);
        }
        if (op.equals("Sales")) {
            v = new Sales();
            switch (accion) {
                case "Cliente":
                    String dni = request.getParameter("txt_cod_customer");
                    u = cDao.searchCustomerDNI(dni);
                    request.setAttribute("client", u);
                    request.setAttribute("serial", serialNumber);
                    request.getRequestDispatcher("sales.jsp").forward(request, response);
                    break;
                case "Producto":
                    idProduct = Integer.parseInt(request.getParameter("txt_cod_product"));
                    Product pr = pDao.searchProduct(idProduct);
                    request.setAttribute("client", u);
                    request.setAttribute("producto", pr);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalPay", TotalPay);
                    request.setAttribute("serial", serialNumber);
                    request.getRequestDispatcher("sales.jsp").forward(request, response);
                    break;
                case "Agregar":
                    TotalPay = 0.0;
                    idproduct = Integer.parseInt(request.getParameter("txt_cod_product"));
                    description_product = request.getParameter("txt_name_product");
                    price = Double.parseDouble(request.getParameter("txt_precio"));
                    cantidad = Integer.parseInt(request.getParameter("txt_cantidad"));
                    subTotal = price * cantidad;

                    v.setIdproduct(idproduct);
                    v.setDescription_product(description_product);
                    v.setPrice(price);
                    v.setCantidad(cantidad);
                    v.setSubTotal(subTotal);
                    lista.add(v);
                    for (int i = 0; i < lista.size(); i++) {
                        TotalPay = TotalPay + lista.get(i).getSubTotal();
                    }
                    request.setAttribute("totalPay", TotalPay);
                    request.setAttribute("lista", lista);
                    request.setAttribute("client", u);
                    request.setAttribute("serial", serialNumber);
                    request.getRequestDispatcher("sales.jsp").forward(request, response);
                    break;
                case "Generate_Venta":

                    for (int i = 0; i < lista.size(); i++) {
                        Product p = new Product();
                        int cantidad = lista.get(i).getCantidad();
                        int idproduct = lista.get(i).getIdproduct();
                        ProductDao pDaoStock = new ProductDao();
                        p = pDaoStock.searchProduct(idproduct);

                        int stockAc = Integer.parseInt(p.getStock()) - cantidad;
                        pDaoStock.updateStock(idproduct, stockAc);
                    }
                    //save venta
                    v.setIdcustomer(u.getIdperson());
                    v.setIduser(1);
                    v.setSerialNumber(serialNumber);
                    v.setSale_date("23-01-01");
                    v.setStatus("1");
                    v.setPrice(TotalPay);
                    vDao.addSale(v);
                    //save detail venta
                    int idSalerecuperado = Integer.parseInt(vDao.MaxIdSales());
                    for (int i = 0; i < lista.size(); i++) {
                        v = new Sales();
                        v.setIdsales(idSalerecuperado);
                        v.setIdproduct(lista.get(i).getIdproduct());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrice(lista.get(i).getPrice());
                        vDao.addSaleDetail(v);
                        lista.clear();
                        
                    }
                    request.getRequestDispatcher("sales.jsp").forward(request, response);
                    break;
                case "Delete":
                    int idProductoEliminar = Integer.parseInt(request.getParameter("id"));
                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).getIdproduct() == idProductoEliminar) {
                            lista.remove(i);
                            break; 
                        }
                    }
                    request.getRequestDispatcher("sales.jsp").forward(request, response);
                    break;
                default:
                    serialNumber = vDao.GenerateSerieNumber();
                    if (serialNumber == null) {
                        serialNumber = "00000001";
                        request.setAttribute("serial", serialNumber);
                    } else {
                        int increment = Integer.parseInt(serialNumber);
                        GenerateSerie g = new GenerateSerie();
                        serialNumber = g.SerialNumber(increment);
                        request.setAttribute("serial", serialNumber);
                        request.getRequestDispatcher("sales.jsp").forward(request, response);
                    }
                    request.getRequestDispatcher("sales.jsp").forward(request, response);
            }
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
