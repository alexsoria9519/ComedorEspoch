<%-- 
    Document   : test
    Created on : May 5, 2021, 11:06:48 PM
    Author     : corebitsas
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="Test.TestClass"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            TestClass test = new TestClass();

            Date fecha = new SimpleDateFormat("dd/MM/yyyy").parse("08/09/2021");

            test.ingresoDatos(1, 20, fecha);
//            test.ingresoDatos(250, 150, fecha);


        %>    

        <h1>Hello World!</h1>
    </body>
</html>
