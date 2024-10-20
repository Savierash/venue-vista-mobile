<?php
header('Content-Type: application/json');

// Include database connection
include 'db.php';

try {
    // SQL query to select venues
    $sql = "SELECT venue_id, name, price, photo FROM venues WHERE confirmed = 1"; // Fetch only confirmed venues
    $stmt = $pdo->prepare($sql);
    $stmt->execute();

    // Fetch all venues
    $venues = $stmt->fetchAll(PDO::FETCH_ASSOC);

    // Check if venues are found
    if ($venues) {
        // Return the data as JSON
        echo json_encode([
            "success" => true,
            "venues" => $venues
        ]);
    } else {
        // If no venues are found, return an appropriate message
        echo json_encode([
            "success" => true, // Indicate success even if no venues are found
            "venues" => [] // Return an empty array
        ]);
    }
} catch (PDOException $e) {
    // Handle database errors
    echo json_encode([
        "success" => false,
        "message" => "Database error: " . $e->getMessage()
    ]);
} catch (Exception $e) {
    // Handle any other errors
    echo json_encode([
        "success" => false,
        "message" => "Error: " . $e->getMessage()
    ]);
}

// Close the database connection
$pdo = null;
?>
