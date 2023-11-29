<?php
require_once('databaseMySQL.php');

if($_SERVER['REQUEST_METHOD']=='POST') {
  $response = array();
  $identifier_request_customer = $_POST['identifier_request_customer'];

  $sql = "DELETE FROM tb_request_customer WHERE identifier_request_customer = '$identifier_request_customer'";
  
  if(mysqli_query($con, $sql)) {
    $response["value"] = 1;
    $response["message"] = "Delete Request Customer, Success";
    echo json_encode($response);
  } else {
    $response["value"] = 0;
    $response["message"] = "Delete Request Customer, Fail";
    echo json_encode($response);
  }
  
  mysqli_close($con);
}
?>