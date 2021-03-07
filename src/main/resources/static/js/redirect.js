function redirectToProgram() {
    var input = document.getElementById("prog").value;
    var selected = document.querySelector(".logo a");
    var pCode = input.match(/\(([^)]*)\)[^(]*$/);
    var attr = selected.getAttribute("href").split("/");

    url = new URL(window.location.href);
    baseURL = url.hostname;
    protocol = url.protocol;
    redirectTo = protocol + '//' + baseURL + '/opleidingen/' + attr[2] + '/' + pCode[1];
    location.replace(redirectTo);
}