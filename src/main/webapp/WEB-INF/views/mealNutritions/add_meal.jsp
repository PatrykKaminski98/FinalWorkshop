<jsp:include page="../header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- partial -->
<div class="main-panel">
    <div class="content-wrapper">
        <div class="row">
            <div class="col-md-8 grid-margin stretch-card" id="mealName">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Dodaj posiłek</h4>
                        <div class="form-group">
                            <form:form modelAttribute="mealNutrition" method="post">
                            <form:hidden path="id"/>
                            <form:label path="meal">Nazwa posiłku:</form:label>
                            <form:select path="meal" items="${meals}" itemLabel="name"/>
                            <div class="template-demo">
                                <button class="btn btn-success btn-icon-text" type="submit">
                                    <i class="mdi mdi-upload btn-icon-prepend"></i> Idź dalej </button>
                                </form:form>
                                <a href=<c:url value="/product/all"/> <button type="button" class="btn btn-danger btn-icon-text"> Anuluj</a>
                                </button>
                            </div>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- content-wrapper ends -->
<jsp:include page="../footer.jsp"/>