
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
                        <h4 class="card-title">Dodaj produkt</h4>
                        <div class="form-group">
                            <input type="text" class="form-control" id="search_input" placeholder="Wyszukaj po nazwie">
                        </div>

                        <div class="table-responsive">
                            <table class="table table-dark">
                                <thead>
                                <tr>
                                    <th> Nazwa </th>
                                    <th> Producent </th>
                                    <th> kcal/100g </th>
                                    <th> białko/100g </th>
                                    <th> węglowodany/100g </th>
                                    <th> tłuszcze/100g </th>
                                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;akcja </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${products}" var="product">
                                    <tr>
                                        <td  id="product"> ${product.name}</td>
                                        <td> ${product.producent}</td>
                                        <td> &nbsp;&nbsp;${product.kilocalories}</td>
                                        <td> &nbsp;&nbsp;&nbsp;&nbsp;${product.proteins}</td>
                                        <td>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${product.carbohydrates}</td>
                                        <td> ${product.fats}</td>
                                        <td>
                                            <div class="template-demo">
                                                <a href=<c:url value="/meal/productAdd/${mealId}/${product.id}"/>><button type="button" class="btn btn-outline-success btn-icon-text"> Dodaj </button></a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src=<c:url value="/resources/assets/js/search_bar.js"/>/>
    <!-- content-wrapper ends -->
    <jsp:include page="../footer.jsp"/>
