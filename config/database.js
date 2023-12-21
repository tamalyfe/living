
const mysql = require('mysql2');

require('dotenv').config();

const dbpool = mysql.createPool({
    host: process.env.DB_HOST,
    user: process.env.DB_USERNAME,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    waitForConnections: true,
    connectionLimit: 10,
    queueLimit: 0,
});

console.log(`Connecting to MySQL database on host ${process.env.DB_HOST}`);

module.exports = dbpool.promise();


