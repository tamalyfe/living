<?php
require_once('databaseMySQL.php');

if($_SERVER['REQUEST_METHOD']=='POST') {
  $response = array();
  $identifier_request_customer = $_POST['identifier_request_customer'];
  $name_customer = $_POST['name_customer'];
  $contact_customer = $_POST['contact_customer'];
  $domicile_customer = $_POST['domicile_customer'];
  $description_request_customer = $_POST['description_request_customer'];
  $location_project = $_POST['location_project'];
  $price_list_project_cash = $_POST['price_list_project_cash'];
  $price_list_project_credit = $_POST['price_list_project_credit'];
  $status_project = $_POST['status_project'];

  $sql = "UPDATE tb_request_customer SET name_customer = '$name_customer', 
  contact_customer = '$contact_customer', domicile_customer = '$domicile_customer', 
  description_request_customer = '$description_request_customer', location_project = '$location_project',    
  price_list_project_cash = '$price_list_project_cash', price_list_project_credit = '$price_list_project_credit',
  status_project = '$status_project' WHERE identifier_request_customer = '$identifier_request_customer'";
  
  if(mysqli_query($con, $sql)) {
    $response["value"] = 1;
    $response["message"] = "Update Request Customer, Success";
    echo json_encode($response);
  } else {
    $response["value"] = 0;
    $response["message"] = "Update Request Customer, Fail";
    echo json_encode($response);
  }
  
  mysqli_close($con);
}
?>