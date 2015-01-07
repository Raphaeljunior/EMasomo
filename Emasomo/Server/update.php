<?php
/**
 * Created by PhpStorm.
 * User: CODING_MOVART
 * Date: 24/04/14
 * Time: 04:59
 */

echo " I may take some time";
$handle = fopen("/biology_form_2.txt","r");
echo "I have created a handle";
while(!feof($handle))
{
    $currentLine = fgets($handle);
    $elements = explode(":",$currentLine);
    echo "I am done splitting ";
    $name = $elements[0]."01";
    $link = $elements[1].":"."$elements[2]";
    $conn = mysqli_connect("localhost","nanoxcor_emasomo","mungaingina99","nanoxcor_emasomo");
    $query = "INSERT INTO `nanoxcor_emasomo`.`Videos` (`piclink`, `name`, `description`, `dlink`, `category`, `RID`) VALUES ('$link', '$name', '', '$link', 'biology', NULL)";
    mysqli_query($conn,$query);
echo "executed query a success";
}