const RecruitmentModel = require('../models/tb_recruitment_team')

const getAllRecruitment = async (req, res) => {
    try {
        const [data]  = await RecruitmentModel.getAllRecruitment();
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
    const {identifier_recruitment_team} = req.params;
    try {
        const [data]  = await RecruitmentModel.getRecruitmentbyIdentifier(identifier_recruitment_team);
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

const createNewRecruitment = async (req, res) => {
    const { body } = req;

    if (
        !body.name_team ||
        !body.post_team ||
        !body.domicile_team ||
        !body.job_description ||
        !body.experience ||
        !body.certificate
      ) {
        return res.status(400).json({
            message: 'Masukkan data yang benar',
            result: null,
        });
    }

    try {
            await RecruitmentModel.createNewRecruitment(body);
            res.status(201).json({
                value: 1,
                result: body,
            });
        }
    catch (error) {
        res.status(500).json({
            value: 0,
            ServerMessage: error,
        });
    }
};


const UpdateRecruitment = async(req, res) => {
    const {identifier_recruitment_team} = req.params;
    const {body} = req;
    try {
        await RecruitmentModel.UpdateRecruitment(body, identifier_recruitment_team);
        res.json({
            message: 'UPDATE Team Success',
            result: {
                id: identifier_recruitment_team,
                ...body
            },
        })
    }catch (error) {
        res.status(500).json({
            value:0,
            ServerMessage: error,
        })
    }  
}

const DeleteRecruitment = async(req, res) => {
    const {identifier_recruitment_team} = req.params;
    console.log('identifier_recruitment_team:', identifier_recruitment_team);

     try{
        await RecruitmentModel.DeleteRecruitment(identifier_recruitment_team);
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
   getAllRecruitment,
   createNewRecruitment,
   UpdateRecruitment,
   DeleteRecruitment,
   getRecruitmentbyId
}