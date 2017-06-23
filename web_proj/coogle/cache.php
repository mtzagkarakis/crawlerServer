<?php
    require_once('php-lib/db.php');
    // Flushes the cache
    if (isset($_POST['flush_cache']) && $_POST['flush_cache'] == 'flush_cache') :
        try {
            DB::query("delete from ".CACHE_TABLE);
            echo '<div class="alert alert-success"><strong>Success!</strong> Flushed all caches.</div>';
        } catch (MeekroDBException $e) {
            echo '<div class="alert alert-danger"><strong>Failure!</strong> '.$e->getMessage().'</div>';
        }
    endif;

    $results = DB::query("select * from ".CACHE_TABLE);
?>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="lib/css/bootstrap.min.css">
    <link rel="stylesheet" href="lib/css/style.css">
    <script src="lib/js/jquery-3.2.1.min.js"></script>
    <title>Manage Cache</title>
    <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
</head>
<body>
<div class="container-fluid">
    <div class="empty-space"></div>
    <div id="logo">
        <a href="<?php echo BASE_URL;?>" title="Home"><img src="img/coogle-logo.png"></a>
    </div>
    <div class="empty-space"></div>
    <h4>Previously Cached Searches</h4>
    <div class="empty-space"></div>
    <div class="table-responsive">
        <table class="table table-hover table-bordered">
            <tbody>
                <tr>
                    <th class="table-header">Id</th>
                    <th class="table-header">String</th>
                    <th class="table-header">Screen Size</th>
                    <th class="table-header">Ram</th>
                    <th class="table-header">Camera</th>
                    <th class="table-header">Storage</th>
                    <th class="table-header">Weight</th>
                    <th class="table-header">Battery</th>
                    <th class="table-header">Android</th>
                    <th class="table-header">Apple</th>
                    <th class="table-header">Windows</th>
                    <th class="table-header">Other</th>
                    <th class="table-header">Price</th>
                    <th class="table-header">Company</th>
                    <th class="table-header">Network</th>
                    <th class="table-header">Screen Resolution</th>
                    <th class="table-header">Query Date</th>
                    <th class="table-header">Response Time</th>
                    <th class="table-header">Probed Sources</th>
                    <th class="table-header">Merged Products Found</th>
                    <th class="table-header">Total Products Found</th>
                    <th class="table-header">Link</th>
                </tr>
                <?php
                    foreach($results as $row):
                        parse_str($row['query'], $query);
                        $string = isset($query['searchstring']) ? $query['searchstring'] : '-';
                        $screensize_from = isset($query['screensize-from']) ? $query['screensize-from'] : '';
                        $screensize_to = isset($query['screensize-to']) ? $query['screensize-to'] : '';
                        $screensize = $screensize_from.' - '.$screensize_to;
                        $ram_from = isset($query['ram-from']) ? $query['ram-from'] : '';
                        $ram_to = isset($query['ram-to']) ? $query['ram-to'] : '';
                        $ram = $ram_from.' - '.$ram_to;
                        $camera_from = isset($query['camera-from']) ? $query['camera-from'] : '';
                        $camera_to = isset($query['camera-to']) ? $query['camera-to'] : '';
                        $camera = $camera_from.' - '.$camera_to;
                        $storage_from = isset($query['storage-from']) ? $query['storage-from'] : '';
                        $storage_to = isset($query['storage-to']) ? $query['storage-to'] : '';
                        $storage = $storage_from.' - '.$storage_to;
                        $weight_from = isset($query['weight-from']) ? $query['weight-from'] : '';
                        $weight_to = isset($query['weight-to']) ? $query['weight-to'] : '';
                        $weight = $weight_from.' - '.$weight_to;
                        $battery_from = isset($query['battery-from']) ? $query['battery-from'] : '';
                        $battery_to = isset($query['battery-to']) ? $query['battery-to'] : '';
                        $battery = $battery_from.' - '.$battery_to;
                        $android = isset($query['android-os']) ? 'Yes' : 'No';
                        $apple = isset($query['apple-os']) ? 'Yes' : 'No';
                        $windows = isset($query['windows-os']) ? 'Yes' : 'No';
                        $otheros = isset($query['other-os']) ? 'Yes' : 'No';
                        $price_from = isset($query['price-from']) ? $query['price-from'] : '';
                        $price_to = isset($query['price-to']) ? $query['price-to'] : '';
                        $price = $price_from.' - '.$price_to;
                        $price = $query['price-from'].' - '.$query['price-to'];
                        $company = isset($query['company']) ? ucfirst($query['company']) : '-';
                        $network = isset($query['network']) ? strtoupper($query['network']) : '-';
                        $screenresolution = isset($query['screenresolution']) ? $query['screenresolution'] : '-';
                        $sources = $row['sources'];
                        $merged_products_found = $row['merged_products_found'];
                        $total_products_found = $row['total_products_found'];
                        $elapsed_time = round($row['elapsed_time'] / 60, 3)." mins";
                ?>
                    <tr>
                        <td class="table-data"><?php echo $row['id']; ?></td>
                        <td class="table-data"><?php echo $string; ?></td>
                        <td class="table-data"><?php echo $screensize.'\''; ?></td>
                        <td class="table-data"><?php echo $ram.' GB'; ?></td>
                        <td class="table-data"><?php echo $camera.' MP'; ?></td>
                        <td class="table-data"><?php echo $storage.' GB'; ?></td>
                        <td class="table-data"><?php echo $weight.' gr'; ?></td>
                        <td class="table-data"><?php echo $battery.' mAh'; ?></td>
                        <td class="table-data"><?php echo $android; ?></td>
                        <td class="table-data"><?php echo $apple; ?></td>
                        <td class="table-data"><?php echo $windows; ?></td>
                        <td class="table-data"><?php echo $otheros; ?></td>
                        <td class="table-data"><?php echo $price.' €'; ?></td>
                        <td class="table-data"><?php echo $company; ?></td>
                        <td class="table-data"><?php echo $network; ?></td>
                        <td class="table-data"><?php echo $screenresolution; ?></td>
                        <td class="table-data"><?php echo $row['insert_date']; ?></td>
                        <td class="table-data"><?php echo $elapsed_time; ?></td>
                        <td class="table-data"><?php echo $sources; ?></td>
                        <td class="table-data"><?php echo $merged_products_found; ?></td>
                        <td class="table-data"><?php echo $total_products_found; ?></td>
                        <td class="table-data"><a href="<?php echo BASE_URL.'?'.$row['query']; ?>" title="" target="_blank">View</a></td>
                    </tr>
                <?php endforeach; ?>
            </tbody>
        </table>
    </div>
    <div class="empty-space"></div>
    <div class="container">
        <form action="" method="post">
            <input type="hidden" name="flush_cache" id="flush_cache" value="flush_cache">
            <input class="btn btn-danger btn-sm" type="submit" value="Flush All Caches">
        </form>
        <div class="empty-space"></div>
    </div>
    <?php include 'footer.php'; ?>
</body>
</html>