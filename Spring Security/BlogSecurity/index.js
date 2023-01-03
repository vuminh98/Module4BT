function getAllBlog() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/blog",
        success: function (a) {
            let content = '<table border="1"><tr>\n' +
                '        <td>Title</td>\n' +
                '        <td>Contents</td>\n' +
                '        <td>Author</td>\n' +
                '        <td>Category</td>\n' +
                '        <td colspan="2">Action</td>\n' +
                '    </tr>';
            for (let i = 0; i < a.length; i++) {
                content += displayBlog(a[i]);
            }
            content += '</table>'
            document.getElementById('list_blog').innerHTML = content;
        }
    });
}

function getAllCategory(category) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/category",
        success: function (b) {
            let data = '<select name="category" id="category">\n' +
            '           <option>${category.name}</option>'
            for (let i = 0; i < b.length; i++) {
                data += displayCategory(b[i]);
            } 
            document.getElementById('list_category').innerHTML = data;
        }
    });
}

function displayBlog(blog) {
    return `<tr><td >${blog.title}</td>
            <td >${blog.contents}</td>
            <td >${blog.author}</td>
            <td >${blog.category}</td>
        <td><button onclick="deleteBlog(${blog.id})">Delete</button></td>
        <td><button onclick="updateBlog(${blog.id})">Update</button></td></tr>`;
}

function displayCategory(category) {
    return `<tr><td >${category.name}</td>
<td><button onclick="deleteBlog(${category.id})">Delete</button></td>
<td><button onclick="updateBlog(${category.id})">Update</button></td></tr>`;
}

let idUpdate;

function updateCustomer(id) {
    idUpdate = id
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/customers/" + id,
        success: function (data) {
            $("#name1").val(data.name)
            $("#age1").val(data.age)
            $("#address1").val(data.address)
            document.getElementById("update").hidden = false
        }
    });
}

function update() {
    let name = $("#name1").val()
    let age = $("#age1").val()
    let address = $("#address1").val()
    let newCustomer = {
        id: idUpdate,
        name: name,
        age: age,
        address: address
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "PUT",
        url: "http://localhost:8080/customers/" + idUpdate,
        data: JSON.stringify(newCustomer),
        success: function (data) {
            getAllCustomer()
            if (data.name != null) {
                alert("Thay đổi thành công!")
            }
            document.getElementById("update").hidden = true
        }
    })
    event.preventDefault();
}

function deleteCustomer(id) {
    if (confirm("Bạn chắc chắn muốn xóa?")) {
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/customers/" + id,
            success: function (data) {
                getAllCustomer()
                alert(data)
            }
        });
    }
}

function createBlog() {
    let title = $("#title").val()
    let contents = $("#age").val()
    let author = $("#address").val()
    let category = $("#category").val()
    let newBlog = {
        title: title,
        contents: contents,
        author: author,
        category : category
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8080/customers",
        data: JSON.stringify(newCustomer),
        success: function (data) {
            getAllCustomer()
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
