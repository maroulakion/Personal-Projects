<?php
if ($_SERVER["REQUEST_METHOD"] === "POST") {
    // Securely passing form data using POST method 
    $username = $_POST["username"];
    $email = $_POST["email"];
    $password = $_POST["password"];

    // VALIDATE INPUT
    // *** FOR TESTING PURPOSES, 
    //     THE ONLY ACCEPTED INPUT IS 
    //     -> "test" as Username
    //     -> anything as Email (if passing the javascript validity test)
    //     -> "password" as Password

    if ($username === "test" && $password === "password") {
        // Redirect to "thank_you.html" on successful login
        header("Location: thank_you.html");
        exit; // STOP executing PHP code is executed after redirection
    } else {
        $response = "Login Failed";
    }

    // Send the response message back to the HTML page
    echo $response;
}
?>
