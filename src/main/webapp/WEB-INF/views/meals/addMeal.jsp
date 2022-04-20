<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../header.jsp"/>
<script type="text/javascript">
    $(document).ready(function(){
        const searchInput = document.querySelector("#searchInput");
        const select = document.querySelector("#checkbox");
        const products = document.querySelectorAll("select span");
        console.log(select);
        searchInput.addEventListener("input", function(){
            if(searchInput.value === ""){
                products.forEach(function(element){
                    element.style.removeProperty("display");
                })
            } else {
                products.forEach(function(element){
                    let input = element.innerText.toLowerCase();
                    if(!input.includes(searchInput.value)){
                        element.style.display = "none";
                    } else {
                        element.style.removeProperty("display");
                    }
                })
            }

        });
    })
</script>

<!-- partial -->
<div class="main-panel">
    <div class="content-wrapper">
        <div class="row">
            <div class="col-md-5 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Zapisz nowy posiłek</h4>
                        <form:form method="post" class="forms-sample" modelAttribute="meal">
                            <div class="form-group">
                                <form:label path="name">Nazwa:</form:label>
                                <form:input path="name" class="form-control"/>
                            </div>
                            <div class="form-group" id="checkbox">
                                <form:label path="products">Produkty:</form:label>
                                <input type="text" id="searchInput" placeholder="Wyszukaj..." class="form-control"/>
                                <form:checkboxes id="select" path="products"  class="form-control js-example-basic-multiple" multiple="multiple" items="${products}" itemLabel="name" itemValue="name"/>
                            </div>
                            <div class="template-demo">
                                <button type="submit" class="btn btn-success btn-icon-text">
                                    <i class="mdi mdi-upload btn-icon-prepend"></i> Zapisz posiłek </button>
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
                        <h4 class="card-title">Produkty:</h4>
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Product</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${products}" var="product">
                                    <tr>
                                        <td id="product" style="display: none">${product.name}</td>
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