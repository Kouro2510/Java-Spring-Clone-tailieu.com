$(document).ready(function() {
    $(".btnUpdate").on("click", async function () {
        // Lấy dữ liệu từ hàng chứa nút Update được bấm
        var classValue = $(this).closest('tr').find('td:nth-child(1)').text();
        var descriptionValue = $(this).closest('tr').find('td:nth-child(2)').text();
        // Gắn dữ liệu vào các input trong modal sửa
        var modalId = $(this).attr('data-target');
        $(modalId).find('#classname').val(classValue);
        $(modalId).find('#description').val(descriptionValue);
    });

});
$(document).ready(function() {
    $(".btnUpdate").on("click", async function () {
        // Lấy dữ liệu từ hàng chứa nút Update được bấm
        var SubjectName = $(this).closest('tr').find('td:nth-child(1)').text();
        var descriptionValue = $(this).closest('tr').find('td:nth-child(2)').text();
        // Gắn dữ liệu vào các input trong modal sửa
        // Sử dụng biểu thức chính quy để lấy chỉ số

        var modalId = $(this).attr('data-target');
        $(modalId).find('#updateSubjectName').val(SubjectName);
        $(modalId).find('#updateDescriptionName').val(descriptionValue);

    });

});
$(document).ready(function() {
    $(".btnUpdate").on("click", async function () {
        // Lấy dữ liệu từ hàng chứa nút Update được bấm
        var ingredientsName = $(this).closest('tr').find('td:nth-child(1)').text();
        var descriptionValue = $(this).closest('tr').find('td:nth-child(2)').text();
        console.log(descriptionValue)
        // Gắn dữ liệu vào các input trong modal sửa
        // Sử dụng biểu thức chính quy để lấy chỉ số

        var modalId = $(this).attr('data-target');
        $(modalId).find('#updateingredientsName').val(ingredientsName);
        $(modalId).find('#updatedescription').val(descriptionValue);

    });

});
$(document).ready(function() {
    $(".btnUpdate").on("click", async function () {
        // Lấy dữ liệu từ hàng chứa nút Update được bấm
        var chapterName = $(this).closest('tr').find('td:nth-child(1)').text();
        var descriptionValue = $(this).closest('tr').find('td:nth-child(2)').text();
        console.log(descriptionValue)
        // Gắn dữ liệu vào các input trong modal sửa
        // Sử dụng biểu thức chính quy để lấy chỉ số

        var modalId = $(this).attr('data-target');
        $(modalId).find('#updatechapterName').val(chapterName);
        $(modalId).find('#updatedescription').val(descriptionValue);

    });

});
let table = new DataTable('#myTable');
// Lấy đường dẫn URL hiện tại
//validate class
$(document).ready(function() {
    $('#addnewclass').submit(function(e) {
        e.preventDefault();

        var inputClassName = $('#addsubjectName').val().trim();
        var inputDescription = $('#adddescription').val().trim();

        // Kiểm tra dữ liệu nhập liệu
        if (inputClassName === '' || inputDescription === '') {
            showErrorMessage('Vui lòng nhập đầy đủ thông tin.');
            return;
        }

        // Kiểm tra dữ liệu trùng khớp với bảng HTML
        var isDuplicate = false;
        $('#myTable tbody tr').each(function() {
            var classNameValue = $(this).find('td#classnamevalue').text().trim();

            if (inputClassName === classNameValue) {
                isDuplicate = true;
                return false; // Dừng vòng lặp khi tìm thấy dữ liệu trùng khớp
            }
        });

        if (isDuplicate) {
            showErrorMessage('Dữ liệu đã tồn tại. Vui lòng nhập dữ liệu khác.');
            return;
        }

        // Submit form khi không có dữ liệu trùng khớp
        $.ajax({
            url: $(this).attr('action'),
            method: $(this).attr('method'),
            data: $(this).serialize(),
            success: function(response) {
                // Show success message using SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    // Reload the page or perform any other actions
                    window.location.reload(true);
                });
            },
            error: function(error) {
                // Show error message using SweetAlert2
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'An error occurred while adding the class.'
                });
            }
        });

    });

    function showErrorMessage(message) {
        // Xóa thông báo cũ (nếu có)
        $('#error-message').remove();

        // Tạo và hiển thị thông báo mới
        var errorMessage = $('<span id="error-message" class="text-danger">' + message + '</span>');
        errorMessage.insertAfter('#addsubjectName');
    }
});
$(document).ready(function() {
    $('#addnewsubject').submit(function(e) {
        e.preventDefault();

        var inputClassName = $('#subjectName').val().trim();
        var inputDescription = $('#description').val().trim();
        var className = $('#className option:selected').text(); // Lấy tên lớp từ lựa chọn đã chọn

        // Kiểm tra dữ liệu nhập liệu
        if (inputClassName === '' || inputDescription === '') {
            showErrorMessage('Vui lòng nhập đầy đủ thông tin.');
            return;
        }

        // Kiểm tra dữ liệu trùng khớp với bảng HTML
        var isDuplicate = false;
        $('#myTable tbody tr').each(function() {
            var classSubjectValue = $(this).find('td#classnamevalue').text().trim();
            var classValue = $(this).find('td#classvalue').text().trim();

            if (inputClassName === classSubjectValue && classValue === className) {
                isDuplicate = true;
                return false; // Dừng vòng lặp khi tìm thấy dữ liệu trùng khớp
            }
        });

        if (isDuplicate) {
            showErrorMessage('Dữ liệu đã tồn tại. Vui lòng nhập dữ liệu khác.');
            return;
        }

        // Submit form khi không có dữ liệu trùng khớp
        $.ajax({
            url: $(this).attr('action'),
            method: $(this).attr('method'),
            data: $(this).serialize(),
            success: function(response) {
                // Show success message using SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    // Reload the page or perform any other actions
                    window.location.reload(true);
                });
            },
            error: function(error) {
                // Show error message using SweetAlert2
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'An error occurred while adding the class.'
                });
            }
        });

    });

    function showErrorMessage(message) {
        // Xóa thông báo cũ (nếu có)
        $('#error-message').remove();

        // Tạo và hiển thị thông báo mới
        var errorMessage = $('<span id="error-message" class="text-danger">' + message + '</span>');
        errorMessage.insertAfter('#subjectName');
    }
});


