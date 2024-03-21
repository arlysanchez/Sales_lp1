<%-- 
    Document   : customers
    Created on : 9 ene. 2024, 15:45:58
    Author     : JAST
--%>

<%@page import="Model.Person"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String fullname = "";
    String num_document = "";
    String address = "";
    String telephone = "";
    String type_person = "2";
    int idPerson = 0;
    String n_boton = "";
    String color_buton = "";

    Person cliente_edit = (Person) request.getAttribute("cliente_edit");
    fullname = (cliente_edit != null && cliente_edit.getFullname() != null) ? cliente_edit.getFullname() : "";
    num_document = (cliente_edit != null && cliente_edit.getNum_document() != null) ? cliente_edit.getNum_document() : "";
    address = (cliente_edit != null && cliente_edit.getAddress()!= null) ? cliente_edit.getAddress() : "";
    telephone = (cliente_edit != null && cliente_edit.getTelephone()!= null) ? cliente_edit.getTelephone() : "";
    type_person = (cliente_edit != null && cliente_edit.getIdTypePerson() != null) ? cliente_edit.getIdTypePerson() : "2";
    idPerson = (cliente_edit != null) ? cliente_edit.getIdperson() : idPerson;

    if (idPerson == 0) {
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
        <div class="d-flex">
            <div class="card col-sm-4" style="background-color: aliceblue">
                <div class="card-body">
                    <form action="Controller?op=Customers" method="POST">
                        <div class="mb-3">
                            <label for="fullname" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="fullname" name="txt_fullname" value="<%=fullname%>" placeholder="nombres y apellidos" required>
                        </div>
                        <div class="mb-3">
                            <label for="num_document" class="form-label">DNI</label>
                            <input type="text" class="form-control" id="num_document" name="txt_num_document" value="<%=num_document%>" placeholder="12345678" required>
                        </div>
                        <div class="mb-3">
                            <label for="address" class="form-label">Dirección</label>
                            <input type="text" class="form-control" id="address" name="txt_address" value="<%=address%>" placeholder="jr. los martires 340">
                        </div>
                        <div class="mb-3">
                            <label for="telephone" class="form-label">Telephone</label>
                            <input type="text" class="form-control" id="telephone" value="<%=telephone%>" name="txt_telephone">
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="status" checked name="txt_status" value="1">
                            <label for="status" class="form-check-label">Estado</label>
                        </div>
                        <input type="hidden" name="txt_type_person" id="type_person"  value="<%=type_person%>">
                        <input type="hidden" name="txt_id_persona" value="<%=idPerson%>" />

                        
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <input type="submit" name="accion" value="<%=n_boton%>" class="<%=color_buton%>">
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-sm m-1">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nombres</th>
                            <th scope="col">DNI</th>
                            <th scope="col">Direccion</th>
                            <th scope="col">Telefono</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Person> listCliente = (List) request.getAttribute("clientes");
                            int count = 0;
                            for (Person cl : listCliente) {
                                count++;
                        %>
                        <tr>
                            <td><%=count%></td>
                            <td><%=cl.getFullname()%></td>
                            <td><%=cl.getNum_document()%></td>
                            <td><%=cl.getAddress()%></td>
                            <td><%=cl.getTelephone()%></td>
                            <td>
                                <% if (cl.getStatus().equals("1")) {%>
                                <span class="btn btn-success span_small ">Activo</span>
                                <%} else { %>
                                <span class="btn btn-danger span_small">Desactivo</span>
                                <% }%>
                            <td>
                                <a class="btn btn-warning boton_small" href="Controller?op=Customers&accion=Search&id=<%=cl.getIdperson()%>">Editar</a>
                                <a class="btn btn-danger boton_small" href="Controller?op=Customers&accion=Delete&id=<%=cl.getIdperson()%>">Eliminar</a>
                            </td>
                        </tr>
                        <% }%>

                    </tbody>
                </table>
            </div>
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
            --bs-btn-font-size: .50rem;
        }
    </style>
</html>

