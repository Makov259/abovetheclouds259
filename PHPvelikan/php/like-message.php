
<?php
session_start();
include_once "config.php";

if (isset($_SESSION['unique_id']) && isset($_POST['msg_id'])) {
    $msgId = mysqli_real_escape_string($conn, $_POST['msg_id']);

    $sql = "UPDATE messages SET likes = likes + 1 WHERE msg_id = {$msgId}";
    mysqli_query($conn, $sql);

    $sql = "SELECT likes FROM messages WHERE msg_id = {$msgId}";
    $query = mysqli_query($conn, $sql);
    $row = mysqli_fetch_assoc($query);

    echo $row['likes'];
} else {
    echo "Error";
}
?>
