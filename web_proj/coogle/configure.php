<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="lib/css/bootstrap.min.css">
    <link rel="stylesheet" href="lib/css/style.css">
    <script src="lib/js/jquery-3.2.1.min.js"></script>
    <title>Configuration</title>
    <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
    <style>
        .input-sm {padding:5px!important;}
    </style>
</head>
    <body>
        <?php
            // Include the db config file
            require_once('php-lib/db.php');
            // Inserts a new resource
            if (isset($_POST['append_db']) && $_POST['append_db'] == 'append_db') :
                $_POST['append_db'] = null;
                try {
                    DB::insert(RESOURCES_TABLE, array(
                        'name'                  => $_POST['name'],
                        'category_url'          => $_POST['category_url'],
                        'encoding'              => $_POST['encoding'],
                        'pagination_selector'   => $_POST['pagination_selector'],
                        'pagination_param'      => $_POST['pagination_param'],
                        'pagination_step'       => $_POST['pagination_step'],
                        'product_url'           => $_POST['product_url'],
                        'product_name'          => $_POST['product_name'],
                        'product_price'         => $_POST['product_price'],
                        'product_price_decimal' => $_POST['product_price_decimal'],
                        'product_manufacturer'  => $_POST['product_manufacturer'],
                        'product_image'         => $_POST['product_image'],
                        'key_selector'          => $_POST['key_selector'],
                        'value_selector'        => $_POST['value_selector'],
                        'is_active'             => 1,
                        'source_order'          => $_POST['source_order']
                    ));
                    echo '<div class="alert alert-success"><strong>Success!</strong> Inserted resource.</div>';
                } catch (MeekroDBException $e) {
                    echo '<div class="alert alert-danger"><strong>Failure!</strong> '.$e->getMessage().'</div>';
                }
            endif;
            // Updates an existing resource
            if (isset($_POST['update_resource']) && $_POST['update_resource'] == 'update_resource'):
                $_POST['update_resource'] = null;
                try {
                    DB::update(RESOURCES_TABLE,
                        array(
                            'name'                  => $_POST['name'],
                            'category_url'          => $_POST['category_url'],
                            'encoding'              => $_POST['encoding'],
                            'pagination_selector'   => $_POST['pagination_selector'],
                            'pagination_param'      => $_POST['pagination_param'],
                            'pagination_step'       => $_POST['pagination_step'],
                            'product_url'           => $_POST['product_url'],
                            'product_name'          => $_POST['product_name'],
                            'product_price'         => $_POST['product_price'],
                            'product_price_decimal' => $_POST['product_price_decimal'],
                            'product_manufacturer'  => $_POST['product_manufacturer'],
                            'product_image'         => $_POST['product_image'],
                            'key_selector'          => $_POST['key_selector'],
                            'value_selector'        => $_POST['value_selector'],
                            'is_active'             => $_POST['is_active'],
                            'source_order'          => $_POST['source_order']
                        ), "id=%d", $_POST['resource_id']
                    );
                    echo '<div class="alert alert-success"><strong>Success!</strong> Updated resource.</div>';
                } catch (MeekroDBException $e) {
                    echo '<div class="alert alert-danger"><strong>Failure!</strong> '.$e->getMessage().'</div>';
                }
            endif;
            // Deletes an existing resource
            if (isset($_POST['delete_resource']) && $_POST['delete_resource'] == 'delete_resource'):
                $_POST['delete_resource'] = null;
                try {
                    DB::delete(RESOURCES_TABLE, 'id=%d', $_POST['resource_id']);
                    echo '<div class="alert alert-success"><strong>Success!</strong> Deleted resource.</div>';
                } catch (MeekroDBException $e) {
                    echo '<div class="alert alert-danger"><strong>Failure!</strong> '.$e->getMessage().'</div>';
                }
            endif;
            // Query the resources
            $results = DB::query("select * from ".RESOURCES_TABLE." order by is_active desc, source_order asc");
        ?>
        <div class="container-fluid">
            <div class="empty-space"></div>
            <div id="logo">
                <a href="<?php echo BASE_URL;?>" title="Home"><img src="img/coogle-logo.png"></a>
            </div>
            <div class="empty-space"></div>
            <div class="">
                <div class="table-responsive">
                    <h4>Dabatase Resources</h4>
                    <table class="table table-condensed table-hover">
                        <tr>
                            <th class="table-header">Id</th>
                            <th class="table-header">Order</th>
                            <th class="table-header">Name</th>
                            <th class="table-header">Category URL</th>
                            <th class="table-header">Encoding</th>
                            <th class="table-header">Pagination Selector</th>
                            <th class="table-header">Pagination Parameter</th>
                            <th class="table-header">Pagination Step</th>
                            <th class="table-header">Product URL</th>
                            <th class="table-header">Product Name</th>
                            <th class="table-header">Product Price</th>
                            <th class="table-header">Price Decimal</th>
                            <th class="table-header">Product Manufacturer</th>
                            <th class="table-header">Product Image</th>
                            <th class="table-header">Key Selector</th>
                            <th class="table-header">Value Selector</th>
                            <th class="table-header">Active</th>
                            <th class="table-header">Update</th>
                            <th class="table-header">Delete</th>
                        </tr>
                        <?php
                            $count = 0;
                            foreach($results as $row) :
                                $count++;
                        ?>
                            <tr>
                                <td><?php echo '<form action="" method="post"><a title="" class="btn btn-primary btn-sm" href="'.$row['category_url'].'" target="_blank">'.$count; ?></a></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="source_order" name="source_order" value="'.$row['source_order'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="name" name="name" value="'.$row['name'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="category_url" name="category_url" value="'.$row['category_url'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="encoding" name="encoding" value="'.$row['encoding'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="pagination_selector" name="pagination_selector" value="'.$row['pagination_selector'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="pagination_param" name="pagination_param" value="'.$row['pagination_param'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="pagination_step" name="pagination_step" value="'.$row['pagination_step'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="product_url" name="product_url" value="'.$row['product_url'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="product_name" name="product_name" value="'.$row['product_name'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="product_price" name="product_price" value="'.$row['product_price'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="product_price_decimal" name="product_price_decimal" value="'.$row['product_price_decimal'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="product_manufacturer" name="product_manufacturer" value="'.$row['product_manufacturer'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="product_image" name="product_image" value="'.$row['product_image'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="key_selector" name="key_selector" value="'.$row['key_selector'].'">'; ?></td>
                                <td><?php echo '<input class="form-control input-sm expandable" type="text" id="value_selector" name="value_selector" value="'.$row['value_selector'].'">'; ?></td>
                                <?php if ($row['is_active'] == 1): ?>
                                <td><?php echo '<select style="width:55px;background-color:#4CAF50;color:#fff;" class="form-control input-sm expandable" id="is_active" name="is_active"><option value="1" selected>Yes</option><option value="0">No</option></select>'; ?></td>
                                <?php else: ?>
                                <td><?php echo '<select style="width:55px;background-color:#f44336;color:#fff;" class="form-control input-sm expandable" id="is_active" name="is_active"><option value="1">Yes</option><option value="0" selected>No</option></select>'; ?></td>
                                <?php endif; ?>
                                <td><?php echo '<input type="hidden" value="'.$row['id'].'" id="resource_id" name="resource_id">
                                                <input type="hidden" value="update_resource" id="update_resource" name="update_resource">
                                                <input class="btn btn-warning btn-sm" type="submit" value="Update">
                                                </form>'; ?></td>
                                <td><?php echo '<form action="" method="post">
                                                <input type="hidden" value="'.$row['id'].'" id="resource_id" name="resource_id">
                                                <input type="hidden" value="delete_resource" id="delete_resource" name="delete_resource">
                                                <input class="btn btn-danger btn-sm" type="submit" value="Delete">
                                                </form>'; ?></td>
                            </tr>
                        <?php endforeach; ?>
                    </table>
                </div>
            </div>
            <div class="empty-space"></div>
            <div class="">
                <form method="post" action="">
                    <div class="table-responsive">
                        <h4>Append Resource</h4>
                        <table class="table table-condensed table-hover">
                            <tr>
                                <th class="table-header"><label for="source_order">Source Order</label></th>
                                <th class="table-header"><label for="name">Name</label></th>
                                <th class="table-header"><label for="category_url">Category URL</label></th>
                                <th class="table-header"><label for="encoding">Encoding</label></th>
                                <th class="table-header"><label for="pagination_selector">Pagination Selector</label></th>
                                <th class="table-header"><label for="pagination_param">Pagination Param</label></th>
                                <th class="table-header"><label for="pagination_step">Pagination Step</label></th>
                                <th class="table-header"><label for="product_url">Product URL</label></th>
                                <th class="table-header"><label for="product_name">Product Name</label></th>
                                <th class="table-header"><label for="product_price">Product Price</label></th>
                                <th class="table-header"><label for="product_price">Price Decimal</label></th>
                                <th class="table-header"><label for="product_manufacturer">Product Manufacturer</label></th>
                                <th class="table-header"><label for="product_image">Product Image</label></th>
                                <th class="table-header"><label for="key_selector">Key Selector</label></th>
                                <th class="table-header"><label for="value_selector">Value Selector</label></th>
                            </tr>
                            <tr>
                                <td><input class="form-control input-sm" value="0" id="source_order" name="source_order" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="name" name="name" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="category_url" name="category_url" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="encoding" name="encoding" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="pagination_selector" name="pagination_selector" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="pagination_param" name="pagination_param" type="text"></td>
                                <td><input class="form-control input-sm" value="1" id="pagination_step" name="pagination_step" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="product_url" name="product_url" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="product_name" name="product_name" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="product_price" name="product_price" type="text"></td>
                                <td><input class="form-control input-sm" value="." id="product_price_decimal" name="product_price_decimal" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="product_manufacturer" name="product_manufacturer" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="product_image" name="product_image" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="key_selector" name="key_selector" type="text"></td>
                                <td><input class="form-control input-sm" value="" id="value_selector" name="value_selector" type="text"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="empty-space"></div>
                    <div class="container">
                        <div class="filter-buttons">
                            <input type="hidden" name="append_db" id="append_db" value="append_db">
                            <input type="submit" value="Append" class="btn btn-primary btn-sm">
                        </div>

                    </div>
                </form>
                <div class="empty-space"></div>
            </div>
        </div>
        <?php include 'footer.php'; ?>
    </body>
</html>