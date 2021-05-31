var ShowSubjectList = (function () {
    // $('#add_name').val();
    var curid=0;
    var initial =function () {
        GetAll();
        bindEvent();
    };

    var GetAll = function () {
        $.ajax({
            url: 'http://localhost:8080/api/v1/subjects',
            type: "GET",

            success: function(result) {
                console.log(result[0].id);
                if(result.length > 0) {
                    sessionStorage.setItem("Subject",JSON.stringify(result));
                } else {
                    alert("không có danh sách");
                }
            },
            error: function(error) {
                // alert("ERROR:"+error);
            }
        } );
    };

    var bindEvent = function () {
        $(document).on('click','#search', function (){
            var name = $('input[name = "searchSub"]').val();
            $.ajax({
                url: 'http://localhost:8080/api/v1/subjects?search='+name,
                type: "GET",
                success: function(list) {

                    if(list.length > 0) {
                        var html = "";
                        for (var i = 0; i < list.length; i++) {
                            var stt = i + 1;
                            html += '<tr>';
                            html += '<td style="text-align: center">'+ stt +'</td>';
                            html += '<td style="text-align: center">'+ list[i].id +'</td>';
                            html += '<td style="text-align: center">'+ list[i].subjectName +'</td>';
                            html += '<td style="text-align: center">'+ list[i].percentCC +'</td>';
                            html += '<td style="text-align: center">'+ list[i].percentTH +'</td>';
                            html += '<td style="text-align: center">'+ list[i].percentBTL +'</td>';
                            html += '<td style="text-align: center">'+ list[i].percentKT +'</td>';
                            html += '<td style="text-align: center">'+ list[i].percentCuoiKy +'</td>';
                            html += '<td style="text-align: center"><button class="editS" onClick="reply_click('+(list[i].id)+')" >Sửa</button></td>';
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

    }

    return {
        initial: initial
    }
})();
ShowSubjectList.initial();