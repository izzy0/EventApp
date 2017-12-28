<?php
    $connect = mysqli_connect("localhost", "id3122174_root", "B3t4admin", "id3122174_eventapp_beta");

    $eventname = $_POST["eventname"];
    $date = $_POST["date"];
    $location = $_POST["location"];
    $_fkuserid = $_POST["user"];
    
     function registerEvent() {
        global $connect, $_fkuserid, $eventname, $date, $location;
        
        $statement = mysqli_prepare($connect, "INSERT INTO Events (_fkuserid, eventname, date, location) VALUES (?, ?, ?, ?)");
        // var_dump($statement);
        mysqli_stmt_bind_param($statement, "ssss", $_fkuserid, $eventname, $date, $location);
        //  var_dump($statement);
        mysqli_stmt_execute($statement);
        // var_dump($statement);
        // echo($_fkuserid);
        mysqli_stmt_close($statement);
        // var_dump($statement);

    }

    
    $response = array();
    $response["success"] = false;
    if($statement === NULL){
        registerEvent();
        $response["success"] = true;
    }
    
    echo json_encode($response);
?>