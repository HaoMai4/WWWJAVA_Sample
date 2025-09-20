let ws;

function connect() {
    const userName = document.getElementById("userName").value;
    if ("WebSocket" in window) { // Open WebSocket
        ws = new WebSocket("ws://localhost:8080/WebSocketSample/chatEndpoint/" + userName);
        ws.onopen = function () { /*Perform handling when connection is opened */
        };
        ws.onmessage = function (evt) {
            const json = JSON.parse(evt.data);
            const currentValue = document.getElementById('output').innerHTML;
            document.getElementById('output').innerHTML = currentValue
                + '<br />' + json.userName + ": " + json.message;
        };
        ws.onclose = function () { // websocket is closed.
            alert("Connection is closed...");
        };
    } else { // The browser doesn't support WebSocket
        alert("WebSocket NOT supported by your Browser!");
    }
}

function sendMessage() {
    const userName = document.getElementById('userName').value;
    const message = document.getElementById('message').value;
    const json = {
        'userName': userName,
        'message': message
    };
    ws.send(JSON.stringify(json));
    return false;
}