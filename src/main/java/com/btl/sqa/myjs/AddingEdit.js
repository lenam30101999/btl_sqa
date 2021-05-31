var AddingEdit = (function () {
    var lecturer = null;
    var student = null;
    var obj = JSON.parse(localStorage.checkAddEdit);
    var initial =function () {
        rederHtml();
        bindEvent();
    };

    var bindEvent = function () {
        $(document).on('click','#addEdit', function () {
            var name = $('input[name=name]').val();
            var account = $('input[name=username]').val();
            var email = $('input[name=email]').val();
            var password = $('input[name=password]').val();
            var dob = $('input[name=dOB]').val();
            var address = $('input[name=address]').val();
            var phonenum = $('input[name=number]').val();
            var checkbox = $('input[name=cb]:checked').val();
            var falcutyWork = $('input[name=facultyWork]').val();
            var studentId = $('input[name=idS]').val();
            var falcuty = $('input[name=faculty]').val();
            var classs = $('#lop').val();
            if (obj.isAdd && obj.stuLec==='stu') {
                $.ajax({
                    url: "http://localhost:8080/api/v1/users/create",
                    type: "post",
                    data: JSON.stringify({
                        "name" : name,
                        "username": account,
                        "password": password,
                        "dob": dob,
                        "phoneNo": phonenum,
                        "gender": checkbox,
                        "address": address,
                        "email": email,
                        "identifyCard": studentId,
                        "classId": classs,
                        "facultyName": falcuty
                    }),
                    contentType: 'application/json',
                    dataType: "json",
                    // body : data,
                    success: function(result) {
                        console.log(result);
                        alert("Tạo thành công");
                        window.location.href = '../html/DanhSachSinhVien.html';
                    },
                    error: function(error) {
                        console.log(error);
                        alert(error);
                    }
                });
            } else if (obj.isEdit && obj.stuLec==='stu') {
                $.ajax({
                    url: "http://localhost:8080/api/v1/users/updateStudent",
                    type: "PUT",
                    data: JSON.stringify({
                        "id" : student.id,
                        "name" : name,
                        "username": account,
                        "password": password,
                        "dob": dob,
                        "phoneNo": phonenum,
                        "gender": checkbox,
                        "address": address,
                        "email": email,
                        "identifyCard": studentId,
                        "classId": classs,
                        "facultyName": falcuty
                    }),
                    contentType: 'application/json',
                    dataType: "json",
                    // body : data,
                    success: function(result) {
                        alert("Sửa thành công");
                        window.location.href = '../html/DanhSachSinhVien.html';
                        console.log(result);
                    },
                    error: function(error) {
                        alert(error);
                        console.log(error);
                    }
                });
            }

            else if (obj.isAdd && obj.stuLec==='lec') {
                $.ajax({
                    url: "http://localhost:8080/api/v1/users/create",
                    type: "post",
                    data: JSON.stringify({
                        "name" : name,
                        "username": account,
                        "password": password,
                        "dob": dob,
                        "phoneNo": phonenum,
                        "gender": checkbox,
                        "address": address,
                        "email": email,
                        "facultyName": falcutyWork,
                    }),
                    contentType: 'application/json',
                    dataType: "json",
                    // body : data,
                    success: function(result) {
                        alert("Tạo thành công");
                        window.location.href = '../html/DanhSachGiangVien.html';

                    },
                    error: function(error) {
                        console.log(error);
                        alert(error);

                    }
                });
            }
            else if (obj.isEdit && obj.stuLec==='lec') {
                $.ajax({
                    url: "http://localhost:8080/api/v1/users/updateLecturer",
                    type: "PUT",
                    data: JSON.stringify({
                        "id" : lecturer.id,
                        "name" : name,
                        "username": account,
                        "password": password,
                        "dob": dob,
                        "phoneNo": phonenum,
                        "gender": checkbox,
                        "address": address,
                        "email": email,
                        "facultyName": falcutyWork,
                    }),
                    contentType: 'application/json',
                    dataType: "json",
                    // body : data,
                    success: function(result) {
                        alert("Sửa thành công");
                        window.location.href = '../html/DanhSachGiangVien.html';
                        console.log(result);
                    },
                    error: function(error) {
                        alert(error);
                        console.log(error);
                    }
                });
            }
        });
    };

    var rederHtml = function () {
        if (obj.stuLec==='stu') {
            $('#forL').addClass('hidden');
            if (obj.isEdit === true) {
                var html = '<span style="font-size: large">Sửa sinh viên</span>';
                $('#title').html(html);
                var stu = JSON.parse(sessionStorage.Student);
                for (var i = 0; i < stu.length; i++) {
                    if (stu[i].username === obj.ma) {
                        student = stu[i];
                        break;
                    }
                }
                $('input[name=name]').val(student.name);
                $('input[name=username]').val(student.username);
                $('input[name=email]').val(student.email);
                $('input[name=password]').val(student.password);
                $('input[name=dOB]').val(student.dob);
                $('input[name=address]').val(student.address);
                $('input[name=number]').val(student.phoneNo);
                $('#'+student.gender).prop("checked", true);
                $('input[name=idS]').val(student.id);
                $('input[name=faculty]').val(student.facultyName);
                var classid = JSON.parse(sessionStorage.classroom);
                var id = 1;
                for (var i = 0; i < classid.length; i++) {
                    if (classid[i].name === student.className) {
                        id = classid[i].id;
                        break;
                    }
                }
                document.getElementById('lop').value = id;
            } else {
                var html = '<span style="font-size: large">Thêm sinh viên</span>';
                $('#title').html(html);
            }
        } else if (obj.stuLec==='lec') {
            $('#forS').addClass('hidden');
            if (obj.isEdit === true) {
                var html = '<span style="font-size: large">Sửa giảng viên</span>';
                $('#title').html(html);
                var lec = JSON.parse(sessionStorage.Lecturer);
                for (var i = 0; i < lec.length; i++) {
                    if (lec[i].id === parseInt(obj.ma)) {
                        lecturer = lec[i];
                        break;
                    }
                }
                $('input[name=name]').val(lecturer.name);
                $('input[name=username]').val(lecturer.username);
                $('input[name=email]').val(lecturer.email);
                $('input[name=password]').val(lecturer.password);
                $('input[name=dOB]').val(lecturer.dob);
                $('input[name=address]').val(lecturer.address);
                $('input[name=number]').val(lecturer.phoneNo);
                $('#'+lecturer.gender).prop("checked", true);
                $('input[name=facultyWork]').val(lecturer.facultyName);
                $('input[name=idS]').val(lecturer.id);
            } else {
                var html = '<span style="font-size: large">Thêm giảng viên</span>';
                $('#title').html(html);
            }
        }


    }

    return {
        initial: initial
    }
})();
AddingEdit.initial();