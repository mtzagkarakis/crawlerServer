<?php require_once('php-lib/db.php'); ?>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="lib/css/bootstrap.min.css">
    <link rel="stylesheet" href="lib/css/style.css">
    <link rel="stylesheet" href="lib/css/jquery-ui.min.css">
    <script src="lib/js/jquery-3.2.1.min.js"></script>
    <script src="lib/js/jquery-ui.min.js"></script>
    <title>About Project</title>
    <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
    <script>
        $(document).ready(function() {
            $(".show-pdf").click(function(e) {
                e.preventDefault();
                var window_width = $(window).width();
                var window_height = $(window).height();
                if (window_width > 1000) {
                    var horizontalPadding = 30;
                    var verticalPadding = 30;
                    var _width = 1400;
                    var _height = 700;
                    var _href = $(this).attr("href");
                    $('<iframe id="modalDialog" src="' + _href + '" />').dialog({
                        title: $(this).attr("title"),
                        autoOpen: true,
                        width: _width,
                        height: _height,
                        modal: true,
                        closeOnEscape: true,
                        draggable: true,
                        resizable: true,
                        show: {effect: 'fade', duration: 250},
                        hide: {effect: 'fade', duration: 250},
                        close: function(event, ui) { $(this).remove(); },
                        overlay: {opacity: 0.5, background: "black"},
                        position: ['center', 80],
                        buttons: [
                         {
                             text: "Download",
                             class: "btn btn-success btn-sm",
                             icons: { primary: "ui-icon-folder-open" },
                             click: function() { window.location.assign ( _href ) }
                         },
                         {
                             text: "Print",
                             class: "btn btn-primary btn-sm",
                             icons: { primary: "ui-icon-print" },
                             click: function() { window.print(); }
                         },
                         {
                             text: "Canvel",
                             class: "btn btn-warning btn-sm",
                             icons: { primary: "ui-icon-close" },
                             click: function() { $( this ).dialog( "close" ); }
                         }
                         ]
                    })
                        .width(_width - horizontalPadding).height(_height - verticalPadding);
                    return false;
                }
                else {
                    window.location.assign ( $(this).attr("href") );
                }
            });
        });
    </script>
</head>
    <body>
        <div class="container">
            <div class="empty-space"></div>
            <div id="logo">
                <a href="<?php echo BASE_URL;?>" title="Home"><img src="img/coogle-logo.png"></a>
            </div>
            <div class="empty-space"></div>
            <div>
                <ul class="list-group pdf-group">
                    <li class="list-group-item"><a class="show-pdf" title="Description" href="documentation/Description.pdf">Base Description</a></li>
                    <li class="list-group-item"><a class="show-pdf" title="Deliverable" href="documentation/Deliverable.pdf">Final Deliverable</a></li>
                </ul>
            </div>
            <div class="empty-space"></div>
        </div>
        <?php include 'footer.php'; ?>
    </body>
</html>