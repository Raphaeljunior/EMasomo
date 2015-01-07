<?php
/**
 * Created by PhpStorm.
 * User: CODING_MOVART
 * Date: 14/04/14
 * Time: 04:49
 */

class Quiz {
    public $piclink,$name,$description,$dlink,$category;
    function  __construct($row)
    {
        $this->piclink = $row['link'];
        $this->dlink = $row['linkd'];
        $this->description = $row['description'];
        $this->name = $row['name'];




    }
    function newArray($quiz)
    {
        global $x;
        array_push($x,$quiz);
    }



    function retrieveList($category)
    {
        $link =  new Connect(SERVER,PASSWORD,USER);
        $link->setDatabase(DATABASE);
        $query = "SELECT * 
FROM  `Quiz` 
WHERE  `category` LIKE  'science'
LIMIT 0 , 30";
        $result = mysql_query($query);
        while ($row = mysql_fetch_array($result))
        {
            $this->createInstance($row);
        }
        return $GLOBALS['x'];

    }
    function createInstance($row){
        $this->newArray(new Quiz($row));
    }

} 