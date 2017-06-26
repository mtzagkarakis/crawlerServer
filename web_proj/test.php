<html>
<head>
</head>
    <body>
		<?php
			require_once('php-lib/db.php');
			$url = 'http://'.SERVER_URL.':'.SERVER_PORT;
			//$string = file_get_contents($url);
			$ch = curl_init($url);
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
			$output = curl_exec($ch);
			echo $output;
		?>
    </body>
</html>
