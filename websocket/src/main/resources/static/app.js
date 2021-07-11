var stompClient = null;

function setConnected(connected) {
    $("#enter").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        join();
        stompClient.subscribe('/topic/subscribers', function (msg) {

            showChat(JSON.parse(msg.body).from, JSON.parse(msg.body).text, JSON.parse(msg.body).time);
            console.log(msg);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function join() {

    stompClient.send("/app/chat.join", {}, JSON.stringify({'from': $('#name').val()}));
}

function message() {

    stompClient.send("/app/chat.message", {}, JSON.stringify({'from': $('#name').val(), 'text' : $('#msg').val()}));
}

function showChat(name, message, time) {
    if(message){
        $("#greetings").append("<tr><td>" +name + message + "</td></tr>");
    }
    else{
        $("#chat").append("<tr><td>" +name  + "</td></tr>");
        $("#chat").append("<tr><td>" +message + time + "</td></tr>");
    }

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#enter" ).click(function() { connect()});
    $( "#send" ).click(function() { message(); });
});

