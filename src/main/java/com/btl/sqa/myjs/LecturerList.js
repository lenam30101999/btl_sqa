var ShowLecturerList = (function () {
    // $('#add_name').val();
    var initial =function () {
        GetAll();
        bindEvent();
    };

    var GetAll = function () {
        $.ajax({
            url: 'http://localhost:8080/api/v1/users/getAllLecturer',
            type: "GET",
            success: function(result) {
                if(result.length > 0) {
                    sessionStorage.setItem("Lecturer",JSON.stringify(result));
                } else {
                    alert("không có giảng viên");
                }
            },
            error: function(error) {
                console.log(error);
            }
        });
    };

    var bindEvent = function () {
        $(document).on('click','#search', function (){
            var name = $('input[name = "searchLec"]').val();
            $.ajax({
                url: 'http://localhost:8080/api/v1/users/getAllLecturer?name='+name,
                type: "GET",
                success: function(list) {
                    if(list.length > 0) {
                        var html = "";
                        for (var i = 0; i < list.length; i++) {
                            var STT = i + 1;
                            html += '<tr>';
                            html += '<td style="text-align: center">'+ STT +'</td>';
                            html += '<td style="text-align: center">'+ list[i].id +'</td>';
                            html += '<td>'+ list[i].name +'</td>';
                            html += '<td style="text-align: center">'+ list[i].phoneNo +'</td>';
                            html += '<td style="text-align: center">'+ list[i].email +'</td>';
                            html += '<td>'+ list[i].facultyName +'</td>';
                            html += '<td style="text-align: center"><button id="editL">Sửa</button><button id="deleteL">Xóa</button></td>';
                            html += '</tr>';
                        }
                        $('#bodyList').html(html)
                    } else {
                        alert("không có");
                    }
                },
                error: function(error) {
                    console.log(error);
                }
            });
        })

        $(document).on('click','#deleteL', function (){
            var currentRow = $(this).closest('tr');
            var ma = currentRow.find("td:eq(1)").text();
            $.ajax({
                url: "http://localhost:8080/api/v1/users/deleteLecturer/" + ma,
                type: "delete",
                success: function(result) {
                    alert('Đã xóa');
                    window.location.href = 'DanhSachGiangVien.html';
                },
                error: function(error) {
                    console.log(error);
                }
            });
        })
        $(document).on('click','#editL', function (){
            var currentRow = $(this).closest('tr');
            var ma = currentRow.find("td:eq(1)").text();
            window.location.href = '../html/ThemNguoiDung.html';
            var obj = {isAdd: false, isEdit: true, stuLec: 'lec', ma:ma};
            localStorage.setItem("checkAddEdit",JSON.stringify(obj));
        })
        $(document).on('click','#addL', function (){
            window.location.href = '../html/ThemNguoiDung.html';
            var obj = {isAdd: true, isEdit: false, stuLec: 'lec'};
            localStorage.setItem("checkAddEdit",JSON.stringify(obj));
        })
    }

    return {
        initial: initial
    }
})();
ShowLecturerList.initial();