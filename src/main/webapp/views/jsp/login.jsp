<%-- 
    Document   : index
    Created on : 28/01/2016, 10:33:37
    Author     : victor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="utf-8">
        <meta name=description content="Login">
        <meta name=viewport content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>SisCultural - Login</title>

        <!-- Bootstrap -->
        <link href="views/css/bootstrap-theme.min.css" rel="stylesheet">
        <link href="views/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" type="text/css" href="views/css/style.css">
    </head>

    <body>
        <%@ include file="modals/error-modal.jsp" %>

        <div class="container container-login">
            <div class="row row-login">
                <div class="logo-box">
                    <img class="logo img-rounded" src="views/img/ccbnb.png">
                </div>
                <div class="logo-box">
                    <h1>SisCultural</h1>
                </div>

                <div class="input-group">
                    <form id="form-login" class="form" action="login" method="post">
                        <input type="text" name="email" id="login" class="form-control" placeholder="Usuario ou Email" required>
                        <input type="password" name="password" id="password" class="form-control" placeholder="Senha" required>

                        <div class="form-options">
                            <div class="forgetkey-box">
                                <div class="remember-box">
                                    <label class="checkbox-inline"><input type="checkbox" value="">Lembrar Dados</label>
                                </div>
                                <a href="home.jsp">Esqueceu a senha?</a>
                            </div>

                            <div class="submit-box">
                                <input id="input-login" type="submit" class="btn btn-primary submit" value="Login">
                            </div>
                        </div>
                    </form>
                </div>
                <div>
                    <script src="views/js/jquery-3.1.0.min.js"></script>
                    <script src="views/js/bootstrap.min.js"></script>
                </div>
            </div>
        </div>

    </body>
    
    <script src="views/js/ajax-request-manager.js" type="text/javascript"></script>
    
    <script>
        
        executeAjax('#input-login', 'login', '#form-login');
    </script>

</html>