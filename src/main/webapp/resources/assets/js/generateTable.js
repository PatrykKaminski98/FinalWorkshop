const next = document.getElementById("next");
next.addEventListener("onclick", function(){
    console.log("działa");
    const mealName = document.getElementById("mealName");
    mealName.style.display = 'none';
})
function addRow(event){
    event.preventDefault();
    console.log("tutaj");
    const product = document.getElementById("product");
    const table = document.getElementById("table");

    const rowCount = table.rows.length;
    const row = table.insertRow(rowCount);

    row.insertCell(0).innerHTML = product.value;
    row.insertCell(1).innerHTML = '<input type="button" value = "Delete" onClick="Javacsript:deleteRow(this)">'
    console.log("dodano");

}

function deleteRow(obj) {

    var index = obj.parentNode.parentNode.rowIndex;
    var table = document.getElementById("myTableData");
    table.deleteRow(index);

}