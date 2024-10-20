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
    // Get the user data from the request
    $id = isset($_POST['id']) ? $_POST['id'] : '';
    $user = isset($_POST['user']) ? $_POST['user'] : null;
    $email = isset($_POST['email']) ? $_POST['email'] : null;
    $password = isset($_POST['password']) ? $_POST['password'] : null;

    // Check if user ID is provided
    if (empty($id)) {
        echo json_encode(array("success" => false, "message" => "User ID is required."));
        exit;
    }

    // Prepare the base SQL statement
    $sql = "UPDATE users SET";
    $params = array();
    $types = '';

    // Only add to SQL query if fields are provided
    if ($user !== null) {
        $sql .= " user = ?,";
        $params[] = $user;
        $types .= 's';
    }
    if ($email !== null) {
        $sql .= " email = ?,";
        $params[] = $email;
        $types .= 's';
    }
    if ($password !== null) {
        // Hash the password before storing it
        $hashedPassword = password_hash($password, PASSWORD_BCRYPT);
        $sql .= " password = ?,";
        $params[] = $hashedPassword;
        $types .= 's';
    }

    // Remove the trailing comma and add the WHERE clause
    $sql = rtrim($sql, ',') . " WHERE id = ?";
    $params[] = $id;
    $types .= 'i';

    // Prepare the SQL statement
    $stmt = $conn->prepare($sql);
    if ($stmt === false) {
        echo json_encode(array("success" => false, "message" => "Failed to prepare statement."));
        exit;
    }

    // Bind the parameters dynamically
    $stmt->bind_param($types, ...$params);

    // Execute the statement and check if the update was successful
    if ($stmt->execute()) {
        echo json_encode(array("success" => true, "message" => "User profile updated successfully."));
    } else {
        echo json_encode(array("success" => false, "message" => "Failed to update user profile."));
    }

    // Close the statement
    $stmt->close();
}

// Close the connection
$conn->close();
?>
