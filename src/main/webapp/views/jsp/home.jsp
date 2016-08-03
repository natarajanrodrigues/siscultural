<%-- 
    Document   : home
    Created on : 03/08/2016, 15:51:11
    Author     : Victor Hugo <victor.hugo.origins@gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="utf-8">
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <meta name=description content="Home">
        <meta name=viewport content="width=device-width, initial-s  cale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SistemaX - Home</title>

        <!-- Bootstrap -->
        <link href="bootstrap-3.3.6-dist/css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">

        <!--css-->
        <link rel="stylesheet" type="text/css" href="css/style-pages.css">

        <!--scripts-->
        <script src="js/jquery-1.12.0.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    </head>

    <body>

        <%@ include file="pages/header.jsp" %>

        <div class="container container-full">
            <ol class="breadcrumb">
                <li class="active">Home</li>
            </ol>
        </div>

        <div class="container">
            <div class="panel-group" id="accordion">

                <div class="panel panel-info">
                    <div class="panel-heading">
                        <a href="#collpaseone" data-toggle="collapse" data-parent="accordion">
                            <h4 class="panel-title">Administração</h4></a>
                    </div>
                    <div id="collpaseone" class="panel-collapse collapse in">
                        <div class="panel-body">

                            <div class="panel-option">
                                <a href="gerenciar_usuario.jsp">
                                    <img src="img/gerenciar_usuario_img.png" alt="..." class="img-rounded option-img">
                                    <p>Gerenciar Usuários</p>
                                </a>
                            </div>

                            <div class="panel-option">
                                <a href="calendar.jsp">
                                    <img src="img/gerenciar_calendario_img.png" alt="..." class="img-rounded option-img">
                                    <p>Gerenciar Feriados</p>
                                </a>
                            </div>

                            <!--                                <div class="panel-option">
                                                                <a href="">
                                                                    <img src="img/logo.png" alt="..." class="img-rounded option-img">
                                                                    <p>Funcionalidade 3</p>
                                                                </a>
                                                            </div>-->
                        </div>
                    </div>
                </div>

                <div class="panel panel-info">
                    <div class="panel-heading">
                        <a href="#collpasetwo" data-toggle="collapse" data-parent="accordion">
                            <h4 class="panel-title">Controle de Infraestrutura e Recursos</h4></a>
                    </div>
                    <div id="collpasetwo" class="panel-collapse collapse in">
                        <div class="panel-body">

                            <div class="panel-option">
                                <a href="manter_material.jsp">
                                    <img src="img/gerenciar_material_img.png" alt="..." class="img-rounded option-img">
                                    <p>Gerenciar Material</p>
                                </a>
                            </div>

                            <div class="panel-option">
                                <a href="gerenciar_bloco.jsp">
                                    <img src="img/company-building-blue.png" alt="..." class="img-rounded option-img">
                                    <p>Gerenciar Blocos</p>
                                </a>
                            </div>

                            <div class="panel-option">
                                <a href="gerenciar_salas.jsp">
                                    <img src="img/sala-icon.png" alt="..." class="img-rounded option-img">
                                    <p>Gerenciar Salas</p>
                                </a>
                            </div>

                            <div class="panel-option">
                                <a href="alocar_salas.jsp">
                                    <img src="img/locar-sala-img.png" alt="..." class="img-rounded option-img" style="width: 72px">
                                    <p>Alocar Salas</p>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>


        <footer></footer>
    </body>
</html>