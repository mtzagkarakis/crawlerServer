function disableFilter2(_item, _input1, _input2) {
    _item.change(function() {
        var _this = $(this);
        if (_this.prop('checked')) {
            _input1.prop('disabled', false);
            _input2.prop('disabled', false);
            _input1.css({'background-color':'white'});
            _input2.css({'background-color':'white'});
        }
        else {
            _input1.prop('disabled', true);
            _input2.prop('disabled', true);
            _input1.css({'background-color':'lightgray'});
            _input2.css({'background-color':'lightgray'});
        }
    });
    if (_item.prop('checked')) {
        _input1.prop('disabled', false);
        _input2.prop('disabled', false);
        _input1.css({'background-color':'white'});
        _input2.css({'background-color':'white'});
    }
    else {
        _input1.prop('disabled', true);
        _input2.prop('disabled', true);
        _input1.css({'background-color':'lightgray'});
        _input2.css({'background-color':'lightgray'});
    }
}
function disableFilter1(_item, _input) {
    _item.change(function() {
        var _this = $(this);
        if (_this.prop('checked')) {
            _input.prop('disabled', false);
            _input.css({'background-color':'white'});
        }
        else {
            _input.prop('disabled', true);
            _input.css({'background-color':'lightgray'});
        }
    });
    if (_item.prop('checked')) {
        _input.prop('disabled', false);
        _input.css({'background-color':'white'});
    }
    else {
        _input.prop('disabled', true);
        _input.css({'background-color':'lightgray'});
    }
}
function fixNavBar() {
    $(window).resize(function() {
        var _width = $(window).width();
        if (_width < 770)
            $("nav").removeClass("navbar-fixed-bottom");
        else
            $("nav").addClass("navbar-fixed-bottom");
    });
    var _width = $(window).width();
    if (_width < 770)
        $("nav").removeClass("navbar-fixed-bottom");
    else
        $("nav").addClass("navbar-fixed-bottom");
}
function loopOverlay() {
    $("body").append('<div id="overlay"></div>');
}

function removeOverlay() {
    $("#overlay").remove();
}

