<?php
    $connect = mysqli_connect("localhost", "id3122174_root", "B3t4admin", "id3122174_eventapp_beta");
    
    //$user = "admin";
    //$pass = "admin";
    $user = $_POST["username"];
    var_dump($user);
    $pass = $_POST["password"];
    //var_dump($pass);
    $statement = mysqli_prepare($connect, "SELECT * FROM Users WHERE username = ? AND password = ?");
   // var_dump($statement);
    mysqli_stmt_bind_param($statement, "ss", $user, $pass);
    mysqli_stmt_bind_result($statement, $user_id, $username, $password, $first_name, $last_name, $email);
    mysqli_stmt_execute($statement);
    mysqli_stmt_close($statement);

    
    $response = array();
  //  var_dump($response);
    $response["success"] = false;
    
    while(mysqli_stmt_fetch($statement)){
            $response["success"] = true;  
            $response["__pkuserid"] = $user_id;
            $response["username"] = $username;
            $response["password"] = $password;
            $response["first_name"] = $first_name;
            $response["last_name"] = $last_name;
            $response["email"] = $email;
        }
    echo json_encode($response);
    // header("location: cq7243tk.000webhostapp.com/#features");
    if( $response["success"] == true){
         echo "<script>window.location = 'cq7243tk.000webhostapp.com/#features';</scrpit>";
    }else{
        echo mysqli_error ($connect);
    }
?>
