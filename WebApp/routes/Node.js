const mariadb = require('mariadb');
const pool = mariadb.createPool({
    host: '81.104.43.46',
    port: '3306',
    database: 'ecoSwellDatabase',
    user:'appUser',
    password: '9iPTs#ko',
});

async function asyncFunction() {
    try {
        let conn = await pool.getConnection();
        console.log('connected!');
    } catch (err) {
        throw err;
    }
}