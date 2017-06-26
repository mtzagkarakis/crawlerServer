<?php require_once('php-lib/db.php'); ?>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="lib/css/bootstrap.min.css">
    <link rel="stylesheet" href="lib/css/style.css">
    <link rel="stylesheet" href="lib/css/highlight.9.11.0.min.css">
    <script src="lib/js/jquery-3.2.1.min.js"></script>
    <script src="lib/js/highlight.9.11.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('pre code').each(function(i, block) {
                hljs.highlightBlock(block);
            });
        });
    </script>
    <title>Help Me</title>
    <link rel="shortcut icon" type="image/png" href="img/favicon.png"/>
</head>
    <body>
        <div class="container">
            <div class="empty-space"></div>
            <div id="logo">
                <a href="<?php echo BASE_URL;?>" title="Home"><img src="img/coogle-logo.png"></a>
            </div>
            <div class="empty-space"></div>
            <h4>CSS Selectors</h4>
            <div class="empty-space"></div>
            <div class="table-responsive">
                <table class="table table-hover table-bordered">
                    <tbody>
                        <tr><th style="width:20%">Selector</th><th style="width:25%">Example</th><th>Selects</th></tr>
                        <tr>
                            <td><pre><code class="css">*</pre></code></td>
                            <td><pre><code class="js">$("*")</pre></code></td>
                            <td><pre><code class="html">All elements</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">#<em>id</em></pre></code></td>
                            <td><pre><code class="js">$("#lastname")</pre></code></td>
                            <td><pre><code class="html">The element with id="lastname"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">.<em>class</em></pre></code></td>
                            <td><pre><code class="js">$(".intro")</pre></code></td>
                            <td><pre><code class="html">All elements with class="intro"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">.<em>class,</em>.<em>class</em></pre></code></td>
                            <td><pre><code class="js">$(".intro,.demo")</pre></code></td>
                            <td><pre><code class="html">All elements with the class "intro" or "demo"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css"><em>element</em></pre></code></td>
                            <td><pre><code class="js">$("p")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css"><em>el1</em>,<em>el2</em>,<em>el3</em></pre></code></td>
                            <td><pre><code class="js">$("h1,div,p")</pre></code></td>
                            <td><pre><code class="html">All &lt;h1&gt;, &lt;div&gt; and &lt;p&gt; elements</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:first</pre></code></td>
                            <td><pre><code class="js">$("p:first")</pre></code></td>
                            <td><pre><code class="html">The first &lt;p&gt; element</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:last</pre></code></td>
                            <td><pre><code class="js">$("p:last")</pre></code></td>
                            <td><pre><code class="html">The last &lt;p&gt; element</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:even</pre></code></td>
                            <td><pre><code class="js">$("tr:even")</pre></code></td>
                            <td><pre><code class="html">All even &lt;tr&gt; elements</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:odd</pre></code></td>
                            <td><pre><code class="js">$("tr:odd")</pre></code></td>
                            <td><pre><code class="html">All odd &lt;tr&gt; elements</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:first-child</pre></code></td>
                            <td><pre><code class="js">$("p:first-child")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are the first child of their parent</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:first-of-type</pre></code></td>
                            <td><pre><code class="js">$("p:first-of-type")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are the first &lt;p&gt; element of their parent</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:last-child</pre></code></td>
                            <td><pre><code class="js">$("p:last-child")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are the last child of their parent</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:last-of-type</pre></code></td>
                            <td><pre><code class="js">$("p:last-of-type")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are the last &lt;p&gt; element of their parent</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:nth-child(<em>n</em>)</pre></code></td>
                            <td><pre><code class="js">$("p:nth-child(2)")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are the 2nd child of their parent</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:nth-last-child(<em>n</em>)</pre></code></td>
                            <td><pre><code class="js">$("p:nth-last-child(2)")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are the 2nd child of their parent, counting from the last child</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:nth-of-type(<em>n</em>)</pre></code></td>
                            <td><pre><code class="js">$("p:nth-of-type(2)")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are the 2nd &lt;p&gt; element of their parent</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:nth-last-of-type(<em>n</em>)</pre></code></td>
                            <td><pre><code class="js">$("p:nth-last-of-type(2)")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are the 2nd &lt;p&gt; element of their parent, counting from the last child</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:only-child</pre></code></td>
                            <td><pre><code class="js">$("p:only-child")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are the only child of their parent</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:only-of-type</pre></code></td>
                            <td><pre><code class="js">$("p:only-of-type")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are the only child, of its type, of their parent</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">parent &gt; child</pre></code></td>
                            <td><pre><code class="js">$("div &gt; p")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are a direct child of a &lt;div&gt; element</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">parent descendant</pre></code></td>
                            <td><pre><code class="js">$("div p")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are descendants of a &lt;div&gt; element</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">element + next</pre></code></td>
                            <td><pre><code class="js">$("div + p")</pre></code></td>
                            <td><pre><code class="html">The &lt;p&gt; element that are next to each &lt;div&gt; elements</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">element ~ siblings</pre></code></td>
                            <td><pre><code class="js">$("div ~ p")</pre></code></td>
                            <td><pre><code class="html">All &lt;p&gt; elements that are siblings of a &lt;div&gt; element</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:eq(<em>index</em>)</pre></code></td>
                            <td><pre><code class="js">$("ul li:eq(3)")</pre></code></td>
                            <td><pre><code class="html">The fourth element in a list (index starts at 0)</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:gt(<em>no</em>)</pre></code></td>
                            <td><pre><code class="js">$("ul li:gt(3)")</pre></code></td>
                            <td><pre><code class="html">List elements with an index greater than 3</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:lt(<em>no</em>)</pre></code></td>
                            <td><pre><code class="js">$("ul li:lt(3)")</pre></code></td>
                            <td><pre><code class="html">List elements with an index less than 3</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:not(<em>selector</em>)</pre></code></td>
                            <td><pre><code class="js">$("input:not(:empty)")</pre></code></td>
                            <td><pre><code class="html">All input elements that are not empty</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:focus</pre></code></td>
                            <td><pre><code class="js">$(":focus")</pre></code></td>
                            <td><pre><code class="html">The element that currently has focus</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:contains(<em>text</em>)</pre></code></td>
                            <td><pre><code class="js">$(":contains('Hello')")</pre></code></td>
                            <td><pre><code class="html">All elements which contains the text "Hello"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:has(<em>selector</em>)</pre></code></td>
                            <td><pre><code class="js">$("div:has(p)")</pre></code></td>
                            <td><pre><code class="html">All &lt;div&gt; elements that have a &lt;p&gt; element</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:empty</pre></code></td>
                            <td><pre><code class="js">$(":empty")</pre></code></td>
                            <td><pre><code class="html">All elements that are empty</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:parent</pre></code></td>
                            <td><pre><code class="js">$(":parent")</pre></code></td>
                            <td><pre><code class="html">All elements that are a parent of another element</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:hidden</pre></code></td>
                            <td><pre><code class="js">$("p:hidden")</pre></code></td>
                            <td><pre><code class="html">All hidden &lt;p&gt; elements</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:visible</pre></code></td>
                            <td><pre><code class="js">$("table:visible")</pre></code></td>
                            <td><pre><code class="html">All visible tables</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">[<em>attribute</em>]</pre></code></td>
                            <td><pre><code class="js">$("[href]")</pre></code></td>
                            <td><pre><code class="html">All elements with a href attribute</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">[<em>attribute</em>=<em>value</em>]</pre></code></td>
                            <td><pre><code class="js">$("[href='default.htm']")</pre></code></td>
                            <td><pre><code class="html">All elements with a href attribute value equal to "default.htm"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">[<em>attribute</em>!=<em>value</em>]</pre></code></td>
                            <td><pre><code class="js">$("[href!='default.htm']")</pre></code></td>
                            <td><pre><code class="html">All elements with a href attribute value not equal to "default.htm"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">[<em>attribute</em>$=<em>value</em>]</pre></code></td>
                            <td><pre><code class="js">$("[href$='.jpg']")</pre></code></td>
                            <td><pre><code class="html">All elements with a href attribute value ending with ".jpg"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">[<i>attribute</i>|=<i>value</i>]</pre></code></td>
                            <td><pre><code class="js">$("[title|='Tomorrow']")</pre></code></td>
                            <td><pre><code class="html">All elements with a title attribute value equal to 'Tomorrow', or starting with 'Tomorrow' followed by a hyphen</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">[<i>attribute</i>^=<i>value</i>]</pre></code></td>
                            <td><pre><code class="js">$("[title^='Tom']")</pre></code></td>
                            <td><pre><code class="html">All elements with a title attribute value starting with "Tom"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">[<i>attribute</i>~=<i>value</i>]</pre></code></td>
                            <td><pre><code class="js">$("[title~='hello']")</pre></code></td>
                            <td><pre><code class="html">All elements with a title attribute value containing the specific word "hello"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">[<i>attribute*</i>=<i>value</i>]</pre></code></td>
                            <td><pre><code class="js">$("[title*='hello']")</pre></code></td>
                            <td><pre><code class="html">All elements with a title attribute value containing the word "hello"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:input</pre></code></td>
                            <td><pre><code class="js">$(":input")</pre></code></td>
                            <td><pre><code class="html">All input elements</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:text</pre></code></td>
                            <td><pre><code class="js">$(":text")</pre></code></td>
                            <td><pre><code class="html">All input elements with type="text"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:password</pre></code></td>
                            <td><pre><code class="js">$(":password")</pre></code></td>
                            <td><pre><code class="html">All input elements with type="password"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:radio</pre></code></td>
                            <td><pre><code class="js">$(":radio")</pre></code></td>
                            <td><pre><code class="html">All input elements with type="radio"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:checkbox</pre></code></td>
                            <td><pre><code class="js">$(":checkbox")</pre></code></td>
                            <td><pre><code class="html">All input elements with type="checkbox"</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:enabled</pre></code></td>
                            <td><pre><code class="js">$(":enabled")</pre></code></td>
                            <td><pre><code class="html">All enabled input elements</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:disabled</pre></code></td>
                            <td><pre><code class="js">$(":disabled")</pre></code></td>
                            <td><pre><code class="html">All disabled input elements</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:selected</pre></code></td>
                            <td><pre><code class="js">$(":selected")</pre></code></td>
                            <td><pre><code class="html">All selected input elements</pre></code></td>
                        </tr>
                        <tr>
                            <td><pre><code class="css">:checked</pre></code></td>
                            <td><pre><code class="js">$(":checked")</pre></code></td>
                            <td><pre><code class="html">All checked input elements</pre></code></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="empty-space"></div>
        </div>
        <?php include 'footer.php'; ?>
    </body>
</html>