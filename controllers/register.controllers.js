const RegisterModel = require('../models/tb_recruitment_team')

const RegisterNewRecruitment = async (req, res) => {
    const { body } = req;

    if (
        !body.name_team ||
        !body.post_team ||
        !body.domicile_team ||
        !body.job_description ||
        !body.experience ||
        !body.certificate ||
        !body.email_recruitment_team ||
        !body.password_recruitment_team ||
        !body.username
    ) {
        return res.status(400).json({
            message: 'Masukkan data yang benar',
            data: null,
        });
    }

    try {
        const usernameExists = await RegisterModel.checkUsernameExists(body.username);
        if (usernameExists) {
            return res.status(400).json({
                message: 'Username already exists. Choose a different username.',
                data: null,
            });
        }

        const recruitmentData = {
            name_team: body.name_team,
            post_team: body.post_team,
            domicile_team: body.domicile_team,
            job_description: body.job_description,
            experience: body.experience,
            certificate: body.certificate,
        };
        await RegisterModel.createNewRecruitment(recruitmentData);
        
        // Get the identifier_recruitment_team from the inserted recruitment data
        const { identifier_recruitment_team } = await RegisterModel.checkRecruitmentIdentifier(recruitmentData);
        
        // Create new login data
        const loginData = {
            identifier_recruitment_team,
            email_recruitment_team: body.email_recruitment_team,
            password_recruitment_team: body.password_recruitment_team,
            username: body.username,
        };
        await RegisterModel.createNewRecruitmentLoginData(loginData);

        res.status(201).json({
            message: 'Create New Recruitment Success',
            data: {
                recruitment: recruitmentData,
                login: loginData,
            },
        });
    } catch (error) {
        res.status(500).json({
            message: 'Server Error',
            ServerMessage: error,
        });
    }
};

module.exports = {
    RegisterNewRecruitment
 }