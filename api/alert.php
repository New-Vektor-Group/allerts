<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json');
include_once "db.php";

function pull_alerts($db) {
	if($db) {
		$output=["Score"=>0,"dots"=>[]];

		foreach ($db as $d) {
			$output["Score"]+=1;
			
			$data = [
				"la"=>$d->la,
				"lo"=>$d->lo,
				"hazard"=>$d->hazard,
				"type"=>$d->types,
				"size"=>$d->size,
				"trigger"=>$d->trig,
				"injuries"=>$d->injuries,
				"fatalities"=>$d->fatalities,
				"prob_trig"=>$d->prob_trig,
				"country"=>$d->country
			];

			array_push($output["dots"], $data);
		}
		return $output;
	}
	else
		return false;
}

function api() {
	if(isset($_GET['la']) and isset($_GET['lo'])) {
		$radius = 50; //km
		$radian = $radius/111;
		$input = [
			'la1'=>$_GET['la']-$radian,
			'la2'=>$_GET['la']+$radian,
			'lo1'=>$_GET['lo']-$radian,
			'lo2'=>$_GET['lo']+$radian
		];
		$db = R::find("redalert",'la >= ? and la <= ? and lo >= ? and lo <= ?',[$input['la1'],$input['la2'],$input['lo1'],$input['lo2']]);
		return pull_alerts($db);
	}
	else if (isset($_GET['alerts'])) {
		$db = R::find("redalert",'hazard!=?',["no"]);
		return pull_alerts($db);
	}
	else
		return false;
}

echo json_encode(api(),JSON_PRETTY_PRINT);
?>