$(document).ready(function() {
    $.ajaxSetup({
        beforeSend: function() {
            loopOverlay();
        },
        complete: function() {
            removeOverlay();
        }
    });

    fixNavBar();

    var baseurl = window.location.origin + window.location.pathname;
    var _filter_table = $(".filter-table");
    var _show_filters = $(".show-filters");
    var _hide_filters = $(".hide-filters");
    var _products_list = $("#products");
    var _products_item = $('#products .item');
    var _search_form = $("#search-form");

    var _search_field = $(".search-field");
    var _price_from = $("#price-from");
    var _price_to = $("#price-to");
    var _company = $("#company");
    var _network = $("#network");
    var _screenresolution = $("#screenresolution");
    var _android_os = $("#android-os");
    var _apple_os = $("#apple-os");
    var _window_os = $("#window-os");
    var _other_os = $("#other-os");
    var _cache = $("#cache");

    var _use_searchstring = $("#use-searchstring");
    var _use_screen = $("#use-screen");
    var _use_ram = $("#use-ram");
    var _use_camera = $("#use-camera");
    var _use_storage = $("#use-storage");
    var _use_weight = $("#use-weight");
    var _use_battery = $("#use-battery");
    var _use_price = $("#use-price");
    var _use_company = $("#use-company");
    var _use_network = $("#use-network");
    var _use_resolution = $("#use-resolution");

    var _screensize_from = $("#screensize-from");
    var _screensize_to = $("#screensize-to");
    var _ram_from = $("#ram-from");
    var _ram_to = $("#ram-to");
    var _camera_from = $("#camera-from");
    var _camera_to = $("#camera-to");
    var _storage_from = $("#storage-from");
    var _storage_to = $("#storage-to");
    var _weight_from = $("#weight-from");
    var _weight_to = $("#weight-to");
    var _battery_from = $("#battery-from");
    var _battery_to = $("#battery-to");

    disableFilter2(_use_screen, _screensize_from, _screensize_to);
    disableFilter2(_use_ram, _ram_from, _ram_to);
    disableFilter2(_use_camera, _camera_from, _camera_to);
    disableFilter2(_use_storage, _storage_from, _storage_to);
    disableFilter2(_use_weight, _weight_from, _weight_to);
    disableFilter2(_use_battery, _battery_from, _battery_to);
    disableFilter2(_use_price, _price_from, _price_to);

    disableFilter1(_use_searchstring, _search_field);
    disableFilter1(_use_company, _company);
    disableFilter1(_use_network, _network);
    disableFilter1(_use_resolution, _screenresolution);

    $(".disable-fields").click(function() {
        if (_use_screen.prop('checked')) _use_screen.click();
        if (_use_ram.prop('checked')) _use_ram.click();
        if (_use_camera.prop('checked')) _use_camera.click();
        if (_use_storage.prop('checked')) _use_storage.click();
        if (_use_weight.prop('checked')) _use_weight.click();
        if (_use_battery.prop('checked')) _use_battery.click();
        if (_use_price.prop('checked')) _use_price.click();
        if (_use_searchstring.prop('checked')) _use_searchstring.click();
        if (_use_company.prop('checked')) _use_company.click();
        if (_use_network.prop('checked')) _use_network.click();
        if (_use_resolution.prop('checked')) _use_resolution.click();
        $(this).hide();
        $(".enable-fields").show();
    });

    $(".enable-fields").click(function() {
        if (!_use_screen.prop('checked')) _use_screen.click();
        if (!_use_ram.prop('checked')) _use_ram.click();
        if (!_use_camera.prop('checked')) _use_camera.click();
        if (!_use_storage.prop('checked')) _use_storage.click();
        if (!_use_weight.prop('checked')) _use_weight.click();
        if (!_use_battery.prop('checked')) _use_battery.click();
        if (!_use_price.prop('checked')) _use_price.click();
        if (!_use_searchstring.prop('checked')) _use_searchstring.click();
        if (!_use_company.prop('checked')) _use_company.click();
        if (!_use_network.prop('checked')) _use_network.click();
        if (!_use_resolution.prop('checked')) _use_resolution.click();
        $(this).hide();
        $(".disable-fields").show();
    });

    /* Disable Default Enter Press */
    $(document).on("keypress", "#search-form", function(e) {
        return e.keyCode != 13;
    });

    /* Prevent Submit Action and perform AJAX instead */
    $(".search-button").click(function(e) {
        e.preventDefault();
        _products_list.html('');
        var _form_input = _search_form.serialize();
        window.history.replaceState(null, null, baseurl + '?' + _form_input);
        getListing(_form_input);
    });

    $('#list').click(function(event){
        event.preventDefault();
        _products_item.addClass('list-group-item');
    });
    $('#grid').click(function(event){
        event.preventDefault();
        _products_item.removeClass('list-group-item');
        _products_item.addClass('grid-group-item');
    });

    _filter_table.hide();
    _hide_filters.hide();

    _show_filters.click(function() {
        _filter_table.slideToggle('slow');
        _hide_filters.show();
        $(this).hide();
    });
    _hide_filters.click(function() {
        _filter_table.slideToggle('slow');
        _show_filters.show();
        $(this).hide();
    });

    /* Screen Size Slider */
    var screensize = document.getElementById('screensize');
    var screensize_from = document.getElementById('screensize-from');
    var screensize_to = document.getElementById('screensize-to');
    var screensize_inputs = [screensize_from, screensize_to];
    noUiSlider.create(screensize, {
        start: [3, 11],
        connect: true,
        range: {
            'min': 3,
            'max': 11
        },
        step: 1,
        pips: { // Show a scale with the slider
            mode: 'steps',
            stepped: true,
            density: 1
        }
    });
    screensize.noUiSlider.on('update', function( values, handle ) {
        screensize_inputs[handle].value = parseInt(values[handle]);
    });
    /* RAM */
    var ram = document.getElementById('ram');
    var ram_from = document.getElementById('ram-from');
    var ram_to = document.getElementById('ram-to');
    var ram_inputs = [ram_from, ram_to];
    noUiSlider.create(ram, {
        start: [1, 16],
        connect: true,
        range: {
            'min': 1,
            'max': 16
        },
        step: 1,
        pips: { // Show a scale with the slider
            mode: 'steps',
            stepped: true,
            density: 1
        }
    });
    ram.noUiSlider.on('update', function( values, handle ) {
        ram_inputs[handle].value = parseInt(values[handle]);
    });
    /* Storage Size */
    var storage = document.getElementById('storage');
    var storage_from = document.getElementById('storage-from');
    var storage_to = document.getElementById('storage-to');
    var storage_inputs = [storage_from, storage_to];
    noUiSlider.create(storage, {
        start: [2, 128],
        connect: true,
        range: {
            'min': 2,
            'max': 128
        },
        step: 2,
        pips: { // Show a scale with the slider
            values: [2,4,8,16,32,64,128],
            mode: 'values',
            stepped: true,
            density: 4
        }
    });
    storage.noUiSlider.on('update', function( values, handle ) {
        storage_inputs[handle].value = parseInt(values[handle]);
    });
    /* Processors */
    var weight = document.getElementById('weight');
    var weight_from = document.getElementById('weight-from');
    var weight_to = document.getElementById('weight-to');
    var weight_inputs = [weight_from, weight_to];
    noUiSlider.create(weight, {
        start: [50, 350],
        connect: true,
        range: {
            'min': 50,
            'max': 350
        },
        step: 10,
        pips: { // Show a scale with the slider
            values: [50, 90, 130, 170, 210, 250, 290, 330],
            mode: 'values',
            stepped: true,
            density: 2
        }
    });
    weight.noUiSlider.on('update', function( values, handle ) {
        weight_inputs[handle].value = parseInt(values[handle]);
    });
    /* Battery Size */
    var battery = document.getElementById('battery');
    var battery_from = document.getElementById('battery-from');
    var battery_to = document.getElementById('battery-to');
    var battery_inputs = [battery_from, battery_to];
    noUiSlider.create(battery, {
        start: [1000, 6000],
        connect: true,
        range: {
            'min': 1000,
            'max': 6000
        },
        step: 500,
        pips: { // Show a scale with the slider
            mode: 'steps',
            stepped: true,
            density: 2
        }
    });
    battery.noUiSlider.on('update', function( values, handle ) {
        battery_inputs[handle].value = parseInt(values[handle]);
    });
    /* Camera */
    var camera = document.getElementById('camera');
    var camera_from = document.getElementById('camera-from');
    var camera_to = document.getElementById('camera-to');
    var camera_inputs = [camera_from, camera_to];
    noUiSlider.create(camera, {
        start: [2, 24],
        connect: true,
        range: {
            'min': 2,
            'max': 24
        },
        step: 2,
        pips: { // Show a scale with the slider
            mode: 'steps',
            stepped: true,
            density: 2
        }
    });
    camera.noUiSlider.on('update', function( values, handle ) {
        camera_inputs[handle].value = parseInt(values[handle]);
    });

    /* Set Values if on URL */
    /* Basic Fields */
    _search_field.val(getUrlParameter('searchstring'));
    _price_from.val(getUrlParameter('price-from'));
    _price_to.val(getUrlParameter('price-to'));
    _company.val(getUrlParameter('company'));
    _network.val(getUrlParameter('network'));
    _screenresolution.val(getUrlParameter('screenresolution'));

    /* Checkboxes */
    if (typeof getUrlParameter('android-os') != "undefined" && getUrlParameter('android-os') == 1)
        _android_os.prop('checked', true);
    if (typeof getUrlParameter('apple-os') != "undefined" && getUrlParameter('apple-os') == 1)
        _apple_os.prop('checked', true);
    if (typeof getUrlParameter('windows-os') != "undefined" && getUrlParameter('windows-os') == 1)
        _window_os.prop('checked', true);
    if (typeof getUrlParameter('other-os') != "undefined" && getUrlParameter('other-os') == 1)
        _other_os.prop('checked', true);
    if (typeof getUrlParameter('cache') != "undefined" && getUrlParameter('cache') == 1)
        _cache.prop('checked', true);

    /* Slider Fields */
    var p_screensize_from = getUrlParameter('screensize-from');
    var p_screensize_to = getUrlParameter('screensize-to');
    screensize.noUiSlider.set([p_screensize_from, p_screensize_to]);


    var p_ram_from = getUrlParameter('ram-from');
    var p_ram_to = getUrlParameter('ram-to');
    ram.noUiSlider.set([p_ram_from, p_ram_to]);

    var p_weight_from = getUrlParameter('weight-from');
    var p_weight_to = getUrlParameter('weight-to');
    weight.noUiSlider.set([p_weight_from, p_weight_to]);

    var p_storage_from = getUrlParameter('storage-from');
    var p_storage_to = getUrlParameter('storage-to');
    storage.noUiSlider.set([p_storage_from, p_storage_to]);

    var p_battery_from = getUrlParameter('battery-from');
    var p_battery_to = getUrlParameter('battery-to');
    battery.noUiSlider.set([p_battery_from, p_battery_to]);

    var p_camera_from = getUrlParameter('camera-from');
    var p_camera_to = getUrlParameter('camera-to');
    camera.noUiSlider.set([p_camera_from, p_camera_to]);

    console.log('atr:' + getUrlParameter('searchstring'));
    /* Uncheck fields if not set in query */
    if ( typeof getUrlParameter('searchstring') == "undefined" )
        _use_searchstring.click();
    if ( typeof getUrlParameter('screensize-from') == "undefined" && typeof getUrlParameter('screensize-to') == "undefined" )
        _use_screen.click();
    if ( typeof getUrlParameter('ram-from') == "undefined" && typeof getUrlParameter('ram-to') == "undefined" )
        _use_ram.click();
    if ( typeof getUrlParameter('camera-from') == "undefined" && typeof getUrlParameter('camera-to') == "undefined" )
        _use_camera.click();
    if ( typeof getUrlParameter('storage-from') == "undefined" && typeof getUrlParameter('storage-to') == "undefined" )
        _use_storage.click();
    if ( typeof getUrlParameter('weight-from') == "undefined" && typeof getUrlParameter('weight-to') == "undefined" )
        _use_weight.click();
    if ( typeof getUrlParameter('battery-from') == "undefined" && typeof getUrlParameter('battery-to') == "undefined" )
        _use_battery.click();
    if ( typeof getUrlParameter('price-from') == "undefined" && typeof getUrlParameter('price-to') == "undefined" )
        _use_price.click();
    if ( typeof getUrlParameter('company') == "undefined" )
        _use_company.click();
    if ( typeof getUrlParameter('network') == "undefined" )
        _use_network.click();
    if ( typeof getUrlParameter('screenresolution') == "undefined" )
        _use_resolution.click();

    /* Perform the Request Manually if the Query is set */
    if (
        typeof getUrlParameter('searchstring') != "undefined" ||
        typeof getUrlParameter('screensize-from') != "undefined" ||
        typeof getUrlParameter('screensize-to') != "undefined" ||
        typeof getUrlParameter('ram-from') != "undefined" ||
        typeof getUrlParameter('ram-to') != "undefined" ||
        typeof getUrlParameter('weight-from') != "undefined" ||
        typeof getUrlParameter('weight-to') != "undefined" ||
        typeof getUrlParameter('storage-from') != "undefined" ||
        typeof getUrlParameter('storage-to') != "undefined" ||
        typeof getUrlParameter('battery-from') != "undefined" ||
        typeof getUrlParameter('battery-to') != "undefined" ||
        typeof getUrlParameter('camera-from') != "undefined" ||
        typeof getUrlParameter('camera-to') != "undefined" ||
        typeof getUrlParameter('android-os') != "undefined" ||
        typeof getUrlParameter('apple-os') != "undefined" ||
        typeof getUrlParameter('windows-os') != "undefined" ||
        typeof getUrlParameter('other-os') != "undefined" ||
        typeof getUrlParameter('price-from') != "undefined" ||
        typeof getUrlParameter('price-to') != "undefined" ||
        typeof getUrlParameter('company') != "undefined" ||
        typeof getUrlParameter('network') != "undefined" ||
        typeof getUrlParameter('screenresolution') != "undefined"
        )
    {
        _products_list.html('');
        var _form_input = _search_form.serialize();
        getListing(_form_input);
    }
});

function getListing(_form_input) {
    var _products_list = $("#products");
    $.ajax({
        url: 'product-listing.php',
        data: _form_input,
        type: 'GET',
        async: true,
        success: function(output) {
            _products_list.append(output);
        },
        error: function(status, err) {
            console.log("Error: " + status + err);
        },
        beforeSend: function() {
            loopOverlay();
        },
        complete: function() {
            removeOverlay();
        }
    });
}

var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;
    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

function updatePipsScreenResolution ( value ){
    switch(true)   {
        case (value == 1):value = "1280x720";break;
        case (value == 2):value = "1920x1080";break;
        case (value == 3):value = "2560x1440";break;
        case (value == 4):value = "2960x1440";break;
    }
    return value;
}
