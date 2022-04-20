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
                    <form:form method="post" class="forms-sample" modelAttribute="ingredient">
                      <div class="form-group">
                        <form:label path="product">Produkt:</form:label>
                        <form:select id="select-state" path="product" class="form-control js-example-basic-single" items="${products}" itemLabel="name" itemValue="name"/>

                      </div>
                      <div class="form-group">
                        <form:label path="productQuantity">Ilość składnika:</form:label>
                        <form:input path="productQuantity" type="number" class="form-control"/>
                        <form:errors path="productQuantity"/>
                      </div>
                      <div class="template-demo">
                        <button type="submit" class="btn btn-success btn-icon-text">
                          <i class="mdi mdi-upload btn-icon-prepend"></i> Dodaj produkt </button>
                        </button>
                        <br>
                      </div>
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
                          <th>Product</th>
                          <th>Ilość</th>
                          <th>Kilocalorie</th>
                          <th>Białko</th>
                          <th>Węglowodany</th>
                          <th>Tłuszcze</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${mealNutrition.ingredients}" var="ingr">
                          <tr>
                            <td>${ingr.product.name}</td>
                            <td>${ingr.productQuantity}</td>
                            <td>${ingr.kilocalories}</td>
                            <td>${ingr.proteins}</td>
                            <td>${ingr.carbohydrates}</td>
                            <td>${ingr.fats}</td>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>
                    </div>
                    <a href="/mealNutritions/add_meal">
                    <button type="submit" class="btn btn-success btn-icon-text">
                      <i class="mdi mdi-upload btn-icon-prepend"></i> Dodaj posiłek </button>
                    </a>
                  </div>

                </div>
              </div>

            </div>

          </div>
          <!-- content-wrapper ends -->
   <jsp:include page="../footer.jsp"/>