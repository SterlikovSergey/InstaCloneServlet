<%--
  Created by IntelliJ IDEA.
  User: sergeysterlikov
  Date: 19.09.23
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: red">
        <div>
            <a href="https://teachmeskills.by" class="navbar-brand"> < TeachMeSkills /> </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/stories"
                   class="nav-link">Stories</a></li>
        </ul>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%=request.getContextPath()%>/profile"
                   class="nav-link">Profile</a></li>
            <li><a href="<%=request.getContextPath()%>/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Stories</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new"
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
            </tr>
            </thead>
            <tbody>
            <!--   for (Stories stories: Stories) {  -->
            <c:forEach var="todo" items="${peoples}"><%--listStories--%>

                <tr>
                    <td><c:out value="${peoples.username}"/></td>
                        <%--stories.--%>
                    <td><c:out value="${peoples.email}"/></td>
                    <td><c:out value="${peoples.registrationOfDate}"/></td>

                    <td><a href="edit?id=<c:out value='${Stories.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${Stories.id}' />">Delete</a></td>

                    <!--  <td><button (click)="updateTodo(Stories.id)" class="btn btn-success">Update</button>
                              <button (click)="deleteTodo(Stories.id)" class="btn btn-warning">Delete</button></td> -->
                </tr>
            </c:forEach>
            <!-- } -->
            </tbody>

        </table>
    </div>
</div>

<jsp:include page="../../footer/_footer.jsp"></jsp:include>
</body>
</html>
