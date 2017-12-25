<?php
	require_once 'dbDetails.php';
	
	$con = mysqli_connect(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME) or die('unable to connect');
	
	if(!$con){
		echo "unable to connect";
        die("connection failed: " . mysqli_connect_error());
    }
	
	$sql = "SELECT * From Photos";
	
	$result = mysqli_query($con, $sql);
	if($result){
		
		while($row=mysqli_fetch_array($result)){
			$flag[]=$row;
		}
		print(json_encode($flag));
	
	}
	
	mysqli_close($con);
?>