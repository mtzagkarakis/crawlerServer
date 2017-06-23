<?php
    require_once('meekrodb.2.3.class.php');
    DB::$user = 'root';
    DB::$password = 'root';
    DB::$dbName = 'meta_configuration';
    DB::$host = 'localhost';
    DB::$port = '3306';
    DB::$encoding = 'utf8';
    define('RESOURCES_TABLE', 'resources');
    define('CACHE_TABLE', 'cache');
    define('BASE_URL', baseUrl().'/coogle/');
    define('SERVER_URL','localhost');
    define('SERVER_PORT','4567');
    DB::$error_handler = false; // since we're catching errors, don't need error handler
    DB::$throw_exception_on_error = true;

	ini_set('max_execution_time', 0);
	ini_set('memory_limit', '-1');

function baseUrl(){
    return sprintf(
        "%s://%s",
        isset($_SERVER['HTTPS']) && $_SERVER['HTTPS'] != 'off' ? 'https' : 'http'
        ,$_SERVER['SERVER_NAME']
        //,$_SERVER['REQUEST_URI']
    );
}
