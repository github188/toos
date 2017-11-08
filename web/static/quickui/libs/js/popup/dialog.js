function stopEvent(a) {
    a = window.event || a,
    a && (isGecko && (a.preventDefault(), a.stopPropagation()), a.cancelBubble = !0, a.returnValue = !1)
}
function removeWinArray(a, b, c) {
    var d, e;
    if (c) {
        for (d = [], e = 0; e < a.length; e++) b == a[e] && d.push(a.splice(e, 1)[0]);
        return d
    }
    for (e = 0; e < a.length; e++) b == a[e] && a.splice(e, 1);
    return a
}
function fixProgress() {
    try {
        1 == top.progressFlag && (top.progressFlag = 0)
    } catch(a) {}
}
function getArrayPosition(a, b) {
    var d, c = -1;
    for (d = 0; d < b.length; d++) if (a == b[d]) {
        c = d;
        break
    }
    return c
}
function getTaskTarget() {
    var b, a = "dialogTask";
    return 1 == $("#skin").attr("splitMode") || "true" == $("#skin").attr("splitMode") || (b = $(window.top.document.getElementById("theme")), null != b.attr("taskTarget") && (a = b.attr("taskTarget"))),
    a
}
function setTaskCurrent(a, b) {
    a.find("a").removeClass("dialogTaskItemCurrent"),
    0 != b.length && a.find("#" + b[b.length - 1]).addClass("dialogTaskItemCurrent")
}
function resetDialogTask() {
    var b, c, d, a = 0;
    1 == $("#skin").attr("splitMode") || "true" == $("#skin").attr("splitMode") ? (itemContainerWidth = window.document.documentElement.clientWidth - dialogTaskWidthModify, dialogTaskTop = window.document.documentElement.clientHeight - a + "px") : (itemContainerWidth = top.window.document.documentElement.clientWidth - dialogTaskWidthModify, b = $(window.top.document.getElementById("theme")), null != b.attr("dialogTaskBottom") && (a = Number(b.attr("dialogTaskBottom"))), dialogTaskTop = top.window.document.documentElement.clientHeight - a + "px"),
    c = getTaskTarget(),
    "" != c && (d = $("#" + c), d[0] && "none" != d[0].style.display && (itemTotalWidth > itemContainerWidth ? (d.find(".taskItemButtonLeft").show(), d.find(".taskItemButtonRight").show()) : (d.find(".taskItemButtonLeft").hide(), d.find(".taskItemButtonRight").hide(), d.find(".taskItemContainer").css("left", dialogTaskStart))))
}
var instance, dialogTaskTop, $topWindow, $bodyDimensions, fadeEffect, topWin, topDoc, Dialog, timer, itemContainerWidth, currentItemTotalWidth, itemTotalWidth, dialogTaskWidthModify, dialogTaskStart, IMAGESPATH = "",
HideScrollbar = !0,
agt = window.navigator.userAgent,
isIE = -1 != agt.toLowerCase().indexOf("msie"),
isGecko = -1 != agt.toLowerCase().indexOf("gecko"),
ieVer = isIE ? parseInt(agt.split(";")[1].replace(/(^\s*)|(\s*$)/g, "").split(" ")[1]) : 0,
isIE8 = !!window.XDomainRequest && !!document.documentMode,
isIE7 = 7 == ieVer && !isIE8,
ielt7 = isIE && 7 > ieVer,
isQuirks = "BackCompat" == document.compatMode,
maxIndex = 900,
$id = function(a) {
    return "string" == typeof a ? document.getElementById(a) : a
};
if (!isIE && HTMLElement) HTMLElement.prototype.attachEvent || (window.attachEvent = document.attachEvent = HTMLElement.prototype.attachEvent = function(a, b) {
    a = a.substring(2),
    this.addEventListener(a, b, !1)
},
window.detachEvent = document.detachEvent = HTMLElement.prototype.detachEvent = function(a, b) {
    a = a.substring(2),
    this.removeEventListener(a, b, !1)
});
else if (isIE && 8 > ieVer) try {
    document.execCommand("BackgroundImageCache", !1, !0)
} catch(e) {}
$topWindow = function() {
    var a = window;
    return a
},
$bodyDimensions = function(a) {
    var b, c, d, e, f, g, h;
    return a = a || window,
    b = a.document,
    c = "BackCompat" == b.compatMode ? b.body.clientWidth: b.documentElement.clientWidth,
    d = "BackCompat" == b.compatMode ? b.body.clientHeight: b.documentElement.clientHeight,
    e = Math.max(b.documentElement.scrollLeft, b.body.scrollLeft),
    f = Math.max(b.documentElement.scrollTop, b.body.scrollTop),
    g = Math.max(b.documentElement.scrollWidth, b.body.scrollWidth),
    h = Math.max(b.documentElement.scrollHeight, b.body.scrollHeight),
    d > h && (h = d),
    {
        clientWidth: c,
        clientHeight: d,
        scrollLeft: e,
        scrollTop: f,
        scrollWidth: g,
        scrollHeight: h
    }
},
fadeEffect = function(a, b, c, d, e) {
    a.effect || (a.effect = {
        fade: 0,
        move: 0,
        size: 0
    }),
    clearInterval(a.effect.fade);
    var d = d || 20;
    a.effect.fade = setInterval(function() {
        b = c > b ? Math.min(b + d, c) : Math.max(b - d, c),
        a.style.opacity = b / 100,
        a.style.filter = "alpha(opacity=" + b + ")",
        b == c && (clearInterval(a.effect.fade), e && e.call(a))
    },
    20)
},
topWin = $topWindow(),
topDoc = topWin.document,
Dialog = function() {
    if (this.ID = null, this.Width = 550, this.Height = 380, this.URL = null, this.OnLoad = null, this.InnerHtml = "", this.InvokeElementId = "", this.Top = "50%", this.Left = "50%", this.Title = "　", this.OkButtonText = uncompile(quiLanguage.dialog.okButtonText), this.CancelButtonText = uncompile(quiLanguage.dialog.cancelButtonText), this.maxButtonTitle = uncompile(quiLanguage.dialog.maxButtonTitle), this.minButtonTitle = uncompile(quiLanguage.dialog.minButtonTitle), this.closeButtonTitle = uncompile(quiLanguage.dialog.closeButtonTitle), this.decreaseButtonTitle = uncompile(quiLanguage.dialog.decreaseButtonTitle), this.autoCloseMessage = uncompile(quiLanguage.dialog.autoCloseMessage), this.errorMessage = uncompile(quiLanguage.dialog.errorMessage), this.errorMessage2 = uncompile(quiLanguage.dialog.errorMessage2), this.alertTitleMessage = uncompile(quiLanguage.dialog.alertTitleMessage), this.confirmTitleMessage = uncompile(quiLanguage.dialog.confirmTitleMessage), this.OKEvent = null, this.CancelEvent = null, this.MaxEvent = null, this.DecreaseEvent = null, this.MinEvent = null, this.ShowButtonRow = !1, this.ShowOkButton = !0, this.ShowCancelButton = !0, this.MessageIcon = "window.gif", this.MessageTitle = "", this.Message = "", this.ShowMessageRow = !1, this.Modal = !0, this.Drag = !0, this.AutoClose = null, this.ShowCloseButton = !0, this.ShowMaxButton = !1, this.ShowMinButton = !1, this.Animator = !ielt7, this.MsgForESC = "", this.InnerFrameName = null, this.Style = "normal", this.ButtonAlign = "right", this.DecreaseResetPosition = !1, this.ResizeResetPosition = !0, this.Alpha = 40, this.Bgcolor = "#333333", this.CloseHideScroller = !1, this.ShowHideScroller = !0, this.AllowChangeIndex = null, this.MinToTask = !1, this.IconClass = "dialog_icon_default", this.IconSrc = "", this.ParamsObj = null, this.showShadow = !0, this.IframeBg = !1, this.AllowOutside = !1, this.ButtonWidth = !1, this.dialogDiv = null, this.bgDiv = null, this.openerWindow = null, this.openerDialog = null, this.innerFrame = null, this.innerWin = null, this.innerDoc = null, this.zindex = 900, this.cancelButton = null, this.okButton = null, this.maxButton = null, this.minButton = null, this.unauthorized = !1, this.maxFlag = !1, this.minFlag = !1, this.defaultFontSize = 12, this.defaultFontFamily = "微软雅黑", this.dialogWidthFix = 0, 1 == $("#skin").attr("splitMode") || "true" == $("#skin").attr("splitMode"));
    else {
        var a = $(window.top.document.getElementById("theme"));
        null != a.attr("defaultFontSize") && (this.defaultFontSize = Number(a.attr("defaultFontSize"))),
        null != a.attr("defaultFontFamily") && (this.defaultFontFamily = a.attr("defaultFontFamily")),
        null != a.attr("dialogWidthFix") && (this.dialogWidthFix = Number(a.attr("dialogWidthFix")))
    }
    arguments.length > 0 && "string" == typeof arguments[0] ? this.ID = arguments[0] : arguments.length > 0 && "object" == typeof arguments[0] && Dialog.setOptions(this, arguments[0]),
    this.ID || (this.ID = topWin.Dialog._dialogArray.length + "fk"),
    instance = this
},
Dialog._dialogArray = [],
Dialog._childDialogArray = [],
Dialog._taskArray = [],
Dialog.bgDiv = null,
Dialog.setOptions = function(a, b) {
    if (b) for (var c in b) a[c] = b[c]
},
Dialog.attachBehaviors = function() {
    document.attachEvent("onkeydown", Dialog.onKeyDown),
    window.attachEvent("onresize",
    function() {
        var a, b, c;
        for (Dialog.setBgDivSize(), a = 0, b = topWin.Dialog._dialogArray.length; b > a; a++) c = topWin.Dialog._dialogArray[a],
        c.ResizeResetPosition && c.setPosition()
    }),
    !HideScrollbar && ielt7 && window.attachEvent("onscroll", Dialog.resetPosition)
},
Dialog.prototype.attachBehaviors = function() {
    var b, c, a = this;
    this.Drag && topWin.Drag && (b = topWin.$id("_Draghandle_" + this.ID), c = topWin.$id("_DialogDiv_" + this.ID), topWin.Drag.init(b, c), c.onDragStart = function() { ! isIE && a.URL && (topWin.$id("_Covering_" + a.ID).style.display = "")
    },
    c.onDragEnd = function(b, c) { ! isIE && a.URL && (topWin.$id("_Covering_" + a.ID).style.display = "none");
        var f = $bodyDimensions(topWin);
        a.AllowOutside || (0 > b && (this.style.left = "0px"), b + this.clientWidth > f.clientWidth && (this.style.left = f.clientWidth - this.clientWidth + "px")),
        a.fixedPosition ? (0 > c && (this.style.top = "0px"), c + 33 > f.clientHeight && (this.style.top = f.clientHeight - 33 + "px")) : (c < f.scrollTop && (this.style.top = f.scrollTop + "px"), c + 33 > f.scrollTop + f.clientHeight && (this.style.top = f.scrollTop + f.clientHeight - 33 + "px"))
    })
},
Dialog.prototype.displacePath = function() {
    var a, b;
    if ("http://" == this.URL.substr(0, 7) || "/" == this.URL.substr(0, 1) || "javascript:" == this.URL.substr(0, 11)) return this.URL;
    for (a = this.URL, b = window.location.href, b = b.substring(0, b.lastIndexOf("/")); a.indexOf("../") >= 0;) a = a.substring(3),
    b = b.substring(0, b.lastIndexOf("/"));
    return b + "/" + a
},
Dialog.prototype.setPosition = function() {
    var e, f, a = $bodyDimensions(topWin),
    b = this.Top,
    c = this.Left,
    d = this.getDialogDiv();
    "string" == typeof this.Top && -1 != this.Top.indexOf("%") && (e = .01 * parseFloat(this.Top), b = this.fixedPosition ? a.clientHeight * e - d.scrollHeight * e: a.clientHeight * e - d.scrollHeight * e + a.scrollTop),
    "string" == typeof this.Left && -1 != this.Left.indexOf("%") && (f = .01 * parseFloat(this.Left), c = ielt7 ? a.clientWidth * f - d.scrollWidth * f + a.scrollLeft: a.clientWidth * f - d.scrollWidth * f),
    d && (d.style.top = Math.round(b) + "px", d.style.left = Math.round(c) + "px")
},
Dialog.setBgDivSize = function() {
    var a = $bodyDimensions(topWin);
    Dialog.bgDiv && (ielt7 ? (Dialog.bgDiv.style.height = a.clientHeight + "px", Dialog.bgDiv.style.top = a.scrollTop + "px", Dialog.bgDiv.childNodes[0].style.display = "none", Dialog.bgDiv.childNodes[0].style.display = "") : Dialog.bgDiv.style.height = a.scrollHeight + "px")
},
Dialog.resetPosition = function() {
    Dialog.setBgDivSize();
    for (var a = 0,
    b = topWin.Dialog._dialogArray.length; b > a; a++) topWin.Dialog._dialogArray[a].setPosition()
},
Dialog.prototype.create = function() {
    var b, c, d, e, f, g, h, i, a = $bodyDimensions(topWin);
    "function" == typeof this.OKEvent && (this.ShowButtonRow = !0),
    this.Width || (this.Width = Math.round(4 * a.clientWidth / 10)),
    this.Height || (this.Height = Math.round(this.Width / 2)),
    (this.MessageTitle || this.Message) && (this.ShowMessageRow = !0),
    b = this.Width + this.dialogWidthFix,
    c = this.Height + 33 + 13 + (this.ShowButtonRow ? 40 : 0) + (this.ShowMessageRow ? 50 : 0),
    b > a.clientWidth && (this.Width = Math.round(a.clientWidth - 26)),
    c > a.clientHeight && (this.Height = Math.round(a.clientHeight - 46 - (this.ShowButtonRow ? 40 : 0) - (this.ShowMessageRow ? 50 : 0))),
    d = "",
    "normal" == this.Style ? d = '  <table id="_DialogTable_{thisID}" width="' + (this.Width + this.dialogWidthFix) + '" cellspacing="0" cellpadding="0" border="0" style="font-size:' + this.defaultFontSize + "px; font-family:" + this.defaultFontFamily + '; line-height:1.4;border-collapse: collapse;">    <tr id="_Draghandle_{thisID}" onselectstart="return false;" style="-moz-user-select: -moz-none; ' + (this.Drag ? "cursor: move;": "") + '">      <td class="dialog_lt dialog_borderWidth"><div class="dialog_borderWidth"></div></td>      <td class="dialog_ct" ><div class="dialog_title"><span id="_Title_{thisID}" class="' + ("" != this.IconClass ? this.IconClass: "") + '" style="float:left;' + ("" != this.IconSrc ? "background-repeat: no-repeat;background-position: 0 40%;padding-left:18px;display:inline-block;background-image:url(" + this.IconSrc + ")": "") + '">' + this.Title + '</span><input type="button" class="dialog_trans_icon"/></div>        <div id="_ButtonClose_{thisID}" onclick="fixProgress();Dialog.getInstance(\'{thisID}\').cancelButton.onclick.apply(Dialog.getInstance(\'{thisID}\').cancelButton,[]);" title="' + this.closeButtonTitle + '" class="dialog_closebtn" onmouseout="this.className=\'dialog_closebtn\'" onmouseover="this.className=\'dialog_closebtn_over\'" style=" ' + (this.ShowCloseButton ? "": "display:none;") + '"></div><div id="_ButtonMax_{thisID}" onclick="Dialog.getInstance(\'{thisID}\').max()" title="' + this.maxButtonTitle + '" class="dialog_maxbtn"  style="' + (this.ShowMaxButton ? "": "display:none;") + '"></div><div id="_ButtonMin_{thisID}" onclick="Dialog.getInstance(\'{thisID}\').minHandler()" title="' + this.minButtonTitle + '" class="dialog_minbtn"  style="' + (this.ShowMinButton ? "": "display:none;") + '"></div></td>      <td class="dialog_rt dialog_borderWidth"><div class="dialog_borderWidth"><a id="_forTab_{thisID}" href="#;"></a></div></td>    </tr>    <tr valign="top">      <td class="dialog_mlm dialog_borderWidth"></td>      <td align="center"><table width="100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#ffffff">          <tr id="_MessageRow_{thisID}" style="' + (this.ShowMessageRow ? "": "display:none") + '">            <td valign="top" height="50"><table width="100%" cellspacing="0" cellpadding="0" border="0" class="dialog_bg" id="_MessageTable_{thisID}">                <tr>                  <td width="50" height="50" align="center"><input type="button"  class="dialog_messageIcon"  id="_MessageIcon_{thisID}"/></td>                  <td align="left" style="line-height: 16px;"><div id="_MessageTitle_{thisID}" style="font-weight:bold">' + this.MessageTitle + '</div>                    <div id="_Message_{thisID}">' + this.Message + '</div></td>                </tr>              </table></td>          </tr>          <tr>            <td valign="top"><div id="_Container_{thisID}" style="text-align:left;position: relative; width: ' + this.Width + "px; height: " + this.Height + 'px;">                <div  style="position: absolute; height: 100%; width: 100%; display: none; background-color:#fff; opacity: 0.5;" id="_Covering_{thisID}">&nbsp;</div> ' +
    function(a) {
        return a.InnerHtml ? a.InnerHtml: a.URL ? '<iframe width="100%" height="100%" frameborder="0" style="background-color:#ffffff;border:none 0;" id="_DialogFrame_' + a.ID + '" ' + (a.InnerFrameName ? 'name="' + a.InnerFrameName + '"': "") + ' src="' + a.displacePath() + '"></iframe>': ""
    } (this) + '              </div></td>          </tr>          <tr id="_ButtonRow_{thisID}" style="' + (this.ShowButtonRow ? "": "display:none") + '">            <td height="36"><div id="_DialogButtons_{thisID}" style="text-align:' + this.ButtonAlign + '; border-top: 1px solid #DADEE5; padding: 8px 20px;background-color:#f6f6f6;">                <input type="button" style="' + (this.ShowCancelButton ? "": "display:none;") + (this.ButtonWidth ? "width:" + this.ButtonWidth + "px": "") + '"  value="' + this.CancelButtonText + '" onclick="Dialog.getInstance(\'{thisID}\').close();" id="_ButtonCancel_{thisID}"/>              <input type="button" class="primary" style="' + (this.ShowOkButton ? "": "display:none;") + (this.ButtonWidth ? "width:" + this.ButtonWidth + "px": "") + '"  value="' + this.OkButtonText + '" id="_ButtonOK_{thisID}"/>              </div></td>          </tr>        </table></td>      <td class="dialog_mrm dialog_borderWidth"></td>    </tr>    <tr>      <td class="dialog_lb dialog_borderWidth"></td>      <td class="dialog_cb"></td>      <td class="dialog_rb dialog_borderWidth"><a onfocus=\'$id("_forTab_{thisID}").focus();\' href="#;"></a></td>    </tr>  </table></div>': "simple" == this.Style ? d = '  <table id="_DialogTable_{thisID}" width="' + (this.Width + this.dialogWidthFix) + '" cellspacing="0" cellpadding="0" border="0" style="font-size:' + this.defaultFontSize + "px;font-family:" + this.defaultFontFamily + '; line-height:1.4;border-collapse: collapse;">    <tr id="_Draghandle_{thisID}" onselectstart="return false;" style="-moz-user-select: -moz-none; ' + (this.Drag ? "cursor: move;": "") + '">      <td ><div class="dialog_sample_top"><div style="padding: 9px 0 0 4px; float: left; "><span id="_Title_{thisID}">' + this.Title + '</span></div>        <div id="_ButtonClose_{thisID}" onclick="fixProgress();Dialog.getInstance(\'{thisID}\').cancelButton.onclick.apply(Dialog.getInstance(\'{thisID}\').cancelButton,[]);" class="dialog__simple_closebtn" style=" ' + (ielt7 ? "margin-top: 3px;": "") + (this.ShowCloseButton ? "": "display:none;") + '"></div></div></td>    </tr>    <tr valign="top">      <td align="center"><div class="dialog_sample_middle"><table width="100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#ffffff">          <tr id="_MessageRow_{thisID}" style="' + (this.ShowMessageRow ? "": "display:none") + '">            <td valign="top" height="50"><table width="100%" cellspacing="0" cellpadding="0" border="0" class="dialog_bg" id="_MessageTable_{thisID}">                <tr>                  <td width="50" height="50" align="center"><input type="button"  class="dialog_messageIcon"  id="_MessageIcon_{thisID}"/></td>                  <td align="left" style="line-height: 16px;"><div id="_MessageTitle_{thisID}" style="font-weight:bold">' + this.MessageTitle + '</div>                    <div id="_Message_{thisID}">' + this.Message + '</div></td>                </tr>              </table></td>          </tr>          <tr>            <td valign="top"><div id="_Container_{thisID}" style="text-align:left;position: relative; width: ' + this.Width + "px; height: " + this.Height + 'px;">                <div  style="position: absolute; height: 100%; width: 100%; display: none; background-color:#fff; opacity: 0.5;" id="_Covering_{thisID}">&nbsp;</div> ' +
    function(a) {
        return a.InnerHtml ? a.InnerHtml: a.URL ? '<iframe width="100%" height="100%" frameborder="0" style="background-color:#ffffff;border:none 0;" id="_DialogFrame_' + a.ID + '" ' + (a.InnerFrameName ? 'name="' + a.InnerFrameName + '"': "") + ' src="' + a.displacePath() + '"></iframe>': ""
    } (this) + '              </div></td>          </tr>          <tr id="_ButtonRow_{thisID}" style="' + (this.ShowButtonRow ? "": "display:none") + '">            <td height="36"><div id="_DialogButtons_{thisID}" class="dialog_sample_botton" style="border-top: 1px solid #DADEE5; padding: 8px 20px; text-align: right; background-color:#f6f6f6;">                <input type="button" style="' + (this.ShowCancelButton ? "": "display:none") + '"  value="' + this.CancelButtonText + '" onclick="Dialog.getInstance(\'{thisID}\').close();" id="_ButtonCancel_{thisID}"/>                <input type="button" class="primary" style="' + (this.ShowOkButton ? "": "display:none") + '"  value="' + this.OkButtonText + '" id="_ButtonOK_{thisID}"/>              </div></td>          </tr>        </table></div></td>    </tr>  </table></div>': "simpleTip" == this.Style ? d = '     <div id="_DialogTable_{thisID}" style="width:' + (this.Width + this.dialogWidthFix) + 'px"  class="simpleTip">          <div class="con">              <div class="open_win">                  <div class="title" id="_Draghandle_{thisID}" onselectstart="return false;" style="-moz-user-select: -moz-none; ' + (this.Drag ? "cursor: move;": "") + '">                        <span id="_Title_{thisID}" class="' + ("" != this.IconClass ? this.IconClass: "") + '" style="float:left;' + ("" != this.IconSrc ? "background-repeat: no-repeat;background-position: 0 40%;padding-left:18px;display:inline-block;background-image:url(" + this.IconSrc + ")": "") + '">' + this.Title + '</span>                      <div id="_ButtonClose_{thisID}" onclick="fixProgress();Dialog.getInstance(\'{thisID}\').cancelButton.onclick.apply(Dialog.getInstance(\'{thisID}\').cancelButton,[]);" class="dialog__simple_closebtn" style=" ' + (ielt7 ? "margin-top: 3px;": "") + (this.ShowCloseButton ? "": "display:none;") + '"></div>                     <div class="clear"></div>                  </div>                    <div id="_Container_{thisID}" style="text-align:left;position: relative; width: ' + this.Width + "px; height: " + this.Height + 'px;">                <div  style="position: absolute; height: 100%; width: 100%; display: none; background-color:#fff; opacity: 0.5;" id="_Covering_{thisID}">&nbsp;</div>   ' +
    function(a) {
        return a.InnerHtml ? a.InnerHtml: a.URL ? '<iframe width="100%" height="100%" frameborder="0" style="background-color:#ffffff;border:none 0;" id="_DialogFrame_' + a.ID + '" ' + (a.InnerFrameName ? 'name="' + a.InnerFrameName + '"': "") + ' src="' + a.displacePath() + '"></iframe>': ""
    } (this) + '              </div>              <table><tr id="_ButtonRow_{thisID}" style="display:none;" >            <td height="36"><div id="_DialogButtons_{thisID}" style="border-top: 1px solid #DADEE5; padding: 8px 20px; text-align: right; background-color:#f6f6f6;">                <input type="button" style="' + (this.ShowOkButton ? "": "display:none") + '"  value="' + this.OkButtonText + '" id="_ButtonOK_{thisID}"/>                <input type="button" style="' + (this.ShowCancelButton ? "": "display:none") + '"  value="' + this.CancelButtonText + '" onclick="Dialog.getInstance(\'{thisID}\').close();" id="_ButtonCancel_{thisID}"/>              </div></td>          </tr></table>              </div>            </div>            <div class="arrow">              <div class="arrowMask"></div>          </div>        </div>': "shadowTip" == this.Style ? (e = this.Height + 95, d = '  <table><tr><td><div class="dialog_shadow_content"><table id="_DialogTable_{thisID}" width="' + this.Width + '" cellspacing="0" cellpadding="0" border="0" style="font-size:' + this.defaultFontSize + "px;font-family:" + this.defaultFontFamily + '; line-height:1.4;border-collapse: collapse;">    <tr id="_Draghandle_{thisID}" onselectstart="return false;" style="-moz-user-select: -moz-none; ' + (this.Drag ? "cursor: move;": "") + '">      <td><div class="dialog_shadow_content_top"><div style="padding: 9px 0 0 4px; float: left; "><span id="_Title_{thisID}">' + this.Title + '</span></div>        <div id="_ButtonClose_{thisID}" onclick="fixProgress();Dialog.getInstance(\'{thisID}\').cancelButton.onclick.apply(Dialog.getInstance(\'{thisID}\').cancelButton,[]);" class="dialog__simple_closebtn" style=" ' + (ielt7 ? "margin-top: 3px;": "") + (this.ShowCloseButton ? "": "display:none;") + '"></div></div></td>    </tr>    <tr valign="top">      <td align="center"><table width="100%" cellspacing="0" cellpadding="0" border="0" bgcolor="#ffffff">          <tr id="_MessageRow_{thisID}" style="' + (this.ShowMessageRow ? "": "display:none") + '">            <td valign="top" height="50"><table width="100%" cellspacing="0" cellpadding="0" border="0" class="dialog_bg" id="_MessageTable_{thisID}">                <tr>                  <td width="50" height="50" align="center"><input type="button"  class="dialog_messageIcon"  id="_MessageIcon_{thisID}"/></td>                  <td align="left" style="line-height: 16px;"><div id="_MessageTitle_{thisID}" style="font-weight:bold">' + this.MessageTitle + '</div>                    <div id="_Message_{thisID}">' + this.Message + '</div></td>                </tr>              </table></td>          </tr>          <tr>            <td valign="top"><div id="_Container_{thisID}" style="text-align:left;position: relative; width: ' + this.Width + "px; height: " + this.Height + 'px;">                <div  style="position: absolute; height: 100%; width: 100%; display: none; background-color:#fff; opacity: 0.5;" id="_Covering_{thisID}">&nbsp;</div> ' +
    function(a) {
        return a.InnerHtml ? a.InnerHtml: a.URL ? '<iframe width="100%" height="100%" frameborder="0" style="background-color:#ffffff;border:none 0;" id="_DialogFrame_' + a.ID + '" ' + (a.InnerFrameName ? 'name="' + a.InnerFrameName + '"': "") + ' src="' + a.displacePath() + '"></iframe>': ""
    } (this) + '              </div></td>          </tr>          <tr id="_ButtonRow_{thisID}" style="' + (this.ShowButtonRow ? "": "display:none") + '">            <td height="36"><div id="_DialogButtons_{thisID}" style="border-top: 1px solid #DADEE5; padding: 8px 20px; text-align: right; background-color:#f6f6f6;">                <input type="button" style="' + (this.ShowOkButton ? "": "display:none") + '"  value="' + this.OkButtonText + '" id="_ButtonOK_{thisID}"/>                <input type="button" style="' + (this.ShowCancelButton ? "": "display:none") + '"  value="' + this.CancelButtonText + '" onclick="Dialog.getInstance(\'{thisID}\').close();" id="_ButtonCancel_{thisID}"/>              </div></td>          </tr>        </table></td>    </tr>  </table>  </div><div><table cellpadding="0" cellspacing="0" width="' + (this.Width + 120) + '"  height="' + e + '"><tr><td class="dialog_shadow_lt">&nbsp;</td><td class="dialog_shadow_ct">&nbsp;</td><td class="dialog_shadow_rt">&nbsp;</td></tr>  <tr><td class="dialog_shadow_lm" height="' + (this.Height + 95 - 39 - 130) + '">&nbsp;</td><td class="dialog_shadow_cm">&nbsp;</td><td class="dialog_shadow_rm">&nbsp;</td></tr>  <tr><td class="dialog_shadow_lb">&nbsp;</td><td class="dialog_shadow_cb">&nbsp;</td><td class="dialog_shadow_rb">&nbsp;</td></tr></table> </div></td></tr></table></div>') : "custom" == this.Style && (d = '  <table id="_DialogTable_{thisID}" width="' + (this.Width + this.dialogWidthFix) + '" cellspacing="0" cellpadding="0" border="0" style="font-size:' + this.defaultFontSize + "px; font-family:" + this.defaultFontFamily + ';line-height:1.4;border-collapse: collapse;">    <tr id="_Draghandle_{thisID}" style="display:none;" onselectstart="return false;" style="-moz-user-select: -moz-none; ' + (this.Drag ? "cursor: move;": "") + '">    </tr>          <tr>            <td valign="top"><div id="_Container_{thisID}" style="text-align:left;position: relative; width: ' + this.Width + "px; height: " + this.Height + 'px;">                <div  style="position: absolute; height: 100%; width: 100%; display: none; background-color:#fff; opacity: 0.5;" id="_Covering_{thisID}">&nbsp;</div> ' +
    function(a) {
        return a.InnerHtml ? a.InnerHtml: a.URL ? '<iframe width="100%" height="100%" frameborder="0" style="border:none 0;" id="_DialogFrame_' + a.ID + '" ' + (a.InnerFrameName ? 'name="' + a.InnerFrameName + '"': "") + ' src="' + a.displacePath() + '"></iframe>': ""
    } (this) + '              </div></td>          </tr>          <tr id="_ButtonRow_{thisID}" style="display:none;" >            <td height="36"><div id="_DialogButtons_{thisID}" style="border-top: 1px solid #DADEE5; padding: 8px 20px; text-align: right; background-color:#f6f6f6;">                <input type="button" style="' + (this.ShowOkButton ? "": "display:none") + '"  value="' + this.OkButtonText + '" id="_ButtonOK_{thisID}"/>                <input type="button" style="' + (this.ShowCancelButton ? "": "display:none") + '"  value="' + this.CancelButtonText + '" onclick="Dialog.getInstance(\'{thisID}\').close();" id="_ButtonCancel_{thisID}"/>              </div></td>          </tr>        </table></div></td>    </tr>  </table></div>'),
    d = d.replace(/\{IMAGESPATH\}/gm, IMAGESPATH).replace(/\{thisID\}/gm, this.ID),
    f = topWin.$id("_DialogDiv_" + this.ID),
    f || (f = topDoc.createElement("div"), f.id = "_DialogDiv_" + this.ID, topDoc.getElementsByTagName("BODY")[0].appendChild(f)),
    isIE && "BackCompat" == topDoc.compatMode || ielt7 ? (f.style.position = "absolute", this.fixedPosition = !1) : (f.style.position = "fixed", this.fixedPosition = !0),
    f.style.left = "-9999px",
    f.style.top = "-9999px",
    f.innerHTML = d,
    this.AllowChangeIndex || (this.AllowChangeIndex = 1 == this.MinToTask ? !0 : !1),
    $(f).attr("AllowChangeIndex", this.AllowChangeIndex),
    $(f).attr("MinToTask", this.MinToTask),
    "normal" == this.Style && $(f).addClass("dialog_main"),
    $(f).attr("taskId", "task_" + this.ID),
    $(f).bind("click",
    function() {
        var a, b, c, d; (1 == $(this).attr("AllowChangeIndex") || "true" == $(this).attr("AllowChangeIndex")) && (maxIndex = maxIndex + topWin.Dialog._dialogArray.length + 2, $(this)[0].style.zIndex = maxIndex, (1 == $(this).attr("MinToTask") || "true" == $(this).attr("MinToTask")) && (a = topWin.Dialog._taskArray, b = getArrayPosition($(this).attr("taskId"), a), -1 != b && (a.splice(b, 1), a.push($(this).attr("taskId"))), c = getTaskTarget(), "" != c && (d = $("#" + c), setTaskCurrent(d, a))))
    }),
    this.InvokeElementId && (g = $id(this.InvokeElementId), g.style.position = "", g.style.display = "", isIE ? (h = topDoc.createElement("div"), h.innerHTML = g.outerHTML, g.outerHTML = "", topWin.$id("_Covering_" + this.ID).parentNode.appendChild(h)) : topWin.$id("_Covering_" + this.ID).parentNode.appendChild(g)),
    this.openerWindow = window,
    window.ownerDialog && (this.openerDialog = window.ownerDialog),
    this.URL && (topWin.$id("_DialogFrame_" + this.ID) && (this.innerFrame = topWin.$id("_DialogFrame_" + this.ID)), i = this, this.innerFrameOnload = function() {
        i.innerWin = i.innerFrame.contentWindow;
        try {
            i.innerWin.ownerDialog = i,
            i.innerDoc = i.innerWin.document,
            "　" == i.Title && i.innerDoc && i.innerDoc.title && i.innerDoc.title && (topWin.$id("_Title_" + i.ID).innerHTML = i.innerDoc.title)
        } catch(a) {
            i.unauthorized = !0
        }
        "function" == typeof i.OnLoad && i.OnLoad()
    },
    isGecko ? this.innerFrame.onload = i.innerFrameOnload: this.innerFrame.attachEvent("onreadystatechange",
    function() { / loaded | complete / .test(i.innerFrame.readyState) && i.innerFrameOnload()
    })),
    topWin.$id("_DialogDiv_" + this.ID).dialogId = this.ID,
    topWin.$id("_DialogDiv_" + this.ID).dialogInstance = this,
    this.attachBehaviors(),
    this.okButton = topWin.$id("_ButtonOK_" + this.ID),
    this.cancelButton = topWin.$id("_ButtonCancel_" + this.ID),
    this.maxButton = topWin.$id("_ButtonMax_" + this.ID),
    this.minButton = topWin.$id("_ButtonMin_" + this.ID),
    $(f).find("input[type='button']").each(function() {
        $(this).attr("class") && "primary" != $(this).attr("class") || ($(this).addClass("button"), $(this).hover(function() {
            $(this).addClass("button_hover")
        },
        function() {
            $(this).removeClass("button_hover")
        }))
    }),
    0 == $(f).find(".progressBar").length && ("none" == $(f).find(".button").parents("tr")[0].style.display ? $(f).find(".icon_dialog").eq(0).focus() : $(f).find(".button").eq(0).focus()),
    f = null
},
Dialog.prototype.setSize = function(a, b) {
    a && +a > 20 && (this.Width = +a, topWin.$id("_DialogTable_" + this.ID).width = this.Width + this.dialogWidthFix, topWin.$id("_Container_" + this.ID).style.width = this.Width + "px"),
    b && +b > 10 && (this.Height = +b, topWin.$id("_Container_" + this.ID).style.height = this.Height + "px"),
    this.setPosition()
},
Dialog.prototype.max = function() {
    var c, d, a = $("#_DialogDiv_" + this.ID),
    b = $("#_DialogTable_" + this.ID).find(">tbody").find(">tr").eq(1);
    "none" == b[0].style.display && (b.show(), $("#_Draghandle_" + this.ID).find(".dialog_minbtn").removeClass("dialog_decreasebtn"), $("#_Draghandle_" + this.ID).find(".dialog_minbtn").attr("title", this.minButtonTitle)),
    1 == this.maxFlag ? (topWin.$id("_DialogTable_" + this.ID).style.width = this.Width + this.dialogWidthFix, topWin.$id("_Container_" + this.ID).style.width = this.Width + "px", topWin.$id("_Container_" + this.ID).style.height = this.Height + "px", $("#_Draghandle_" + this.ID).find(".dialog_maxbtn").removeClass("dialog_decreasebtn"), $("#_Draghandle_" + this.ID).find(".dialog_maxbtn").attr("title", this.maxButtonTitle), this.maxFlag = !1, this.DecreaseResetPosition ? this.setPosition() : (a[0].style.left = a.attr("oldleft"), a[0].style.top = a.attr("oldtop"))) : (a.attr("oldleft", a[0].style.left), a.attr("oldtop", a[0].style.top), c = window.document.documentElement.clientHeight, d = window.document.documentElement.clientWidth, topWin.$id("_DialogTable_" + this.ID).style.width = d, topWin.$id("_Container_" + this.ID).style.width = d - this.dialogWidthFix + "px", topWin.$id("_Container_" + this.ID).style.height = "none" == $("#_ButtonRow_" + this.ID)[0].style.display ? this.MinToTask ? c - 46 - $(".dialogTaskBg").height() + "px": c - 46 + "px": this.MinToTask ? c - 46 - $("#_ButtonRow_" + this.ID).height() - $(".dialogTaskBg").height() + "px": c - 46 - $("#_ButtonRow_" + this.ID).height() + "px", $("#_Draghandle_" + this.ID).find(".dialog_maxbtn").addClass("dialog_decreasebtn"), $("#_Draghandle_" + this.ID).find(".dialog_maxbtn").attr("title", this.decreaseButtonTitle), (1 == this.MinToTask || "true" == this.MinToTask) && (this.Left = 0, this.Top = 0), this.maxFlag = !0, this.setPosition())
},
Dialog.prototype.minHandler = function() {
    1 == this.MinToTask || "true" == this.MinToTask ? this.minToTask() : this.min()
},
Dialog.prototype.min = function() {
    var a = $("#_DialogTable_" + this.ID).find(">tbody").find(">tr").eq(1);
    "none" == a[0].style.display ? (topWin.$id("_DialogTable_" + this.ID).width = this.Width + this.dialogWidthFix, topWin.$id("_Container_" + this.ID).style.width = this.Width + "px", topWin.$id("_Container_" + this.ID).style.height = this.Height + "px", $("#_Draghandle_" + this.ID).find(".dialog_minbtn").removeClass("dialog_decreasebtn"), $("#_Draghandle_" + this.ID).find(".dialog_minbtn").attr("title", this.minButtonTitle), a.show(), this.DecreaseResetPosition && this.setPosition(), this.minFlag = !1) : (topWin.$id("_DialogTable_" + this.ID).style.width = 240 + this.dialogWidthFix + "px", topWin.$id("_Container_" + this.ID).style.width = "240px", $("#_Draghandle_" + this.ID).find(".dialog_minbtn").addClass("dialog_decreasebtn"), $("#_Draghandle_" + this.ID).find(".dialog_minbtn").attr("title", this.decreaseButtonTitle), a.hide(), this.minFlag = !0),
    this.maxFlag = !1
},
Dialog.prototype.minToTask = function() {
    var b, c, d, e, f, g, h, i, j, k, a = 0;
    1 == $("#skin").attr("splitMode") || "true" == $("#skin").attr("splitMode") ? dialogTaskTop = window.document.documentElement.clientHeight - a + "px": (b = $(window.top.document.getElementById("theme")), null != b.attr("dialogTaskBottom") && (a = Number(b.attr("dialogTaskBottom"))), dialogTaskTop = top.window.document.documentElement.clientHeight - a + "px"),
    c = $("#_DialogDiv_" + this.ID),
    d = getTaskTarget(),
    e = this.ID,
    "" != d && (f = $("#" + d), g = c[0].style.left, h = c[0].style.top, i = c.width(), j = c.height(), k = $('<div class="dialogMinEffect"></div>'), k.css({
        width: i,
        height: j,
        left: g,
        top: h
    }).animate({
        width: 0,
        height: 0,
        left: f.find("#task_" + this.ID).offset().left + "px",
        top: dialogTaskTop,
        opacity: 0
    },
    200,
    function() {
        var a, b;
        k.remove(),
        a = topWin.Dialog._taskArray,
        b = getArrayPosition("task_" + e, a),
        -1 != b && a.splice(b, 1),
        setTaskCurrent(f, a)
    })),
    c.hide(),
    $("body").append(k)
},
Dialog.prototype.show = function() {
    var b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, a = 0;
    return 1 == $("#skin").attr("splitMode") || "true" == $("#skin").attr("splitMode") ? dialogTaskTop = window.document.documentElement.clientHeight - a + "px": (b = $(window.top.document.getElementById("theme")), null != b.attr("dialogTaskBottom") && (a = Number(b.attr("dialogTaskBottom"))), dialogTaskTop = top.window.document.documentElement.clientHeight - a + "px"),
    (c = topWin.$id("_DialogDiv_" + this.ID)) ? (maxIndex = maxIndex + topWin.Dialog._dialogArray.length + 2, $(c)[0].style.zIndex = maxIndex, (1 == this.MinToTask || "true" == this.MinToTask) && (d = topWin.Dialog._taskArray, e = $(c)[0].style.left, f = $(c)[0].style.top, g = $(c).width(), h = $(c).height(), i = $('<div class="dialogMinEffect"/>'), j = getArrayPosition("task_" + this.ID, d), -1 != j ? (d.splice(j, 1), d.push("task_" + this.ID)) : d.push("task_" + this.ID), k = getTaskTarget(), "" != k && (l = $("#" + k), setTaskCurrent(l, d)), "none" == $(c)[0].style.display ? (i.css({
        width: 0,
        height: 0,
        left: l.find("#task_" + this.ID).offset().left + "px",
        top: dialogTaskTop
    }).animate({
        width: g,
        height: h,
        left: e,
        top: f
    },
    200,
    function() {
        $(c).show(),
        i.remove()
    }), $("body").append(i)) : this.ID != d[d.length - 1]), void 0) : (this.create(), m = this.getBgdiv(), n = this.getDialogDiv(), n.style.zIndex = this.zindex = parseInt(Dialog.bgDiv.style.zIndex) + 1, topWin.Dialog._dialogArray.length > 0 ? (maxIndex = maxIndex + topWin.Dialog._dialogArray.length + 2, n.style.zIndex = maxIndex) : (m.style.display = "none", HideScrollbar && (o = topDoc.getElementsByTagName("BackCompat" == topDoc.compatMode ? "BODY": "HTML")[0], o.styleOverflow = o.style.overflow, -1 != window.navigator.userAgent.indexOf("Firefox/3.6") ? (p = o.scrollTop, 1 == this.ShowHideScroller && (o.style.overflow = "hidden", o.scrollTop = p)) : 1 == this.ShowHideScroller && (o.style.overflow = "hidden"))), topWin.Dialog._dialogArray.push(this), Dialog._childDialogArray.push(this), 1 == Dialog._childDialogArray.length && window.ownerDialog && ownerDialog.hiddenCloseButton(), this.CancelEvent && (this.cancelButton.onclick = this.CancelEvent), q = this, this.OKEvent && (this.okButton.onclick = this.OKEvent), this.maxButton && (this.maxButton.onclick = function() {
        q.maxFlag ? q.DecreaseEvent && $(q.DecreaseEvent) : q.MaxEvent && $(q.MaxEvent),
        q.max()
    }), this.minButton && (this.minButton.onclick = function() {
        q.minFlag ? q.DecreaseEvent && $(q.DecreaseEvent) : q.MinEvent && $(q.MinEvent),
        q.minHandler()
    }), this.AutoClose && this.AutoClose > 0 && this.autoClose(), this.opened = !0, this.setPosition(), (1 == this.MinToTask || "true" == this.MinToTask) && (this.Modal = !1, this.ResizeResetPosition = !1, this.DecreaseResetPosition = !1, this.AllowChangeIndex = !0, r = $("#_DialogDiv_" + this.ID), s = getTaskTarget(), "" != s && (t = $("#" + s), t[0] && "none" == t[0].style.display && (t.fadeIn(500), $("body").trigger("dialogTaskShow")), u = $('<a class="dialogTaskItem"><span keepDefaultStyle="true" class="dialogTaskItemRight"><span class="dialogTaskItemIcon"></span></span></a>'), u.attr("id", "task_" + this.ID), v = u.find(".dialogTaskItemIcon"), v.text(this.Title), v.attr("title", this.Title), "" != this.IconSrc ? v.css({
        paddingLeft: "18px",
        backgroundPositon: "0 40%",
        backgroundRepeat: "no-repeat",
        backgroundImage: "url(" + this.IconSrc + ")"
    }) : "" != this.IconClass && v.addClass(this.IconClass), t.find(".taskItemContainer").append(u), itemTotalWidth = itemTotalWidth + u.width() + 8, resetDialogTask(), w = topWin.Dialog._taskArray, -1 == getArrayPosition("task_" + this.ID, w) && w.push("task_" + this.ID), this.Left = r.offset().left + 20 * (w.length - 1), this.Top = r.offset().top + 20 * (w.length - 1), setTaskCurrent(t, w), u.bind("click",
    function() {
        var a = topWin.Dialog._taskArray,
        b = getArrayPosition($(this).attr("id"), a),
        c = r[0].style.left,
        d = r[0].style.top,
        e = r.width(),
        f = r.height(),
        g = $('<div class="dialogMinEffect"/>');
        "none" == r[0].style.display ? (maxIndex = maxIndex + topWin.Dialog._dialogArray.length + 2, r[0].style.zIndex = maxIndex, -1 == b ? a.push($(this).attr("id")) : (a.splice(b, 1), a.push($(this).attr("id"))), g.css({
            width: 0,
            height: 0,
            left: $(this).offset().left + "px",
            top: dialogTaskTop
        }).animate({
            width: e,
            height: f,
            left: c,
            top: d
        },
        200,
        function() {
            r.show(),
            g.remove()
        }), $("body").append(g), setTaskCurrent(t, a)) : $(this).attr("id") != a[a.length - 1] ? (maxIndex = maxIndex + topWin.Dialog._dialogArray.length + 2, r[0].style.zIndex = maxIndex, -1 == b ? a.push($(this).attr("id")) : (a.splice(b, 1), a.push($(this).attr("id"))), setTaskCurrent(t, a)) : (g.css({
            width: e,
            height: f,
            left: c,
            top: d
        }).animate({
            width: 0,
            height: 0,
            left: $(this).offset().left + "px",
            top: dialogTaskTop,
            opacity: 0
        },
        200,
        function() {
            g.remove(),
            setTaskCurrent(t, a)
        }), $("body").append(g), r.hide(), a.pop($(this).attr("id")))
    })), this.setPosition()), this.Modal && (m.style.zIndex = n.style.zIndex - 1, Dialog.setBgDivSize(), m.style.display = ""), m = null, void 0)
},
Dialog.prototype.close = function() {
    var a, b, c, d, e, f, g, h, i, j, k, l, m, n;
    if (! (0 == this.unauthorized && this.innerWin && this.innerWin.Dialog && this.innerWin.Dialog._childDialogArray.length > 0)) {
        if (a = this.getDialogDiv(), this == topWin.Dialog._dialogArray[topWin.Dialog._dialogArray.length - 1] ? b = topWin.Dialog._dialogArray.pop() : removeWinArray(topWin.Dialog._dialogArray, this), removeWinArray(Dialog._childDialogArray, this), 0 == Dialog._childDialogArray.length && window.ownerDialog && ownerDialog.showCloseButton(), this.InvokeElementId && (c = topWin.$id(this.InvokeElementId), c.style.display = "none", isIE ? (d = document.createElement("div"), d.innerHTML = c.outerHTML, c.outerHTML = "", document.getElementsByTagName("BODY")[0].appendChild(d)) : document.getElementsByTagName("BODY")[0].appendChild(c)), topWin.Dialog._dialogArray.length > 0) {
            if (this.Modal && b) {
                for (e = topWin.Dialog._dialogArray.length, f = !0; e;) if (--e, topWin.Dialog._dialogArray[e].Modal) {
                	maxIndex = maxIndex - topWin.Dialog._dialogArray.length - 3;
                	//alert(topWin.Dialog._dialogArray.length);
                	if(maxIndex < 900)
                		maxIndex = 900;
                	//alert(maxIndex);
                    Dialog.bgDiv.style.zIndex = maxIndex;//topWin.Dialog._dialogArray[e].zindex - 1,
                    f = !1;
                    //topWin.Dialog._dialogArray.length
                    //alert(topWin.Dialog._dialogArray[e].zindex);
                    break
                }
                f && (Dialog.bgDiv.style.display = "none")
            }
        } else Dialog.bgDiv.style.zIndex = "900",
        Dialog.bgDiv.style.display = "none",
        HideScrollbar && (g = topDoc.getElementsByTagName("BackCompat" == topDoc.compatMode ? "BODY": "HTML")[0], void 0 != g.styleOverflow && ( - 1 != window.navigator.userAgent.indexOf("Firefox/3.6") ? (h = g.scrollTop, g.style.overflow = this.CloseHideScroller ? "hidden": g.styleOverflow, g.scrollTop = h) : g.style.overflow = this.CloseHideScroller ? "hidden": g.styleOverflow));
        this.openerWindow.focus(),
        isIE && !isIE8 ? (a.dialogInstance = null, this.CancelEvent && (this.cancelButton.onclick = null), this.OKEvent && (this.okButton.onclick = null), topWin.$id("_DialogDiv_" + this.ID).onDragStart = null, topWin.$id("_DialogDiv_" + this.ID).onDragEnd = null, topWin.$id("_Draghandle_" + this.ID).onmousedown = null, topWin.$id("_Draghandle_" + this.ID).root = null, a.outerHTML = "", CollectGarbage()) : (i = topWin.$id("_RycDiv"), i || (i = topDoc.createElement("div"), i.id = "_RycDiv"), i.appendChild(a), i.innerHTML = "", i = null),
        this.innerFrame = null,
        this.bgDiv = null,
        a = null,
        this.closed = !0,
        (1 == this.MinToTask || "true" == this.MinToTask) && (j = topWin.Dialog._taskArray, k = getArrayPosition("task_" + this.ID, j), -1 != k && j.splice(k, 1), l = getTaskTarget(), "" != l && (m = $("#" + l), n = m.find("#task_" + this.ID), itemTotalWidth = itemTotalWidth - n.width() - 8, resetDialogTask(), n.remove(), setTaskCurrent(m, j), "" == m.find(".taskItemContainer").html() && (m.fadeOut(500), $("body").trigger("dialogTaskHide"))))
    }
},
Dialog.prototype.autoClose = function() {
    if (this.closed) return clearTimeout(this._closeTimeoutId),
    void 0;
    if (this.AutoClose -= 1, topWin.$id("_Title_" + this.ID).innerHTML = this.AutoClose + this.autoCloseMessage, this.AutoClose <= 0) this.close();
    else {
        var a = this;
        this._closeTimeoutId = setTimeout(function() {
            a.autoClose()
        },
        1e3)
    }
},
Dialog.getInstance = function(a) {
    var b = topWin.$id("_DialogDiv_" + a);
    b || top.Toast("showWarningToast", this.errorMessage);
    try {
        return b.dialogInstance
    } finally {
        b = null
    }
},
Dialog.prototype.addButton = function(a, b, c) {
    topWin.$id("_ButtonRow_" + this.ID).style.display = "",
    this.ShowButtonRow = !0;
    var d = topDoc.createElement("input");
    return d.id = "_Button_" + this.ID + "_" + a,
    d.type = "button",
    d.style.cssText = this.ButtonWidth ? "margin-right:5px;width:" + this.ButtonWidth + "px": "margin-right:5px",
    d.value = b,
    d.onclick = c,
    $(topWin.$id("_DialogButtons_" + this.ID)).append($(d)),
    $(d).addClass("primary"),
    $(d).buttonInputRender(),
    d
},
Dialog.prototype.removeButton = function(a) {
    var b = topWin.$id("_DialogButtons_" + this.ID).getElementsByTagName("INPUT")[0];
    b.parentNode.removeChild(a)
},
Dialog.prototype.hiddenCloseButton = function() {
    var b = topWin.$id("_ButtonClose_" + this.ID);
    b && (b.style.display = "none")
},
Dialog.prototype.showCloseButton = function() {
    var b = topWin.$id("_ButtonClose_" + this.ID);
    b && (b.style.display = "")
},
Dialog.prototype.getBgdiv = function() {
    var c, d, e, f, g, a = this.Alpha,
    b = String(this.Alpha / 100);
    return Dialog.bgDiv ? (this.Modal && (document.getElementById("_DialogBGMask").style.opacity = b, document.getElementById("_DialogBGMask").style.filter = "alpha(opacity=" + a + ")", document.getElementById("_DialogBGMask").style.backgroundColor = this.Bgcolor), Dialog.bgDiv) : (c = topWin.$id("_DialogBGDiv"), c || (c = topDoc.createElement("div"), c.id = "_DialogBGDiv", c.style.cssText = "position:absolute;left:0px;top:0px;width:100%;height:100%;z-index:900", d = '<div style="position:relative;width:100%;height:100%;">', e = '<div id="_DialogBGMask" style="position:absolute;width:100%;height:100%;"></div>', f = this.IframeBg ? '<iframe src="about:blank" style="filter:alpha(opacity=0);" width="100%" height="100%"></iframe>': "", c.innerHTML = d + e + f + "</div>", topDoc.getElementsByTagName("BODY")[0].appendChild(c), document.getElementById("_DialogBGMask").style.opacity = b, document.getElementById("_DialogBGMask").style.filter = "alpha(opacity=" + a + ")", document.getElementById("_DialogBGMask").style.backgroundColor = this.Bgcolor, this.IframeBg && (g = c.getElementsByTagName("IFRAME")[0].contentWindow.document, g.open(), g.write("<body oncontextmenu='return false;'></body>"), g.close(), g = null)), Dialog.bgDiv = c, c = null, Dialog.bgDiv)
},
Dialog.prototype.getDialogDiv = function() {
    var a = topWin.$id("_DialogDiv_" + this.ID);
    try {
        return a
    } finally {
        a = null
    }
},
Dialog.onKeyDown = function(a) {
    var b, c;
    return a = window.event || a,
    (a.shiftKey && 9 == a.keyCode || 8 == a.keyCode) && topWin.Dialog._dialogArray.length > 0 && (b = a.srcElement || a.target, "INPUT" != b.tagName && "TEXTAREA" != b.tagName) ? (stopEvent(a), !1) : (27 == a.keyCode && (c = topWin.Dialog._dialogArray[topWin.Dialog._dialogArray.length - 1], c.ShowCloseButton && Dialog.close()), void 0)
},
Dialog.close = function() {
    if (topWin.Dialog._dialogArray.length > 0) {
        var b = topWin.Dialog._dialogArray[topWin.Dialog._dialogArray.length - 1];
        b.MsgForESC ? Dialog.confirm(b.MsgForESC,
        function() {
            b.cancelButton.onclick.apply(b.cancelButton, [])
        }) : b.cancelButton.onclick.apply(b.cancelButton, [])
    }
},
Dialog.alert = function(a, b, c, d, e) {
    var f, g;
    if (c = c || 300, d = d || 110, f = new Dialog({
        Width: c,
        Height: d
    }), f.ShowButtonRow = !0, f.CancelEvent = function() {
        f.close(),
        b && b()
    },
    a ? (g = a.split("|"), f.Title = g.length > 1 ? g[1] : f.alertTitleMessage, f.InnerHtml = '<table height="100%" border="0" align="center" cellpadding="10" cellspacing="0">          <tr><td align="right"><input type="button" id="Icon_' + this.ID + '" class="icon_alert" align="absmiddle"></td>               <td align="left" id="Message_' + this.ID + '" style="font-size:' + f.defaultFontSize + "px;font-family:" + f.defaultFontFamily + '">' + g[0] + "</td></tr>        </table>") : (f.Title = f.alertTitleMessage, f.InnerHtml = '<table height="100%" border="0" align="center" cellpadding="10" cellspacing="0">           <tr><td align="right"><input type="button" id="Icon_' + this.ID + '" class="icon_alert" align="absmiddle"></td>               <td align="left" id="Message_' + this.ID + '" style="font-size:' + f.defaultFontSize + "px;font-family:" + f.defaultFontFamily + '"></td></tr>     </table>'), f.show(), f.okButton && (f.okButton.parentNode.style.textAlign = "center", f.okButton.style.display = "none"), f.cancelButton && (f.cancelButton.value = f.OkButtonText, setTimeout(function() {
        f.cancelButton.focus()
    },
    200)), e && e > 0) {
        if (this.closed) return clearTimeout(this._closeTimeoutId),
        void 0;
        this.AutoClose -= 1,
        topWin.$id("_Title_" + f.ID).innerHTML = e + f.autoCloseMessage,
        this._closeTimeoutId = setTimeout(function() {
            f.autoClose()
        },
        1e3 * e)
    }
},
Dialog.confirm = function(a, b, c, d, e, f) {
    var g, h;
    d = d || 300,
    e = e || 110,
    g = new Dialog({
        Width: d,
        Height: e,
        ButtonWidth: f
    }),
    g.ShowButtonRow = !0,
    h = a.split("|"),
    g.Title = h.length > 1 ? h[1] : g.confirmTitleMessage,
    g.CancelEvent = function() {
        g.close(),
        c && c()
    },
    g.OKEvent = function() {
        g.close(),
        b && b()
    },
    g.InnerHtml = '<table height="100%" border="0" align="center" cellpadding="10" cellspacing="0">      <tr><td align="right"><input type="button" id="Icon_' + this.ID + '" class="icon_query" align="absmiddle"></td>           <td align="left" id="Message_' + this.ID + '" style="font-size:' + g.defaultFontSize + "px;font-family:" + g.defaultFontFamily + '">' + h[0] + "</td></tr>    </table>",
    g.show(),
    g.okButton.parentNode.style.textAlign = "center",
    setTimeout(function() {
        g.okButton.focus()
    },
    200)
},
Dialog.open = function(a) {
    var b = new Dialog(a);
    return b.show(),
    b
},
window.attachEvent("onload", Dialog.attachBehaviors),
itemContainerWidth = 0,
currentItemTotalWidth = 0,
itemTotalWidth = 0,
dialogTaskWidthModify = 0,
dialogTaskStart = 18,
$(function() {
    var a, b, c, d, e, f, g, h;
    1 == $("#skin").attr("splitMode") || "true" == $("#skin").attr("splitMode") || (a = $(window.top.document.getElementById("theme")), null != a.attr("dialogTaskWidthModify") && (dialogTaskWidthModify = Number(a.attr("dialogTaskWidthModify"))), null != a.attr("dialogTaskStart") && (dialogTaskStart = Number(a.attr("dialogTaskStart")))),
    itemContainerWidth = window.document.documentElement.clientWidth - dialogTaskWidthModify,
    $(window).resize(function() {
        resetDialogTask()
    }),
    b = getTaskTarget(),
    "" != b && (c = $("#" + b), d = 2, e = $('<div class="taskItemContainer"></div>'), f = $('<div class="taskItemContainerParent"></div>'), c.append(f), f.append(e), g = $('<div class="taskItemButtonLeft" style="display:none;" ></div>'), h = $('<div class="taskItemButtonRight" style="display:none;" ></div>'), c.append(g), c.append(h), g.bind("mousedown",
    function() {
        timer = setInterval(function() {
            return d > dialogTaskStart - 1 ? (d = dialogTaskStart, e.css("left", d), clearInterval(timer), void 0) : (d += 8, e.css("left", d), void 0)
        },
        30)
    }), g.bind("mouseup",
    function() {
        clearInterval(timer)
    }), h.bind("mousedown",
    function() {
        $(this).next("ul"),
        timer = setInterval(function() {
            return d > dialogTaskStart ? (d = dialogTaskStart - 1, clearInterval(timer), void 0) : itemContainerWidth - itemTotalWidth > d ? (d = itemContainerWidth - itemTotalWidth, clearInterval(timer), void 0) : (d -= 8, e.css("left", d), void 0)
        },
        30)
    }), h.bind("mouseup",
    function() {
        clearInterval(timer)
    }))
});