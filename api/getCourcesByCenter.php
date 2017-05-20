<?php


require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_GET['center'])) {

    // receiving the get params
    $center = $_GET['center'];




            $courses_list = $db->getCourcesByCenterID($center);
            $response["error"] = FALSE;
            $response["courses_list"] = $courses_list;
            echo json_encode($response);



} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}
?>

