var express = require('express');
var router = express.Router();

let database = require('../database');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('add', { title: 'Add' });
});

router.get('/update', function (req, res, next) {
  res.render('pre-update', {title: 'Update'});
});

router.post('/post-update', function(req, res, next) {
  database.beginTransaction(function(err) {
    if (err) {
      res.render('error', {title: 'Error', message: 'An error happened.', error: err})
    }

    database.query('UPDATE DirectoryEntry SET name = ?, address = ?, description = ? WHERE phone = ?;', [req.body.name, req.body.address, req.body.description, req.body.phone], (err, result, fields) => {
      if (err) {
        return database.rollback(function() {
          res.render('error', {title: 'Error', message: 'An error happened.', error: err})
        });
      }

      database.query('UPDATE OpeningTimes SET open = ?, close = ? WHERE id = ? AND day = ?;', [req.body.mondayOpeningTime, req.body.mondayClosingTime, req.body.phone, 1], (err, result, fields) => {
        if (err) {
          return database.rollback(function() {
            res.render('error', {title: 'Error', message: 'An error happened.', error: err})
          });
        }

        database.query('UPDATE OpeningTimes SET open = ?, close = ? WHERE id = ? AND day = ?;', [req.body.tuesdayOpeningTime, req.body.tuesdayClosingTime, req.body.phone, 2], (err, result, fields) => {
          if (err) {
            return database.rollback(function() {
              res.render('error', {title: 'Error', message: 'An error happened.', error: err})
            });
          }

          database.query('UPDATE OpeningTimes SET open = ?, close = ? WHERE id = ? AND day = ?;', [req.body.wednesdayOpeningTime, req.body.wednesdayClosingTime, req.body.phone, 3], (err, result, fields) => {
            if (err) {
              return database.rollback(function() {
                res.render('error', {title: 'Error', message: 'An error happened.', error: err})
              });
            }

            database.query('UPDATE OpeningTimes SET open = ?, close = ? WHERE id = ? AND day = ?;', [req.body.thursdayOpeningTime, req.body.thursdayClosingTime, req.body.phone, 4], (err, result, fields) => {
              if (err) {
                return database.rollback(function() {
                  res.render('error', {title: 'Error', message: 'An error happened.', error: err})
                });
              }

              database.query('UPDATE OpeningTimes SET open = ?, close = ? WHERE id = ? AND day = ?;', [req.body.fridayOpeningTime, req.body.tuesdayClosingTime, req.body.phone, 5], (err, result, fields) => {
                if (err) {
                  return database.rollback(function() {
                    res.render('error', {title: 'Error', message: 'An error happened.', error: err})
                  });
                }

                database.query('UPDATE OpeningTimes SET open = ?, close = ? WHERE id = ? AND day = ?;', [req.body.saturdayOpeningTime, req.body.saturdayClosingTime, req.body.phone, 6], (err, result, fields) => {
                  if (err) {
                    return database.rollback(function() {
                      res.render('error', {title: 'Error', message: 'An error happened.', error: err})
                    });
                  }

                  database.query('UPDATE OpeningTimes SET open = ?, close = ? WHERE id = ? AND day = ?;', [req.body.sundayOpeningTime, req.body.sundayClosingTime, req.body.phone, 7], (err, result, fields) => {
                    if (err) {
                      return database.rollback(function() {
                        res.render('error', {title: 'Error', message: 'An error happened.', error: err})
                      });
                    }

                    database.commit(function(err) {
                      if (err) {
                        return database.rollback(function() {
                          res.render('error', {title: 'Error', message: 'An error happened.', error: err})
                        });
                      }

                      res.render('complete', {title: 'Complete', message: 'Successfully Updated'});
                    });
                  });
                });
              });
            });
          });
        });
      });
    });
  });
});

