var x = 0;
var y = 0;
var side = 0;
var sideTemp = 0;
var sessionId = "";
var gameId = "";




$(document).ready(function () {


    $("#updateFirstCol").click(function () {
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "list",
            "method": "GET",
            "headers": {
                "list": 3
            }
        }
            $.ajax(settings).done(function (response) {
            if(response!=null) {
                console.log(response.channels.length)
                console.log(response);
                var data= "";

                for(var i=0;i<response.channels.length;i++){
                    data += "<div class=\"raw container-fluid TTVChannelBTN\"  channelId=\"" + response.channels[i].id +"\">\n       " +
                        "                    <div class=\"col-xs-3\">\n" +
                        "                        <div class=\"img-container\">\n" +
                        "                            <img src=\"http://1ttv.org/uploads/" +  response.channels[i].logo + "\">\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"col-xs-9\">\n" +
                        "                        <a class=\"btn btn-block\" >" + response.channels[i].name +"</a>\n" +
                        "                    </div></div>"
                }

                data = "<div class=\"col-xs-12\" id=\"dataForTTV\">" + data + "</div></div>"
                $('#dataForTTV').replaceWith(data);

            }

        });


    });


    $('body').on("click", ".TTVChannelBTN", function (e) {
        console.log(e.currentTarget.attributes.channelid.value)
        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "channel",
            "method": "GET",
            "headers": {
                "channelId": e.currentTarget.attributes.channelid.value,
            }
        }

        $.ajax(settings).done(function (response) {
            console.log(response);
        });
    });


    $("#nx").click(function () {
        var i =Number($('#id').val());
        if(i+1>1020){
            i=1020
        }else {
            i++;
        }

        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "getById",
            "method": "GET",
            "headers": {
                "id": i
            }
        }
        $.ajax(settings).done(function (response) {
            if(response!=null) {
                console.log(response);
                $('#title').replaceWith('<h1><div id="title">' + response.title+ '</div></h1>');
                $('#answer').replaceWith('<h1><div id="answer">' + response.body+ '</div></h1>');
                $("#answer").hide();
                $('#id').val(i);

            }

        });


    });

    $("#pr").click(function () {
        var i =Number($('#id').val());
        if(i-1<0){
            i=0
        }else {
            i--;
        }


        var settings = {
            "async": true,
            "crossDomain": true,
            "url": "getById",
            "method": "GET",
            "headers": {
                "id": i
            }
        }
        $.ajax(settings).done(function (response) {
            if(response!=null) {
                console.log(response);
                $('#title').replaceWith('<h1><div id="title">' + response.title+ '</div></h1>');
                $('#answer').replaceWith('<h1><div id="answer">' + response.body+ '</div></h1>');
                $("#answer").hide();
                $('#id').val(i);
            }

        });


    });

    $("#show").click(function () {
        $("#answer").show();


    });



});