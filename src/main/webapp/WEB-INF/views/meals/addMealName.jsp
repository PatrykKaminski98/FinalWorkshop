<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>

<!-- partial -->
<div class="main-panel">
    <div class="content-wrapper">
        <div class="row">
            <div class="col-md-5 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Podaj nazwę posiłku</h4>
                        <form:form method="post" class="forms-sample" modelAttribute="meal">
                            <div class="form-group">
                                <form:label path="name">Nazwa:</form:label>
                                <form:input path="name" class="form-control"/>
                                <form:errors path="name" cssClass="error"/>
                            </div>
                            <div class="template-demo">
                                <button type="submit" class="btn btn-success btn-icon-text">
                                    <i class="mdi mdi-upload btn-icon-prepend"></i> Przejdź dalej </button>
                                </button>
                                <br>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <!-- content-wrapper ends -->
    <jsp:include page="../footer.jsp"/>