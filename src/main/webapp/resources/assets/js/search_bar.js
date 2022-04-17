let searchBarInput = document.getElementById("search_input");
let product = document.querySelectorAll("#product");

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