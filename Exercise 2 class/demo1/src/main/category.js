function getAllCategory() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/category",
        success: function (a) {
            let content = '<tr>\n' +
                '           <td>#</td>\n' +
                '           <td>Name</td>\n' +
                '        <td colspan="3" style="padding-left:  50px">Action</td>\n' +
                '           </tr>';
            for (let i = 0; i < a.length; i++) {
                content += displayCategory(a[i]);
            }
            document.getElementById('list_category').innerHTML = content;
        }
    });
}

function displayCategory(category) {
    return `<tr><td>${category.id}</td>
                <td>${category.name}</td>
        <td><button class="btn btn-danger" onclick="deleteCategory(${category.id})">Delete</button>`
}

function createCategory() {
    let name = $("#name").val()
    let newCategory = {
        name: name,
    }
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        url: "http://localhost:8080/category",
        data: JSON.stringify(newCategory),
        success: function (data) {
            getAllCategory()
            if (data.name != null) {
                alert("Create Successfully!")
            }
            document.getElementById("create_category").hidden = true;
        }
    })
    event.preventDefault();
}

function deleteCategory(id) {
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: 'Are you sure?',
        // text: "You won't be able to revert this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Yes, delete it!',
        cancelButtonText: 'No, cancel!',
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            $.ajax({
                type: "DELETE",
                url: "http://localhost:8080/category/" + id,
                success: function (data) {
                    if (data.name != null) {
                        swalWithBootstrapButtons.fire(
                            'Deleted!',
                        )
                        getAllCategory()
                    } else {
                        swalWithBootstrapButtons.fire(
                            'This category can not delete!',
                        )
                    }
                }
            })
        } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire(
                'Cancelled',
            )
        }
    })
}


