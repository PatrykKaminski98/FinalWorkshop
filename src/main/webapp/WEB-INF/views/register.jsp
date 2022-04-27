<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Corona Admin</title>
    <!-- plugins:css -->
    <link rel="stylesheet" href=<c:url value="../resources/assets/vendors/mdi/css/materialdesignicons.min.css"/>>
    <link rel="stylesheet" href=<c:url value="../resources/assets/vendors/css/vendor.bundle.base.css"/>>
    <!-- endinject -->
    <!-- Plugin css for this page -->
    <!-- End plugin css for this page -->
    <!-- inject:css -->
    <!-- endinject -->
    <!-- Layout styles -->
    <link rel="stylesheet" href=<c:url value="../resources/assets/css/style.css"/>>
    <!-- End layout styles -->
    <link rel="shortcut icon" href=<c:url value="../resources/assets/images/favicon.png"/>>
  </head>
  <body>
    <div class="container-scroller">
      <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="row w-100 m-0">
          <div class="content-wrapper full-page-wrapper d-flex align-items-center auth login-bg">
            <div class="card col-lg-4 mx-auto">
              <div class="card-body px-5 py-5">
                <h3 class="card-title text-left mb-3">Register</h3>
                <form:form modelAttribute="userDto" method="post" >
                  <div class="form-group">
                    <p> <form:errors path="*"/></p>
                    <form:label path="email"> Email *</form:label>
                    <form:input type="text" path="email" class="form-control p_input"/>

                  </div>
                  <div class="form-group">
                    <form:label path="username">Username *</form:label>
                    <form:input path="username" type="text" class="form-control p_input"/>
                  </div>
                  <div class="form-group">
                    <form:label path="password" >Password *</form:label>
                    <form:input path="password" type="password" class="form-control p_input"/>

                  </div>
                  <div class="form-group">
                    <form:label path="confirmPassword" >Confirm password *</form:label>
                    <form:input path="confirmPassword" type="password" class="form-control p_input"/>
                  </div>
                  <div class="form-group d-flex align-items-center justify-content-between">
                    <a href="#" class="forgot-pass">Forgot password</a>
                  </div>
                  <div class="text-center">
                    <button type="submit" class="btn btn-primary btn-block enter-btn">Register</button>
                  </div>
                  <p class="sign-up text-center">Already have an Account?<a href=<c:url value="/login"/>> Sign Up</a></p>
                </form:form>
              </div>
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
    <script src=<c:url value="../resources/assets/vendors/js/vendor.bundle.base.js"/>></script>
    <!-- endinject -->
    <!-- Plugin js for this page -->
    <!-- End plugin js for this page -->
    <!-- inject:js -->
    <script src=<c:url value="../resources/assets/js/off-canvas.js"/>></script>
    <script src=<c:url value="../resources/assets/js/hoverable-collapse.js"/>></script>
    <script src=<c:url value="../resources/assets/js/misc.js"/>></script>
    <script src=<c:url value="../resources/assets/js/settings.js"/>></script>
    <script src=<c:url value="../resources/assets/js/todolist.js"/>></script>
    <!-- endinject -->
  </body>
</html>