<?php


require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_GET['ID'])) {

    // receiving the get params
    $id = $_GET['ID'];


    $center = $db->getCenterByID($id);

    if ($center != false) {
        // center is found
        $response["error"] = FALSE;

        $response["center"]["name"] = $center["Name"];
        //$response["center"]["email"] = $center["email"];
        $response["center"]["Adrress"] = $center["Adrress"];
        $response["center"]["latitude"] = $center["latitude"];
        $response["center"]["longitude"] = $center["longitude"];
        $response["center"]["type"] = $center["type"];

        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Center Not Found";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "You should have center id";
    echo json_encode($response);
}
?>

