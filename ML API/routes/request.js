const express = require('express');
const RequestControllers = require('../controllers/request.controllers');

const router = express.Router();

//GET
router.get('/', RequestControllers.getAllRequests);

//GET by Id
router.get('/:identifier_request_customer', RequestControllers.getRecruitmentbyId);

//CREATE - POST
router.post('/', RequestControllers.createNewRequests);

//UPDATE - PATCH
router.patch('/:identifier_request_customer', RequestControllers.UpdateRequest)

//DELETE - DELETE
router.delete('/:identifier_request_customer', RequestControllers.DeleteRequest)

module.exports = router;