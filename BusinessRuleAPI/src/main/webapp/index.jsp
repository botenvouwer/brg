<html>
    <head>
        <title>$Title$</title>
        <script src="javascript/jquery.js"></script>
        <script>

            $(function(){

                $.getJSON('service/bsn/testtest', {jsonMessage: '{"test":"Dit is een testBericht"}'}, function(shit){
                    console.log('gelukt');
                    console.log(shit);
                    $('#shithier').html('hallo ' + shit);
                });

            });

        </script>
    </head>
    <body>
        <div>
            <h2>GET:</h2>
            <div><p>1. <b>Geeft uitgebreide JSON terug (object-in-object, arrays)</b><br/><a href="/service/bsn">/service/bsn</a></p>
            <p>2. <b>Geeft plaintext terug</b><br/><a href="/service/bsn/raw">/service/bsn/raw</a></p>
            <p>3. <b>Geeft een simpel JSON-oject terug</b><br/><a href="/service/bsn/simple">/service/bsn/simple</a></p>
            <p>4. <b>Geeft officieel voorbeeld van businessrules JSON object terug</b><br/><a href="/service/bsn/example">/service/bsn/example</a></p></div>
        </div>
        <div id="shithier"></div>
    </body>
</html>
