 <?php
 define('databaseMySQLHost', 'localhost');
 define('databaseMySQLUser', 'id21485821_du_tama_lyfe');
 define('databaseMySQLPassword', '');
 define('databaseMySQLName', 'id21485821_dn_tama_lyfe');

 $con = mysqli_connect(databaseMySQLHost, databaseMySQLUser, databaseMySQLPassword, databaseMySQLName) 
 or die('Unable to Connect');
 ?>