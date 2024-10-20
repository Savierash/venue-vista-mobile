<?php
header("Content-Type: application/json");
include 'db.php'; // Ensure this file establishes a connection to your database

// Get the POST data from the request body
$data = json_decode(file_get_contents("php://input"));

// Log the received data for debugging
error_log(print_r($data, true));

// Validate input
if (!isset($data->username) || !isset($data->password)) {
    http_response_code(400); // Bad Request
    echo json_encode(["success" => false, "message" => "Username and password are required!"]);
    exit();
}

// Extract the username and password
$username = $data->username;
$password = $data->password;

// Prepare and bind the SQL statement to prevent SQL injection
$stmt = $conn->prepare("SELECT * FROM users WHERE user = ?");
if ($stmt === false) {
    http_response_code(500); // Internal Server Error
    echo json_encode(["success" => false, "message" => "Database query preparation failed: " . $conn->error]);
    exit();
}

$stmt->bind_param("s", $username);
$stmt->execute();
$result = $stmt->get_result();

// Check if the user exists
if ($result->num_rows > 0) {
    $user = $result->fetch_assoc(); // Fetch the user data

    // Log the user data for debugging (ensure to remove sensitive data in production)
    error_log("Fetched user: " . print_r($user, true));

    // Verify the password using password_verify
    if (password_verify($password, $user['password'])) {
        http_response_code(200); // OK
        echo json_encode(["success" => true, "message" => "Login successful!"]); // Send success response
    } else {
        http_response_code(401); // Unauthorized
        echo json_encode(["success" => false, "message" => "Invalid password!"]); // Password mismatch
    }
} else {
    http_response_code(404); // Not Found
    echo json_encode(["success" => false, "message" => "User not found!"]); // User does not exist
}

// Close the statement and connection
$stmt->close();
$conn->close();
?>
