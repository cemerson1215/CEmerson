<%-- 
    Document   : newjsp
    Created on : Dec 11, 2017, 12:50:54 PM
    Author     : n0149245
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
<head>
  <script src='https://cloud.tinymce.com/stable/tinymce.min.js'></script>
  <script>
  tinymce.init({
    selector: '#mytextarea'
  });
  </script>
</head>

<body>
<h1>TinyMCE Quick Start Guide</h1>
  <form method="post">
    <textarea id="mytextarea">Hello, World!</textarea>
  </form>
</body>
</html>

