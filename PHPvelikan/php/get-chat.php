<?php
session_start();
include_once "config.php";

if (isset($_SESSION['unique_id'])) {
    $outgoing_id = mysqli_real_escape_string($conn, $_POST['outgoing_id']);
    $incoming_id = mysqli_real_escape_string($conn, $_POST['incoming_id']);

    $output = "";

    $sql = "SELECT * FROM messages LEFT JOIN users ON users.unique_id = messages.outgoing_msg_id
            WHERE (outgoing_msg_id = {$outgoing_id} AND incoming_msg_id = {$incoming_id})
            OR (outgoing_msg_id = {$incoming_id} AND incoming_msg_id = {$outgoing_id}) ORDER BY msg_id";
    $query = mysqli_query($conn, $sql);

    if (mysqli_num_rows($query) > 0) {
        while ($row = mysqli_fetch_assoc($query)) {
            $output .= '<div class="chat ' . (($row['outgoing_msg_id'] == $outgoing_id) ? "outgoing" : "incoming") . '">';
            $output .= '<div class="details">';
            $output .= '<p ondblclick="likeMessage(' . $row['msg_id'] . ', event)">' . $row['msg'] . '</p>';
            $output .= '<span id="likesCount_' . $row['msg_id'] . '" style="display: none;">' . $row['likes'] . '</span>';
            $output .= '<span class="heart-icon" id="heartIcon_' . $row['msg_id'] . '" style="display: ' . (($row['likes'] > 0) ? "inline-block" : "none") . ';">&hearts;</span>';
            $output .= '</div>';
            $output .= '</div>';
        }
        echo $output;
    }
} else {
    header("location: ../login.php");
}
?>
