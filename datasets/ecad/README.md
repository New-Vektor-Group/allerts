Data and metadata available at [ecad](https://www.ecad.eu)  
*FILE FORMAT (MISSING VALUE CODE IS -9999)*  

## Stations
  
`STAID`  : Station identifier  
`STANAME`: Station name  
`CN`     : Country code (ISO3116 countrycodes)  
`LAT`    : Latitude in degrees:minutes:seconds (+: North, -: South)  
`LON`    : Longitude in degrees:minutes:seconds (+: East, -: West)  
`HGTH`   : Station elevation in meters  
  
## Sources  
  
FILE FORMAT (MISSING VALUE CODE IS -9999):  
  
`SOUID`  : Source identifier  
`SOUNAME`: Source name  
`CN`     : Country code (ISO3116 countrycodes)   
`LAT`    : Latitude in degrees:minutes:seconds (positive: North, negative: South)  
`LON`    : Longitude in degrees:minutes:seconds (positive: East, negative: West)  
`HGHT`   : Station elevation in meters  
`ELEID`  : Element identifier  
`START`  : Begin date YYYYMMDD  
`STOP`   : End date YYYYMMDD  
`PARID`  : Participant identifier  
`PARNAME`: Participant name  
  
## TX (Max.Temp.)  
    
`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`TX`   : Maximum temperature in 0.1 &#176;C  
`Q_TX` : quality code for TX (0='valid'; 1='suspect'; 9='missing')  

## TN (Min.Temp.)  
    
`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`TN`   : Minimum temperature in 0.1 &#176;C  
`Q_TN` : quality code for TN (0='valid'; 1='suspect'; 9='missing')  

## CC (Cloud Covering)  

`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`CC`   : Cloud cover in oktas  
`Q_CC` : quality code for CC (0='valid'; 1='suspect'; 9='missing')  

## DD (Wind Direction)  

`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`DD`   : Wind direction in degrees  
`Q_DD` : quality code for DD (0='valid'; 1='suspect'; 9='missing')  

## FG (Wind Speed)  

`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`FG`   : Wind speed in 0.1 m/s  
`Q_FG` : quality code for FG (0='valid'; 1='suspect'; 9='missing')  

## FX (Wind Gust)  

`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD   
`FX`   : Wind gust in 0.1 m/s  
`Q_FX` : quality code for FX (0='valid'; 1='suspect'; 9='missing')  

## PP (Sea level pressure)  
  
`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`PP`   : Sea level pressure in 0.1 hPa  
`Q_PP` : quality code for PP (0='valid'; 1='suspect'; 9='missing')  

## HU (Humidity)  
  
`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`HU`   : Humidity in 1 %  
`Q_HU` : quality code for HU (0='valid'; 1='suspect'; 9='missing')  

## QQ (Radiation)  

`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`QQ`   : Global radiation in W/m2  
`Q_QQ` : quality code for QQ (0='valid'; 1='suspect'; 9='missing')  

## RR (Precipitation)  
  
`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`RR`   : Precipitation amount in 0.1 mm  
`Q_RR` : quality code for RR (0='valid'; 1='suspect'; 9='missing')  

## SD (Snow depth)  

`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`SD`   : Snow depth in 1 cm  
`Q_SD` : quality code for SD (0='valid'; 1='suspect'; 9='missing')  

## SS (Sunshine)  

`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`SS`   : Sunshine in 0.1 Hours  
`Q_SS` : quality code for SS (0='valid'; 1='suspect'; 9='missing')  

## TG (Mean. Temp.)  

`STAID`: Station identifier  
`SOUID`: Source identifier  
`DATE` : Date YYYYMMDD  
`TG`   : Mean temperature in 0.1 &#176;C  
`Q_TG` : quality code for TG (0='valid'; 1='suspect'; 9='missing')  