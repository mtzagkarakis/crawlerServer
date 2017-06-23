<?php
    require_once('db.php');
    if (isset($_POST["stop_server"]) && !empty($_POST["stop_server"]) && $_POST["stop_server"] == "stop_server") {
        $pid = $_POST["process"];
        $output = shell_exec('taskkill /PID '.$pid.' /F');
        echo $output;
    }
    if (isset($_POST["start_server"]) && !empty($_POST["start_server"]) && $_POST["start_server"] == "start_server") {
        $output = shell_exec('start cmd.exe /k java -Xmx2048m -jar java\productCrawlerServer-0.0.1-SNAPSHOT-jar-with-dependencies.jar');
        echo $output;
    }