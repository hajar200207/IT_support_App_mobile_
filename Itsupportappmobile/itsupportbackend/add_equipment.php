<?php
require_once "conn.php";
require_once "validate.php";

if (isset($_POST['name']) && isset($_POST['description']) && isset($_POST['email'])) {
    $name = validate($_POST['name']);
    $description = validate($_POST['description']);
    $email = validate($_POST['email']);  // Get the email

    // Ensure fields are not empty
    if (empty($name) || empty($description) || empty($email)) {
        echo "fields_empty";
        exit;
    }

    // Prepare SQL query to insert data including email
    $sql = "INSERT INTO equipment (name, description, email) VALUES ('$name', '$description', '$email')";

    if ($conn->query($sql)) {
        echo "success";
    } else {
        echo "failed";
    }
} else {
    echo "invalid_request";
}
?>
