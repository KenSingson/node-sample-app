const express = require('express');
const app = express();

app.get("/", function (req, res) {
	res.send('{"response": "Hello from Express"}');
});

app.get("/about", function(req, res) {
	res.send('{"response": "welcome to About page"}');
});

app.get("/careers", function(req, res) {
	res.send('{"response":"Need work? Career Opportunities We Offer"}');
});

app.listen(process.env.PORT || 3000);
module.exports = app