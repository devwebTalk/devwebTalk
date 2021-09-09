$(document).ready(function () {
	getFriendList();
});

function getFriendList() {
	var settings = {
		"url": "/friend/list",
		"method": "GET",
		"timeout": 0,
	};

	$.ajax(settings).done(function (response) {
		parsingList(response);
	});
}

function parsingList(list) {
	var saveId = -1;
	var html = "";
	var lineStrBegin = "<div class=\"d-flex text-muted pt-3 pointer\"><svg class=\"bd-placeholder-img flex-shrink-0 me-2 rounded\" width=\"32\" height=\"32\" xmlns=\"http://www.w3.org/2000/svg\" role=\"img\" aria-label=\"Placeholder: 32x32\" preserveAspectRatio=\"xMidYMid slice\" focusable=\"false\"><title>Placeholder</title><rect width=\"100%\" height=\"100%\" fill=\"#007bff\"/><text x=\"50%\" y=\"50%\" fill=\"#007bff\" dy=\".3em\">32x32</text></svg>\n" +
		"<p class=\"mb-0 border-bottom\"><strong class=\"d-block text-gray-dark\">";
	var lineStrEnd = "</strong></p></div>";
	var groupCount = [];
	var groupCountIndex = -1;
	for (var ob of list) {
		var groupName = !!ob.groupName ? ob.groupName : "친구";
		if(ob.groupId === saveId) {
			html += lineStrBegin + ob.friendName+ lineStrEnd;
			groupCount[groupCountIndex] += 1;
		} else {
			groupCountIndex++;
			groupCount[groupCountIndex] = 1;
			html += "<h6 class=\"border-bottom pb-2 mb-0 mt-3\">" + groupName + " <span id='groupCount" + groupCountIndex + "'></span></h6>";
			html += lineStrBegin + ob.friendName+ lineStrEnd;
			saveId = ob.groupId;
		}
	}

	listRefresh(html);
	let length = groupCount.length;
	for (var i = 0; i < length; i++) {
		var count = document.getElementById("groupCount" + i);
		count.innerText = groupCount[i] + ' 명';
	}
}

function listRefresh(html) {
	var friendList = document.getElementById('friendList');
	while(friendList.hasChildNodes()) friendList.removeChild(friendList.firstChild);
	friendList.innerHTML = html;
}