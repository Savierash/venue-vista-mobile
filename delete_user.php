<?php
$servername = "localhost";
$username = "root";  // Update if necessary
$password = "";      // Update if necessary
$dbname = "venuevista";  // Your database name

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Validate if the ID is provided
    if (!isset($_POST['id']) || empty($_POST['id'])) {
        echo json_encode(["success" => false, "message" => "User ID is required"]);
        exit;
    }

    // Get the id from the request
    $id = $_POST['id'];

    // Prepare the SQL statement to delete the user
    $stmt = $conn->prepare("DELETE FROM users WHERE id = ?");
    
    if ($stmt === false) {
        echo json_encode(["success" => false, "message" => "Failed to prepare statement"]);
        exit;
    }

    $stmt->bind_param("i", $id);

    // Execute the query and check the result
    if ($stmt->execute()) {
        if ($stmt->affected_rows > 0) {
            echo json_encode(["success" => true, "message" => "User deleted successfully"]);
        } else {
            echo json_encode(["success" => false, "message" => "No user found with the provided ID"]);
        }
    } else {
        echo json_encode(["success" => false, "message" => "Failed to delete user"]);
    }

    // Close the statement
    $stmt->close();
}

// Close the connection
$conn->close();
?>
