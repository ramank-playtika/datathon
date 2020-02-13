<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hakathon</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="main.css"/>
    <style>
        .div_right {
            height: 50%;
            width: 50%;
            float: right;
        }
    </style>
</head>
<body>

<div align="center">
    <p>Greeting: ${treatment.greeting}</p>
</div>
<div class="div_right">
    <p>Weather: ${treatment.weather}</p>
    <img src="${treatment.weatherImg}" width=400" height="300"/>
</div>
<div class="div_right">
    <p>Time: ${treatment.time}</p>
    <img src="${treatment.timeImg}" width=400" height="300"/>
</div>
<div class="div_right">
    <p>Holiday: ${treatment.holiday}</p>
    <img src="${treatment.holidayImg}" width=400" height="300"/>
</div>
<div class="div_right">
    <p>Location: ${treatment.location}</p>
    <img src="${treatment.locationImg}" width=400" height="300"/>
</div>
</body>
</html>