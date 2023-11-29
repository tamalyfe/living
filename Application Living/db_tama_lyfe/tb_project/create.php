<?php
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

   require_once('databaseMySQL.php');
   $sql = "SELECT * FROM  tb_project WHERE identifier_project ='$identifier_project'";
   $check = mysqli_fetch_array(mysqli_query($con, $sql));
   
   if(isset($check)) {
     $response["value"] = 0;
     $response["message"] = "Create Project, Already";
     echo json_encode($response);
   } else {
     $sql = "INSERT INTO tb_project (identifier_project, name_project, type_project, access_project, status_project, location_project, price_list_project_cash, price_list_project_credit, promo, description_project, bedroom, bathroom, carport, building_area, surface_area, facility, name_developer, contact_developer, loan_bank, handover) VALUES('$identifier_project', '$name_project', '$type_project', '$access_project', '$status_project', '$location_project', '$price_list_project_cash', '$price_list_project_credit', '$promo', '$description_project', '$bedroom', '$bathroom', '$carport', '$building_area', '$surface_area', '$facility', '$name_developer', '$contact_developer', '$loan_bank', '$handover')";
     
     if(mysqli_query($con,$sql)) {
       $response["value"] = 1;
       $response["message"] = "Create Project, Success";
       echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "Create Project, Fail";
       echo json_encode($response);
     }
   }

   mysqli_close($con);
} else {
  $response["value"] = 0;
  $response["message"] = "Create Project, Try Again";
  echo json_encode($response);
}
?>