<?php
header("Content-Type: application/json"); // Set content type to JSON

include 'db.php'; // Include the database connection file

// Get the POST data
$data = json_decode(file_get_contents("php://input"));

// Extract venue_id, event_date, and status
$venue_id = $data->venue_id;
$event_date = $data->event_date;
$status = $data->status;

// Insert new booking into the database
$stmt = $conn->prepare("INSERT INTO bookings (venue_id, event_date, status) VALUES (?, ?, ?)");
$stmt->bind_param("iss", $venue_id, $event_date, $status);

if ($stmt->execute()) {
    echo json_encode(["success" => true, "message" => "Booking successful!"]);
} else {
    echo json_encode(["success" => false, "message" => "Error in booking!"]);
}

// Close the statement and connection
$stmt->close();
$conn->close();
?>
