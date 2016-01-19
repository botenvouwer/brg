<html>
<head>
    <title>$Title$</title>
    <script src="javascript/jquery.js"></script>
    <script>

        $(function(){

            $(document).on('click', '.postdinkie', function(){

                var link = $(this).attr("href");
                $('#url').val(link);

                return false;
            });

            $(document).on('click', 'button', function(){

                var json = $('#payload').val();
                var url = $('#url').val();

                if(json == ""){

                    $.getJSON(url, function(data){
                        $('#shithier').html(print_r(data));
                    });

                }
                else {

                    // construct an HTTP request
                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', url);
                    xhr.setRequestHeader("Content-type", "application/json");
                    //xhr.setRequestHeader("Content-Length",json.length);
                    xhr.setRequestHeader('Accept', 'application/json');


                    //alert(json);
                    // send the collected data as JSON
                    xhr.send(json);

                    xhr.onloadend = function () {

                        json = xhr.responseText;
                        json = JSON.parse(json);

                        console.log(json);
                        $('#shithier').html(print_r(json));
                    }
                }
            });

        });

        var print_r = function (obj, t) {

            // define tab spacing
            var tab = t || '';

            // check if it's array
            var isArr = Object.prototype.toString.call(obj) === '[object Array]';

            // use {} for object, [] for array
            var str = isArr ? ('Array\n' + tab + '[\n') : ('Object\n' + tab + '{\n');

            // walk through it's properties
            for (var prop in obj) {
                if (obj.hasOwnProperty(prop)) {
                    var val1 = obj[prop];
                    var val2 = '';
                    var type = Object.prototype.toString.call(val1);
                    switch (type) {

                        // recursive if object/array
                        case '[object Array]':
                        case '[object Object]':
                            val2 = print_r(val1, (tab + '\t'));
                            break;

                        case '[object String]':
                            val2 = '\'' + val1 + '\'';
                            break;

                        default:
                            val2 = val1;
                    }
                    str += tab + '\t' + prop + ' => ' + val2 + ',\n';
                }
            }

            // remove extra comma for last property
            str = str.substring(0, str.length - 2) + '\n' + tab;

            return isArr ? (str + ']') : (str + '}');
        };

    </script>
</head>
<body>
<div>
    <h2>GET:</h2>
    <div><p>1. <b>Returns complicated JSON (object-in-object, arrays)</b><br/><a href="/service/businessrulegenerator">/service/businessrulegenerator</a></p>
        <p>2. <b>Returns plaintext </b><br/><a href="/service/businessrulegenerator/raw">/service/businessrulegenerator/raw</a></p>
        <p>3. <b>Returns a simple JSON-object </b><br/><a href="/service/businessrulegenerator/simple">/service/businessrulegenerator/simple</a></p>
        <p>4. <b>Returns an official example of a businessrules JSON object </b><br/><a href="/service/businessrulegenerator/example">/service/businessrulegenerator/example</a></p></div>
        <p>5. <b>Example of what we expect to receive in the body of POST #2 on this page </b><br/><a href="/service/businessrulegenerator/tablenames_example">/service/businessrulegenerator/tablenames_example/</a></p>

    <h2>POST:</h2>
    <div>
        <p>1. <b>An arraylist of businessrules can be send here to be converted/generated </b><br/><a class="postdinkie" href="/service/businessrulegenerator/generate/{template}/{generator}">/service/businessrulegenerator/generate/{template}/{generator}</a></p>
        <p>2. <b>Allows you to request table-names by providing database credentials </b><br/><a href="/service/businessrulegenerator/tablenames/">/service/businessrulegenerator/tablenames/</a></p>
    </div>
</div>
<h3>target</h3>
<input type="text" id="url" value="" style=" width: 500px; ">
<h3>payload</h3>
<textarea id="payload" style=" width: 100%; height: 500px; "></textarea>
<br>
<button>test</button>
<div><pre id="shithier"></pre></div>
</body>
</html>
