const logRequest = (req,res,next) => {
    console.log('log req di path: ', req.path);
    next();
};

module.exports = logRequest;