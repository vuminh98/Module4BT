function getAllProduct() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/products",
        success: function (a) {
            let content = '<table><tr>\n' +
                '        <td >Name</td>\n' +
                '        <td >Price</td>\n' +
                '        <td >Description</td>\n' +
                '        <td >NSX</td>\n' +
                '        <td >Category</td>\n' +
                '        <td >Action</td>\n' +
                '    </tr>';
            for (let i = 0; i < a.length; i++) {
                content += displayProduct(a[i]);
            }
            content += '</table>'
            document.getElementById('list_product').innerHTML = content;
        }
    });
}

function displayProduct(product) {
    displayCategory(category);
    return `<tr><td >${product.name}</td>
            <td >${product.price}</td>
            <td >${product.description}</td>
            <td>${product.nsx}</td>
            <td>${product.category.name}</td>
        <td><button onclick="deleteProduct(${product.id})">Delete</button></td>
        <td><button onclick="updateProduct(${product.id})">Update</button></td></tr>`;
}

function displayCategory(category) {
    return `<tr><td >${category.name}</td>
        <td><button onclick="deleteProduct(${product.id})">Delete</button></td>
        <td><button onclick="updateProduct(${product.id})">Update</button></td></tr>`;
}



let idUpdate;

function updateProduct(id) {
    idUpdate = id
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/products/" + id,
        success: function (data) {
            $("#name1").val(data.name)
            $("#price1").val(data.price)
            $("#description1").val(data.description)
            $("#nsx1").val(data.nsx)
            document.getElementById("update").hidden = false
        }
    });
}

function update() {
    let name = $("#name1").val()
    let price = $("#price1").val()
    let description = $("#description1").val()
    let nsx = $("#nsx1").val()
    let newProduct = {
        id: idUpdate,
        name: name,
        price: price,
        description: description,
        nsx : nsx
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        url: "http://localhost:8080/products/" + idUpdate,
        data: JSON.stringify(newProduct),
        success: function (data) {
            getAllProduct()
            if (data.name != null) {
                alert("Thay đổi thành công!")
            }
            document.getElementById("update").hidden = true
        }
    })
    event.preventDefault();
}

function deleteProduct(id) {
    if (confirm("Bạn chắc chắn muốn xóa?")) {
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/products/" + id,
            success: function (data) {
                getAllProduct()
                alert(data)
            }
        });
    }
}

function createProduct() {
    let name = $("#name").val()
    let price = $("#price").val()
    let description = $("#description").val()
    let nsx = $("#nsx").val()
    let newProduct = {
        name: name,
        price: price,
        description: description,
        nsx : nsx
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8080/products",
        data: JSON.stringify(newProduct),
        success: function (data) {
            getAllProduct()
            if (data.name != null) {
                alert("Tạo thành công!")
            }
            document.getElementById("create").hidden = true
        }
    })
    event.preventDefault();
}

function displayCreateForm() {
    document.getElementById("create").hidden = false
}
