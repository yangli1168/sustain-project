var express = require('express');
var router = express.Router();
var fs = require('fs');
var PATH = './public/data/';

/* 读取数据模块 */
//data/read?type=it
//data/read?type=it.json
router.get('/read', function(req, res, next) {
   var type = req.param('type') || '';
   fs.readFile(PATH + type + '.json', function (err, data){
       if(err){
           return res.send({
               status:400,
               info:'读取文件出现异常'
           });
       }
       var count = 50;
       var obj = [];
       try {
           obj = JSON.parse(data.toString());
       } catch (e){
           obj = [];
       }

       if (obj.length > count){
           obj = obj.slice(0, count);
       }
       return res.send({
           status:200,
           data:obj
       });
   });
});

//数据存储模块
router.get('/write', function (req, res, next) {
    //文件名
    var type = req.param('type') || '';
    //关键字段
    var url = req.param('url') || '';
    var title = req.param('title') || '';
    if (!type || !url || !title){
        return res.send({
            status:400,
            info:'提交的字段不全'
        });
    }
    //拿到文件信息
    var filePah = PATH + type + '.json';
    fs.readFile(filePah, function(err, data) {
        if (err){
            return res.send({
               status:400,
               info:'读取数据失败'
            });
        }
        var arr = JSON.parse(data.toString());
        var obj = {
            url : url,
            title : title,
            id : guidGenerate(),
            time : new Date()
        };
        arr.splice(0, 0, obj);
        //写入文件
        var newData = JSON.stringify(arr);
        fs.writeFile(PATH + 'config.json', newData, function(err) {
            if (err){
                return res.send({
                    status : 400,
                    info : '写入文件失败'
                });
            }
            return res.send({
                status : 200,
                info : obj
            });
        });
    });

});

//阅读模块写入接口
router.post('/write_config', function(req, res, next) {
    //TODO:后期进行提交数据的验证
    //防xss攻击
    //npm install xss
    //require('xss')
    //var str = xss(name);
    var data = req.body.data;
    //Todo : try catch
    var obj = JSON.parse(data);
    var newData = JSON.stringify(obj);
    //写入
    fs.writeFile(filePah, newData, function(err) {
        if (err){
            return res.send({
                status : 400,
                info : '写入配置失败'
            });
        }
        return res.send({
            status : 200,
            info : obj
        });
    });
});

//登录接口
router.post('/login', function(req, res, next) {
    //用户名、密码、验证码
    var username = req.body.username;
    var password = req.body.password;

    //TODO : 验证
    //xss处理
    //密码加密 md5(password + '随机字符串')
    if (username === 'admin' && password === '123456'){
        req.session.user = {
          username : username
        };
        return req.send({
            status : 200
        });
    }

    return req.send({
       status : 400,
       info : '登录失败'
    });
});

//guid
function guidGenerate() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16 | 0,
            v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    }).toUpperCase();
}


module.exports = router;
