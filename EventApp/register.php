<?php
    //require("password.php");
    $connect = mysqli_connect("localhost", "id3122174_root", "B3t4admin", "id3122174_eventapp_beta");

    $first_name = $_POST["first_name"];
    $last_name = $_POST["last_name"];
    $username = $_POST["username"];
    $password = $_POST["password"];
     function registerUser() {
        global $connect, $first_name, $last_name, $username, $password;
        $passwordHash = password_hash($password, PASSWORD_DEFAULT);
        $statement = mysqli_prepare($connect, "INSERT INTO Users (first_name, last_name, username, password) VALUES (?, ?, ?, ?)");
        mysqli_stmt_bind_param($statement, "ssss", $first_name, $last_name, $username, $passwordHash);
        mysqli_stmt_execute($statement);
        mysqli_stmt_close($statement);
    }
    function usernameAvailable() {
        global $connect, $username;
        $statement = mysqli_prepare($connect, "SELECT * FROM Users WHERE username = ?");
        mysqli_stmt_bind_param($statement, "s", $username);
        mysqli_stmt_execute($statement);
        mysqli_stmt_store_result($statement);
        $count = mysqli_stmt_num_rows($statement);
        mysqli_stmt_close($statement);
        if ($count < 1){
            return true;
        }else {
            return false;
        }
    }
    $response = array();
    $response["success"] = false;
    if (usernameAvailable()){
        registerUser();
        $response["success"] = true;
    }

    echo json_encode($response);
?>