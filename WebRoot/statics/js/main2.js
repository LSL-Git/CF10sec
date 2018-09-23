var show = document.getElementById("show");
var result = document.getElementById("result");
var button = document.getElementById("button");
var token=$("#token").val();
var path = $("#path").val();
var t;
var num1 = 0;
var num2 = 0;
var num3 = 0;
var text_num1 = "";
var text_num2 = "";
var text_num3 = "";
var count = 0;
function stop() {

	if (button.value === "开始") {
		timedCount();
		button.style.background = "#d40007";
		button.value = "停";
	} else if (button.value === "停") {
		stopCount();
		button.style.background = "#d0ca0d";
		button.value = "重置";
	} else {
		window.location.reload();
	}
}

var arr = new Array("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
function oneton() {
	return Math.floor(Math.random() * 9 + 1);
}
function encode(k, n, c) {
	var x = oneton();// %3; // 1-9整数
	var r = x + arr[oneton()] + (k << x) + arr[oneton()] + ((n + 1) << x)
			+ arr[oneton()] + (c << x);
	return r;
}

function timedCount() {
	result.innerText = "";
	show.style.fontSize = "60px";
	show.style.color = "#000000";
	if (num3 % 2 === 1) {
		num2 += 1;
	}
	if (num3 === 100) {
		num3 = 0;
	}
	if (num2 === 100) {
		num1 += 1;
		num2 = 0;
	}
	if (num3 < 10) {
		text_num3 = "0" + num3;
	} else {
		text_num3 = num3;
	}
	if (num2 < 10) {
		text_num2 = "0" + num2;
	} else {
		text_num2 = num2;
	}
	if (num1 < 10) {
		text_num1 = "0" + num1;
	} else {
		text_num1 = num1;
	}

	show.innerText = text_num1 + ":" + text_num2 + ":" + text_num3;
	t = setTimeout("timedCount()", 1); // 开始计时
	num3 += 1;
	count += 1;
	if (num1 >= 15) {
		stopCount();
	}
}

function stopCount() {
	clearTimeout(t); // 停止计时
	Count(encode(num1, num2, num3), encode(num2, num3, num2), encode(num3,
			num2, num1));
}

function trans(c, tx) {
	show.style.color = c;
	result.style.color = c;
	show.style.fontSize = "40px";
	button.style.background = "#d0ca0d";
	button.value = "重置";
	result.innerText = tx;
}

function Count(a, b, c) {
	$.ajax( {
		type : "POST",
		url : path + "/r.json",
		data : {
			n1 : a,
			n2 : b,
			n3 : c,
			token:token,
		},
//		contentType:"application/json",
		dataType : "json",
		success : function(data) {
			if (data["result"] == "true") {
				trans("#3dc711", "挑战成功");
			} else {
				trans("#d40007", "挑战失败");
			}
		},
		error : function(data) {
			alert("访问出错,请刷新重试！");
		}
	});
}
