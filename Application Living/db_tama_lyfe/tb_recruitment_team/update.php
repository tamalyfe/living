<?php
require_once('databaseMySQL.php');

if($_SERVER['REQUEST_METHOD']=='POST') {
  $response = array();
  $identifier_recruitment_team = $_POST['identifier_recruitment_team'];
  $name_team = $_POST['name_team'];
  $post_team = $_POST['post_team'];
  $domicile_team = $_POST['domicile_team'];
  $job_description = $_POST['job_description'];
  $experience = $_POST['experience'];
  $certificate = $_POST['certificate'];

  $sql = "UPDATE tb_recruitment_team SET name_team = '$name_team', post_team = '$post_team', 
  domicile_team = '$domicile_team', job_description = '$job_description', experience = '$experience',    
  certificate = '$certificate' WHERE identifier_recruitment_team = '$identifier_recruitment_team'";
  
  if(mysqli_query($con, $sql)) {
    $response["value"] = 1;
    $response["message"] = "Update Recruitment Team, Success";
    echo json_encode($response);
  } else {
    $response["value"] = 0;
    $response["message"] = "Update Recruitment Team, Fail";
    echo json_encode($response);
  }
  
  mysqli_close($con);
}
?>