<div class="empty-space"></div>
<div class="empty-space"></div>
<div class="empty-space"></div>

<nav class="navbar navbar-fixed-bottom navbar-inverse">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li><a title="Home" href="<?php echo BASE_URL; ?>">Home</a></li>
            <li><a title="Configuration" href="<?php echo BASE_URL.'configure.php'; ?>">Configuration</a></li>
            <li><a title="Manage Cache" href="<?php echo BASE_URL.'cache.php'; ?>">Manage Cache</a></li>
            <li><a title="Manage Server" href="<?php echo BASE_URL.'server.php'; ?>">Manage Server</a></li>
            <li><a title="Help Me" href="<?php echo BASE_URL.'help.php'; ?>">Help Me</a></li>
            <li><a title="About" href="<?php echo BASE_URL.'about.php'; ?>">About</a></li>
            <li><a title="GitHub" target="_blank" href="https://github.com/mtzagkarakis/crawlerServer">GitHub</a></li>
        </ul>
    </div>
</nav>
<script>
    $(document).ready(function() {
        var _loc = window.location.href;
        $("ul.nav li a").each(function() {
            var _this = $(this);
            if (_this.attr("href") == _loc)
                _this.parent().addClass("active");
        });
    });
</script>