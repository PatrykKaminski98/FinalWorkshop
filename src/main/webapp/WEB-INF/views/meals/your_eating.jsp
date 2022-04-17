<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<fmt:parseDate value="${date}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy EEEEE" />
<!-- partial -->
<script>
    window.addEventListener('DOMContentLoaded', () => {
        const buttons = document.getElementsByClassName("btn btn-outline-danger btn-icon-text");
        Array.from(buttons).forEach(function(button){
            button.addEventListener("click", function(event){
                if(confirm('Czy na pewno chcesz usunąć ten składnik?')) {
                    return;
                } else {
                    event.preventDefault();
                }
            })
        })
    });
    window.addEventListener('DOMContentLoaded', () => {
        const buttons = document.getElementsByClassName("btn btn-dark btn-rounded btn-fw");
        Array.from(buttons).forEach(function(button){
            button.addEventListener("click", function(event){
                if(confirm('Czy na pewno chcesz usunąć ten posiłek?')) {
                    return;
                } else {
                    event.preventDefault();
                }
            })
        })
    });

</script>

<div class="main-panel">
    <div class="content-wrapper">
        <div class="page-header">
            <a href=<c:url value="/meal/table/previous"/>><button type="button" class="btn btn-warning btn-rounded btn-fw">Poprzedni dzień</button></a>
            <h3 class="page-title">${newParsedDate}</h3>
            <a href=<c:url value="/meal/table/next"/>><button type="button" class="btn btn-warning btn-rounded btn-fw">Następny dzień</button></a>
        </div>
        <div class="row">
            <div class="col-lg-12 stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Twoja dieta</h4>
                        <div class="table-responsive">
                            <table class="table table-bordered table-contextual">
                                <thead class="table-success">
                                <tr>
                                    <th> Nazwa </th>
                                    <th> Kalorie </th>
                                    <th> Białko </th>
                                    <th> Węglowodany </th>
                                    <th> Tłuszcze </th>
                                    <th> Ilość (g) </th>
                                </tr>
                                </thead>
                                <tbody>

                                <c:forEach items="${meals}" var="meal">
                                    <tr class="table-danger">
                                        <td> ${meal.mealName}</td>
                                        <td> ${meal.kilocalories}</td>
                                        <td> ${meal.proteins}</td>
                                        <td> ${meal.carbohydrates} </td>
                                        <td> ${meal.fats} </td>
                                        <td></td>
                                        <td><a href=<c:url value="/history/delete_meal/${meal.history.id}"/>><button type="button" class="btn btn-dark btn-rounded btn-fw">Usuń</button></a></td>
                                    </tr>

                                <c:forEach items="${meal.ingredients}" var="ingredient">
                                    <tr>
                                        <td> ${ingredient.product.name}</td>
                                        <td> ${ingredient.kilocalories} </td>
                                        <td> ${ingredient.proteins} </td>
                                        <td> ${ingredient.carbohydrates} </td>
                                        <td> ${ingredient.fats}</td>
                                        <td> ${ingredient.productQuantity}</td>
                                        <td><a href=<c:url value="/ingredient/delete/${ingredient.id}"/>><button type="button" class="btn btn-outline-danger btn-icon-text">
                                            <i class="mdi mdi-delete  btn-icon-prepend"></i> Usuń</button></a>
                                            <a href=<c:url value="/ingredient/edit/${ingredient.id}"/>><button type="button" class="btn btn-outline-warning btn-icon-text">
                                                <i class=" mdi mdi-table-edit btn-icon-prepend"></i> Edytuj </button></a></td>
                                    </tr>
                                </c:forEach>
                                    <tr>
                                        <td><button type="button" class="btn btn-primary btn-rounded btn-fw">Dodaj produkt</button></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <br>
                        <a href="/meal/add">
                            <button type="submit" class="btn btn-info btn-rounded btn-fw">Dodaj posiłek</button></a>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <!-- content-wrapper ends -->
    <!-- partial:../../partials/_footer.html -->

    <!-- content-wrapper ends -->
    <!-- partial:partials/_footer.html -->
<jsp:include page="../footer.jsp"/>