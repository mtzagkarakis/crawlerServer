<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="lib/css/bootstrap.min.css">
    <link rel="stylesheet" href="lib/css/style.css">
    <script src="lib/js/jquery-3.2.1.min.js"></script>
    <script>
        var _timeout = 250;
        $('a[id^="show-prod-group-"]').click(function() {
            var _prod_group_id = $(this).attr('id').replace('show-','');
            $('div[id^="prod-group-"').hide(_timeout);
            $("#" + _prod_group_id).show(_timeout);
        });
        $('div[id^="hide-prod-group-"]').click(function() {
            var _prod_group_id = $(this).attr('id').replace('hide-','');
            $("#" + _prod_group_id).hide(_timeout);
        });
    </script>
</head>
<body>
<?php
    require_once('php-lib/db.php');
    $server_query = trim(str_replace('&cache=1','',$_SERVER['QUERY_STRING']));
    $cache_query = "select * from ".CACHE_TABLE." where query=%s";
    $is_cached = false;

    /* Load from Cache or Server */
    $start = microtime(true);
    try {
        DB::query($cache_query, $server_query);
        $counter = DB::count();
        if ($counter > 0) {
            $is_cached = true;
            $results = DB::query($cache_query, $server_query);
            $output = $results[0]["json"];
            $json = json_decode($output, true);
            echo '<div class="container-fluid"><div class="row"><div class="alert alert-success"><strong>Used cache</strong></div></div></div>';
        } else {
            /* Remote Call to Service */
            $url = 'http://'.SERVER_URL.':'.SERVER_PORT.'/?'.$server_query;
            $ch = curl_init($url);
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch, CURLOPT_CONNECTTIMEOUT ,0);
            curl_setopt($ch, CURLOPT_TIMEOUT, 6000);
            $output = curl_exec($ch);
            curl_close($ch);
            //$output = file_get_contents($url);
            if(!$output)
				die("Server or output unavailable.");
            $json = json_decode($output, true);
            if ($json["status"] >= 400)
                die("Server responded with invalid status code.");
        }
    } catch (MeekroDBException $e) {
        die($e->getMessage());
    }
    $end = microtime(true);
    $elapsed_time = $end - $start;
    $exec_time = round($elapsed_time / 60, 3);

    $data = $json["data"];
    $merged_products_found = count($data);
    $total_products_found = countAllProducts($data);
    $probed_sources = $is_cached ? 0 : countProbedSources();

    /* Save in cache */
    if(isset($_GET['cache']) && $_GET['cache'] == 1) {
        $clean_json = trim(preg_replace('/\s\s+/', ' ', $output));
        try {
            DB::query($cache_query, $server_query);
            $counter = DB::count();
            if ($counter == 0) {
                DB::insert(CACHE_TABLE, array(
                    'query'                 => $server_query,
                    'json'                  => $clean_json,
                    'sources'               => $probed_sources,
                    'elapsed_time'          => $elapsed_time,
                    'merged_products_found' => $merged_products_found,
                    'total_products_found'  => $total_products_found
                ));
            }
            else {
                DB::update(CACHE_TABLE, array(
                    'json'                  => $clean_json,
                    'sources'               => $probed_sources,
                    'elapsed_time'          => $elapsed_time,
                    'merged_products_found' => $merged_products_found,
                    'total_products_found'  => $total_products_found
                ), 'query', $server_query);
            }
            echo '<div class="container-fluid"><div class="row"><div class="alert alert-success"><strong>Cached results</strong></div></div></div>';
        } catch (MeekroDBException $e) {
            if ($e->getErrno() != 1292)
                die($e->getMessage());
        }
    }

    for($i = 0; $i < count($data); $i++) {
        // First Product of each group for preview
        $single_product = $data[$i]["productSet"][0];
        $single_prod_title = $single_product["name"];
        $single_prod_price = $single_product["price"];
        $single_prod_img = findImgInSet($data[$i]["productSet"]);
    ?>
    <!-- Single Product View -->
    <div class="item col-xs-12 col-sm-6 col-lg-4">
        <div class="thumbnail">
            <img class="group list-group-image" src="<?php echo $single_prod_img; ?>" alt="" />
            <div class="caption">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-8" style="background-color: rgba(255,255,255,.7);border-radius:4px">
                        <h5 style="text-transform:uppercase" class="group inner list-group-item-heading"><?php echo '('.count($data[$i]["productSet"]).') '.$single_prod_title; ?></h5>
                        <p class="group inner list-group-item-text"><?php echo $single_prod_price; ?> €</p>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-4">
                        <a class="btn btn-success" href="#prod-group-<?php echo $i; ?>" id="show-prod-group-<?php echo $i; ?>">View</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Condensed Products View -->
    <div class="relative-products" id="prod-group-<?php echo $i; ?>">
        <div class="hide-prod-group">
            <div class="btn btn-warning" id="hide-prod-group-<?php echo $i; ?>">Hide</div>
        </div>
        <div class="table-responsive">
            <?php
                echo '<strong>Similar Found: '.count($data[$i]["productSet"]).'</strong>';
                for($j = 0; $j < count($data[$i]["productSet"]); $j++) {
                    $single_product_group = $data[$i]["productSet"][$j]["product"];
                    $single_prod_url = $single_product_group["url"];
                    $single_product_group_title = $single_product_group["name"];
                    $single_prod_price = $single_product_group["price"];
                    $single_prod_attrs = $single_product_group["attributes"];
            ?>
            <table class="table table-hover table-bordered">
                <tr>
                    <th>Τίτλος</th>
                    <th>Τιμή</th>
                    <?php foreach($single_prod_attrs as $key => $attr) { ?>
                        <th><?php echo $key; ?></th>
                    <?php } ?>
                </tr>
                <tr>
                    <td>
                        <a target="_blank" title="<?php echo $single_prod_url; ?>" href="<?php echo $single_prod_url; ?>">
                            <?php echo $single_product_group_title; ?>
                        </a>
                    </td>
                    <td><?php echo $single_prod_price; ?></td>
                    <?php foreach($single_prod_attrs as $key => $attr) { ?>
                        <td><div class="cell-contents"><?php echo $attr; ?></div></td>
                    <?php } ?>
                </tr>
            </table>
            <?php } ?>
        </div>
    </div>
<?php } ?>
    <div class="clear"></div>
    <div class="empty-space"></div>
    <div class="table-responsive" style="width:100%;clear:both">
        <table class="table table-hover table-bordered">
            <tr>
                <th class="table-header">Elapsed</th>
                <th class="table-header">Probed Sources</th>
                <th class="table-header">Merged Products Found</th>
                <th class="table-header">Total Products Found</th>
            </tr>
            <tr>
                <td class="table-data"><?php echo $exec_time;?> mins</td>
                <td class="table-data"><?php echo $probed_sources;?></td>
                <td class="table-data"><?php echo $merged_products_found;?></td>
                <td class="table-data"><?php echo $total_products_found;?></td>
            </tr>
        </table>
    </div>
    <div class="empty-space"></div>
</body>
</html>

<?php
function findImgInSet($prodSet) {
    if ( isset($prodSet[0]["product"]["imageUrl"])
        && !empty($prodSet[0]["product"]["imageUrl"])
        && $prodSet[0]["product"]["imageUrl"] != '' )
    {
        $img = $prodSet[0]["product"]["imageUrl"];
    }
    else {
        $img = 'img/unavailable.png';
        foreach($prodSet as $prod) {
            if ( isset($prod["product"]["imageUrl"])
                && !empty($prod["product"]["imageUrl"])
                && $prod["product"]["imageUrl"] != '' ) {
                $img = $prod["product"]["imageUrl"];
                break;
            }
        }
    }
    return $img;
}
function countAllProducts($data) {
    $cnt = 0;
    for($i = 0; $i < count($data); $i++)
        $cnt += count($data[$i]["productSet"]);
    return $cnt;
}
function countProbedSources() {
    $query = "select * from ".RESOURCES_TABLE." where is_active = 1";
    DB::query($query);
    $cnt = DB::count();
    return $cnt;
}