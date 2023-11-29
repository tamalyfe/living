<?php
require_once('databaseMySQL.php');

if($_SERVER['REQUEST_METHOD']=='GET') {
  $sql = "SELECT * FROM tb_project ORDER BY name_project ASC";
  $res = mysqli_query($con, $sql);
  $result = array();
  
  while($row = mysqli_fetch_array($res)) {
    array_push($result, array('identifier_project'=>$row[0], 'name_project'=>$row[1], 'type_project'=>$row[2],
    'access_project'=>$row[3], 'status_project'=>$row[4], 'location_project'=>$row[5], 
    'price_list_project_cash'=>$row[6], 'price_list_project_credit'=>$row[7], 'promo'=>$row[8], 
    'description_project'=>$row[9], 'bedroom'=>$row[10], 'bathroom'=>$row[11], 'carport'=>$row[12], 
    'building_area'=>$row[13], 'surface_area'=>$row[14], 'facility'=>$row[15], 'name_developer'=>$row[16], 
    'contact_developer'=>$row[17], 'loan_bank'=>$row[18], 'handover'=>$row[19]));
  }
  
  echo json_encode(array("value"=>1, "result"=>$result));
  mysqli_close($con);
}
?>
