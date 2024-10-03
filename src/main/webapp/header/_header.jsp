<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <!-- Font Awesome -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
            rel="stylesheet"
    />
    <!-- Google Fonts -->
    <link
            href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
            rel="stylesheet"
    />
    <!-- MDB -->
    <link
            href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.css"
            rel="stylesheet"
    />
    <!-- MDB -->
    <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.2/mdb.min.js"
    ></script>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: red">
        <div>
            <a href="https://teachmeskills.by" class="navbar-brand"> < TeachMeSkills /> </a>
        </div>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <div class="container-fluid">
                <ul class="navbar-nav d-flex flex-row">
                    <!-- Icons -->
                    <li class="nav-item me-3 me-lg-0">
                        <a class="nav-link" href="#">
                            <i class="fas fa-shopping-cart"></i>
                        </a>
                    </li>
                    <li class="nav-item me-3 me-lg-0">
                        <a class="nav-link" href="#">
                            <i class="fab fa-twitter"></i>
                        </a>
                    </li>
                    <!-- Icon dropdown -->
                    <li class="nav-item me-3 me-lg-0 dropdown">
                        <a
                                class="nav-link dropdown-toggle"
                                href="#"
                                id="navbarDropdown"
                                role="button"
                                data-mdb-toggle="dropdown"
                                aria-expanded="false"
                        >
                            <i class="fas fa-user"></i>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <c:if test="${sessionScope.author != null}">
                                <li>
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/subscription">Subscriptions</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.author != null}">
                                <li>
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/register">Edit Profile</a>
                                </li>
                            </c:if>
                            <li>
                                <hr class="dropdown-divider"/>
                            </li>
                            <li>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/else">Something else
                                    here</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>

            <c:if test="${sessionScope.author != null}">
                <li><a href="${pageContext.request.contextPath}/add_publication"
                       class="nav-link">Add new publication</a></li>

                <li><a href="${pageContext.request.contextPath}/subscribers"
                       class="nav-link">Subscribers</a></li>

                <li><a href="${pageContext.request.contextPath}/subscribe"
                       class="nav-link">Subscriptions</a></li>

                <li><a href="${pageContext.request.contextPath}/subscription"
                       class="nav-link">List of registered users</a></li>

                <li><a href="${pageContext.request.contextPath}/stories"
                       class="nav-link">Stories</a>
                </li>

            </c:if>
            <c:if test="${sessionScope.author == null}">
                <li><a href="<%= request.getContextPath() %>/register" class="nav-link">Signup</a></li>
            </c:if>
            <c:if test="${sessionScope.author != null}">
                <li><a href="<%= request.getContextPath() %>/logout" class="nav-link">LogOut</a></li>
            </c:if>
        </ul>
    </nav>
</header>
