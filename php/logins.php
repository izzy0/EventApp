<?php
    $con = mysqli_connect("localhost", "id3122174_root", "B3t4admin", "id3122174_eventapp_beta");

    $user = $_POST["username"];
    $pass = $_POST["password"];

    $statement = mysqli_prepare($con, "SELECT __pkuserid, username, password, first_name, last_name, email FROM Users WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $user);
    mysqli_stmt_execute($statement);
    mysqli_stmt_bind_result($statement, $__pkuserid, $username, $password, $first_name, $last_name, $email);
    
    $response = array();
    $response["success"] = false;
    
    while(mysqli_stmt_fetch($statement)){
        if (password_verify($pass, $password)) {
            $response["success"] = true;  
            $response["username"] = $username;
            $response["password"] = $pass;
            $response["user_id"] = $__pkuserid;
            $response["first_name"] = $first_name;
            $response["last_name"] = $last_name;
            $response["email"] = $email;
        }
    }
    echo json_encode($response);
    
     if( $response["success"] == true){
         echo "<script>window.location = 'cq7243tk.000webhostapp.com/#features';</scrpit>";
    }else{
        echo mysqli_error ($connect);
    }
?>