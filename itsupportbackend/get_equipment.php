<?php
require_once "conn.php";

$sql = "SELECT * FROM equipment ORDER BY id DESC";
$result = $conn->query($sql);
$equipment = array();

if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $equipment[] = array(
            'id' => $row['id'],
            'name' => $row['name'],
            'description' => $row['description'],
            'email' => $row['email'],
            'isChecked' => $row['isChecked']
        );
    }
}

header('Content-Type: application/json');
echo json_encode($equipment);
?>