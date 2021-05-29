var FollowStuPoint = (function () {
    var initial =function () {
        redrawTable();
        bindEvent();
    };

    var redrawTable = function () {

    }

    var bindEvent = function () {
        $(document).on('click','#search_stu', function () {
            var obj = JSON.parse(sessionStorage.Student);
            var id = $('input[name=stu_name]').val().trim();
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].name === id) {
                    id = obj[i].id;
                    break;
                }
            }
            $.ajax({
                url: 'http://localhost:8080/api/v1/points?studentId='+id,
                type: "GET",
                success: function(result) {
                    if(result.length > 0) {
                        var point = result;
                        var html = "";
                        for (var i = 0; i < point.length; i++) {
                            html += '<tr>';
                            html += '<td colspan="11">'+ point[i].description + " " + point[i].name +'</td>'
                            html += '</tr>';
                            for (var j = 0; j < point[i].points.length; j++) {
                                html += '<tr>';
                                html += '<td>'+ point[i].points[j].codeSubject +'</td>';
                                html += '<td>'+ point[i].points[j].subjectName +'</td>';
                                html += '<td>'+ point[i].points[j].diemCC +'</td>';
                                html += '<td>'+ point[i].points[j].diemKT +'</td>';
                                html += '<td>'+ point[i].points[j].diemTH +'</td>';
                                html += '<td>'+ point[i].points[j].diemBTL +'</td>';
                                html += '<td>'+ point[i].points[j].diemCuoiKy +'</td>';
                                html += '<td>'+ point[i].points[j].point4 +'</td>';
                                html += '<td>'+ point[i].points[j].point10 +'</td>';
                                html += '<td>'+ point[i].points[j].pointString +'</td>';
                                html += '<td>'+ "" +'</td>';

                                html += '</tr>';
                            }
                        }
                        $('#bodyDiem').html(html);
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