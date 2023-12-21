const express = require('express');
const RegisterControllers = require('../controllers/register.controllers');

const router = express.Router();

router.post('/', RegisterControllers.RegisterNewRecruitment);

module.exports = router;