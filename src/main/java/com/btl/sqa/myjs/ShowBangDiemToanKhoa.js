var ShowBangDiem = (function () {
    // $('#add_name').val();
    var obj = JSON.parse(sessionStorage.user);
    var id = obj.id;
    var sub = null;
    var initial =function () {
        drawTable();
        getAllSub();
        getAllPoit();
        bindEvent();
    };

    var drawTable = function (data) {
        var point = null;
        if (data) {
            point = data;
        }
        else point = JSON.parse(sessionStorage.AllPoint);
        var html = "";
        for (var i = 0; i < point.length; i++) {
            if (point[i].points.length > 0) {
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
        }
        $('#bodyDiem').html(html);
    }

    var getAllSub = function () {
        $.ajax({
            url: 'http://localhost:8080/api/v1/subjects',
            type: "GET",
            success: function(result) {
                sub = result;
                if(result.length > 0) {
                    sessionStorage.setItem("Subject",JSON.stringify(result));
                }
            },
            error: function(error) {
                alert(error);
            }
        });
    }

    var getAllPoit = function () {
        $.ajax({
            url: 'http://localhost:8080/api/v1/points?studentId='+id,
            type: "GET",
            success: function(result) {
                if(result.length > 0) {
                    sessionStorage.setItem("AllPoint",JSON.stringify(result));
                } else {
                    alert("không có điểm");
                }
            },
            error: function(error) {
                console.log(error);
            }
        });
    };

    var bindEvent = function () {
        $(document).on('click','#search_sub', function () {
            var name = $('input[name=sub]').val().trim();
            $.ajax({
                url: 'http://localhost:8080/api/v1/points?studentId='+id +'&subjectName='+name,
                type: "GET",
                success: function(result) {
                    if(result.length > 0) {
                        drawTable(result)
                    } else {
                        alert("không có điểm");
                    }
                },
                error: function(error) {
                    console.log(error);
                }
            });
            var point = JSON.parse(sessionStorage.AllPoint);
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
        })
    }

    return {
        initial: initial
    }
})();
ShowBangDiem.initial();