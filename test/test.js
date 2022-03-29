const request = require('supertest');
const app = require('../index.js');

describe('GET /about', function() {
	it('respond with welcome to About page', function(done){
		request(app).get('/about').expect('{"response": "welcome to About page"}', done);
	});
});