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

                // construct an HTTP request
                var xhr = new XMLHttpRequest();
                xhr.open('POST', $('#url').val());
                xhr.setRequestHeader("Content-type", "application/json");
                //xhr.setRequestHeader("Content-Length",json.length);
                xhr.setRequestHeader('Accept', 'application/json');


                //alert(json);
                // send the collected data as JSON
                xhr.send(json);

                xhr.onloadend = function() {

                    json = xhr.responseText;
                    json = JSON.parse(json);

                    console.log(json);
                    $('#shithier').html(print_r(json, true));
                }

            });

        });

        function print_r (array, return_val) {
            // http://kevin.vanzonneveld.net
            // +   original by: Michael White (http://getsprink.com)
            // +   improved by: Ben Bryan
            // +      input by: Brett Zamir (http://brett-zamir.me)
            // +      improved by: Brett Zamir (http://brett-zamir.me)
            // +   improved by: Kevin van Zonneveld (http://kevin.vanzonneveld.net)
            // -    depends on: echo
            // *     example 1: print_r(1, true);
            // *     returns 1: 1
            var output = '',
                    pad_char = ' ',
                    pad_val = 4,
                    d = this.window.document,
                    getFuncName = function (fn) {
                        var name = (/\W*function\s+([\w\$]+)\s*\(/).exec(fn);
                        if (!name) {
                            return '(Anonymous)';
                        }
                        return name[1];
                    },
                    repeat_char = function (len, pad_char) {
                        var str = '';
                        for (var i = 0; i < len; i++) {
                            str += pad_char;
                        }
                        return str;
                    },
                    formatArray = function (obj, cur_depth, pad_val, pad_char) {
                        if (cur_depth > 0) {
                            cur_depth++;
                        }

                        var base_pad = repeat_char(pad_val * cur_depth, pad_char);
                        var thick_pad = repeat_char(pad_val * (cur_depth + 1), pad_char);
                        var str = '';

                        if (typeof obj === 'object' && obj !== null && obj.constructor && getFuncName(obj.constructor) !== 'PHPJS_Resource') {
                            str += 'Array\n' + base_pad + '(\n';
                            for (var key in obj) {
                                if (Object.prototype.toString.call(obj[key]) === '[object Array]') {
                                    str += thick_pad + '[' + key + '] => ' + formatArray(obj[key], cur_depth + 1, pad_val, pad_char);
                                }
                                else {
                                    str += thick_pad + '[' + key + '] => ' + obj[key] + '\n';
                                }
                            }
                            str += base_pad + ')\n';
                        }
                        else if (obj === null || obj === undefined) {
                            str = '';
                        }
                        else { // for our "resource" class
                            str = obj.toString();
                        }

                        return str;
                    };

            output = formatArray(array, 0, pad_val, pad_char);

            if (return_val !== true) {
                if (d.body) {
                    this.echo(output);
                }
                else {
                    try {
                        d = XULDocument; // We're in XUL, so appending as plain text won't work; trigger an error out of XUL
                        this.echo('<pre xmlns="http://www.w3.org/1999/xhtml" style="white-space:pre;">' + output + '</pre>');
                    } catch (e) {
                        this.echo(output); // Outputting as plain text may work in some plain XML
                    }
                }
                return true;
            }
            return output;
        }

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
