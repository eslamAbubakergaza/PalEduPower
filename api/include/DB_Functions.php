<?php


class DB_Functions {

    private $conn;

    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
        $this->conn = $db->connect();
    }

    // destructor
    function __destruct() {
        
    }

    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($name, $email, $password,$phone,$age) {
        $uuid = uniqid('', true);
        $hash = $this->hashSSHA($password);
        $encrypted_password = $hash["encrypted"]; // encrypted password
        $salt = $hash["salt"]; // salt

        $stmt = $this->conn->prepare("INSERT INTO users(unique_id, name, email,phone,age, encrypted_password, salt, created_at) VALUES(?, ?, ?, ?, ?, ?, ?, NOW())");
        $stmt->bind_param("ssssdss",$uuid, $name, $email,$phone,$age, $encrypted_password, $salt);
        $result = $stmt->execute();
        $stmt->close();

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ?");
            $stmt->bind_param("s", $email);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $user;
        } else {
            return false;
        }
    }

    /**
     * Get user by email and password
     */
    public function getUserByEmailAndPassword($email, $password) {

        $stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ?");

        $stmt->bind_param("s", $email);

        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            // verifying user password
            $salt = $user['salt'];
            $encrypted_password = $user['encrypted_password'];
            $hash = $this->checkhashSSHA($salt, $password);
            // check for password equality
            if ($encrypted_password == $hash) {
                // user authentication details are correct
                return $user;
            }
        } else {
            return NULL;
        }
    }

    /**
     * Check user is existed or not
     */
    public function isUserExisted($email) {
        $stmt = $this->conn->prepare("SELECT email from users WHERE email = ?");

        $stmt->bind_param("s", $email);

        $stmt->execute();

        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }

    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
    public function hashSSHA($password) {

        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }

    public function checkhashSSHA($salt, $password) {

        $hash = base64_encode(sha1($password . $salt, true) . $salt);

        return $hash;
    }

    public function getCentersList(){
        $stmt = $this->conn->prepare("SELECT * FROM edu_centers");



        $centers_arr=array();
        if ($stmt->execute()) {
            $result=$stmt->get_result();
            while($center = $result->fetch_assoc()) {
                $centers_arr[]=$center;
            }
            $stmt->close();
            return $centers_arr;
        } else {
            return NULL;
        }
    }

    public function getCentersListByType($type){
        $stmt = $this->conn->prepare("SELECT * FROM edu_centers where type=?");
        $stmt->bind_param("s", $type);


        $centers_arr=array();
        if ($stmt->execute()) {
            $result=$stmt->get_result();
            while($center = $result->fetch_assoc()) {
                $centers_arr[]=$center;
            }

            $stmt->close();
            return $centers_arr;
        } else {
            return NULL;
        }
    }

    public function getCenterByID($id){
        $stmt = $this->conn->prepare("SELECT * FROM edu_centers where ID=?");
        $stmt->bind_param("s", $id);



        if ($stmt->execute()) {
            $result=$stmt->get_result();
            if($center = $result->fetch_assoc()) {

                $stmt->close();
                return $center;
            }else{
                return NULL;
            }

        } else {
            return NULL;
        }
    }

    public function getCources(){
        $stmt = $this->conn->prepare("SELECT * FROM cources");


        $courses_arr=array();
        if ($stmt->execute()) {
            $result=$stmt->get_result();
            while($course = $result->fetch_assoc()) {
                $courses_arr[]=$course;
            }

            $stmt->close();
            return $courses_arr;
        } else {
            return NULL;
        }
    }

    public function getCourcesByType($type){
        $stmt = $this->conn->prepare("SELECT * FROM cources where type=?");
        $stmt->bind_param("s", $type);


        $courses_arr=array();
        if ($stmt->execute()) {
            $result=$stmt->get_result();
            while($course = $result->fetch_assoc()) {
                $courses_arr[]=$course;
            }

            $stmt->close();
            return $courses_arr;
        } else {
            return NULL;
        }
    }
    public function getCourcesByCenterID($id){
        $stmt = $this->conn->prepare("SELECT * FROM cources where center_id=?");
        $stmt->bind_param("s", $id);


        $courses_arr=array();
        if ($stmt->execute()) {
            $result=$stmt->get_result();
            while($course = $result->fetch_assoc()) {
                $courses_arr[]=$course;
            }

            $stmt->close();
            return $courses_arr;
        } else {
            return NULL;
        }
    }

    public function getCourcesByID($id){
        $stmt = $this->conn->prepare("SELECT * FROM cources where ID=?");
        $stmt->bind_param("s", $id);



        if ($stmt->execute()) {
            $result=$stmt->get_result();
            $course = $result->fetch_assoc();


            $stmt->close();
            return $course;
        } else {
            return NULL;
        }
    }

    public function getCourceTopic($id){
        $stmt = $this->conn->prepare("SELECT * FROM course_topic where course_id=?");
        $stmt->bind_param("s", $id);


        $course_topics=array();
        if ($stmt->execute()) {
            $result=$stmt->get_result();
            while ($topic = $result->fetch_assoc()){
                $course_topics[]=$topic;
            }


            $stmt->close();
            return $course_topics;
        } else {
            return NULL;
        }
    }
}

?>