<?php
    //require("password.php");
    $con = mysqli_connect("localhost", "id3122174_root", "B3t4admin", "id3122174_eventapp_beta");
    //$user = "admin";
    //$pass = "admin";
    $user = $_POST["username"];
    $pass = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT * FROM Users WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $user);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $__pkuserid, , $username, $password, $first_name, $last_name, $email, $personal_site_url, $isadmin, $ishost);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        if (password_verify($pass, $password)) {
            $response["success"] = true;
            $response["username"] = $username;
            $response["__pkuserid"] = $__pkuserid;
        }
    }
    echo json_encode($response);
?>