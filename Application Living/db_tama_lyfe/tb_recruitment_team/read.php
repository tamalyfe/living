<?php
require_once('databaseMySQL.php');

if($_SERVER['REQUEST_METHOD']=='GET') {
  $sql = "SELECT * FROM tb_recruitment_team ORDER BY name_team ASC";
  $res = mysqli_query($con, $sql);
  $result = array();
  
  while($row = mysqli_fetch_array($res)) {
    array_push($result, array('identifier_recruitment_team'=>$row[0], 'name_team'=>$row[1], 
    'post_team'=>$row[2], 'domicile_team'=>$row[3], 'job_description'=>$row[4],
    'experience'=>$row[5], 'certificate'=>$row[6]));
  }
  
  echo json_encode(array("value"=>1, "result"=>$result));
  mysqli_close($con);
}
?>
