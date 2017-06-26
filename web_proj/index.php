<?php require_once('php-lib/db.php'); ?>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="lib/css/bootstrap.min.css">
    <link rel="stylesheet" href="lib/css/style.css">
    <link rel="stylesheet" href="lib/css/nouislider.min.css">
    <script src="lib/js/jquery-3.2.1.min.js"></script>
    <script src="lib/js/script.js"></script>
    <script src="lib/js/nouislider.min.js"></script>
    <title>Search anything you like...</title>
    <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
</head>
    <body>
        <div class="container-fluid">
            <div class="table-responsive">
                <table class="table table-bordered table-condensed">
                    <tr>
                        <td>
                            <span class="disable-fields btn btn-sm btn-link" style="font-size:13px;padding:0">Disable Fields</span>
                            <span class="enable-fields btn btn-sm btn-link" style="font-size:13px;padding:0;display:none">Enable Fields</span>
                        </td>
                        <td><input checked id="use-searchstring" name="use-searchstring" type="checkbox" value="1"> <label for="use-searchstring">String</label></td>
                        <td><input checked id="use-screen" name="use-screen" type="checkbox" value="1"> <label for="use-screen">Screen</label></td>
                        <td><input checked id="use-ram" name="use-ram" type="checkbox" value="1"> <label for="use-ram">RAM</label></td>
                        <td><input checked id="use-camera" name="use-camera" type="checkbox" value="1"> <label for="use-camera">Camera</label></td>
                        <td><input checked id="use-storage" name="use-storage" type="checkbox" value="1"> <label for="use-storage">Storage</label></td>
                        <td><input checked id="use-weight" name="use-weight" type="checkbox" value="1"> <label for="use-weight">Weight</label></td>
                        <td><input checked id="use-battery" name="use-battery" type="checkbox" value="1"> <label for="use-battery">Battery</label></td>
                        <td><input checked id="use-price" name="use-price" type="checkbox" value="1"> <label for="use-price">Price</label></td>
                        <td><input checked id="use-company" name="use-company" type="checkbox" value="1"> <label for="use-company">Company</label></td>
                        <td><input checked id="use-network" name="use-network" type="checkbox" value="1"> <label for="use-network">Network</label></td>
                        <td><input checked id="use-resolution" name="use-resolution" type="checkbox" value="1"> <label for="use-resolution">Resolution</label></td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="container">
            <div class="empty-space"></div>
            <div id="logo">
                <a href="<?php echo BASE_URL;?>" title="Home"><img src="img/coogle-logo.png"></a>
            </div>
            <div class="empty-space"></div>
            <div id="search">
                <form method="get" action="" id="search-form">
                    <div class="search-table">
                        <label for="searchstring"></label>
                        <input class="search-field" type="text" name="searchstring" id="searchstring" value="" placeholder="Search something...">
                        <input class="search-button" type="submit" value="Search">
                    </div>
                    <span class=""><input type="checkbox" id="cache" name="cache" value="1"> <label for="cache">Cache this search</label></span>
                    <div class="filter-table">
                        <div class="empty-space"></div>
                        <div class="row">
                            <!-- Screen Size -->
                            <div class="col-md-4">
                                <h5>Screen Size (Inch)</h5>
                                <div id="screensize"></div>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="screensize-from"></label>
                                <input type="number" id="screensize-from" name="screensize-from" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="screensize-to"></label>
                                <input type="number" id="screensize-to" name="screensize-to" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                            <!-- RAM Size -->
                            <div class="col-md-4">
                                <h5>RAM Size (GB)</h5>
                                <div id="ram"></div>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="ram-from"></label>
                                <input type="number" id="ram-from" name="ram-from" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="ram-to"></label>
                                <input type="number" id="ram-to" name="ram-to" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                        </div>
                        <div class="empty-space"></div>
                        <div class="row">
                            <!-- Screen Resolution -->
                            <!--div class="col-md-4">
                                <h5>Screen Resolution (Pixels)</h5>
                                <div id="screenresolution"></div>
                                <label for="screenresolution-to"></label>
                                <input type="number" id="screenresolution-to" name="screenresolution-to" value="" class="hidden">
                                <label for="screenresolution-from"></label>
                                <input type="number" id="screenresolution-from" name="screenresolution-from" value="" class="hidden">
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1"></div-->
                            <!-- Camera -->
                            <div class="col-md-4">
                                <h5>Camera (MP)</h5>
                                <div id="camera"></div>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="camera-from"></label>
                                <input type="number" id="camera-from" name="camera-from" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="camera-to"></label>
                                <input type="number" id="camera-to" name="camera-to" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                            <!-- Storage Size -->
                            <div class="col-md-4">
                                <h5>Storage Size (GB)</h5>
                                <div id="storage"></div>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="storage-from"></label>
                                <input type="number" id="storage-from" name="storage-from" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="storage-to"></label>
                                <input type="number" id="storage-to" name="storage-to" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                        </div>
                        <div class="empty-space"></div>
                        <div class="row">
                            <!-- Weight -->
                            <div class="col-md-4">
                                <h5>Weight (Grams)</h5>
                                <div id="weight"></div>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="weight-from"></label>
                                <input type="number" id="weight-from" name="weight-from" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="weight-to"></label>
                                <input type="number" id="weight-to" name="weight-to" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                            <!-- Battery Size -->
                            <div class="col-md-4">
                                <h5>Battery Size (mAh)</h5>
                                <div id="battery"></div>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="battery-from"></label>
                                <input type="number" id="battery-from" name="battery-from" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                            <div class="col-md-1">
                                <h5>&nbsp;</h5>
                                <label for="battery-to"></label>
                                <input type="number" id="battery-to" name="battery-to" value="" readonly>
                                <div class="empty-space"></div>
                            </div>
                        </div>
                        <div class="empty-space"></div>
                        <div class="row">
                            <!-- OS -->
                            <div class="col-md-12">
                                <h5>Operating System</h5>
                                <div class="table-responsive">
									<table class="table table-sm">
										<tr>
											<td>
												<input type="checkbox" id="android-os" name="android-os" value="1">
												<label for="android-os">Android</label>
											</td>
											<td>
												<input type="checkbox" id="apple-os" name="apple-os" value="1">
												<label for="apple-os">Apple</label>
											</td>
											<td>
												<input type="checkbox" id="windows-os" name="windows-os" value="1">
												<label for="windows-os">Windows</label>
											</td>
											<td>
												<input type="checkbox" id="other-os" name="other-os" value="1">
												<label for="other-os">Other</label>
											</td>
										</tr>
									</table>
								</div>
                            </div>
                        </div>
                        <div class="empty-space"></div>
                        <div class="row">
                            <div class="table-responsive">
                                <table class="table">
                                    <tr>
										<td><label for="price-from">Price From</label></td>
										<td><label for="price-to">Price To</label></td>
                                        <td><label for="company"><h5>Company</h5></label></td>
                                        <td><label for="network"><h5>Network</h5></label></td>
                                        <td><label for="screenresolution"><h5>Screen Resolution</h5></label></td>
                                    </tr>
                                    <tr>
										<!-- Price -->
										<td>
											<input type="number" id="price-from" name="price-from" value="0">
										</td>
										<td>
											<input type="number" id="price-to" name="price-to" value="300">
										</td>
                                        <td>
                                            <select id="company" name="company">
                                                <option value="">Select...</option>
                                                <?php
                                                    $results = DB::query("select * from ".MANUFACTURERS_TABLE." order by manufacturer asc");
                                                    foreach ($results as $row):
                                                ?>
                                                        <option value="<?php echo $row['manufacturer']; ?>"><?php echo $row['manufacturer']; ?></option>
                                                <?php
                                                    endforeach;
                                                ?>
                                            </select>
                                        </td>
                                        <td>
                                            <select id="network" name="network">
                                                <option value="">Select...</option>
                                                <option value="2g">2G</option>
                                                <option value="3g">3G</option>
                                                <option value="4g">4G</option>
                                                <option value="4g+">4G+</option>
                                                <option value="5g">5G</option>
                                            </select>
                                        </td>
                                        <td>
                                            <select id="screenresolution" name="screenresolution">
                                                <option value="">Select...</option>
                                                <option value="240x320">240x320</option>
                                                <option value="480x640">480x640</option>
                                                <option value="480x800">480x800</option>
                                                <option value="480x854">480x854</option>
                                                <option value="540x960">540x960</option>
                                                <option value="640x1136">640x1136</option>
                                                <option value="720x720">720x720</option>
                                                <option value="720x1280">720x1280</option>
                                                <option value="750x1334">750x1334</option>
                                                <option value="1080x1920">1080x1920</option>
                                                <option value="1080x2040">1080x2040</option>
                                                <option value="1440x1440">1440x1440</option>
                                                <option value="1440x2560">1440x2560</option>
                                                <option value="1440x2880">1440x2880</option>
                                                <option value="1440x2960">1440x2960</option>
                                            </select>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="empty-space"></div>
                    </div>
                </form>
            </div>
            <div class="filter-buttons">
                <input type="button" class="btn btn-primary btn-sm show-filters" value="Show Filters">
                <input type="button" class="btn btn-primary btn-sm hide-filters" value="Hide Filters">
            </div>
            <div class="small-empty-space"></div>
            <div id="results">
                <div class="">
                    <div class="well well-sm">
                        <strong>Results</strong>
                        <!--div class="btn-group">
                            <a href="#" id="list" class="btn btn-default btn-sm"><span class=""></span>List</a>
                            <a href="#" id="grid" class="btn btn-default btn-sm"><span class=""></span>Grid</a>
                        </div-->
                    </div>
                    <!-- Start Product Listing -->
                    <div id="products" class="small-row list-group">
                        Search Something...
                        <!-- AJAX Fills this area -->
                    </div>
                    <!-- End Product Listing -->
                </div>
            </div>
            <div class="empty-space"></div>
        </div>
        <?php include 'footer.php'; ?>
    </body>
</html>
