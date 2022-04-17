<jsp:include page="../header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- partial -->
<div class="main-panel">
    <div class="content-wrapper">
        <div class="row">
            <div class="col-md-8 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Zmień hasło:</h4>
                        <form:form method="post"  class="forms-sample">
                            <div class="form-group">
                                <label for="oldPassword">Podaj stare hasło:</label>
                                <input name="oldPassword" id ="oldPassword" type="password" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label for="newPassword1">Podaj nowe hasło:</label>
                                <input name="newPassword1" id ="newPassword1" type="password" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label for="newPassword2">Powtórz nowe hasło:</label>
                                <input name="newPassword2" id ="newPassword2" type="password" class="form-control"/>
                            </div>
                            <div class="template-demo">
                                <button type="submit" class="btn btn-success btn-icon-text">
                                    <i class="mdi mdi-upload btn-icon-prepend"></i> Zapisz zmiany </button>
                            </div>

                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- content-wrapper ends -->
<jsp:include page="../footer.jsp"/>