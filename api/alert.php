<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
include_once "db.php";

function api() {
	if(isset($_GET['la1']) and isset($_GET['la2']) and isset($_GET['lo1']) and isset($_GET['lo2'])) {
		$input = $_GET;
		$db = R::find("redalert",'la1 >= ? and la2 <= ? and lo1 >= ? and lo2 <= ?',[$input['la1'],$input['la2'],$input['lo1'],$input['lo2']]);
		if($db) {
			
		}
		else
			return false;
	}
	else
		return false;
}

echo json_encode(api(),JSON_PRETTY_PRINT);
?>