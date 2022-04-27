<jsp:include page="../header.jsp"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- partial -->
<div class="main-panel">
    <div class="content-wrapper">
        <div class="row">
            <div class="col-md-5 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Dodaj składnik</h4>
                        <form:form method="post" modelAttribute="product" class="forms-sample" action="/meal/update">
                            <div class="form-group">
                                <form:label path="id">Produkt:</form:label>
                                <form:select path="id" class="form-control js-example-basic-single" items="${products}" itemValue="id" itemLabel="name"/>
                            </div>
                            <div class="template-demo">
                                <button type="submit" class="btn btn-success btn-icon-text">
                                    <i class="mdi mdi-upload btn-icon-prepend"></i> Dodaj produkt </button>
                                <br>
                            </div>
                            <input type="hidden" value="${meal.id}" name="mealId">
                        </form:form>
                    </div>
                </div>
            </div>
            <div class="col-lg-7 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Składniki:</h4>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Produkt</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${meal.products}" var="product">
                                    <tr>
                                        <td>${product.name}</td>

                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <a href="/mealNutritions/add_meal"> <!--do zmiany -->
                            <button type="submit" class="btn btn-success btn-icon-text">
                                <i class="mdi mdi-upload btn-icon-prepend"></i>Edytuj posiłek</button>
                        </a>
                    </div>


                </div>
            </div>

        </div>

    </div>
    <!-- content-wrapper ends -->
<jsp:include page="../footer.jsp"/>