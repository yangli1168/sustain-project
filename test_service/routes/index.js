var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express测试' });
  //   return res.send({
  //       status:200,
  //       info:'测试服务'
  //   });
});

module.exports = router;
