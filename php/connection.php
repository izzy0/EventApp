<?php
$servername = "localhost"; //replace it with your database server name
$username = "id3122174_root";  
$password = "B3t4admin";  
$dbname = "db_client";
// Create connection
$connect = new mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$connect) {
    die("Connection failed: " . mysqli_connect_error());
}
?>