<?php


require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_GET['type_center'])) {

    // receiving the get params
    $type = $_GET['type_center'];



    switch($type){
        case "all":
            $centers_list = $db->getCentersList();
            $response["error"] = FALSE;
            $response["centers_list"] = $centers_list;
            echo json_encode($response);
            break;
        default :
            $centers_list = $db->getCentersListByType($type);
            $response["error"] = FALSE;
            $response["centers_list"] = $centers_list;
            echo json_encode($response);


    }

} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}
?>

