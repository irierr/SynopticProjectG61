const sql = require('mysql');

const connection = sql.createConnection({
    host: '81.104.43.46',
    port: '3306',
    database: 'ecoSwellDatabase',
    user:'appUser',
    password: '9iPTs#ko'
})

connection.connect((err) => {
    if (err) {
        console.log('SQL Connection error');
    }

    console.log('SQL Connected')
})

module.exports = connection;