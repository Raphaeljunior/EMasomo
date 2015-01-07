<?php
/**
 * Created by PhpStorm.
 * User: CODING_MOVART
 * Date: 13/04/14
 * Time: 14:21
 */
include('constant.php');
class User_manager {
    private  $fname, $lname, $UID, $password, $email, $Uname,$credits,$course_download,$elimika_points,$badges,$topics,$favourites;
public  $connection;





    function setEmail($email)
    {
        if(filter_var(filter_var($email,FILTER_SANITIZE_EMAIL),FILTER_VALIDATE_EMAIL))
        {
            $this->email = $email;
        }
        else
        {
            trigger_error("Invalid email adress",E_USER_ERROR);
        }
    }

   function __construct($new,$email = "")
   {

       if($new)
       {

       }
       else
       {
           //identify the user
           $link = new Connect(SERVER,PASSWORD,USER);
           $link->setDatabase(DATABASE);
           if(filter_var(filter_var($email,FILTER_SANITIZE_EMAIL),FILTER_VALIDATE_EMAIL))
           {
               $query = "SELECT *FROM  users WHERE  email =  $email LIMIT 0 , 30";
               $result = $link->getQuery(DATABASE,$query);
               $this->fname = $result['fname'];
               $this->lname = $result['lname'];
               $this->password = $result['password'];
               $this-> $result['fname'];
           }

       }
   }

    function setFname($fname)
    {
        if(is_string($fname) )
        {
            $fname = filter_var($fname,FILTER_SANITIZE_STRING);
            $this->fname =  ucfirst($fname);
        }
        else
        {
        trigger_error("Invalid name. Please enter a valid name",E_USER_ERROR);
        }
    }

