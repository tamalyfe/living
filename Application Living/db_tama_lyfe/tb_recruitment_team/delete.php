<?php
require_once('databaseMySQL.php');

if($_SERVER['REQUEST_METHOD']=='POST') {
  $response = array();
  $identifier_recruitment_team = $_POST['identifier_recruitment_team'];

  $sql = "DELETE FROM tb_recruitment_team WHERE identifier_recruitment_team = '$identifier_recruitment_team'";
  
  if(mysqli_query($con, $sql)) {
    $response["value"] = 1;
    $response["message"] = "Delete Recruitment Team, Success";
    echo json_encode($response);
  } else {
    $response["value"] = 0;
    $response["message"] = "Delete Recruitment Team, Fail";
    echo json_encode($response);
  }
  
  mysqli_close($con);
}
?>