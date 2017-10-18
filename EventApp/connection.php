<?php
$servername = "localhost";
$username = "id3122174_root";  
$password = "B3t4admin";  
$dbname = "id3122174_eventapp_beta";
// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);
// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
?>