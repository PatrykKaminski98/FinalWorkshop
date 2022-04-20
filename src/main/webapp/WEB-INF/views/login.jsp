<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<fmt:setBundle basename="messages" />
<fmt:message key="message.password" var="noPass" />
<fmt:message key="message.username" var="noUser" />
<fmt:message key="message.regSucc" var="regSucc" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Corona Admin</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href="../../resources/assets/vendors/mdi/css/materialdesignicons.min.css">
    <link rel="stylesheet" href="../../resources/assets/vendors/css/vendor.bundle.base.css">
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href="../../resources/assets/css/style.css">
    <!-- End layout styles -->
    <link rel="shortcut icon" href="../../resources/assets/images/favicon.png" />
  </head>
  <body>
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="row w-100 m-0">
          <div class="content-wrapper full-page-wrapper d-flex align-items-center auth login-bg">
            <div class="card col-lg-4 mx-auto">
              <div class="card-body px-5 py-5">
                <h3 class="card-title text-left mb-3">Login</h3>
                <c:if test="${param.error != null}">
                  <div id="error">
                    <spring:message code="message.badCredentials">
                    </spring:message>
                  </div>
                </c:if>
                <form method="post" name="login">
                  <div class="form-group">
                    <label>Username *</label>
                    <input type="text" class="form-control p_input" name="username">
                  </div>
                  <div class="form-group">
                    <label>Password *</label>
                    <input type="password" class="form-control p_input" name="password">
                  </div>
                  <div class="form-group d-flex align-items-center justify-content-between">
                    <a href="#" class="forgot-pass">Forgot password</a>
                  </div>
                  <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-block enter-btn">Login</button>
                  </div>
                  <p class="sign-up">Don't have an Account?<a href=<c:url value="/register/form"/>> Sign Up</a></p>
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
              </div>
              <c:if test="${registerSuccess == true}">
                <div id="status">
                  blabla
                  <spring:message code="message.regSucc">
                  </spring:message>
                </div>
              </c:if>

            </div>
          </div>
          <!-- content-wrapper ends -->
        </div>
        <!-- row ends -->
      </div>
      <!-- page-body-wrapper ends -->
    </div>
    <!-- container-scroller -->
    <!-- plugins:js -->
    <script src="../../resources/assets/vendors/js/vendor.bundle.base.js"></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src="../../resources/assets/js/off-canvas.js"></script>
    <script src="../../resources/assets/js/hoverable-collapse.js"></script>
    <script src="../../resources/assets/js/misc.js"></script>
    <script src="../../resources/assets/js/settings.js"></script>
    <script src="../../resources/assets/js/todolist.js"></script>
    <!-- endinject -->
  </body>
</html>