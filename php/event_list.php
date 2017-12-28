<?php
    $con = mysqli_connect("localhost", "id3122174_root", "B3t4admin", "id3122174_eventapp_beta");

    if(!$con){
        die("connection failed: " . mysqli_connect_error());
    }

    $sql = "SELECT * From Events";
    $result = mysqli_query($con, $sql);
    
if ($result->num_rows >0) {
 
 
 while($row[] = $result->fetch_assoc()) {
 
 $tem = $row;
 
 $json = json_encode($tem);
 
 
 }
 
} else {
 echo "No Results Found.";
}
 echo $json;
$conn->close();
?>