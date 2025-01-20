<?php
require_once "conn.php";

if(isset($_POST['id']) && isset($_POST['isChecked'])) {
    $id = $_POST['id'];
    $isChecked = $_POST['isChecked'];
    
    $sql = "UPDATE equipment SET isChecked = ? WHERE id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ii", $isChecked, $id);
    
    if($stmt->execute()) {
        echo "success";
    } else {
        echo "error";
    }
    
    $stmt->close();
}
$conn->close();
?>