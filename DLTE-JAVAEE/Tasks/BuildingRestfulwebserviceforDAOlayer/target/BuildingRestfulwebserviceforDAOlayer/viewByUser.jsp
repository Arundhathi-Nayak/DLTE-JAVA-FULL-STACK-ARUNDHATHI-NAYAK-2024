<%--
  Created by IntelliJ IDEA.
  User: xxnayaka
  Date: 4/20/2024
  Time: 9:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View All Transaction By User</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<% response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
    if(session.getAttribute("username")!=null){ %>
<nav class="navbar navbar-expand-lg" style="background:linear-gradient(90deg,white,red);">
    <div class="container-fluid">
        <!-- 1st logo/ brand -->
        <a class="navbar-brand text-danger display-6 text-uppercase" style="font-weight: bold;" href="#">MyBank</a>
        <!-- 2nd toggle a -->
        <a class="navbar-toggler bg-light" type="a" data-bs-toggle="collapse" data-bs-target="#myBankMenu">
            <span class="navbar-toggler-icon"></span>
        </a>
        <!-- 3rd Menu -->
        <div class="collapse navbar-collapse" id="myBankMenu">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a href="view" class="btn btn-outline-light rounded-5 me-2"><span class="bi bi-list-columns"></span> View</a>
                </li>
                <li class="nav-item">
                    <a href="account-result.jsp" class="btn btn-outline-light rounded-5 me-2"><span class="bi bi-credit-card"></span> New Account</a>
                </li>
                <li class="nav-item">
                    <a href="withdraw.jsp" class="btn btn-outline-light rounded-5 me-2"><span class="bi bi-cloud-plus-fill"></span> Withdraw</a>
                </li>
                <li class="nav-item">
                    <a href="logout" class="btn btn-outline-light rounded-5 me-2"><span class="bi bi-door-open"></span> Logout</a>
                </li>
                <li>
                    <form action="viewByUser.jsp">
                        <input type="text" name="username" />
                        <input type="submit" value="filter">
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>

<%@ taglib prefix="elroy" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="rakchanya" uri="http://java.sun.com/jsp/jstl/core" %>
<elroy:setDataSource var="sanakroy" driver="oracle.jdbc.driver.OracleDriver"
                     url="jdbc:oracle:thin:@localhost:1521:xe"
                     user="system"
                     password="1222024"
/>
<elroy:query var="arunashira" dataSource="${sanakroy}" sql="select * from transactions where transaction_username=?">
    <elroy:param value="${param['limit']}"/>
</elroy:query>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-4 col-md-8 col-12 table-responsive p-5 shadow-lg">
            <table class="table table-striped text-nowrap">
                <thead>
                <tr>
                    <th>Transaction ID</th><th>Username</th>
                    <th>Date of Transaction</th><th>Amount</th>
                    <th>Balance</th>
                </tr>
                </thead>
                <tbody>
                <rakchanya:forEach items="${arunashira.rows}" var="prakash">
                    <tr>
                        <td>${prakash.TRANSACTION_ID}</td>
                        <td>${prakash.TRANSACTION_USERNAME}</td>
                        <td>${prakash.TRANSACTION_DATE}</td>
                        <td>${prakash.TRANSACTION_AMOUNT}</td>
                        <td>${prakash.TRANSACTION_BALANCE}</td>
                    </tr>
                </rakchanya:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<% }
else {
    response.sendRedirect("index.jsp");
}%>
</body>
</html>