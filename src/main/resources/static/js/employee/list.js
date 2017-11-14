var pageSize = 5;
$(function() {
	refreshDable();
});

toastr.options = { 
		closeButton: false,  
		debug: false,  
		progressBar: true,  
		positionClass: "toast-top-center",  
		onclick: null,  
		showDuration: "300",  
		hideDuration: "1000",  
		timeOut: "800",  
		extendedTimeOut: "1000",  
		showEasing: "swing",  
		hideEasing: "linear",  
		showMethod: "fadeIn",  
		hideMethod: "fadeOut"  
 };  

function refreshDable(currentPage) {
	currentPage = currentPage ? currentPage:1;
	$.ajax({
		type : "get",
		cache: false,
		url : "/employee/list",
		data : {"pageSize": pageSize, "currentPage": currentPage},
		timeout : 20000,
		success : function(result) {
			var dataObj = result.data;
			var total = dataObj.total;
			var rowsList = dataObj.rows;
			
			var sHtml = "";
			for (var i = 0; i < rowsList.length; i++) {
				var rowObj = rowsList[i];
				sHtml += "<tr>";
				sHtml += "<td>" + rowObj.empno + "</td>";
				sHtml += "<td>" + rowObj.ename + "</td>";
				sHtml += "<td>" + rowObj.job + "</td>";
				sHtml += "<td><a style='cursor:pointer' onclick='updateEmployeeUI(this);'>修改&nbsp;&nbsp;</a><a style='cursor:pointer' onclick='deleteEmployee("+ rowObj.empno +");'>删除</a></td>";
				sHtml += "</tr>";
			}
			$("#employeeList").html(sHtml);
			createPage(total, currentPage);
		}
	});
}

function createPage(total, currentPage) {
	var totalPage = (total-1)/pageSize + 1;
	var sHtml = "";
	for(var i=1; i<=totalPage; i++) {
		if(i==currentPage) {
			sHtml += "<li class='active'><a style='cursor:pointer' onclick='refreshDable("+ i + ")'>" + i + "</a></li>";
		} else {
			sHtml += "<li><a style='cursor:pointer' onclick='refreshDable("+ i + ")'>" + i + "</a></li>";
		}
	}
	$("#pager").html(sHtml);
}

function addEmployeeUI() {
	clearForm();
	$('#exampleModal').modal('show');
	$('#exampleModal').find('.modal-title').text('添加职员');
}

function saveEmployee() {
	var empno = $("#empno").val();
	var ename = $("#ename").val();
	var job = $("#job").val();
	if(!$.trim(ename)) {
		alert("员工姓名不能为空");
		return;
	}
	if(!$.trim(job)) {
		alert("职务不能为空");
		return;
	}
	
	var sendObj = new Object();
	sendObj.empno = empno;
	sendObj.ename = $.trim(ename);
	sendObj.job = $.trim(job);
	
	$.ajax({
		type : "post",
		url : !empno ? "/employee/add": "/employee/update",
		data : sendObj,
		timeout : 20000,
		success : function(result) {
			if(result.code==0) {
				toastr.success(!empno ? "添加成功": "修改成功");
				$('#exampleModal').modal('hide');
				clearForm();
				refreshDable();
			} else {
				alert(result.msg);
			}
		}
	});
}

function updateEmployeeUI(obj) {
	var tr = $(obj).parents("tr");
	var empno = tr.find("td").eq(0).text();
	var ename = tr.find("td").eq(1).text();
	var job = tr.find("td").eq(2).text();
	$("#empno").val(empno);
	$("#ename").val(ename);
	$("#job").val(job);
	$('#exampleModal').modal('show');
	$('#exampleModal').find('.modal-title').text('修改职员');
}

function updateEmployee() {
	
}

function deleteEmployee(empno) {
	if(window.confirm("确认删除吗")) {
		$.ajax({
			type : "get",
			url : "/employee/delete",
			data : {empno:empno},
			timeout : 20000,
			success : function(result) {
				if(result.code==0) {
					toastr.success('删除成功');
					$('#exampleModal').modal('hide');
					refreshDable();
				} else {
					alert(result.msg);
				}
			}
		});
	}
}

function clearForm() {
	$("#empno").val("");
	$("#ename").val("");
	$("#job").val("");
}