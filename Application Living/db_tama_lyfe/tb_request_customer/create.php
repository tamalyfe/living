<?php
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

   require_once('databaseMySQL.php');
   $sql = "SELECT * FROM  tb_request_customer WHERE identifier_request_customer ='$identifier_request_customer'";
   $check = mysqli_fetch_array(mysqli_query($con, $sql));
   
   if(isset($check)) {
     $response["value"] = 0;
     $response["message"] = "Create Request Customer, Already";
     echo json_encode($response);
   } else {
     $sql = "INSERT INTO tb_request_customer (identifier_request_customer, name_customer, contact_customer, domicile_customer, description_request_customer, location_project, price_list_project_cash, price_list_project_credit, status_project) VALUES('$identifier_request_customer', '$name_customer', '$contact_customer', '$domicile_customer', '$description_request_customer', '$location_project', '$price_list_project_cash', '$price_list_project_credit', '$status_project')";
     
     if(mysqli_query($con,$sql)) {
       $response["value"] = 1;
       $response["message"] = "Create Request Customer, Success";
       echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "Create Request Customer, Fail";
       echo json_encode($response);
     }
   }

   mysqli_close($con);
} else {
  $response["value"] = 0;
  $response["message"] = "Create Request Customer, Try Again";
  echo json_encode($response);
}
?>