var FollowStuPoint = (function () {
    var initial =function () {
        getAllPoint();
        bindEvent();
    };

    var getAllPoint = function () {
        $.ajax({
            url: 'http://localhost:8080/api/v1/points',
            type: "GET",
            success: function(result) {
                if(result.length > 0) {
                    redrawTable(result ,false);
                } else {
                    alert("không có điểm");
                }
            },
            error: function(error) {
                alert(error);
            }
        });

    }

    var redrawTable = function (result, single) {
        var point = result;
        var html = "";
        if (single) {
            for (var i = 0; i < point.length; i++) {
                html += '<tr>';
                html += '<td colspan="11">'+ point[i].description + " " + point[i].name +'</td>'
                html += '</tr>';
                for (var j = 0; j < point[i].points.length; j++) {
                    html += '<tr>';
                    html += '<td>'+ point[i].points[j].name +'</td>';
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

        }
        else {
            for (var i = 0; i < point.length; i++) {
                html += '<tr>';
                html += '<td>'+ point[i].studentName +'</td>';
                html += '<td>'+ point[i].codeSubject +'</td>';
                html += '<td>'+ point[i].subjectName +'</td>';
                html += '<td>'+ point[i].diemCC +'</td>';
                html += '<td>'+ point[i].diemKT +'</td>';
                html += '<td>'+ point[i].diemTH +'</td>';
                html += '<td>'+ point[i].diemBTL +'</td>';
                html += '<td>'+ point[i].diemCuoiKy +'</td>';
                html += '<td>'+ point[i].point4 +'</td>';
                html += '<td>'+ point[i].point10 +'</td>';
                html += '<td>'+ point[i].pointString +'</td>';
                html += '<td>'+ "" +'</td>';
                html += '</tr>';
            }
        }
        $('#bodyDiem').html(html);
    }

    var bindEvent = function () {
        $(document).on('click','#search_stu', function () {
            var obj = JSON.parse(sessionStorage.Student);
            var name = $('input[name=stu_name]').val().trim();
            var id = null;
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].name === name) {
                    id = obj[i].id;
                    break;
                }
            }
            $.ajax({
                url: 'http://localhost:8080/api/v1/points?studentId='+id,
                type: "GET",
                success: function(result) {
                    if(result.length > 0) {
                        redrawTable(result, true);
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