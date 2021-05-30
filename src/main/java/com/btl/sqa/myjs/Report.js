var FollowStuPoint = (function () {
    var initial =function () {
        $.ajax({
            url: 'http://localhost:8080/api/v1/student/getAllStudentReport',
            type: "GET",
            success: function(result) {
                if(result.length > 0) {
                    redrawTable(result);
                } else {
                    alert("không có điểm");
                }
            },
            error: function(error) {
                alert(error);
            }
        });
        bindEvent();
    };

    var redrawTable = function (result) {
        var point = result;
        var html = "";
        for (var i = 0; i < point.length; i++) {
            var stt = i +1;
            html += '<tr>';
            html += '<td>'+ stt +'</td>';
            html += '<td>'+ point[i].name +'</td>';
            html += '<td>'+ point[i].className +'</td>';
            html += '<td>'+ point[i].email +'</td>';
            html += '<td>'+ point[i].facultyName +'</td>';
            html += '<td>'+ point[i].gpa +'</td>';
            html += '<td>'+ point[i].gpa10 +'</td>';
            html += '<td>'+ point[i].gpaApha +'</td>';
            html += '<td>'+ "" +'</td>';
            html += '</tr>';
        }
        $('#bodyDiem').html(html);
    }

    var bindEvent = function () {
        $(document).on('click','#stu_list', function () {
            $.ajax({
                url: 'http://localhost:8080/api/v1/student/getAllStudentReportByClass',
                type: "GET",
                success: function(result) {
                    if(result.length > 0) {
                        redrawTable(result);
                    } else {
                        alert("không có điểm");
                    }
                },
                error: function(error) {
                    alert(error);
                }
            });
        })
        $(document).on('click','#stu_scho', function () {
            $.ajax({
                url: 'http://localhost:8080/api/v1/student/getAllStudentReportBySchoolarship?gpa=3.2',
                type: "GET",
                success: function(result) {
                    if(result.length > 0) {
                        redrawTable(result);
                    } else {
                        alert("không có điểm");
                    }
                },
                error: function(error) {
                    alert(error);
                }
            });
        })
        $(document).on('click','#stu_pass', function () {
            $.ajax({
                url: 'http://localhost:8080/api/v1/student/getAllStudentReportBySchoolarship?gpa=2.01',
                type: "GET",
                success: function(result) {
                    if(result.length > 0) {
                        redrawTable(result);
                    } else {
                        alert("không có điểm");
                    }
                },
                error: function(error) {
                    alert(error);
                }
            });
        })
        $(document).on('click','#stu_fail', function () {
            $.ajax({
                url: 'http://localhost:8080/api/v1/student/getAllStudentReportByFailure?gpa=1.9',
                type: "GET",
                success: function(result) {
                    if(result.length > 0) {
                        redrawTable(result);
                    } else {
                        alert("không có điểm");
                    }
                },
                error: function(error) {
                    alert(error);
                }
            });
        })
    }

    return {
        initial: initial
    }
})();
FollowStuPoint.initial();