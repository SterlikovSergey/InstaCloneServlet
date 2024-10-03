<%--
  Created by IntelliJ IDEA.
  User: simonpirko
  Date: 18.09.23
  Time: 9:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login form</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<jsp:include page="../../header/_header.jsp"></jsp:include>
<div class="container col-md-3 col-md-offset-3" style="overflow: auto">
    <h1>Login Form</h1>
    <div class="alert alert-success center" role="alert">
        <p>${NOTIFICATION}</p>
    </div>
    <form action="<%=request.getContextPath()%>/login" method="post">


        <div class="form-group">
            <label for="email">Email:</label> <input type="email"
                                                     class="form-control" id="email" placeholder="Email"
                                                     name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label> <input type="password"
                                                           class="form-control" id="password" placeholder="Password"
                                                           name="password" required>
        </div>

        <%--        <div class="mb-3">
                    <label for="formFile" class="form-label">Select file input example</label>
                    <input class="form-control" type="file" id="formFile" name="avatar">
                </div>--%>


        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</div>
<jsp:include page="../../footer/_footer.jsp"></jsp:include>
</body>
</html>