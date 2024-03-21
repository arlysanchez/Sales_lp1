<%-- 
    Document   : product
    Created on : 9 ene. 2024, 15:44:45
    Author     : JAST
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="Model.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String name_product = "";
    BigDecimal price = BigDecimal.ZERO;
    String stock = "";
    int idproduct = 0;
    String n_boton = "";
    String color_buton = "";

    Product product_edit = (Product) request.getAttribute("product_edit");
    name_product = (product_edit != null && product_edit.getName_product() != null) ? product_edit.getName_product() : "";
    price = (product_edit != null && product_edit.getPrice() != null) ? product_edit.getPrice() : BigDecimal.ZERO;
    stock = (product_edit != null && product_edit.getStock() != null) ? product_edit.getStock() : "";
    idproduct = (product_edit != null) ? product_edit.getIdproduct() : idproduct;

    if (idproduct == 0) {
        n_boton = "Agregar";
        color_buton = "btn btn-success me-md-2";
    } else {
        n_boton = "Editar";
        color_buton = "btn btn-warning me-md-2";
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Venta</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

    </head>
    <body>
        <div class="container">
            <div class="card body">
                <h5 class="card-title text-center mt-2">Registro de productos</h5>
                <form action="Controller?op=Products" method="POST">
                    <div class="row">
                        <div class="col-4">
                            <div class="m-3">
                                <label for="name_product" class="form-label">Nombre Producto</label>
                                <input type="text" class="form-control" id="name_product" name="txt_name_product" value="<%=name_product%>" placeholder="producto" required>
                            </div>
                        </div>
                        <div class="col-3">
                            <div class="m-3">
                                <label for="price" class="form-label">Precio</label>
                                <input type="text" class="form-control" id="price" name="txt_price" value="<%=price%>" placeholder="0" required>
                            </div>
                        </div>

                        <div class="col-3">
                            <div class="m-3">
                                <label for="stock" class="form-label">Cantidad</label>
                                <input type="number" class="form-control" id="stock" name="txt_stock" value="<%=stock%>" placeholder="0">
                            </div>
                                <input type="hidden" name="txt_id_product" value="<%=idproduct%>" />
                        </div>

                        <div class="col-2">
                            <div class="mt-5">
                                <input type="submit" name="accion" value="<%=n_boton%>" class="<%=color_buton%>">
                            </div>
                        </div>
                    </div>  
                </form>
            </div>
        </div>

        <div class="col-sm m-4">
            <table class="table table-striped" >
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Producto</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Cantidad</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        List<Product> listProducts = (List) request.getAttribute("productos");
                        int count = 0;
                        for (Product pr : listProducts) {
                            count++;
                    %>
                    <tr>
                        <td><%=count%></td>
                        <td><%=pr.getName_product()%></td>
                        <td><%=pr.getPrice()%></td>
                        <td><%=pr.getStock()%></td>
                        <td>
                            <% if (pr.getStatus().equals("1")) {%>
                            <span class="btn btn-success span_small ">Activo</span>
                            <%} else { %>
                            <span class="btn btn-danger span_small">Desactivo</span>
                            <% }%>
                        <td>
                            <a class="btn btn-warning boton_small" href="Controller?op=Products&accion=Search&id=<%=pr.getIdproduct()%>">Editar</a>
                            <a class="btn btn-danger boton_small" href="Controller?op=Products&accion=Delete&id=<%=pr.getIdproduct()%>">Eliminar</a>
                        </td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

    </body>
    <style>
        .boton_small{
            --bs-btn-padding-y: .25rem;
            --bs-btn-padding-x: .5rem;
            --bs-btn-font-size: .75rem;
        }
        .span_small{
            --bs-btn-padding-y: .20rem;
            --bs-btn-padding-x: .4rem;
            --bs-btn-font-size: .70rem;
        }
    </style>

</html>