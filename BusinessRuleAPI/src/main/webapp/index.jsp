<html>
    <head>
        <title>$Title$</title>
        <script src="javascript/jquery.js"></script>
        <script>

            $(function(){
            /*
                $.getJSON('rest/hello/jhfsjdfjh', function(shit){
                    console.log('gelukt');
                    console.log(shit);
                    $('#shithier').html('hallo ' + shit[0]);

                });
            */
            });

        </script>
    </head>
    <body id="shithier">
        <div>
            <h2>GET:</h2>
            <div><p>1. <b>Geeft uitgebreide JSON terug (object-in-object, arrays)</b><br/><a href="/service/bsn">/service/bsn</a></p>
            <p>2. <b>Geeft plaintext terug</b><br/><a href="/service/bsn/raw">/service/bsn/raw</a></p>
            <p>3. <b>Geeft een simpel JSON-oject terug</b><br/><a href="/service/bsn/simple">/service/bsn/simple</a></p>
            <p>4. <b>Geeft officieel voorbeeld van businessrules JSON object terug</b><br/><a href="/service/bsn/example">/service/bsn/example</a></p></div>
        </div>
    </body>
</html>
