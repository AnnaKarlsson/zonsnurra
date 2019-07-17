function displaySport(answer) {
  document.getElementById('swimBlock').style.display = "none";
  document.getElementById('bikeBlock').style.display = "none";
  document.getElementById('runBlock').style.display = "none";

  if (answer == "swim") {
    document.getElementById('swimBlock').style.display = "block";
    displayMeasure("time");
  } else if (answer == "bike") {
    document.getElementById('bikeBlock').style.display = "block";
    displayMeasure("");
  } else if (answer == "run") {
    document.getElementById('runBlock').style.display = "block";
    displayMeasure("");
  }
}

function displayMeasure(answer) {
  document.getElementById("result").innerHTML = "";
  document.getElementById('timeBlock').style.display = "none";
  document.getElementById('pulseBlock').style.display = "none";
  document.getElementById('wattBlock').style.display = "none";

  if (answer === "time") {
    document.getElementById('timeBlock').style.display = "block";
  } else if (answer === "pulse") {
    document.getElementById('pulseBlock').style.display = "block";
  } else if (answer === "watt") {
    document.getElementById('wattBlock').style.display = "block";
  }
}

function send(type, measure) {
  var sport = document.getElementById('sport').value;
  var url = "/" + sport + "/" + type + "/" + measure;

  $.getJSON(url, function (data) {
    var items = [];
    $.each(data, function (key, val) {
      items.push("<div class='row justify-content-center'>" +
        "<div class='col-2'>" + key + "</div>" +
        "<div class='col-2'>" + val + "</div>" +
        "</div>");
    });

    document.getElementById("result").innerHTML = items.join("");
  });
}

function submitWatt() {
  send("watt", document.getElementById('watt').value);
}

function submitPulse() {
  send("pulse", document.getElementById('pulse').value);
}

function submitTime() {
  send("time", document.getElementById('min').value + "/" + document.getElementById('sec').value);
}