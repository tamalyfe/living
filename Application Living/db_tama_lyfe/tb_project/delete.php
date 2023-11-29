<?php
require_once('databaseMySQL.php');

if($_SERVER['REQUEST_METHOD']=='POST') {
  $response = array();
  $identifier_project = $_POST['identifier_project'];

  $sql = "DELETE FROM tb_project WHERE identifier_project = '$identifier_project'";
  
  if(mysqli_query($con, $sql)) {
    $response["value"] = 1;
    $response["message"] = "Delete Project, Success";
    echo json_encode($response);
  } else {
    $response["value"] = 0;
    $response["message"] = "Delete Project, Fail";
    echo json_encode($response);
  }
  
  mysqli_close($con);
}
?>