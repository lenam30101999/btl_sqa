var EditSubjects = (function () {
    var lecturer = null;
    var subject = null;
    var obj = JSON.parse(localStorage.EditSubject);
    const urlParams = new URLSearchParams(window.location.search);
    var initial =function () {
        rederHtml();
        bindEvent();
    };




    var bindEvent = function () {


        $(document).on('click','#confirm', function () {
            var id=urlParams.get('id');
            var subjectname = $('input[name=subjectName]').val();
            var btl = $('input[name=BTL]').val();
            var cc = $('input[name=CC]').val();
            var kt = $('input[name=KT]').val();
            var cuoiki = $('input[name=CuoiKy]').val();
            var th = $('input[name=TH]').val();
            console.log(obj.id);
                $.ajax({
                    url: "http://localhost:8080/api/v1/points/configPoint",
                    type: "PUT",
                    data: JSON.stringify({
                        "id": id,
                        "name":name,
                        "percentCC": cc,
                        "percentTH": th,
                        "percentBTL": btl,
                        "percentKT": kt,
                        "percentCuoiKy": cuoiki
                    }),
                    contentType: 'application/json',
                    dataType: "json",
                    success: function(result) {
                        alert("Sửa thành công");
                        // window.location.replace="http://www.w3schools.com";
                        window.location.href = '../html/CauHinhDiem.html';
                        console.log(result);
                    },
                    error: function(error) {
                        alert(error);
                        console.log(error);
                    }
                });




        });
    };



    var rederHtml = function () {
                var html = '<span style="font-size: large">Cấu hình điểm</span>';
                $('#title').html(html);


        $.ajax({
            url: "http://localhost:8080/api/v1/subjects?id="+urlParams.get('id'),
            type: "GET",
            success: function(result) {
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
                var subject=null;
                var sub = JSON.parse(sessionStorage.Subject);

                for (var i = 0; i < sub.length; i++) {
                    if (sub[i].id === parseInt(urlParams.get('id'))) {
                        subject = sub[i];

                        break;
                    }
                }
                $('input[name=subjectName]').val(subject.subjectName);
                $('input[name=BTL]').val(subject.percentBTL);
                $('input[name=CC]').val(subject.percentCC);
                $('input[name=KT]').val(subject.percentKT);
                $('input[name=CuoiKy]').val(subject.percentCuoiKy);
                $('input[name=TH]').val(subject.percentTH);



            }



    return {
        initial: initial
    }
})();
EditSubjects.initial();