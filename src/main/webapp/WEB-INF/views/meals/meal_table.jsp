
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<!-- partial -->
<div class="main-panel">
    <div class="content-wrapper">
        <div class="page-header">
        </div>
        <div class="row">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Baza posiłków</h4>
                        <div class="form-group">
                            <input type="text" class="form-control" id="search_input" placeholder="Wyszukaj po nazwie">
                        </div>

                        <div class="table-responsive">
                            <table class="table table-dark">
                                <thead>
                                <tr>
                                    <th> Nazwa </th>
                                    <th style="alignment: right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;akcja </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${meals}" var="meal">
                                    <tr>
                                        <td  id="product"> ${meal.name}</td>
                                        <td>
                                            <div class="template-demo">
                                                <a href=<c:url value="/meal/delete/${meal.id}"/>><button type="button" class="btn btn-outline-danger btn-icon-text"> Usuń </button></a>
                                                <a href=<c:url value="/meal/update/${meal.id}"/>> <button type="button" class="btn btn-outline-warning btn-icon-text"> Edytuj </button></a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div><br>
                        <a href=<c:url value="/meal/add"/>><button type="button" class="btn btn-inverse-primary btn-fw">Dodaj posiłek</button></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src=<c:url value="/resources/assets/js/search_bar.js"/>/>
    <!-- content-wrapper ends -->
    <jsp:include page="../footer.jsp"/>
