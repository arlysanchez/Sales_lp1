<%-- 
    Document   : sales
    Created on : 9 ene. 2024, 15:46:07
    Author     : JAST
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Sales"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="Model.Product"%>
<%@page import="Model.Person"%>
<%
    String fullname = "";
    String num_document = "";
    Person client = (Person) request.getAttribute("client");
    fullname = (client != null && client.getFullname() != null) ? client.getFullname() : "";
    num_document = (client != null && client.getNum_document() != null) ? client.getNum_document() : "";
    Integer idPerson = (client != null) ? client.getIdperson() : null;

    Product product = (Product) request.getAttribute("producto");
    String name_product = "";
    BigDecimal price = BigDecimal.ZERO;
    String stock = "";

    name_product = (product != null && product.getName_product() != null) ? product.getName_product() : "";
    price = (product != null && product.getPrice() != null) ? product.getPrice() : BigDecimal.ZERO;
    stock = (product != null && product.getStock() != null) ? product.getStock() : "";
    Integer idproduct = (product != null) ? product.getIdproduct() : 0;

    String totalPay = "";
    Object totalPayAttribute = request.getAttribute("totalPay");

    if (totalPayAttribute != null) {
        totalPay = totalPayAttribute.toString();
    }
    String serie = "";
    Object serieAttribute = request.getAttribute("serial");

    if (serieAttribute != null) {
        serie = serieAttribute.toString();
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <title>Venta</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <style>
            @media print{
                .part-1, .btn, .action {
                    display: none;
                }
            }
        </style>
    </head>
    <body>
        <div class="d-flex">
            <div class="col-sm-4 m-1 part-1">
                <form action="Controller?op=Sales" method="POST">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group">
                                <label class="m-1">Cliente</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-6 d-flex">
                                    <input type="text" name="txt_cod_customer" value="<%=num_document%>" class="form-control m-1 " placeholder="code">
                                    <button type="submit" name="accion" class="btn btn-outline-info m-1" value="Cliente">Buscar</button>

                                </div>
                                <div class="col-sm-6">
                                    <input type="text" name="txt_name_customer" value="<%=fullname%>" class="form-control m-1" placeholder="nombre Cliente">
                                </div>
                            </div>
                            <div class="form-group"> 
                                <div class="form-group">
                                    <label class="m-1">Producto</label>
                                </div>
                                <div class="form-group d-flex">
                                    <div class="col-sm-6 d-flex">
                                        <input type="text" name="txt_cod_product" class="form-control m-1" value="<%=idproduct%>" placeholder="code product">
                                        <button type="submit" name="accion" class="btn btn-outline-success m-1" value="Producto">Buscar</button>

                                    </div>
                                    <div class="col-sm-6">
                                        <input type="text" name="txt_name_product" class="form-control m-1" value="<%=name_product%>" placeholder="Datos Producto">
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="form-group d-flex"> 
                                <div class="col-sm-6">
                                    <label for="txt_precio" class="form-label">Precio</label>
                                    <div class="d-flex">
                                        <input type="text" id="txt_precio" name="txt_precio" class="form-control m-1" value="<%=price%>" placeholder="S/. 0,00">
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <label for="txt_cantidad" class="form-label">Cantidad</label>
                                    <div class="d-flex">
                                        <input type="number" id="txt_cantidad" name="txt_cantidad" class="form-control m-1" value="1" placeholder="0">
                                    </div>
                                </div>
                                <div class="col-sm-3">
                                    <label for="txt_stock" class="form-label">Stock</label>
                                    <div class="d-flex">
                                        <input type="text" id="txt_stock" name="txt_stock" class="form-control m-1" value="<%=stock%>" placeholder="0" disabled>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button type="submit" name="accion" class="btn btn-outline-danger mt-2" value="Agregar">Agregar</button>
                                </div>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
            <div class="col-sm m-1">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex col-sm-3 justify-content-end align-items-start ms-auto">
                            <label class="mt-2 m-1">N°Serie:</label>
                            <input type="text" name="txt_serie" value="<%=serie%>" class="form-control" disabled>

                        </div>
                        <br>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Codigo</th>
                                    <th scope="col">Descripcion</th>
                                    <th scope="col">Precio</th>
                                    <th scope="col">Cantidad</th>
                                    <th scope="col">SubTotal</th>
                                    <th scope="col"  class="action">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>

                                <%
                                    List<Sales> listVenta = new ArrayList<>();
                                    Object listaAtributo = request.getAttribute("lista");

                                    if (listaAtributo != null && listaAtributo instanceof List) {
                                        listVenta = (List<Sales>) listaAtributo;
                                    }

                                    int count = 0;
                                    count++;
                                    for (Sales v : listVenta) {
                                        count++;
                                %>
                                <tr>
                                    <td><%=count%></td>
                                    <td><%=v.getIdproduct()%></td>
                                    <td><%=v.getDescription_product()%></td>
                                    <td><%=v.getPrice()%></td>
                                    <td><%=v.getCantidad()%></td>
                                    <td><%=v.getSubTotal()%></td>
                                    <td>
                                        <a class="btn btn-danger boton_small" href="Controller?op=Sales&accion=Delete&id=<%=v.getIdproduct()%>">Eliminar</a>

                                    </td>
                                </tr>
                                <% }%>
                                <tr>
                                    <td colspan="4"</td>
                                    <td  style="text-align: left; font-weight: bold;">Total:</td>
                                    <td style="font-weight: bold;">S/. <%=totalPay%></td> 
                                    <td></td> 
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="card-footer">
                        <div class="d-grid gap-1 d-md-flex justify-content-md-end">
                            <a href="Controller?op=Sales&accion=Generate_Venta" onclick="print()" class="btn btn-success" > Generar Venta</a>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <style>
        .boton_small{
            --bs-btn-padding-y: .25rem;
            --bs-btn-padding-x: .5rem;
            --bs-btn-font-size: .75rem;
        }
        .span_small{
            --bs-btn-padding-y: .20rem;
            --bs-btn-padding-x: .4rem;
            --bs-btn-font-size: .50rem;
        }
        .short-input {
            width: 4em; /* Ajusta el ancho según tus necesidades */
        }
    </style>
</html>
