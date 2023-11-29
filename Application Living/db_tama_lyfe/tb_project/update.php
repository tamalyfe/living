<?php
require_once('databaseMySQL.php');

if($_SERVER['REQUEST_METHOD']=='POST') {
  $response = array();
  $identifier_project = $_POST['identifier_project'];
  $name_project = $_POST['name_project'];
  $type_project = $_POST['type_project'];
  $access_project = $_POST['access_project'];
  $status_project = $_POST['status_project'];
  $location_project = $_POST['location_project'];
  $price_list_project_cash = $_POST['price_list_project_cash'];
  $price_list_project_credit = $_POST['price_list_project_credit'];
  $promo = $_POST['promo'];
  $description_project = $_POST['description_project'];
  $bedroom = $_POST['bedroom'];
  $bathroom = $_POST['bathroom'];
  $carport = $_POST['carport'];
  $building_area = $_POST['building_area'];
  $surface_area = $_POST['surface_area'];
  $facility = $_POST['facility'];
  $name_developer = $_POST['name_developer'];
  $contact_developer = $_POST['contact_developer'];
  $loan_bank = $_POST['loan_bank'];
  $handover = $_POST['handover'];

  $sql = "UPDATE tb_project SET name_project = '$name_project', type_project = '$type_project', 
  access_project = '$access_project', status_project = '$status_project', 
  location_project = '$location_project', price_list_project_cash = '$price_list_project_cash', 
  price_list_project_credit = '$price_list_project_credit', promo = '$promo', 
  description_project = '$description_project', bedroom = '$bedroom', bathroom = '$bathroom', 
  carport = '$carport', building_area = '$building_area', surface_area = '$surface_area', facility = '$facility',
  name_developer = '$name_developer', contact_developer = '$contact_developer', loan_bank = '$loan_bank', 
  handover = '$handover' WHERE identifier_project = '$identifier_project'";
  
  if(mysqli_query($con, $sql)) {
    $response["value"] = 1;
    $response["message"] = "Update Project, Success";
    echo json_encode($response);
  } else {
    $response["value"] = 0;
    $response["message"] = "Update Project, Fail";
    echo json_encode($response);
  }
  
  mysqli_close($con);
}
?>