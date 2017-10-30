<?php
    require 'connection.php';
   // $connect = mysqli_connect("localhost", "id3122174_root", "B3t4admin", "id3122174_eventapp_beta");
    
    //$user = "admin";
    //$pass = "admin";
    $user = $_POST["username"];
    $pass = $_POST["password"];
    $statement = mysqli_prepare($connect, "SELECT * FROM users WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "s", $user, $pass);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $user_id, $username, $password, $first_name, $last_name, $email);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
            $response["success"] = true;
            $response["user_id"] = $user_id;
            $response["username"] = $username;
            $response["password"] = $password;
            $response["first_name"] = $first_name;
            $response["last_name"] = $last_name;
            $response["email"] = $email;
        }
    echo json_encode($response);
?>