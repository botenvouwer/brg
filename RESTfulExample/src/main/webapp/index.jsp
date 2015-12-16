<html>
    <head>
        <title>$Title$</title>
        <script src="javascript/jquery.js"></script>
        <script>

            $(function(){

                $.getJSON('rest/hello/mkyong/jan', function(shit){
                    console.log('gelukt');
                    console.log(shit);
                    $('#shithier').html('hallo ' + shit[0]);

                });

            });

        </script>
    </head>
    <body id="shithier">
        <h2>Hello World!</h2>
    </body>
</html>
