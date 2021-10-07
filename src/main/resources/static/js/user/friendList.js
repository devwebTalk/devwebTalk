$(document).ready(function () {
	getFriendList();
});

function getFriendList() {
	var settings = {
		"url": "/friend/list",
		"method": "GET",
		"timeout": 0
	};

	$.ajax(settings).done(function (list) {
		listClear("friendList");
		parsingList(list);
		setModalEvent();
		$(".moveGroup").click(function() {
			getFriendGroupList($(this).data().id);
			listModal.show();
		});
		$(".modGroup").click(function() {
			modModal.show();
		});
	});
}

function parsingList(list) {
	var saveId = -1;
	var groupCount = [];
	var groupCountIndex = -1;
	for (var ob of list) {
		var groupName = !!ob.groupName ? ob.groupName : "친구";
		if(ob.groupId === saveId) {
			appendNodeFriendList(ob.friendName, ob.friendId);
			groupCount[groupCountIndex] += 1;
		} else {
			groupCountIndex++;
			groupCount[groupCountIndex] = 1;
			appendHeadingFriendList(groupName,groupCountIndex);
			appendNodeFriendList(ob.friendName, ob.friendId);
			saveId = ob.groupId;
		}
	}

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
	heading.classList.add("text-dark");
	heading.innerText = groupName + " ";
	var span = document.createElement("span");
	span.classList.add("text-secondary");
	span.id = "groupCount" + index;
	document.getElementById("friendList").appendChild(heading);
	heading.appendChild(span);
}

function appendNodeFriendList(friendName, friendId) {
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
	paragraph.classList.add("flex-grow-1");
	paragraph.classList.add("border-bottom");
	paragraph.classList.add("d-flex");
	paragraph.classList.add("align-items-center");
	paragraph.classList.add("list-friend");
	var strong = document.createElement("strong");
	strong.classList.add("d-block");
	strong.classList.add("ms-2");
	strong.classList.add("text-gray-dark");
	strong.innerText = friendName;
	var btnMove	= document.createElement("button");
	var btnMod 	= document.createElement("button");
	var btnDel 	= document.createElement("button");
	btnMove.dataset.id = friendId;
	btnMod.dataset.id = friendId;
	btnDel.dataset.id = friendId;
	btnMove.dataset.action = "move";
	btnMod.dataset.action = "mod";
	btnDel.dataset.action = "del";
	btnMove.classList.add("btn");
	btnMove.classList.add("m-1");
	btnMove.classList.add("btn-outline-primary");
	btnMove.classList.add("moveGroup");
	btnMod.classList.add("btn");
	btnMod.classList.add("m-1");
	btnMod.classList.add("btn-outline-secondary");
	btnMod.classList.add("modGroup");
	btnDel.classList.add("btn");
	btnDel.classList.add("m-1");
	btnDel.classList.add("btn-outline-danger");
	btnMove.innerText = "이동";
	btnMod.innerText = "수정";
	btnDel.innerText = "삭제";

	document.getElementById("friendList").appendChild(head);
	head.appendChild(svg);
	head.appendChild(paragraph);
	head.appendChild(btnMove);
	head.appendChild(btnMod);
	head.appendChild(btnDel);
	paragraph.appendChild(strong);
}

function listClear(nodeId) {
	var node = document.getElementById(nodeId);
	while(node.hasChildNodes()) node.removeChild(node.firstChild);
}

function getFriendGroupList(friendId) {
	var settings = {
		"url": "/friend/group",
		"method": "GET",
		"timeout": 0
	};

	$.ajax(settings).done(function (list) {
		parsingGroupList(list, friendId);
	});
}

function parsingGroupList(list, friendId) {
	listClear("friendGroupList");
	var groupList = document.getElementById("friendGroupList");
	var odd = true;
	for (var group of list) {
		var item = document.createElement("div");
		item.classList.add("list-group-item");
		item.classList.add("list-group-item-action");
		item.classList.add("pointer");
		if(odd) item.classList.add("list-group-item-primary");
		else item.classList.add("list-group-item-secondary");
		item.dataset.groupId = group.groupId;
		item.dataset.friendId = friendId;
		item.innerText = !group.groupName ? "그룹없음" : group.groupName;
		groupList.appendChild(item);
		odd = !odd;
	}
}

function moveFriend(node) {
	var friendId = node.dataset.friendId;
	var groupId = node.dataset.groupId;
}
// modal event	///////////
var listModal;
var modModal;
function setModalEvent() {
	listModal = new bootstrap.Modal(document.getElementById('listModal'));
	modModal = new bootstrap.Modal(document.getElementById('modModal'));
}