    private function validate_email($email)
    {
        $link = new Connect(SERVER,PASSWORD,USER);
        $link->setDatabase(DATABASE);
        $query =  "SELECT *
            FROM  users
WHERE  email =  $email
LIMIT 0 , 30";
            $result = $link->getQuery(DATABASE,$query);
        if(count($result) >= 1 )
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    function getFname()
    {
        return $this->fname;
    }

    function  setLname($lname)
    {
        if(is_string($lname))
        {
            $lname = filter_var($lname,FILTER_SANITIZE_STRING);
            $this->fname =  ucfirst($lname);
        }
        else
        {
            trigger_error("Invalid name. Please enter a valid name",E_USER_ERROR);
        }
    }

    function getlname()
    {
        return $this->lname;
    }

     function setUID()
    {
       $this->UID =  $this->createUID();

    }

 private    function createUID()
    {
        //retrieve last assigned uid to generate user id
        $this->connection = new Connect(SERVER,USER,PASSWORD);
        $link = $this->connection;
        $link->setDatabase(DATABASE);
        $query = "SELECT LAST(UID) AS LastUID FROM USERS";
        $last_known_UID = $link->getQuery(DATABASE,$query);
        $generator = $last_known_UID['LastUID'];
        $split = explode("-",$generator);
        //generating user ID
        $new_suffix = is_int($split[1])? $split[1] + 1: die("fatal error occurred");
        $pref = substr($this->fname,0,3);
        $comp = compact($pref,$new_suffix);
        $uid = implode("-",$comp);
        return $uid;

    }

    function getUID()
    {
     return $this->UID;

    }
    function setPassword($password)
    {
        if($this->check_password_stregnth($password))
        {
            $this->password = password_hash($password,HASH_HMAC);
        }
        else
        {
            trigger_error("The password selected by the user was not strong enough. A strong password consists of lowercase, uppercase, numbers and symbols",E_USER_ERROR);

        }
    }

    private function check_password_stregnth($password)
    {
        global $stregnth;
        $uc = ctype_upper($password) ? 1 : 0;
        $lc = ctype_lower($password) ? 1 : 0;
        $dg = ctype_digit($password) ? 1 : 0;
        $pt = ctype_punct($password) ? 1 : 0;

        $param = array($uc,$lc,$dg,$pt);
        array_walk($param,"calculate");
        if($GLOBALS['stregnth'] > 50)
        {
            return true;
        }
        else
        {
            return false;
        }

   }

    private function calculate($int)
    {
        $GLOBALS['stregnth'] = $int ? $GLOBALS['stregnth'] + 20: $GLOBALS['stregnth'];


    }
   function getPassword()
   {
       return $this->password;

   }
function  setUname($uname)
{
    if(filter_var($uname,FILTER_SANITIZE_STRING))
    {
        $this->Uname = $uname;

    }
}
    function check_username_avalability($uname)
    {
        $link = new Connect(SERVER,PASSWORD,USER);
        $link->setDatabase(DATABASE);
        $query = "SELECT *
FROM  `users`
WHERE  `uname` = $uname LIMIT 0 , 30";
        $result = $link->getQuery(DATABASE,$query);
        if(count($result) > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    function register()
    {
      $uname = $this->getUname();
        $message = "Thanks for joining the E-masomo Learning Community ";
        $body = "This is to confirm you are registered with the E-masomo learning community";
        $email = $this->email;
        $link = new Connect(SERVER,PASSWORD,USER);
        $link->setDatabase(DATABASE);
        $fname = $this->getFname();
        $lname = $this->getlname();
        $password = $this->getPassword();
       if(($this->check_username_avalability($uname))&&(mail($email,$message,$body)) )
        {


            $query = "INSERT INTO `nanoxcor_emasomo`.`users` (`fname`, `lname`, `uname`, `password`, `email`, `UID`) VALUES ('$fname', '$lname', '$uname', '$password', '$email')";
            if($link->postQuery(DATABASE,$query))
            {
                $this->createLog($uname);
             return true;
            }
            else
            {
                trigger_error("Error encountered whule registering user",E_USER_ERROR);
                return false;
            }
        }
        else
        {
            trigger_error("Username already in use. Choose another username",E_USER_ERROR);
            return false;
        }
    }
    function retrievePassword($email)
    {
$link = new Connect(SERVER,PASSWORD,USER);
        $link->setDatabase(DATABASE);
        $query = "SELECT *FROM  `users` WHERE  `email` = '$email' LIMIT 0 , 30";



        $result = $link->getQuery(DATABASE,$query);
        $password= $result[$email];
        return $password;
    }
    function login($input)
    {
        $email = $this->email;
        $password = $this->retrievePassword($email);
        if(password_verify($input,$password))
        {
            return true;
        }
        else
        {
            trigger_error("Incorrect Password entered",E_USER_WARNING);
            return false;
        }
    }
    function updatePassword($input)
    {
        $email = $this->email;
     if($this->login($input))
     {
       $link = new Connect(SERVER,PASSWORD,USER);
        $link->setDatabase(DATABASE);
         $UID = $this->getAUID();
         $query = "UPDATE  `nanoxcor_emasomo`.`users` SET  `password` =  $input WHERE  `users`.`UID` = $UID";
        if($link->postQuery(DATABASE,$query))
        {
            return true;
        }
        else
        {
            return false;
        }
     }
    }
    function changePassword($ucode,$input)
    {
     if($this->verify($ucode))
         {
             $this->updatePassword($input);

         }

    }
    private function send_verification_code()
    {
        $email = $this->email;
     global $code;
        $GLOBALS['code'] = uniqid("");
        $head = "Password Change";
        $message = $code."/n"." Use the above code to verify password change";
        mail($email,$head,$message);
    }
    private function verify($ucode)
    {
        if($GLOBALS['code'] == $ucode)
        {
            return true;
        }
        else
        {
            trigger_error("Invalid code. Please check your email address for the valid code",E_USER_WARNING);


            return false;

        }

    }
    function getAUID()

    {
    $email = $this->email;
        $link = new Connect(SERVER,PASSWORD,USER);
        $link->setDatabase(DATABASE);
        $query = "SELECT *
FROM  `users`
WHERE  `email` = '$email'
LIMIT 0 , 30";
        $result = $link->getQuery(DATABASE,$query);
        $id = $result['UID'];
        return $id;
    }



    function getUname()
    {
        return $this->Uname;
    }
    function createLog($Uname)
    {
        $link = new Connect(SERVER,PASSWORD,USER);
        $link->setDatabase(DATABASE);
        $query = "CREATE TABLE $$Uname
(
Typea varchar(50),
Subject varchar(50),
Topic varchar(255),
Date_entered DATE,
Val varchar(255)
)";
        if($link->postQuery(DATABASE,$query))
        {
            return true;
        }
        else
        {
            return false;
        }
    }





}