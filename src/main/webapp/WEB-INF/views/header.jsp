<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Corona Admin</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href=<c:url value="/resources/assets/vendors/mdi/css/materialdesignicons.min.css"/> type="text/css">
    <link rel="stylesheet" href=<c:url value="/resources/assets/vendors/css/vendor.bundle.base.css"/> type="text/css">

    <!-- endinject -->
    <!-- Plugin css for this page -->
    <link rel="stylesheet" href="/resources/assets/vendors/select2/select2.min.css">
    <link rel="stylesheet" href="/resources/assets/vendors/select2-bootstrap-theme/select2-bootstrap.min.css">
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href=<c:url value="/resources/assets/css/style.css"/> type="text/css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href=<c:url value="/resources/assets/images/favicon.png"/> />
    <script type="text/javascript">
        function submitform() {   document.logout.submit(); }
    </script>
</head>
<body>
<div class="container-scroller">
    <!-- partial:../../partials/_sidebar.html -->
    <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
            <a class="sidebar-brand brand-logo" href=<c:url value="/dashboard"/>><img src=<c:url value="/resources/assets/images/logo.svg"/> alt="logo" /></a>
            <a class="sidebar-brand brand-logo-mini" href=<c:url value="/dashboard"/>><img src=<c:url value="/resources/assets/images/logo-mini.svg"/> alt="logo" /></a>
        </div>
        <ul class="nav">
            <li class="nav-item profile">
                <div class="profile-desc">
                    <div class="profile-pic">
                        <div class="count-indicator">
                            <img class="img-xs rounded-circle " src=<c:url value="/resources/assets/images/faces/face15.jpg"/> alt="">
                            <span class="count bg-success"></span>
                        </div>
                        <div class="profile-name">
                            <h5 class="mb-0 font-weight-normal"><sec:authentication property="principal.username"/></h5>
                            <sec:authentication property="authorities" var="role"/>

                            <c:set var = "admin" value="ROLE_ADMIN"/>
                            <span>
                            <c:forEach items="${role}" var="rola">
                                <c:if test="${rola eq 'ROLE_ADMIN'}">admin</c:if>
                                <c:if test="${rola eq 'ROLE_USER'}">użytkownik</c:if>
                            </c:forEach></span>
                        </div>
                    </div>
                </div>
            </li>
            <li class="nav-item nav-category">
                <span class="nav-link">Navigation</span>
            </li>
            <li class="nav-item menu-items">
                <a class="nav-link" href=<c:url value="/dashboard"/>>
              <span class="menu-icon">
                <i class=" mdi mdi-checkbox-marked-circle "></i>
              </span>
                    <span class="menu-title">Cele</span>
                </a>
            </li>
            <li class="nav-item menu-items">
                <a class="nav-link" href=<c:url value="/mealNutritions/table"/>>
              <span class="menu-icon">
                <i class="mdi mdi-calendar"></i>
              </span>
                    <span class="menu-title">Historia posiłków</span>
                </a>
            </li>


            <li class="nav-item menu-items">
                <a class="nav-link" data-toggle="collapse" href="#product" aria-expanded="false" aria-controls="product">
              <span class="menu-icon">
                <i class="mdi mdi-food"></i>
              </span>
                    <span class="menu-title">Produkty</span>
                    <i class="menu-arrow"></i>
                </a>
                <div class="collapse" id="product">
                    <ul class="nav flex-column sub-menu">
                        <li class="nav-item"> <a class="nav-link" href=<c:url value="/product/all"/>> Baza produktów </a></li>
                        <li class="nav-item"> <a class="nav-link" href=<c:url value="/product/add"/>> Dodaj produkt </a></li>
                    </ul>
                </div>
            </li>

            <li class="nav-item menu-items">
                <a class="nav-link" data-toggle="collapse" href="#auth2" aria-expanded="false" aria-controls="auth2">
              <span class="menu-icon">
                <i class="mdi mdi-security"></i>
              </span>
                    <span class="menu-title">User Pages</span>
                    <i class="menu-arrow"></i>
                </a>
                <div class="collapse" id="auth2">
                    <ul class="nav flex-column sub-menu">
                        <li class="nav-item"> <a class="nav-link" href="/resources/pages/samples/blank-page.html"> Blank Page </a></li>
                        <li class="nav-item"> <a class="nav-link" href="/resourcespages/samples/error-404.html"> 404 </a></li>
                        <li class="nav-item"> <a class="nav-link" href="/resources/pages/samples/error-500.html"> 500 </a></li>
                        <li class="nav-item"> <a class="nav-link" href="/resources/pages/samples/login.html"> Login </a></li>
                        <li class="nav-item"> <a class="nav-link" href="/resources/pages/samples/register.html"> Register </a></li>
                    </ul>
                </div>
            </li>

        </ul>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
        <!-- partial:../../partials/_navbar.html -->
        <nav class="navbar p-0 fixed-top d-flex flex-row">
            <div class="navbar-brand-wrapper d-flex d-lg-none align-items-center justify-content-center">
                <a class="navbar-brand brand-logo-mini" href="#"><img src=<c:url value="/resources/assets/images/logo-mini.svg"/> alt="logo" /></a>
            </div>
            <div class="navbar-menu-wrapper flex-grow d-flex align-items-stretch">
                <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
                    <span class="mdi mdi-menu"></span>
                </button>
                <ul class="navbar-nav navbar-nav-right">
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="notificationDropdown">
                            <h6 class="p-3 mb-0">Notifications</h6>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <div class="preview-icon bg-dark rounded-circle">
                                        <i class="mdi mdi-calendar text-success"></i>
                                    </div>
                                </div>
                                <div class="preview-item-content">
                                    <p class="preview-subject mb-1">Event today</p>
                                    <p class="text-muted ellipsis mb-0"> Just a reminder that you have an event today </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <div class="preview-icon bg-dark rounded-circle">
                                        <i class="mdi mdi-settings text-danger"></i>
                                    </div>
                                </div>
                                <div class="preview-item-content">
                                    <p class="preview-subject mb-1">Settings</p>
                                    <p class="text-muted ellipsis mb-0"> Update dashboard </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item">
                                <div class="preview-thumbnail">
                                    <div class="preview-icon bg-dark rounded-circle">
                                        <i class="mdi mdi-link-variant text-warning"></i>
                                    </div>
                                </div>
                                <div class="preview-item-content">
                                    <p class="preview-subject mb-1">Launch Admin</p>
                                    <p class="text-muted ellipsis mb-0"> New admin wow! </p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <p class="p-3 mb-0 text-center">See all notifications</p>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link" id="profileDropdown" href="#" data-toggle="dropdown">
                            <div class="navbar-profile">
                                <img class="img-xs rounded-circle" src=<c:url value="/resources/assets/images/faces/face15.jpg"/> alt="">
                                <p class="mb-0 d-none d-sm-block navbar-profile-name"><sec:authentication property="principal.username"/></p>
                                <i class="mdi mdi-menu-down d-none d-sm-block"></i>
                            </div>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="profileDropdown">
                            <h6 class="p-3 mb-0">Profil</h6>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item" href=<c:url value="/user/goals"/>>
                                <div class="preview-thumbnail">
                                    <div class="preview-icon bg-dark rounded-circle">
                                        <i class="mdi mdi-settings text-success"></i>
                                    </div>
                                </div>
                                <div class="preview-item-content">
                                    <p class="preview-subject mb-1">Edytuj cele</p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item preview-item" href=<c:url value="/password/edit"/>>
                                <div class="preview-thumbnail">
                                    <div class="preview-icon bg-dark rounded-circle">
                                        <i class="mdi mdi-account-edit text-warning"></i>
                                    </div>
                                </div>
                                <div class="preview-item-content">
                                    <p class="preview-subject mb-1">Zmień hasło</p>
                                </div>
                            </a>
                            <div class="dropdown-divider"></div>
                            <form action="<c:url value="/logout"/>" method="post" name="logout">
                                <a class="dropdown-item preview-item" href="javascript: submitform()">
                                    <div class="preview-thumbnail">
                                        <div class="preview-icon bg-dark rounded-circle">
                                            <i class="mdi mdi-logout text-danger"></i>
                                        </div>
                                    </div>
                                    <div class="preview-item-content">
                                        <p class="preview-subject mb-1">Wyloguj</p>
                                    </div>
                                </a>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </div>
                    </li>
                </ul>
                <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
                    <span class="mdi mdi-format-line-spacing"></span>
                </button>
            </div>
        </nav>