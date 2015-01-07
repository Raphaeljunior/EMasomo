<?php
/**
 * Created by PhpStorm.
 * User: CODING_MOVART
 * Date: 14/04/14
 * Time: 05:26
 *feels an array with the categories available
 */
$resource = $_GET['resource'];
$query = "SELECT DISTINCT category FROM $resource";
$link = new Connect(SERVER,PASSWORD,USER);
$link->setDatabase(DATABASE);
$result = $link->recQuery(DATABASE,$query);
global $list;
foreach($result as $key->$value)
{
  array_push($GLOBALS['list'],$result[$key]);
}
echo json_encode($GLOBALS['list']);