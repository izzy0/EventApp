// <?PHP
//     $connect = mysqli_connect("localhost", "id3122174_root", "B3t4admin", "id3122174_eventapp_beta");

//     $response = array();
//     $response["success"] = false;
//     $response["image"] = $_POST["image"];
    
// if(isset($_POST["image"])){
        
        // $now = DateTime::createFromFormat('U.u', mircotime(true));
        // $id = $now->format('YmdHisu');
        
        // $upload_folder = "uploads/";
        // $path = $upload_folder.$id.'jpeg';
        // $image = $_POST['image'];
        
        // $username = 14;
        // $date = '11/21/2017';
        // $photo = $path;
        
        // $sql = "INSERT INTO Photos(username, compressed_photo, date) VALUES('$username', '$photo', '$date')";
        // mysqli_query($connect, $sql);
        // $response["sql"] = $sql;
        
        //file_put_contents($path, base64_decode($image);
        
        // if(file_put_contents($path, base64_decode($image)) != false){
        //     $response["success"] = true;
        // }else{
        //     $response["success"] = false;
        // }
        
//     }else{
//         echo json_encode($response);
//     }
// ?>

<?php

	require_once 'dbDetails.php';

	$path = 'uploads/';
	$server_ip = gethostbyname(gethostname());
	$url = 'http://'.$server_ip.'/EventApp/'.$path;

	$response = array();
	
	if($_SERVER['REQUEST_METHOD'] == 'POST'){
		if(isset($_POST['name']) and isset($_FILES['image']['name'])){
			
			$con = mysqli_connect(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME) or die('unable to connect');

			$name = $_POST['name'];
			$fileinfo = pathinfo($_FILES['image']['name']);
			
			$extension = $fileinfo['extension'];
			$file_url = $url . getFileName() . '.' .$extension;
			$file_path = $path . getFileName() . '.' . $extension;

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
		$sql = "SELECT max(id) as id FROM Photos";
		$result = mysqli_fetch_array(mysqli_query($con, $sql));
		mysqli_close($con);
		
		if($result['id'] == null){
			return 1;
		}else{
			return ++$result['id'];
		}
	}

?>