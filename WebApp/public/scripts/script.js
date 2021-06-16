function walkingTrailCheck() {
    if (document.getElementById("type").value == "Walking Trail") {
        document.getElementById("ifYes").style.display = "";
    } else {
        document.getElementById("ifYes").style.display = "none";
    }
}