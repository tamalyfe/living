<?php
if($_SERVER['REQUEST_METHOD']=='POST') {
   $response = array();
   $identifier_recruitment_team = $_POST['identifier_recruitment_team'];
   $name_team = $_POST['name_team'];
   $post_team = $_POST['post_team'];
   $domicile_team = $_POST['domicile_team'];
   $job_description = $_POST['job_description'];
   $experience = $_POST['experience'];
   $certificate = $_POST['certificate'];

   require_once('databaseMySQL.php');
   $sql = "SELECT * FROM tb_recruitment_team WHERE identifier_recruitment_team ='$identifier_recruitment_team'";
   $check = mysqli_fetch_array(mysqli_query($con, $sql));
   
   if(isset($check)) {
     $response["value"] = 0;
     $response["message"] = "Create Recruitment Team, Already";
     echo json_encode($response);
   } else {
     $sql = "INSERT INTO tb_recruitment_team (identifier_recruitment_team, name_team, post_team, domicile_team, job_description, experience, certificate) VALUES('$identifier_recruitment_team', '$name_team', '$post_team', '$domicile_team', '$job_description', '$experience', '$certificate')";
     
     if(mysqli_query($con,$sql)) {
       $response["value"] = 1;
       $response["message"] = "Create Recruitment Team, Success";
       echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "Create Recruitment Team, Fail";
       echo json_encode($response);
     }
   }

   mysqli_close($con);
} else {
  $response["value"] = 0;
  $response["message"] = "Create Recruitment Team, Try Again";
  echo json_encode($response);
}
?>