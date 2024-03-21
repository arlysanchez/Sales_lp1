<%-- 
    Document   : users
    Created on : 9 ene. 2024, 15:45:12
    Author     : JAST
--%>

<%@page import="Model.Person"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String fullname = "";
    String num_document = "";
    String user = "";
    String password = "";
    String type_person = "1";
    int idPerson = 0;
    String n_boton = "";
    String color_buton = "";

    Person usuarioEditado = (Person) request.getAttribute("usuarios_edit");
    fullname = (usuarioEditado != null && usuarioEditado.getFullname() != null) ? usuarioEditado.getFullname() : "";
    num_document = (usuarioEditado != null && usuarioEditado.getNum_document() != null) ? usuarioEditado.getNum_document() : "";
    user = (usuarioEditado != null && usuarioEditado.getUser() != null) ? usuarioEditado.getUser() : "";
    password = (usuarioEditado != null && usuarioEditado.getPassword() != null) ? usuarioEditado.getPassword() : "";
    type_person = (usuarioEditado != null && usuarioEditado.getIdTypePerson() != null) ? usuarioEditado.getIdTypePerson() : "1";
    idPerson = (usuarioEditado != null) ? usuarioEditado.getIdperson() : idPerson;

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
            <div class="card col-sm-4">
                <div class="card-body ">
                    <form action="Controller?op=Users" method="POST">
                        <div class="mb-3">
                            <label for="fullname" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="fullname" name="txt_fullname" value="<%=fullname%>" placeholder="Jhan Sanchez Tarrillo">
                        </div>
                        <div class="mb-3">
                            <label for="num_document" class="form-label">DNI</label>
                            <input type="text" class="form-control" id="num_document" name="txt_num_document" value="<%=num_document%>" placeholder="12345678">
                        </div>
                        <div class="mb-3">
                            <label for="user" class="form-label">Usuario</label>
                            <input type="email" class="form-control" id="user" name="txt_user" value="<%=user%>" placeholder="jhan@gmail.com">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña</label>
                            <input type="text" class="form-control" id="password" value="<%=password%>" name="txt_password">
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
                            <th scope="col">Usuario</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            List<Person> listUsuario = (List) request.getAttribute("usuarios");
                            int count = 0;
                            for (Person per : listUsuario) {
                                count++;
                        %>
                        <tr>
                            <td><%=count%></td>
                            <td><%=per.getFullname()%></td>
                            <td><%=per.getNum_document()%></td>
                            <td><%=per.getUser()%></td>
                            <td>
                                <% if (per.getStatus().equals("1")) {%>
                                <span class="btn btn-success span_small ">Activo</span>
                                <%} else { %>
                                <span class="btn btn-danger span_small">Desactivo</span>
                                <% }%>
                            <td>
                                <a class="btn btn-warning boton_small" href="Controller?op=Users&accion=Search&id=<%=per.getIdperson()%>">Editar</a>
                                <a class="btn btn-danger boton_small" href="Controller?op=Users&accion=Delete&id=<%=per.getIdperson()%>">Eliminar</a>
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
