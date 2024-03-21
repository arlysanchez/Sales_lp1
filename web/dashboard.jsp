<%-- 
    Document   : dashboard
    Created on : 9 ene. 2024, 10:24:17
    Author     : JAST
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

        <title>Ventas</title>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-success">
            <div class="container-fluid">
                <button class="navbar-toggler custom-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon" style="color: #ffffff"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none;" class="btn btn-outline-light" aria-current="page" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a style="margin-left: 10px; border: none;" class="btn btn-outline-light" href="Controller?op=Users&accion=List" target="MyFrame">Usuarios</a>
                        </li>
                    </ul>
                    <div class="dropdown">
                        <button class="btn btn-outline-light dropdown-toggle" style="border: none;" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Gestión de Ventas
                        </button>
                        <ul class="dropdown-menu">
                            <li class="nav-item">
                                <a class="dropdown-item" href="Controller?op=Products&accion=List" target="MyFrame">Productos</a>
                            </li>
                        <li class="nav-item">
                            <a  class="dropdown-item" href="Controller?op=Customers&accion=List" target="MyFrame">Clientes</a>
                        </li>
                        <li class="nav-item">
                            <a class="dropdown-item" href="Controller?op=Sales&accion=default" target="MyFrame">Ventas</a>
                        </li>
                        </ul>
                    </div>

                </div>
                <div class="dropdown">
                    <button style="border: none;" class="btn btn-outline-light dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        ${usuario.getFullname()}
                    </button>
                    <ul class="dropdown-menu dropdown-menu-end text-center">
                        <li><a class="dropdown-item" href="#">
                                <img src="resources/img/user.png" alt="60" width="60"/>
                            </a></li>

                        <li><a class="dropdown-item"> ${usuario.getFullname()}</a></li>
                        <li><a class="dropdown-item">${usuario.getUser()}</a></li>
                        <div class="dropdown-divider"></div>
                        <form action="Principal" method="POST">
                            <button name="accion" value="Salir" class="dropdown-item" href="#">Salir</button>
                        </form>

                    </ul>
                </div>
            </div>
        </nav>
        <div class="m-4" style="height: 480px; ">
            <iframe name="MyFrame" style="height: 100%; width: 100%"></iframe>
        </div>                 

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>

    </body>
</html>
