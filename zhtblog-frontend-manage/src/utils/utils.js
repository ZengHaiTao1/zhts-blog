/**
 * 获取元素到浏览器顶部和左边的距离
 * @param  {Element} el
 * @returns {object}
 */
export function getOffset(el) {
    let box;
    try {
        box = el.getBoundingClientRect();
    } catch (e) {
        console.log(e)
    }

    let doc = el.ownerDocument,
        docElem = doc.documentElement;
    let body = doc.body,
        win = doc.defaultView || doc.parentWindow,
        clientTop = docElem.clientTop || body.clientTop || 0,
        clientLeft = docElem.clientLeft || body.clientLeft || 0,
        scrollTop =
            win.pageYOffset || docElem.scrollTop || body.scrollTop,
        scrollLeft =
            win.pageXOffset || docElem.scrollLeft || body.scrollLeft,
        top = box.top + scrollTop - clientTop,
        left = box.left + scrollLeft - clientLeft;

    return { top: top, left: left };
}
/**
 * 获取浏览器滚动条距离
 * @returns {number}
 */
export function getScrollTop() {
    let scrollPos;
    if (window.pageYOffset) {
        scrollPos = window.pageYOffset;
    } else if (
        document.compatMode &&
        document.compatMode != "BackCompat"
    ) {
        scrollPos = document.documentElement.scrollTop;
    } else if (document.body) {
        scrollPos = document.body.scrollTop;
    }
    return scrollPos;
}

/**
 * 创建并返回一个像节流阀一样的函数，当重复调用函数的时候，最多每隔 wait毫秒调用一次该函数
 * @param func 执行函数
 * @param wait 时间间隔
 * @param options 如果你想禁用第一次首先执行的话，传递{leading: false}，
 *                如果你想禁用最后一次执行的话，传递{trailing: false}
 * @returns {Function}
 */
export function throttle(func, wait, options) {
    let context, args, result;
    let timeout = null;
    let previous = 0;
    if (!options) options = {};
    let later = function () {
        previous = options.leading === false ? 0 : new Date().getTime();
        timeout = null;
        result = func.apply(context, args);
        if (!timeout) context = args = null;
    };
    return function () {
        let now = new Date().getTime();
        if (!previous && options.leading === false) previous = now;
        let remaining = wait - (now - previous);
        context = this;
        args = arguments;
        if (remaining <= 0 || remaining > wait) {
            if (timeout) {
                clearTimeout(timeout);
                timeout = null;
            }
            previous = now;
            result = func.apply(context, args);
            if (!timeout) context = args = null;
        } else if (!timeout && options.trailing !== false) {
            timeout = setTimeout(later, remaining);
        }
        return result;
    };
}