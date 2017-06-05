//存放主要交互逻辑js代码
//javascript 模块化
var seckill = {
	//封装秒杀相关ajax的url	
	URL:{
		now:function(){
			return basePath+'/seckill/time/now';
		},
		exposer:function(seckillId){
			return basePath + '/seckill/'+ seckillId+'/exposer';
		},
		execution : function(seckillId,md5){
			return basePath + '/seckill/'+seckillId + '/' + md5 +'/execution';
		}
	},
	handleSeckillkill : function(seckillId,node){
		//处理秒杀逻辑
		node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
		$.post(seckill.URL.exposer(seckillId),{},function(result){
			//在回调函数中，执行交互流程
			if(result && result['success']){
				var exposer =  result['data'];
				if(exposer['exposed']){
					//开启秒杀
					var md5 = exposer['md5'];
					//获取秒杀地址
					var killUrl = seckill.URL.execution(seckillId,md5);
					console.log('killUrl:'+killUrl);
					//绑定一次点击事件
					$('#killBtn').one('click',function(){
						//执行秒杀请求
						$(this).addClass('disabled');
						//2:发送秒杀的请求
						$.post(killUrl,{},function(result){
							if(result && result['success']){
								var killResult = result['data'];
								var state = killResult['state'];
								var stateInfo = killResult['stateInfo'];
								//显示秒杀结果
								node.html('<span class="label label-success">'+stateInfo+'</span>');
							}
						});
					});
					node.show();
				}else{
					//未开启秒杀，客户机时间与服务器的时间有偏差
					var now = exposer['now'];
					var start = exposer['start'];
					var end = exposer['end'];
					//重新计算计时逻辑
					seckill.countdown(seckillId,now,start,end);
				}
			}else{
				console.log('result:'+result);
			}
		});
	},
	//验证手机号
	validatePhone:function(phone){
		if(phone && phone.length == 11 && !isNaN(phone)){
			return true;
		} else {
			return false;
		}
	},
	countdown:function(seckillId,nowTime,startTime,endTime){
		var seckillBox = $('#seckill-box');
		//时间判断
		if(nowTime  > endTime){
			seckillBox.html('秒杀结束!');
		}else if(nowTime < startTime){
			//秒杀未开始,添加时间偏移，保证秒杀安全
			var killTime = new Date(startTime + 1000);
			seckillBox.countdown(killTime,function(event){
				//时间格式
				var format = event.strftime('秒杀倒计时： %D天 %H时 %M分 %S秒');
				seckillBox.html(format);
			}).on('finish.countdown',function(){
				//获取秒杀地址，控制显示逻辑，执行秒杀
				seckill.handleSeckillkill(seckillId,seckillBox);
			});
		}else{
			//秒杀开始
			seckill.handleSeckillkill(seckillId,seckillBox);
		}
	},
	//详情页秒杀逻辑
	detail:{
		//详情页初始化
		init : function(params){
			//手机验证和登陆,计时交互
			//规划我们的交互流程
			//在cookie中查找手机号
			var killPhone = $.cookie('killPhone');
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			var seckillId = params['seckillId'];
			//验证手机号
			if(!seckill.validatePhone(killPhone)){
				//绑定Phone
				//控制输出
				var killPhoneModal = $('#killPhoneModal');
				//显示弹出层
				killPhoneModal.modal({
					//显示弹出层
					show:true,
					//禁止位置关闭
					backdrop:'static',
					//关闭键盘事件
					keyboard:false
				});
				$('#killPhoneBtn').click(function(){
					var inputPhone = $('#killPhoneKey').val();
					if(seckill.validatePhone(inputPhone)){
						//电话写入cookie
						$.cookie('killPhone',inputPhone,{expires:7,path:'/seckill'});
						//验证通过，刷新页面
						window.location.reload();
					}else{
						$('#killPhoneMessage').hide().html('<label class="label label-danger">手机号码错误</label>').show(300);
					}
				});
			}
			//已经登陆
			//计时交互
			$.get(seckill.URL.now(),{},function(result){
				if(result && result['success']){
					var nowTime = result['data'];
					//时间判断,计时交互
					seckill.countdown(seckillId,nowTime,startTime,endTime);
				}else{
					console.log('result:'+result);
				}
			});
		}
	}
}

















