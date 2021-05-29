var ShowBangDiem = (function () {
    // $('#add_name').val();
    var initial =function () {
        getAllSub();
        getAllPoit();
        bindEvent();
    };

    var getAllSub = function () {
        $.ajax({
            url: 'http://localhost:8080/api/v1/subjects',
            type: "GET",
            success: function(result) {
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
        var obj = JSON.parse(sessionStorage.user);
        var id = obj.id;
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
        $(document).on('click','#logout', function () {

        })
    }

    return {
        initial: initial
    }
})();
ShowBangDiem.initial();