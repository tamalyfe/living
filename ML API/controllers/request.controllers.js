const RequestModel = require('../models/tb_request_customer')

const getAllRequests = async (req, res) => {
    try {
        const [data]  = await RequestModel.getAllRequest();
        res.json({
            value: 1,
            result: data
        })
    } catch (error) {
        res.status(500).json({
            value: 0,
            ServerMessage: error,
        })
    }
    
}

const getRecruitmentbyId = async (req, res) => {
    const {identifier_request_customer} = req.params;
    try {
        const [data]  = await RequestModel.getRequestbyIdentifier(identifier_request_customer);
        res.json({
            value: 1,
            result: data
        })
    } catch (error) {
        res.status(500).json({
            value: 0,
            ServerMessage: error,
        })
    }
    
}

const createNewRequests = async (req, res) => {
    const { body } = req;

    if (
        !body.name_customer ||
        !body.contact_customer ||
        !body.domicile_customer ||
        !body.description_request_customer ||
        !body.location_project ||
        !body.price_list_project_cash ||
        !body.price_list_project_credit
      ) {
        return res.status(400).json({
            message: 'Masukkan data yang benar',
            result: null,
        });
    }

    try {
        await RequestModel.createNewRequest(body);
        res.json({
            value: 1,
            result: body
        })
        }
    catch (error) {
        res.status(500).json({
            value: 0,
            ServerMessage: error,
        });
    }
};


const UpdateRequest = async (req, res) => {
    const {identifier_request_customer} = req.params;
    const {body} = req;
    try {
        await RequestModel.UpdateRequest(body, identifier_request_customer);
        res.json({
            value: 0,
            result: {
                id: identifier_request_customer,
                ...body
            },
        })
    }catch (error) {
        res.status(500).json({
            value: 0,
            ServerMessage: error,
        })
    }  
}

const DeleteRequest = async(req, res) => {
    const {identifier_request_customer} = req.params;
    console.log('identifier_request_customer:', identifier_request_customer);

     try{
        await RequestModel.DeleteRequest(identifier_request_customer);
        res.json({
            value: 1,
            result: null
        })
    }catch (error) {
        res.status(500).json({
            value: 0,
            ServerMessage: error,
        })
    }
}

module.exports = {
   getAllRequests,
   createNewRequests,
   UpdateRequest,
   DeleteRequest,
   getRecruitmentbyId,
}