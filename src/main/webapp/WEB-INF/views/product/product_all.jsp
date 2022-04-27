
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
                    <h4 class="card-title">Baza produktów</h4>
                    <div class="form-group">
                      <input type="text" class="form-control" id="search_input" placeholder="Wyszukaj po nazwie">
                    </div>

                    <div class="table-responsive">
                      <table class="table table-dark">
                        <thead>
                          <tr>
                            <th> Nazwa </th>
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
                            <td> &nbsp;&nbsp;${product.kilocalories}</td>
                            <td> &nbsp;&nbsp;&nbsp;&nbsp;${product.proteins}</td>
                            <td>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${product.carbohydrates}</td>
                            <td> ${product.fats}</td>
                            <td>
                              <div class="template-demo">
                                <a href=<c:url value="/product/delete/${product.id}"/>><button type="button" class="btn btn-outline-danger btn-icon-text"> Usuń </button></a>
                                <a href=<c:url value="/product/update/${product.id}"/>> <button type="button" class="btn btn-outline-warning btn-icon-text"> Edytuj </button></a>
                              </div>
                            </td>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>
                    </div><br>
                    <a href=<c:url value="/product/add"/>><button type="button" class="btn btn-inverse-primary btn-fw">Dodaj produkt</button></a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <script type="text/javascript">

            const searchBarInput = document.getElementById("search_input");
            const product = document.querySelectorAll("#product");

            searchBarInput.addEventListener("input", function (e) {

              if (searchBarInput.value === "") {
                product.forEach(function (element) {
                  element.parentElement.style.removeProperty("display");
                });
              } else {
                product.forEach(function (element) {
                  let input = element.innerText.toLowerCase();
                  if (!input.includes(searchBarInput.value)) {
                    element.parentElement.style.display = 'none';
                  } else {
                    element.parentElement.style.removeProperty("display");
                  }
                });
              }

            });

          </script>
         <!-- <script type="text/javascript" src=<c:url value="/resources/assets/js/search_bar.js"/>/> -->
          <!-- content-wrapper ends -->
            <jsp:include page="../footer.jsp"/>
