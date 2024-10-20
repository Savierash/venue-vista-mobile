<?php
$servername = "localhost"; // The server name (usually 'localhost' for local development)
$username = "root";         // Your MySQL username (default is 'root' for XAMPP)
$password = "";             // Your MySQL password (default is empty for XAMPP)
$dbname = "venuevista";     // Your database name

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    // Improved error handling with a more secure way of reporting errors
    error_log("Connection failed: " . $conn->connect_error); // Log the error to server logs
    die(json_encode(["success" => false, "message" => "Database connection failed."])); // Return a JSON response
}

// Set the character set to UTF-8 to avoid character encoding issues
$conn->set_charset("utf8mb4");

?>
