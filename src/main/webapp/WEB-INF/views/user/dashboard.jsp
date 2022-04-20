<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:parseDate value="${eaten.date}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
<fmt:formatDate value="${parsedDate}" var="newParsedDate" type="date" pattern="dd.MM.yyyy EEEEE" />
<jsp:include page="/WEB-INF/views/header.jsp"/>
    <!-- partial -->
        <div class="main-panel">
            <div class="content-wrapper">
                <div class="col-lg-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">${newParsedDate}</h4>
                            <div class="table-responsive">
                                <table class="table table-striped">
                                    <thead>
                                    <tr>
                                        <th> Składnik</th>
                                        <th> Zjedzone</th>
                                        <th> Wymagane</th>
                                        <th> Postęp </th>
                                        <th> Różnica </th>

                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td class="py-1">
                                           Białko
                                        </td>
                                        <td> ${eaten.proteins} </td>
                                        <td> ${goals.proteins} </td>
                                        <td>
                                            <div class="progress">

                                                <div class="progress-bar bg-success" role="progressbar" style="width: ${eaten.proteins / goals.proteins * 100}%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </td>
                                        <td> ${eaten.proteins - goals.proteins}</td>
                                    </tr>
                                    <tr>
                                        <td class="py-1">
                                            Węglowodany
                                        </td>
                                        <td> ${eaten.carbohydrates} </td>
                                        <td> ${goals.carbohydrates} </td>
                                        <td>
                                            <div class="progress">
                                                <div class="progress-bar bg-success" role="progressbar" style="width: ${eaten.carbohydrates / goals.carbohydrates * 100}%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </td>
                                        <td> ${eaten.carbohydrates - goals.carbohydrates}</td>
                                    </tr>
                                    <tr>
                                        <td class="py-1">
                                            Tłuszcze
                                        </td>
                                        <td> ${eaten.fats} </td>
                                        <td> ${goals.fats} </td>
                                        <td>
                                            <div class="progress">
                                                <div class="progress-bar bg-success" role="progressbar" style="width: ${eaten.fats / goals.fats * 100}%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </td>
                                        <td> ${eaten.fats - goals.fats}</td>
                                    </tr>
                                    <tr>
                                        <td class="py-1">
                                            Kalorie:
                                        </td>
                                        <td> ${eaten.kilocalories} </td>
                                        <td> ${goals.kilocalories} </td>
                                        <td>
                                            <div class="progress">
                                                <div class="progress-bar bg-success" role="progressbar" style="width: ${eaten.kilocalories / goals.kilocalories * 100}%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                            </div>
                                        </td>
                                        <td> ${eaten.kilocalories - goals.kilocalories}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div><div class="card-body">
                            <div class="template-demo">
                                <a href=<c:url value="/dashboard/previous"/>><button type="button" class="btn btn-info btn-lg btn-block">Poprzedni dzień</button></a><br>
                                <a href=<c:url value="/dashboard/next"/>><button type="button" class="btn btn-primary btn-lg btn-block">Następny dzień</button></a>
                            </div>

                        </div><p class="card-description"> Swoje cele możesz edytować w ustawieniach swojego konta</p>
                        </div>
                    </div>

                </div>


            </div>

            <!-- content-wrapper ends -->
            <!-- partial:partials/_footer.html -->
            <jsp:include page="/WEB-INF/views/footer.jsp"/>