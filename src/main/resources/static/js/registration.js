
$(document).ready(function () {


    $("#reg").click(function () {
        if($('#password').val()==$('#passwordRepeat').val()&&$('#password').val()!="") {
            var settings = {
                "async": true,
                "crossDomain": true,
                "url": "register",
                "method": "GET",
                "headers": {
                    "name": $('#name').val(),
                    "password": $('#password').val()
                }
            }
                $.ajax(settings).done(function (response) {
                if(response!=null) {
                    console.log(response);


                }

            });
        }
        console.log($('#name').val());


    });




});