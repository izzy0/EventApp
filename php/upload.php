<?php

	require_once 'dbDetails.php';

	$path = 'uploads/';
	$server_ip = 'cq7243tk.000webhostapp.com';
	$url = 'http://'.$server_ip.'/'.$path;
	
// 	$server_ip = gethostbyname(gethostname());
// 	$url = 'http://'.$server_ip.'/EventApp/'.$path;

	$response = array();
	
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		if(isset($_POST['name']) and isset($_FILES['image']['name'])){
			
			$con = mysqli_connect(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME) or die('unable to connect');

			$name = $_POST['name'];
			$fileinfo = pathinfo($_FILES['image']['name']);
			
			$extension = $fileinfo['extension'];
			$filename = microtime(true) . '.' .$extension;
			$file_url = $url . $filename;
			$file_path = $path . $filename;

			try{
				move_uploaded_file($_FILES['image']['tmp_name'], $file_path);
				$sql = "INSERT INTO Photos (username,photo_path) VALUES ('$name','$file_url')";
				
				if(mysqli_query($con, $sql)){
					$response['url'] = $file_url;
					$response['name'] = $name;
				}
				
				mysqli_close($con);

			}catch(Exception $e){
				$response['error'] = false;
				$response['message'] = $e-getMessage();
			}
		}
		
		echo json_encode($response);
	}else{
	    echo "NO POST METHOD";
    }

	
	function getFileName(){
		$con = mysqli_connect(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME) or die('unable to connect');
		
// 		$sql = "SELECT * FROM Photos";
// 		$result = mysql_num_rows(mysqli_query($con, $sql));
// 		mysqli_close($con);
// 		echo $result;
// 		if($result == null){
// 			return 1;
// 		}else{
// 			return ++$result;
// 		}
		
		$sql = "SELECT max(__pkphotoid) as __pkphotoid FROM Photos";
		$result = mysqli_fetch_array(mysqli_query($con, $sql));
		mysqli_close($con);
// 		var_dump($result);
		echo $result[0];
		if($result[0] == null){
			return 1;
		}else{
			return ++$result[0];
		}
	}

?>