const express = require('express');
const app = express();

const projectsRoute = require('./routes/project.js');
const recruitmentRoute = require('./routes/recruitment.js');
const requestsRoute = require('./routes/request.js');
const registerRoute = require('./routes/register.js');
const loginRoute = require('./routes/login.js');

const middlewareLogRequest = require('./middleware/log.js')

app.use(middlewareLogRequest);

app.use(express.json({ strict: false }))

app.use('/register', registerRoute)
app.use('/login', loginRoute)
app.use('/projects', projectsRoute);
app.use('/recruitments', recruitmentRoute);
app.use('/requests', requestsRoute);

module.exports = app;