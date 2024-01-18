<?php
session_start();
include_once "config.php";

if (isset($_SESSION['unique_id'])) {
    $user_id = mysqli_real_escape_string($conn, $_SESSION['unique_id']);
    
    // Delete messages for the user from the messages table
    $sql = "DELETE FROM messages WHERE incoming_msg_id = {$user_id} OR outgoing_msg_id = {$user_id}";
    mysqli_query($conn, $sql);
}
?>
