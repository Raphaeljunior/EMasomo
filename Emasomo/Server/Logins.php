<?php
/**
 * Created by PhpStorm.
 * User: CODING_MOVART
 * Date: 14/04/14
 * Time: 02:29
 */
$email =$_GET['email'];
$input = $_GET['password'];

$user = new User_manager(false,$email);
if($user->login($input))
{
  echo json_encode(1);
}
else
{
    echo(json_encode(0));
}

