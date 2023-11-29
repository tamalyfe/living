<?php
require_once('databaseMySQL.php');

if($_SERVER['REQUEST_METHOD']=='POST') {
  $search_request_customer = $_POST['search_request_customer'];
  $sql = "SELECT * FROM tb_request_customer where name_customer LIKE '%$search_request_customer%' 
  ORDER BY name_customer ASC";
  $res = mysqli_query($con, $sql);
  $result = array();
  
  while($row = mysqli_fetch_array($res)) {
    array_push($result, array('identifier_request_customer'=>$row[0], 'name_customer'=>$row[1], 
    'contact_customer'=>$row[2], 'domicile_customer'=>$row[3], 'description_request_customer'=>$row[4],
    'location_project'=>$row[5], 'price_list_project_cash'=>$row[6],
    'price_list_project_credit'=>$row[7], 'status_project'=>$row[8]));
  }
  
  echo json_encode(array("value"=>1, "result"=>$result));
  mysqli_close($con);
}
?>