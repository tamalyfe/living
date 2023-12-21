const express = require('express');
const LoginControllers = require('../controllers/login.controllers');

const router = express.Router();

router.post('/', LoginControllers.checkLogin);

module.exports = router;