<?php


require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_GET['ID'])) {

    // receiving the get params
    $id = $_GET['ID'];


    $course = $db->getCourcesByID($id);

    if ($course != false) {
        // center is found
        $response["error"] = FALSE;
        $response["course"]["id"] = $id;
        $response["course"]["name"] = $course["Name"];
        $response["course"]["center_id"] = $course["center_id"];
        $response["course"]["type"] = $course["type"];
        $response["course"]["img"] = $course["img"];
        $response["course"]["description"] = $course["description"];
        $response["course"]["topics"] = $db->getCourceTopic($id);;


        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Course Not Found";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "You should have course id";
    echo json_encode($response);
}
?>

