<?php
/**
 * Created by PhpStorm.
 * User: CODING_MOVART
 * Date: 13/04/14
 * Time: 13:56
 */

class Connect {
    private  $server,$password,$username,$database,$connection;

    function  __construct($server,$password,$username,$db)  //constructor
    {
        $this->connection = mysqli_connect($server,$username,$password,$db) or die(mysql_error());
        //return $this->connection;
    }
    function __destructor(){
	mysqli_close($this->connection);
    }
    function setServer($new_server)
    {
        $this->server = $new_server;

    }
    function  getServer()
    {
        return $this->server;

    }
    function getPassword()
    {
        return $this->password;

    }
    function  setPassword($new_password)
    {
        $this->password = $new_password;
    }
    function setUsername($new_username)
    {
        $this->username = $new_username;
    }
    function getUsername()
    {
        return $this->username;
    }
    function setDatabase($new_database)
    {
        $this->database = $new_database;
    }
    function getDatabase()
    {
        return $this->database;
    }
    function postQuery($database,$query)
    {
        mysql_select_db($database);
        $result = mysql_query($query);
        return $result;
    }

    function getQuery($database,$query)
    {

        global $array;
        mysql_select_db($database);
        $result = mysql_query($query);
        while($array1 = mysql_fetch_array($result))
        {

            $array= array_merge($array,$array1);
        }
        return $array;
    }
    function getInstance(){
        return $this->connection;
    }

    function recQuery($database,$query)
    {
        $i = 0;
        $array = array();
        //mysql_select_db($database)
        if($result = mysqli_query($this->connection,$query)){
		echo "S <br>";
		
	
        while($array1 = mysqli_fetch_array($result))
        {
            
	    //echo $array1[$i] . "<br>";
            array_push($array,$array1[$i]);
            $i++;
        }
        //echo $array[0];
        return $array;
        }else{
            echo "F";
           }
    }
} 
