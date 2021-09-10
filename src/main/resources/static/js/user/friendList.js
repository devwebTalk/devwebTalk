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
	var groupCount = [];
	var groupCountIndex = -1;
	for (var ob of list) {
		var groupName = !!ob.groupName ? ob.groupName : "친구";
		if(ob.groupId === saveId) {
			appendNodeFriendList(ob.friendName);
			groupCount[groupCountIndex] += 1;
		} else {
			groupCountIndex++;
			groupCount[groupCountIndex] = 1;
			appendHeadingFriendList(groupName,groupCountIndex);
			appendNodeFriendList(ob.friendName);
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

function appendHeadingFriendList(groupName, index) {
	var heading = document.createElement("h6");
	heading.classList.add("border-bottom");
	heading.classList.add("pb-2");
	heading.classList.add("mb-0");
	heading.classList.add("mt-3");
	heading.innerText = groupName;
	var span = document.createElement("span");
	span.id = groupName + index;
	document.getElementById("friendList").appendChild(heading);
	heading.appendChild(span);
}

function appendNodeFriendList(friendName) {
	var head = document.createElement("div");
	head.classList.add("d-flex");
	head.classList.add("text-muted");
	head.classList.add("pt-3");
	head.classList.add("pointer");
	var svg = document.createElement("svg");
	svg.classList.add("bd-placeholder-img");
	svg.classList.add("flex-shrink-0");
	svg.classList.add("me-2");
	svg.classList.add("rounded");
	svg.clientWidth = 32;
	svg.clientHeight = 32;
	svg.setAttribute("xmlns", "http://www.w3.org/2000/svg");
	svg.setAttribute("role", "img");
	svg.setAttribute("aria-label", "32x32");
	svg.setAttribute("preserveAspectRatio", "MidYMid slice");
	svg.setAttribute("focusable", "false");
	var paragraph = document.createElement("p");
	paragraph.classList.add("mb-0");
	paragraph.classList.add("border-bottom");
	var strong = document.createElement("strong");
	strong.classList.add("d-block");
	strong.classList.add("text-gray-dark");
	strong.innerText = friendName;

	document.getElementById("friendList").appendChild(head);
	head.appendChild(svg);
	head.appendChild(paragraph);
	paragraph.appendChild(strong);
}



function listRefresh(html) {
	var friendList = document.getElementById('friendList');
	while(friendList.hasChildNodes()) friendList.removeChild(friendList.firstChild);
	friendList.innerHTML = html;
}