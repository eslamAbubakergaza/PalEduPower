<?php


require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_GET['type_course'])) {

    // receiving the get params
    $type = $_GET['type_course'];



    switch($type){
        case "all":
            $courses_list = $db->getCources();
            $response["error"] = FALSE;
            $response["courses_list"] = $courses_list;
            echo json_encode($response);
            break;
        default :$courses_list = $db->getCourcesByType($type);
            $response["error"] = FALSE;
            $response["courses_list"] = $courses_list;
            echo json_encode($response);

    }

} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}
?>