$(document).ready(function() {
    $('#addingredients').submit(function(e) {
        e.preventDefault();

        var inputClassName = $('#ingredientsName').val().trim();
        var inputDescription = $('#description').val().trim();
        var subjectName = $('#subjectId option:selected').text(); // Lấy tên lớp từ lựa chọn đã chọn



        // Kiểm tra dữ liệu trùng khớp với bảng HTML
        var isDuplicate = false;
        $('#myTable tbody tr').each(function() {
            var classNameValue = $(this).find('td#classnamevalue').text().trim();
            var classValue = $(this).find('td#classvalue').text().trim();
            console.log(classValue);
            if (inputClassName === classNameValue && classValue===subjectName) {
                isDuplicate = true;
                return false; // Dừng vòng lặp khi tìm thấy dữ liệu trùng khớp
            }
        });

        // Kiểm tra dữ liệu nhập liệu
        if (inputClassName === '' || inputDescription === '') {
            showErrorMessage('Vui lòng nhập đầy đủ thông tin.');
            return;
        }
        if (isDuplicate) {
            showErrorMessage('Dữ liệu đã tồn tại. Vui lòng nhập dữ liệu khác.');
            return;
        }

        // Submit form khi không có dữ liệu trùng khớp
        $.ajax({
            url: $(this).attr('action'),
            method: $(this).attr('method'),
            data: $(this).serialize(),
            success: function(response) {
                // Show success message using SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    // Reload the page or perform any other actions
                    window.location.reload(true);
                });
            },
            error: function(error) {
                // Show error message using SweetAlert2
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'An error occurred while adding the class.'
                });
            }
        });

    });

    function showErrorMessage(message) {
        // Xóa thông báo cũ (nếu có)
        $('#error-message').remove();

        // Tạo và hiển thị thông báo mới
        var errorMessage = $('<span id="error-message" class="text-danger">' + message + '</span>');
        errorMessage.insertAfter('#ingredientsName');
    }
});
$(document).ready(function() {
    $('#addnewchapter').submit(function(e) {
        e.preventDefault();

        var inputClassName = $('#chapterName').val().trim();
        var inputDescription = $('#description').val().trim();
        var ingredientsName = $('#ingredientsId option:selected').text(); // Lấy tên lớp từ lựa chọn đã chọn
        // Kiểm tra dữ liệu nhập liệu

        // Kiểm tra dữ liệu trùng khớp với bảng HTML
        var isDuplicate = false;
        $('#myTable tbody tr').each(function() {
            var classNameValue = $(this).find('td#classnamevalue').text().trim();
            var classValue = $(this).find('td#classvalue').text().trim();
            if (inputClassName === classNameValue && classValue===ingredientsName) {
                isDuplicate = true;
                return false; // Dừng vòng lặp khi tìm thấy dữ liệu trùng khớp
            }
        });
        if (inputClassName === '' || inputDescription === '') {
            showErrorMessage('Vui lòng nhập đầy đủ thông tin.');
            return;
        }

        if (isDuplicate) {
            showErrorMessage('Dữ liệu đã tồn tại. Vui lòng nhập dữ liệu khác.');
            return;
        }

        // Submit form khi không có dữ liệu trùng khớp
        $.ajax({
            url: $(this).attr('action'),
            method: $(this).attr('method'),
            data: $(this).serialize(),
            success: function(response) {
                // Show success message using SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    // Reload the page or perform any other actions
                    window.location.reload(true);
                });
            },
            error: function(error) {
                // Show error message using SweetAlert2
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'An error occurred while adding the class.'
                });
            }
        });

    });

    function showErrorMessage(message) {
        // Xóa thông báo cũ (nếu có)
        $('#error-message').remove();

        // Tạo và hiển thị thông báo mới
        var errorMessage = $('<span id="error-message" class="text-danger">' + message + '</span>');
        errorMessage.insertAfter('#chapterName');
    }
});
$(document).ready(function() {
    $('#editclass').submit(function(e) {
        e.preventDefault();

        var inputClassName = $('#classname').val().trim();
        var inputDescription = $('#description').val().trim();

        // Kiểm tra dữ liệu nhập liệu
        if (inputClassName === '' || inputDescription === '') {
            showErrorMessage('Vui lòng nhập đầy đủ thông tin.');
            return;
        }
        // Submit form khi không có dữ liệu trùng khớp
        $.ajax({
            url: $(this).attr('action'),
            method: $(this).attr('method'),
            data: $(this).serialize(),
            success: function(response) {
                // Show success message using SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    // Reload the page or perform any other actions
                    window.location.reload(true);
                });
            },
            error: function(error) {
                // Show error message using SweetAlert2
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'An error occurred while adding the class.'
                });
            }
        });

    });

    function showErrorMessage(message) {
        // Xóa thông báo cũ (nếu có)
        $('#error-message').remove();

        // Tạo và hiển thị thông báo mới
        var errorMessage = $('<span id="error-message" class="text-danger">' + message + '</span>');
        errorMessage.insertAfter('#classname');
    }
});
$(document).ready(function() {
    $('#updatesubject').submit(function(e) {
        e.preventDefault();

        var inputClassName = $('#updateSubjectName').val().trim();
        var inputDescription = $('#updateDescriptionName').val().trim();

        // Kiểm tra dữ liệu nhập liệu
        if (inputClassName === '' || inputDescription === '') {
            showErrorMessage('Vui lòng nhập đầy đủ thông tin.');
            return;
        }
        // Submit form khi không có dữ liệu trùng khớp
        $.ajax({
            url: $(this).attr('action'),
            method: $(this).attr('method'),
            data: $(this).serialize(),
            success: function(response) {
                // Show success message using SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    // Reload the page or perform any other actions
                    window.location.reload(true);
                });
            },
            error: function(error) {
                // Show error message using SweetAlert2
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'An error occurred while adding the class.'
                });
            }
        });

    });

    function showErrorMessage(message) {
        // Xóa thông báo cũ (nếu có)
        $('#error-message').remove();

        // Tạo và hiển thị thông báo mới
        var errorMessage = $('<span id="error-message" class="text-danger">' + message + '</span>');
        errorMessage.insertAfter('#updateSubjectName');
    }
});
$(document).ready(function() {
    $('#updateingredients').submit(function(e) {
        e.preventDefault();

        var inputClassName = $('#updateingredientsName').val().trim();
        var inputDescription = $('#updatedescription').val().trim();

        // Kiểm tra dữ liệu nhập liệu
        if (inputClassName === '' || inputDescription === '') {
            showErrorMessage('Vui lòng nhập đầy đủ thông tin.');
            return;
        }
        // Submit form khi không có dữ liệu trùng khớp
        $.ajax({
            url: $(this).attr('action'),
            method: $(this).attr('method'),
            data: $(this).serialize(),
            success: function(response) {
                // Show success message using SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    // Reload the page or perform any other actions
                    window.location.reload(true);
                });
            },
            error: function(error) {
                // Show error message using SweetAlert2
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'An error occurred while adding the class.'
                });
            }
        });

    });

    function showErrorMessage(message) {
        // Xóa thông báo cũ (nếu có)
        $('#error-message').remove();

        // Tạo và hiển thị thông báo mới
        var errorMessage = $('<span id="error-message" class="text-danger">' + message + '</span>');
        errorMessage.insertAfter('#updateingredientsName');
    }
});
$(document).ready(function() {
    $('#updatechapter').submit(function(e) {
        e.preventDefault();

        var inputClassName = $('#updatechapterName').val().trim();
        var inputDescription = $('#updatedescription').val().trim();

        // Kiểm tra dữ liệu nhập liệu
        if (inputClassName === '' || inputDescription === '') {
            showErrorMessage('Vui lòng nhập đầy đủ thông tin.');
            return;
        }
        // Submit form khi không có dữ liệu trùng khớp
        $.ajax({
            url: $(this).attr('action'),
            method: $(this).attr('method'),
            data: $(this).serialize(),
            success: function(response) {
                // Show success message using SweetAlert2
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    showConfirmButton: false,
                    timer: 1500,
                }).then(function () {
                    // Reload the page or perform any other actions
                    window.location.reload(true);
                });
            },
            error: function(error) {
                // Show error message using SweetAlert2
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'An error occurred while adding the class.'
                });
            }
        });

    });

    function showErrorMessage(message) {
        // Xóa thông báo cũ (nếu có)
        $('#error-message').remove();

        // Tạo và hiển thị thông báo mới
        var errorMessage = $('<span id="error-message" class="text-danger">' + message + '</span>');
        errorMessage.insertAfter('#updatechapterName');
    }
});
$(document).ready(function() {
    // Xử lý sự kiện click cho nút Xóa
    $('.btn-delete').click(function(e) {
        e.preventDefault();

        // Lấy URL xóa từ thuộc tính href của nút Xóa
        var deleteUrl = $(this).attr('href');

        // Hiển thị hộp thoại xác nhận xóa
        Swal.fire({
            title: 'Are you sure you want to delete?',
            text: "This action cannot be undone!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Delete',
            cancelButtonText: 'Cancel',
            reverseButtons: true
        }).then(function(result) {
            if (result.isConfirmed) {
                // Nếu người dùng xác nhận xóa, thực hiện yêu cầu xóa
                deleteClass(deleteUrl);
            }
        });
    });

    // Hàm xóa lớp học
    function deleteClass(url) {
        $.ajax({
            url: url,
            method: 'GET',
            success: function(response) {
                // Hiển thị thông báo xóa thành công
                Swal.fire({
                    icon: 'success',
                    title: 'Deleted successfully!',
                    showConfirmButton: false,
                    timer: 1500,
                    }).then(function(result) {
                        window.location.reload(true);
                        // Tải lại trang sau khi xóa thành công
                    });
            },
            error: function(error) {
                // Hiển thị thông báo lỗi xóa
                Swal.fire({
                    icon: 'error',
                    title: 'Error!',
                    text: 'An error occurred while deleting the class.'
                });
            }
        });
    }
});

