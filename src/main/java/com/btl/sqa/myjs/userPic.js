var user = JSON.parse(sessionStorage.user);
$('#img').html('<img src="ptit/' + user.name + '.jpg" style="width: 125px">')
$('#username').html('<label>'+"Tên: "+user.name+'</label>')