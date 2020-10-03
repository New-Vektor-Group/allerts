<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
include_once "db.php";

function api() {
	if(isset($_GET['la']) and isset($_GET['lo'])) {
		$input = $_GET;
		$db = R::find("redalert",'la >= ? and lo <= ?',[$input['la'],$input['lo']]);
		if($db) {
			$output=["Score"=>0];

			foreach ($db as $d) {
				$output["Score"]+=1;
				
				$data = [
					"la1"=>floatval($d->la1),
					"la2"=>floatval($d->la2),
					"lo1"=>floatval($d->lo1),
					"lo2"=>floatval($d->lo2)
				];

				$output["dot".$output["Score"]]=$data;
			}
			return $output;
		}
		else
			return false;
	}
	else
		return false;
}

echo json_encode(api(),JSON_PRETTY_PRINT);
?>