var express = require('express');
var router = express.Router();

let database = require('../database');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('add', { title: 'Add' });
});

router.get('/update', function(req, res, next) {
  res.render('update', { title: 'Update' });
});

router.get('/complete', function(req, res, next) {
  res.render('complete', {title: 'Complete', message: 'You\'re a little lost. Use the navigation bar to get back on track'});
});

router.post('/', function(req, res, next) {
  console.log(req.body);

  //Credit: https://github.com/mysqljs/mysql#transactions
  database.beginTransaction(function(err) {
    if (err) {
      res.render('complete', {title: 'Complete', message: 'An error happened. That\'s all we know'});
    }

    database.query('INSERT INTO DirectoryEntry (phone, name, address, description) VALUES (?, ?, ?, ?);', [req.body.phone, req.body.name, req.body.address, req.body.description], (err, result, fields) => {
      if (err) {
        return database.rollback(function() {
          res.render('complete', {title: 'Complete', message: 'An error happened. That\'s all we know'});
        });
      }
    });

    database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.mondayOpeningTime, req.body.mondayClosingTime, 0], (err, result, fields) => {
      if (err) {
        return database.rollback(function() {
          res.render('complete', {title: 'Complete', message: 'An error happened. That\'s all we know'});
        });
      }
    });
    database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.tuesdayOpeningTime, req.body.tuesdayClosingTime, 1], (err, result, fields) => {
      if (err) {
        return database.rollback(function() {
          res.render('complete', {title: 'Complete', message: 'An error happened. That\'s all we know'});
        });
      }
    });
    database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.wednesdayOpeningTime, req.body.wednesdayClosingTime, 2], (err, result, fields) => {
      if (err) {
        return database.rollback(function() {
          res.render('complete', {title: 'Complete', message: 'An error happened. That\'s all we know'});
        });
      }
    });
    database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.thursdayOpeningTime, req.body.thursdayClosingTime, 3], (err, result, fields) => {
      if (err) {
        return database.rollback(function() {
          res.render('complete', {title: 'Complete', message: 'An error happened. That\'s all we know'});
        });
      }
    });
    database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.fridayOpeningTime, req.body.fridayClosingTime, 4], (err, result, fields) => {
      if (err) {
        return database.rollback(function() {
          res.render('complete', {title: 'Complete', message: 'An error happened. That\'s all we know'});
        });
      }
    });
    database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.saturdayOpeningTime, req.body.saturdayClosingTime, 5], (err, result, fields) => {
      if (err) {
        return database.rollback(function() {
          res.render('complete', {title: 'Complete', message: 'An error happened. That\'s all we know'});
        });
      }
    });
    database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.sundayOpeningTime, req.body.sundayClosingTime, 6], (err, result, fields) => {
      if (err) {
        return database.rollback(function() {
          res.render('complete', {title: 'Complete', message: 'An error happened. That\'s all we know'});
        });
      }
      database.commit(function(err) {
        if (err) {
          return database.rollback(function() {
            res.render('complete', {title: 'Complete', message: 'An error happened. That\'s all we know'});
          });
        }
        console.log('success!');
        res.render('complete', {title: 'Complete', message: 'Successfully Added'});
      });
    });
  });
});

module.exports = router;
