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
                    <h4 class="card-title">Nowy produkt</h4>
                    <p class="card-description"> Uzupełnij wszystkie dane o produkcie<br> Tabele wartości odżywczych znajdziesz na każdym produkcie spożywczym </p>
                    <form:form modelAttribute="product" method="post" class="forms-sample">
                      <div class="form-group">
                        <form:label path="name">Nazwa</form:label>
                        <form:input path="name"  class="form-control"  placeholder="Nazwa"/>
                      </div>
                      <div class="form-group">
                        <form:label path="kilocalories">Kalorie: (100g)</form:label>
                        <form:input path="kilocalories" type="number" step="0.1" class="form-control"  placeholder="kcal"/>
                      </div>
                      <div class="form-group">
                        <form:label path="proteins">Białko: (100g)</form:label>
                        <form:input path="proteins" type="number" step="0.1" class="form-control"  placeholder="białko"/>
                      </div>
                      <div class="form-group">
                        <form:label path="carbohydrates">Węglowodany: (100g)</form:label>
                        <form:input path="carbohydrates" type="number" step="0.1" class="form-control"  placeholder="węglowodany"/>
                      </div>
                      <div class="form-group">
                        <form:label path="fats">Tłuszcze: (100g)</form:label>
                        <form:input path="fats" type="number" step="0.1" class="form-control"  placeholder="tłuszcze"/>
                      </div>
                    <div class="template-demo">
                      <button type="submit" class="btn btn-success btn-icon-text">
                        <i class="mdi mdi-upload btn-icon-prepend"></i> Dodaj produkt </button>
                      <a href=<c:url value="/product/all"/> <button type="button" class="btn btn-danger btn-icon-text"> Anuluj </button></a>
                      </button>
                      <br>
                      <form:errors path="name" cssClass="text-info"/>
                    </div>

                    </form:form>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <!-- content-wrapper ends -->
   <jsp:include page="../footer.jsp"/>