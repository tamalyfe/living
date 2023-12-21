const LoginModel = require('../models/tb_recruitment_team')

const checkLogin = async (req, res) => {
    const { body } = req;

    if (!body.emailorusername || !body.password) {
        return res.status(400).json({
            message: 'Invalid request. Provide both email or username and password.',
            data: null,
        });
    }

    let condition;
    let value;

    if (body.emailorusername.includes('@')) {
        condition = 'email';
        value = body.emailorusername;
    } else {
        condition = 'username';
        value = body.emailorusername;
    }

    try {
        const result = await LoginModel.checkDataRecruitmentLogin({
            [condition]: value,
            password: body.password,
        }); 

        const [dataResult, columnInfo] = result;

        if (dataResult.length > 0) {
            const { username, email, password, user_exists } = dataResult[0];
        
            if (user_exists) {
                if (body.password === password) {
                    res.status(200).json({
                        message: 'Login successful',
                        data: { username, email },
                    });
                } else {
                    res.status(401).json({
                        message: 'Incorrect password. Please check your password.',
                        data: body.password,
                    });
                }
            } else {
               res.status(401).json({
                    message: 'Invalid data. Please check your username or email.',
                    data: null,
                });
            }
        } else {
            res.status(401).json({
                message: 'Invalid data. Please check your username or email.',
                data: null,
            });
        }
    } catch (error) {
        console.error(error); 
        res.status(500).json({
            message: 'Server Error',
            ServerMessage: error.message || 'An internal server error occurred.',
        });
    }
};



module.exports = {
    checkLogin
 }