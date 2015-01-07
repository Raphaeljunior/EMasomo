<?php
/**
 * Created by PhpStorm.
 * User: CODING_MOVART
 * Date: 14/04/14
 * Time: 03:01
 */
include("constant.php");
class Book {
    public $piclink,$name,$description,$dlink,$category;
   function  __construct($row)
   {
       $this->piclink = $row['piclink'];
       $this->dlink = $row['dlink'];
       $this->description = $row['description'];
       $this->name = $row['name'];
       $this->category = $row['category'];
   }
   
    function newArray($book)
    {
        global $x;
       array_push($x,$book);
     }



    function retrieveList($category)
    {
      $link =  new Connect(SERVER,PASSWORD,USER,DATABASE);
        //$link->setDatabase(DATABASE);
        $query = "SELECT * 
FROM  Books 
WHERE  category LIKE  '$category'
LIMIT 0 , 30";
        $result = mysqli_query($link->getInstance(),$query);
        while ($row = mysqli_fetch_array($result))
        {
            $this->createInstance($row);
        }
        return $GLOBALS['x'];

    }
    function createInstance($row){
       $this->newArray(new Book($row));
    }


} 
