var Login = (function () {
    // $('#add_name').val();
    var initial =function () {
        $.ajax({
            url: 'http://localhost:8080/api/v1/classrooms',
            type: "GET",
            success: function(result) {
                if(result.length > 0) {
                    sessionStorage.setItem("classroom",JSON.stringify(result));
                    var obj = JSON.parse(sessionStorage.classroom);
                } else {
                    alert("không thể kết nối");
                }
            },
            error: function(error) {
                console.log(error);
            }
        });
        bindEvent();
    };

    var bindEvent = function () {
        $(document).on('click','#login', function () {
            var name = $('input[name=username]').val();
            var pass = $('input[name=password]').val();
            var obj = {name: name};
            $.ajax({
                url: "http://localhost:8080/api/v1/users/login",
                type: "post",
                data: JSON.stringify({ "username" : name, "password": pass}),
                contentType: 'application/json',
                dataType: "json",
                success: function(result) {
                    if (result.role === "STUDENT") {
                        window.location.href = '../html/BangDiemCaNhanToanKhoa.html';
                        sessionStorage.setItem("user",JSON.stringify(result));
                    } else if (result.role === "LECTURER") {
                        window.location.href = '../html/trangChuSqa.html';
                        sessionStorage.setItem("user",JSON.stringify(result));
                    } else {
                        $.ajax({
                            url: 'http://localhost:8080/api/v1/users/getAllStudent',
                            type: "GET",
                            success: function(result) {
                                if(result.length > 0) {
                                    sessionStorage.setItem("Student",JSON.stringify(result));
                                } else {
                                    alert("không có danh sách");
                                }
                            },
                            error: function(error) {
                                console.log(error);
                            }
                        });
                        window.location.href = '../html/TrangChuQuanLy.html';
                        sessionStorage.setItem("user",JSON.stringify(result));
                    }
                    sessionStorage.setItem("user",JSON.stringify(result));
                    console.log(result);
                },
                error: function(error) {
                    $('#errorField').html('<div>' +error.responseJSON.message+ '<div>');
                    console.log(error);
                }
            });
        });
    };

    return {
        initial: initial
    }
})();
Login.initial();