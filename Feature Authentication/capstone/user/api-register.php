<?php
    include "koneksi.php";
    $userName = $_POST["username"];
    $password = $_POST["password"];
    $alamat = $_POST["alamat"];

    $queryRegister = "SELECT * FROM user WHERE username = '$userName'";
    $msql = mysqli_query($koneksi, $queryRegister);
    $result = mysqli_num_rows($msql);
    if(!empty($userName) && !empty($password)&&!empty($alamat)){
        if($result == 0){
            $regis = "INSERT INTO user(username, password, alamat) VALUES ('$userName', '$password', '$alamat')";
            $msqlRegis = mysqli_query($koneksi, $regis);
            echo "Daftar berhasil";
        }else{
            echo "username sudah digunakan";
        }
    }else{
        echo "Semua data harus diisi";
    }
?>