<%@ page import="java.util.Base64" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Register Form</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<jsp:include page="../../header/_header.jsp"></jsp:include>
<c:if test="${sessionScope.author == null}">
    <div class="container col-md-3 col-md-offset-3" style="overflow: auto">
        <h2>User Register Form</h2>
        <div class="alert alert-success center" role="alert">
            <p>${NOTIFICATION}</p>
        </div>
        <form action="<%=request.getContextPath()%>/register" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="username">User Name:</label> <input type="text"
                                                                class="form-control" id="username"
                                                                placeholder="User Name"
                                                                name="username" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label> <input type="email"
                                                         class="form-control" id="email" placeholder="Email"
                                                         name="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label> <input type="text"
                                                               class="form-control" id="password" placeholder="Password"
                                                               name="password" required>
            </div>
            <div class="mb-3">
                <label for="formFile" class="form-label">Choose a profile photo:</label>
                <input class="form-control" type="file" id="formFile" name="avatar">
            </div>
            <div class="form-group">
                <label for="bio">Bio:</label> <input type="text"
                                                     class="form-control" id="bio" placeholder="Bio"
                                                     name="bio" required>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>
</c:if>


<c:if test="${sessionScope.author != null}">
    <style>
        img {
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 5px;
            width: 350px;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
    <%--<img src="data:image/jpeg;base64,${dataAvatar}" alt="User Avatar">--%>
    <img src="data:image/jpeg;base64,${author.avatar}" alt="User Avatar">
    <div class="container col-md-4">
        <div class="card">
            <div class="card-body">
                <c:if test="${sessionScope.author != null}">
                <form action="update" method="post">
                    </c:if>
                    <c:if test="${sessionScope.author == null}">
                    <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${sessionScope.author != null}">
                                    Edit Profile
                                </c:if>
                                <c:if test="${sessionScope.author == null}">
                                    Add New Stories
                                </c:if>
                            </h2>
                        </caption>


                        <fieldset class="form-group">
                            <label>Username</label> <label>
                            <input type="text"
                                   value="<c:out value='${author.username}' />" class="form-control"
                                   name="username" required="required" minlength="2">
                        </label>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Email</label> <label>
                            <input type="text"
                                   value="<c:out value='${author.email}' />" class="form-control"
                                   name="email" required="required" minlength="2">
                        </label>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Registration date: </label> <label>
                            <input type="text"
                                   value="<c:out value='${author.registrationOfDate}' />"
                                   class="form-control"
                                   name="description" minlength="2">
                        </label>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Profile Status</label> <label>
                            <select class="form-control"
                                    name="publicAccount">
                                <option value="false">Private</option>
                                <option value="true">Public</option>
                            </select>
                        </label>
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Publication end date</label> <label>
                            <input type="date"
                                   value="<c:out value='${author.registrationOfDate}' />"
                                   class="form-control"
                                   name="targetDate" required="required">
                        </label>
                        </fieldset>

                        <button type="submit" class="btn btn-success">Publish</button>
                    </form>
            </div>
        </div>
    </div>
</c:if>
<jsp:include page="../../footer/_footer.jsp"></jsp:include>
</body>
</html>
