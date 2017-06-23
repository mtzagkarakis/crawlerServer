<?php require_once('php-lib/db.php'); ?>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="lib/css/bootstrap.min.css">
    <link rel="stylesheet" href="lib/css/style.css">
    <script src="lib/js/jquery-3.2.1.min.js"></script>
    <script>
        function refreshPage() {
            location.reload();
        }
        $(document).ready(function() {
            var _info = $("#information");
            $("#stop_srv_btn").click(function(e) {
                e.preventDefault();
                var _form_input = $("#stop_server_form").serialize();
                $.ajax({
                    url: 'php-lib/srv.php',
                    data: _form_input,
                    type: 'POST',
                    async: true,
                    success: function(output) {
                        _info.html('<div class="alert alert-info">' + output + '</div>');
                        setTimeout(function() { refreshPage() }, 3000);
                    },
                    error: function(status, err) {
                        console.log("Error: " + status + err);
                    }
                });
            });
            $("#start_srv_btn").click(function(e) {
                e.preventDefault();
                var _form_input = $("#start_server_form").serialize();
                $.ajax({
                    url: 'php-lib/srv.php',
                    data: _form_input,
                    type: 'POST',
                    async: true,
                    success: function(output) {
                        _info.html('<div class="alert alert-info">' + output + '</div>');
                        setTimeout(function() { refreshPage() }, 3000);
                    },
                    error: function(status, err) {
                        console.log("Error: " + status + err);
                    }
                });
            });
        });
    </script>
    <title>Manage Server</title>
    <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
</head>
<body>
    <div class="container">
        <div class="empty-space"></div>
        <div id="logo">
            <a href="<?php echo BASE_URL;?>" title="Home"><img src="img/coogle-logo.png"></a>
        </div>
        <div class="empty-space"></div>
        <?php
            $output = shell_exec('netstat -ano | find "0.0.0.0:'.SERVER_PORT.'"');
            if (isset($output) && !empty($output)) :
                $output = trim(preg_replace('/\s+/', ' ', $output), "\t\n\r\0");
                $out = explode(" ", $output);
                $pid = $out[5];
                ?>
                <div class="alert alert-success"><strong>Server is online!</strong> Process: <span><?php echo $pid; ?></span></div>
                <div class="empty-space"></div>
                <form id="stop_server_form" action="" method="post">
                    <input id="stop_server" name="stop_server" value="stop_server" type="hidden">
                    <input id="process" name="process" type="hidden" value="<?php echo $pid; ?>">
                    <input id="stop_srv_btn" class="btn btn-danger btn-sm" type="submit" value="Stop Server">
                </form>
                <?php
            else:
                ?>
                <div class="alert alert-danger"><strong>Server is offline!</strong></div>
                <div class="empty-space"></div>
                <!--form id="start_server_form" action="" method="post">
                    <input id="start_server" name="start_server" value="start_server" type="hidden">
                    <input id="start_srv_btn" class="btn btn-success btn-sm" type="submit" value="Start Server">
                </form-->
                <?php
            endif;
        ?>
        <div class="empty-space"></div>
        <div id="information"></div>
        <div class="empty-space"></div>
    </div>
    <?php include 'footer.php'; ?>
</body>
</html>