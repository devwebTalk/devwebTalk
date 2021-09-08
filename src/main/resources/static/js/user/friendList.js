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
	var lineStrBegin = "<div class=\"d-flex text-muted pt-3\"><svg class=\"bd-placeholder-img flex-shrink-0 me-2 rounded\" width=\"32\" height=\"32\" xmlns=\"http://www.w3.org/2000/svg\" role=\"img\" aria-label=\"Placeholder: 32x32\" preserveAspectRatio=\"xMidYMid slice\" focusable=\"false\"><title>Placeholder</title><rect width=\"100%\" height=\"100%\" fill=\"#007bff\"/><text x=\"50%\" y=\"50%\" fill=\"#007bff\" dy=\".3em\">32x32</text></svg>\n" +
		"<p class=\"pb-3 mb-0 small lh-sm border-bottom\"><strong class=\"d-block text-gray-dark\">";
	var lineStrEnd = "</strong></p></div>";
	for (var ob of list) {
		if(ob.groupId === saveId) {
			html += lineStrBegin + ob.friendName+ lineStrEnd;
		} else {
			html += "<h6 class=\"border-bottom pb-2 mb-0\">" + ob.groupName + "</h6>";
			html += lineStrBegin + ob.friendName+ lineStrEnd;
			saveId = ob.groupId;
		}
	}
	listRefresh(html);
}

function listRefresh(html) {
	const friendList = document.getElementById('friendList');
	while(friendList.hasChildNodes()) friendList.removeChild(friendList.firstChild);
	friendList.innerHTML = html;
}