router.post('/update', function (req, res, next) {
  database.beginTransaction(function(err) {
    if (err) {
      res.render('error', {title: 'Error', message: 'An error happened.', error: err})
    }

    database.query('SELECT * FROM DirectoryEntry WHERE phone = ?;', [req.body.phone], (err, result, fields) => {
      if (err) {
        return database.rollback(function() {
          res.render('error', {title: 'Error', message: 'An error happened.', error: err})
        });
      }

      if (result.length === 0) {
        return database.rollback(function() {
          res.render('complete', {title: 'Complete', message: 'That phone number does not exist'});
        });
      }

      var dir = result[0];

      database.query("SELECT * FROM OpeningTimes WHERE id = ? ORDER BY day ASC;", [req.body.phone], (err, result, fields) => {
        if (err) {
          return database.rollback(function() {
            res.render('error', {title: 'Error', message: 'An error happened.', error: err})
          });
        }

        database.commit(function(err) {
          if (err) {
            return database.rollback(function() {
              res.render('error', {title: 'Error', message: 'An error happened.', error: err})
            });
          }

          if (result.length < 7) {
            return database.rollback(function() {
              res.render('error', {title: 'Error', message: 'An error happened.', error: err})
            });
          }

          res.render('update', {title: 'Complete', phone: dir.phone, name: dir.name, address: dir.address, description: dir.description,
            mondayOpen: result[0].open, mondayClose: result[0].close,
            tuesdayOpen: result[0].open, tuesdayClose: result[0].close,
            wednesdayOpen: result[0].open, wednesdayClose: result[0].close,
            thursdayOpen: result[0].open, thursdayClose: result[0].close,
            fridayOpen: result[0].open, fridayClose: result[0].close,
            saturdayOpen: result[0].open, saturdayClose: result[0].close,
            sundayOpen: result[0].open, sundayClose: result[0].close
          });
        });
      });
    });
  });
});

router.post('/', function(req, res, next) {

  //Credit: https://github.com/mysqljs/mysql#transactions
  database.beginTransaction(function(err) {
    if (err) {
      res.render('error', {title: 'Error', message: 'An error happened.', error: err})
    }

    database.query('INSERT INTO DirectoryEntry (phone, name, address, description) VALUES (?, ?, ?, ?);', [req.body.phone, req.body.name, req.body.address, req.body.description], (err, result, fields) => {
      if (err) {
        return database.rollback(function() {
          res.render('error', {title: 'Error', message: 'An error happened.', error: err})
        });
      }

      database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.mondayOpeningTime, req.body.mondayClosingTime, 0], (err, result, fields) => {
        if (err) {
          return database.rollback(function() {
            res.render('error', {title: 'Error', message: 'An error happened.', error: err})
          });
        }

        database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.tuesdayOpeningTime, req.body.tuesdayClosingTime, 1], (err, result, fields) => {
          if (err) {
            return database.rollback(function() {
              res.render('error', {title: 'Error', message: 'An error happened.', error: err})
            });
          }

          database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.wednesdayOpeningTime, req.body.wednesdayClosingTime, 2], (err, result, fields) => {
            if (err) {
              return database.rollback(function() {
                res.render('error', {title: 'Error', message: 'An error happened.', error: err})
              });
            }

            database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.thursdayOpeningTime, req.body.thursdayClosingTime, 3], (err, result, fields) => {
              if (err) {
                return database.rollback(function() {
                  res.render('error', {title: 'Error', message: 'An error happened.', error: err})
                });
              }

              database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.fridayOpeningTime, req.body.fridayClosingTime, 4], (err, result, fields) => {
                if (err) {
                  return database.rollback(function() {
                    res.render('error', {title: 'Error', message: 'An error happened.', error: err})
                  });
                }

                database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.saturdayOpeningTime, req.body.saturdayClosingTime, 5], (err, result, fields) => {
                  if (err) {
                    return database.rollback(function() {
                      res.render('error', {title: 'Error', message: 'An error happened.', error: err})
                    });
                  }

                  database.query('INSERT INTO OpeningTimes (id, open, close, day) VALUES (?,?,?,?)', [req.body.phone, req.body.sundayOpeningTime, req.body.sundayClosingTime, 6], (err, result, fields) => {
                    if (err) {
                      return database.rollback(function() {
                        res.render('error', {title: 'Error', message: 'An error happened.', error: err})
                      });
                    }
                    database.commit(function(err) {
                      if (err) {
                        return database.rollback(function() {
                          res.render('error', {title: 'Error', message: 'An error happened.', error: err})
                        });
                      }
                      res.render('complete', {title: 'Complete', message: 'Successfully Added'});
                    });
                  });
                });
              });
            });
          });
        });
      });
    });
  });
});

module.exports = router;
