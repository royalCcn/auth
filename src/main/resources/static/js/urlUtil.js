/*
 * [通过参数名获取url中的参数值]
 * 示例URL:http://127.0.0.1/index.html?id=admin&name=小明
 * @param  {[string]} queryName [参数名]
 * @return {[string]}           [参数值]
 */
function GetQueryValue(queryName) {
	var query = decodeURI(window.location.search.substring(1));
	var vars = query.split("&");
	for (var i = 0; i < vars.length; i++) {
		var pair = vars[i].split("=");
		if (pair[0] == queryName) { return pair[1]; }
	}
	return null;
}