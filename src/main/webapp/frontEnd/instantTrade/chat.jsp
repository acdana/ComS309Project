<!-- javascript for the instant chat -->
<html>
<head>
<!-- simple temp css to make the chat more readable -->
<style type="text/css">
input#chat {
    width: 400px
}

#console-container {
    width: 392px;
}

#console {
    border: 1px solid #000000;
    height: 170px;
    overflow-y: scroll;
    padding: 5px;
    width: 100%;
}

#console p {
    padding: 0;
    margin: 0;
}
</style>
<script src="chat.js"></script>
</head>
<body>

    <div>
        <p>
            <input type="text" placeholder="type and press enter to chat"
                id="chat" />
        </p>
        <div id="console-container">
            <div id="console"></div>
        </div>
    </div>
</body>
</html>