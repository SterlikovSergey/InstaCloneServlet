<%--
  Created by IntelliJ IDEA.
  User: sergeysterlikov
  Date: 5.10.23
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Base64" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<meta charset="ISO-8859-1">
<title>User Register Form</title>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<head>
    <title>Subscriptions</title>
</head>
<body>
<jsp:include page="../../header/_header.jsp"></jsp:include>
<style>
    img {
        border: 1px solid #ddd;
        border-radius: 4px;
        padding: 5px;
        width: 250px;
        display: block;
        margin-left: auto;
        margin-right: auto;
    }
</style>
<div class="row">
    <div class="container col-md-8 col-md-offset-3" style="overflow: auto">

        <h5 class="text-center"> Author name: ${author.username} <img src="data:image/jpeg;base64,${author.avatar}"
                                                                      alt="User Avatar"></h5>
        <h3 class="text-center">List of subscriptions</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/add_publication"
               class="btn btn-success">Add publication</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Avatar</th>
                <th>Registration date</th>
                <th>Biography</th>
                <th>Click to subscribe</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="peoples" items="${requestScope.peoples}">
                <tr>
                    <td><c:out value="${peoples.username}"/></td>
                    <td><c:out value="${peoples.email}"/></td>
                    <td><img src="data:image/jpeg;base64,${peoples.avatar}" alt="User Avatar"></td>
                    <td><c:out value="${peoples.registrationOfDate}"/></td>
                    <td><c:out value="${peoples.bio}"/></td>
                    <td>
                        <form method="post" action="<c:url value='/subscribe'/>">
                            <label>
                                <input type="number" hidden name="id" value="${peoples.id}"/>
                            </label>
                            <input type="submit" name="subscribe" value="Subscribe"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../../footer/_footer.jsp"></jsp:include>
</body>
</html>