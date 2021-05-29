var ShowStudentList = (function () {
    // $('#add_name').val();
    var initial =function () {
        GetAll();
        bindEvent();
    };

    var GetAll = function () {
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
    };

    var bindEvent = function () {
        $(document).on('click','#search', function (){
            var name = $('input[name = "searchStu"]').val();
            $.ajax({
                url: 'http://localhost:8080/api/v1/users/getAllStudent?search='+name,
                type: "GET",
                success: function(list) {
                    if(list.length > 0) {
                        var html = "";
                        for (var i = 0; i < list.length; i++) {
                            var stt = i + 1;
                            html += '<tr>';
                            html += '<td style="text-align: center">'+ stt +'</td>';
                            html += '<td style="text-align: center">'+ list[i].id +'</td>';
                            html += '<td>'+ list[i].name +'</td>';
                            html += '<td style="text-align: center">'+ list[i].className +'</td>';
                            html += '<td style="text-align: center">'+ list[i].phoneNo +'</td>';
                            html += '<td style="text-align: center">'+ list[i].email +'</td>';
                            html += '<td>'+ list[i].facultyName +'</td>';
                            html += '<td style="text-align: center"><button>Thêm</button> <button>Sửa</button><button>Sửa</button></td>';
                            html += '</tr>';
                        }
                        $('#bodyList').html(html);
                    } else {
                        alert("không có");
                    }
                },
                error: function(error) {
                    console.log(error);
                }
            });
        })

        $(document).on('click','#deleteS', function (){
            alert('Đã xóa');
            window.location.href = 'DanhSachSinhVien.html';
        })
        $(document).on('click','#editS', function (){
            var currentRow = $(this).closest('tr');
            var ma = currentRow.find("td:eq(1)").text();
            debugger
            window.location.href = '../html/ThemNguoiDung.html';
            var obj = {isAdd: false, isEdit: true, stuLec: 'stu', ma:ma};
            localStorage.setItem("checkAddEdit",JSON.stringify(obj));
        })
        $(document).on('click','#addS', function (){
            window.location.href = '../html/ThemNguoiDung.html';
            var obj = {isAdd: true, isEdit: false, stuLec: 'stu'};
            localStorage.setItem("checkAddEdit",JSON.stringify(obj));
        })
    }

    return {
        initial: initial
    }
})();
ShowStudentList.initial();