<?php
header("Content-Type: application/json"); // Set content type to JSON
error_reporting(E_ALL); // Enable error reporting for development
ini_set('display_errors', 1); // Display errors on the screen

include 'db.php'; // Include the database connection file

// Get the POST data
$data = json_decode(file_get_contents("php://input"));

// Log the incoming request data for debugging
file_put_contents('php://stderr', print_r($data, TRUE)); // This logs the data to the server's error log

// Check if required data is present
if (!isset($data->username) || !isset($data->email) || !isset($data->password)) {
    http_response_code(400); // Bad Request
    echo json_encode(["success" => false, "message" => "All fields are required!"]);
    exit();
}

// Extract username, email, and password
$username = $data->username;
$email = $data->email;
$password = $data->password;

// Check if the username already exists
$stmt = $conn->prepare("SELECT * FROM users WHERE `user` = ?");
$stmt->bind_param("s", $username);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    http_response_code(409); // Conflict
    echo json_encode(["success" => false, "message" => "Username already taken!"]);
    exit();
}

// Check if the email already exists
$stmt = $conn->prepare("SELECT * FROM users WHERE email = ?");
$stmt->bind_param("s", $email);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    http_response_code(409); // Conflict
    echo json_encode(["success" => false, "message" => "Email already taken!"]);
    exit();
}

// Hash the password before storing it
$hashedPassword = password_hash($password, PASSWORD_DEFAULT);

// Insert new user into the database
$stmt = $conn->prepare("INSERT INTO users (`user`, email, password) VALUES (?, ?, ?)");
$stmt->bind_param("sss", $username, $email, $hashedPassword);

if ($stmt->execute()) {
    http_response_code(201); // Created
    echo json_encode(["success" => true, "message" => "User registered successfully!", "userId" => $conn->insert_id]);
} else {
    http_response_code(500); // Internal Server Error
    echo json_encode(["success" => false, "message" => "Error in registration!"]);
}

// Close the statement and connection
$stmt->close();
$conn->close();
?>
