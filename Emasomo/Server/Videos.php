<?php
/**
 * Created by PhpStorm.
 * User: CODING_MOVART
 * Date: 14/04/14
 * Time: 04:50
 */
include("constant.php");
class Videos {
    public $piclink,$name,$description,$dlink,$category;
    function  __construct($row)
    {
        $this->piclink = $row['piclink'];
        $this->dlink = $row['dlink'];
        $this->description = $row['description'];
        $this->name = $row['name'];




    }
    function newArray($videos)
    {
        global $x;
        array_push($x,$videos);
        
    }



    function retrieveList($category)
    {
        $link =  new Connect(SERVER,PASSWORD,USER,DATABASE);
        //$link->setDatabase(DATABASE);
        $query = "SELECT *
FROM  Videos
WHERE  category =  '$category'
LIMIT 0 , 30";
        $result = mysqli_query($link->getInstance(),$query);
        while ($row = mysqli_fetch_array($result))
        {
            $this->createInstance($row);
        }
        return $GLOBALS['x'];

    }
    function createInstance($row)
    {
        $this->newArray(new Videos($row));
    }
} 
