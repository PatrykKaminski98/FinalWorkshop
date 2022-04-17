<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
    <!-- partial -->
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="row">
                    <p align="center">Twoje cele</p>
                </div>
                <div class="col-md-6 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Twoje cele</h4>
                            <p class="card-description"> Jeżeli nie wiesz jak dobrać swoje cele, zajrzyj do zakładki: zapotrzebowanie energi </p>
                            <form:form class="forms-sample" method="post" action="/user/goals" modelAttribute="userGoals">
                                <div class="form-group">
                                    <form:label path="kilocalories">Kalorie</form:label>
                                    <form:input path="kilocalories" type="number" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="proteins">Białko</form:label>
                                    <form:input path="proteins" type="number" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="carbohydrates">Węglowodany</form:label>
                                    <form:input path="carbohydrates" type="number" class="form-control"/>
                                </div>
                                <div class="form-group">
                                    <form:label path="fats">Tłuszcze</form:label>
                                    <form:input path="fats" type="number" class="form-control"/>
                                </div>
                                <form:hidden path="id"/>
                                <form:hidden path="user"/>
                                <button type="submit" class="btn btn-success btn-icon-text">
                                    <i class="mdi mdi-upload btn-icon-prepend"></i> Zapisz zmiany </button>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- content-wrapper ends -->
            <!-- partial:partials/_footer.html -->
            <jsp:include page="../footer.jsp"/>