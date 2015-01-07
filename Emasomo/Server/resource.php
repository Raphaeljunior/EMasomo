<?php
/**
 * Created by PhpStorm.
 * User: CODING_MOVART
 * Date: 14/04/14
 * Time: 23:43
 */
//global $resource;
include("Book.php");
include("Connect.php");
include("Videos.php");
$resource = $_GET['resource'];
$category = $_GET['category'];
$x = array();

$z = getResourceList($resource);
echo json_encode($z);
function getResourceList($res){

    $row = array("link" => "d","linkd"=>"d","description"=>"d","name"=>"d");
    $rest =  $res;//$GLOBALS['resource'];
    $category = $_GET['category'];
    if((trim($rest)== "book"))
    {
      echo "BOOK";
      $agent = new Book($row);
      $resourceList =  $agent->retrieveList($category);
        return $resourceList;

    }
    elseif((trim($rest)== "video"))
    {
     $agent =  new Videos($row);
       $resourceList =  $agent->retrieveList($category);
        return $resourceList;
    }
    elseif((trim($rest)== "quiz"))
    {
      $agent =  new Quiz($row);
        $resourceList = $agent->retrieveList($category);
        return $resourceList;

    }
    elseif((trim($rest)== "flash"))
    {
        $agent = new Flash($row);
        $resourceList = $agent->retrieveList($category);
        return $resourceList;

    }
    else
    {
        return "resource requested not available";
    }
}


