var totSec = {
  swim: 0,
  t1: 0,
  run: 0,
  t2: 0,
  bike: 0
};

function getIdValue(elementId) {
  return document.getElementById(elementId).value;
}

function setIdValue(elementId, value) {
  document.getElementById(elementId).value = value;
}

function disableAndSetId(elementId, value) {
  document.getElementById(elementId).value = value;
  document.getElementById(elementId).disabled = true;
}

function enableId(elementId) {
  document.getElementById(elementId).disabled = false;
}

function secToResString(time) {

  var hrs = ~~(time / 3600);
  var mins = ~~((time % 3600) / 60);
  var secs = ~~time % 60;

  var ret = hrs + ":";
  ret += (mins < 10 ? "0" : "") + mins + ":";
  ret += (secs < 10 ? "0" : "") + secs;

  return ret;
}

function calcSwim() {
  var dist100m = getIdValue('swimDist') * 10;
  var paceSecPer100m = getIdValue('swimPaceMin') * 60 + getIdValue('swimPaceSec') * 1;

  var resTotSec = dist100m * paceSecPer100m;
  totSec.swim = resTotSec;

  var resSting = secToResString(resTotSec);
  setIdValue('swimRes', resSting);

  calcTotal();
}

function calcT1() {
  var tTime = getIdValue('t1Min') * 60 + getIdValue('t1Sec') * 1;
  totSec.t1 = tTime;

  calcTotal();
}

function calcT2() {
  var tTime = getIdValue('t2Min') * 60 + getIdValue('t2Sec') * 1;
  totSec.t2 = tTime;

  calcTotal();
}

function calcBike() {
  var distKm = getIdValue('bikeDist');
  var paceKmh = getIdValue('bikePace');
  console.log("Bike dist=" + distKm + "km, km/h" + paceKmh);

  var resTotSec = distKm / paceKmh * 3600;
  totSec.bike = resTotSec;

  console.log("Bike res in sec=" + resTotSec);
  var resSting = secToResString(resTotSec);
  console.log("Bike res " + resSting);

  setIdValue('bikeRes', resSting);

  calcTotal();
}

function calcRun() {
  var distKm = getIdValue('runDist');
  var paceSecPerKm = getIdValue('runPaceMin') * 60 + getIdValue('runPaceSec') * 1;

  var resTotSec = distKm * paceSecPerKm;
  totSec.run = resTotSec;

  var resSting = secToResString(resTotSec);
  setIdValue('runRes', resSting);

  calcTotal();
}

function calcTotal() {
  var sec = totSec.swim + +totSec.t1 + totSec.bike + totSec.t2 + totSec.run;
  var resSting = secToResString(sec);
  setIdValue('res', resSting);
  return resSting;
}

function selectDistance(distance) {
  if (distance === 'im') {
    disableAndSetId('swimDist', 3.86);
    disableAndSetId('bikeDist', 180.25);
    disableAndSetId('runDist', 42.16);
  } else if (distance === 'him') {
    disableAndSetId('swimDist', 1.93);
    disableAndSetId('bikeDist', 90.12);
    disableAndSetId('runDist', 21.08);
  } else if (distance === 'olympic') {
    disableAndSetId('swimDist', 1.5);
    disableAndSetId('bikeDist', 40);
    disableAndSetId('runDist', 10);
  } else {
    enableId('swimDist');
    enableId('bikeDist');
    enableId('runDist');
  }
  enableId('t1Min');
  enableId('t1Sec');
  enableId('t2Min');
  enableId('t2Sec');
  calcAll();
}

function calcAll() {
  calcSwim();
  calcBike();
  calcRun();
  return calcTotal();
}

