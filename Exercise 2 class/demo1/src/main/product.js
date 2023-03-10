function getAllProduct() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/product",
        success: function (a) {
            let content = '<tr>\n' +
                '           <td>Name</td>\n' +
                '           <td>Price</td>\n' +
                '           <td>Category</td>\n' +
                '        <td colspan="3" style="padding-left:  50px">Action</td>\n' +
                '           </tr>';
            for (let i = 0; i < a.length; i++) {
                content += displayProduct(a[i]);
            }
            document.getElementById('list_product').innerHTML = content;
        }
    });
}


function displayProduct(product) {
    return `<tr><td>${product.name}</td>
                <td>${product.price}</td>
               <td>${product.category.name}</td>
        <td><button class="btn btn-danger" onclick="deleteProduct(${product.id})">Delete</button>
        <button class="btn btn-warning" onclick="updateProduct(${product.id})">Update</button></td></tr>`;
}



function findAllCategory(product) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/category",
        success: function (data) {
            let content = `<select id="category">`
            if (product != null) {
                content += `<option value="${product.category.id}">${product.category.name}</option>`
                for (let i = 0; i < data.length; i++) {
                    if (product.category.id !== data[i].id) {
                        content += displayCategory(data[i])
                    }
                }
                content += `<select>`
                document.getElementById("categoryUpdate").innerHTML = content;
            } else {
                for (let i = 0; i < data.length; i++) {
                    content += displayCategory(data[i])
                }
                content += `<select>`
                document.getElementById("categoryForm").innerHTML = content;
            }

        }
    });
}

function displayCategory(category) {
    return `<option value="${category.id}">${category.name}</option>`
}

function displayCreateForm() {
    findAllCategory()
    document.getElementById("create").hidden = false;
}

function createProduct() {
    let name = $("#name").val()
    let price = $("#price").val()
    let category = $("#category").val()
    let newProduct = {
        name: name,
        price: price,
        category: {
            id: category
        }
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8080/product",
        data: JSON.stringify(newProduct),
        success: function (data) {
            getAllProduct()
            if (data.name != null) {
                Swal.fire(
                    'Good job!',
                    'You clicked the button!',
                    'success'
                )
            }
            document.getElementById("create").hidden = true;
        }
    })
    event.preventDefault();
}

function deleteProduct(id) {
    if (confirm("Are you sure to delete?")) {
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/product/" + id,
            success: function (data) {
                getAllProduct()
                alert(data)
            }
        });
    }
}


let idUpdate
function updateProduct(id) {
    idUpdate = id
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/product/" + id,
        success: function (data) {
            $("#nameUpdate").val(data.name)
            $("#priceUpdate").val(data.price)
            $("#categoryUpdate").val(data.category.id)
            findAllCategory(data)
            document.getElementById("update").hidden = false
        }
    });
}

function update() {
    let name = $("#nameUpdate").val()
    let price = $("#priceUpdate").val()
    let categoryId = $("#category").val()
    let newProduct = {
        id: idUpdate,
        name: name,
        price: price,
        category: {
            id : categoryId
        }
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        url: "http://localhost:8080/product/" + idUpdate,
        data: JSON.stringify(newProduct),
        success: function (data) {
            getAllProduct()
            if (data.name != null) {
                alert("Thay ?????i th??nh c??ng!")
            }
            document.getElementById("update").hidden = true
        }
    });
    event.preventDefault();
}

function search() {
    let text = $("#search").val()
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/product/search?search=" + text,
        success: function (a) {
            let content = '<tr>\n' +
                '           <td>Name</td>\n' +
                '           <td>Price</td>\n' +
                '           <td>Category</td>\n' +
                '        <td colspan="3" style="padding-left:  50px">Action</td>\n' +
                '           </tr>';
            for (let i = 0; i < a.length; i++) {
                content += displayProduct(a[i]);
            }
            document.getElementById('list_product').innerHTML = content;
        }
    });
}

//chuyen trang

function getAllProductPage(page) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/product/page?page=" + page + "&size=2",
        success: function (data) {
            displayProductPage(data.content)
            displayPage(data)
            if (data.pageable.pageNumber === 0) {
                document.getElementById("backup").hidden = true
            }
            if (data.pageable.pageNumber + 1 === data.totalPages) {
                document.getElementById("next").hidden = true
            }
        }
    });
    event.preventDefault()
}

function displayProductPage(data) {
    let content = '<tr>\n' +
        '           <td>Name</td>\n' +
        '           <td>Price</td>\n' +
        '           <td>Category</td>\n' +
        '        <td colspan="3" style="padding-left:  50px">Action</td>\n' +
        '           </tr>';
    for (let i = 0; i < data.length; i++) {
        content += `<tr><td>${data[i].name}</td>
            <td>${data[i].price}</td>
            <td>${data[i].category.name}</td>
            <td><button class="btn btn-danger" onclick="deleteProduct(${data[i].id})">Delete</button>
                <button class="btn btn-warning" onclick="updateProduct(${data[i].id})">Update</button></td></tr>`;
    }
    document.getElementById("list_product").innerHTML = content;
}

function displayPage(data) {
    let content = `<button class="btn btn-primary" id="backup" onclick="isPrevious(${data.pageable.pageNumber})">Previous</button>
<span >${data.pageable.pageNumber+1} | ${data.totalPages}</span>
<button class="btn btn-primary"  id="next" " onclick="isNext(${data.pageable.pageNumber})">Next</button>`
    document.getElementById("page").innerHTML = content;
}

//h??m l??i page
function isPrevious(pageNumber) {
    getAllProductPage(pageNumber-1)
}

//h??m ti???n page
function isNext(pageNumber) {
    getAllProductPage(pageNumber+1)
}