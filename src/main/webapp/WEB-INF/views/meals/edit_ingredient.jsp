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
                        <h4 class="card-title">Edycja składnika: ${ingredient.product.name} (${ingredient.product.producent})</h4>
                        <form:form method="post" action="/ingredient/edit_ing" modelAttribute="ingredient" class="forms-sample">
                            <form:hidden path="id"/>
                            <form:hidden path="product"/>
                            <div class="form-group">
                                <form:label path="productQuantity">Ilość składnika: (g)</form:label>
                                <form:input path="productQuantity" type="number" class="form-control"/>
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