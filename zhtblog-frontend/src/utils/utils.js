/**
 * 获取可视窗口距离
 * @returns {number}
 */
let getClientHeight = function () {
    let clientHeight = 0;
    if (document.body.clientHeight && document.documentElement.clientHeight) {
        clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;
    } else {
        clientHeight = (document.body.clientHeight > document.documentElement.clientHeight) ? document.body.clientHeight : document.documentElement.clientHeight;
    }
    return clientHeight;
}

/**
 * 获取元素到浏览器顶部和左边的距离
 * @param  {Element} el
 * @returns {object}
 */
let getOffset = function (el) {
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
let getScrollTop = function () {
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
 * 使滚动条位置改变
 * @param  {Number} number  滚动条位置，默认去顶部
 * @param  {Number} time    滚动时间，默认1s
 */
let changScrollTop = function (number = 0, time = 1000) {
    let nowScroll = getScrollTop();  //当前滚动条位置
    let distance = 20//每次路程
    let len;  //移动路程
    let cishu;  //运行次数
    if (number > nowScroll) {
        len = number - nowScroll
        cishu = len / distance;
    } else if (number < nowScroll) {
        len = nowScroll - number
        cishu = len / distance;
        distance = -distance
    } else {
        return
    }
    let settime = time / cishu;  //每次运行的时间
    if (len > 1000) {
        if (distance > 0) {
            distance = distance + 10
        } else {
            distance = distance - 10
        }
        // distance = distance + 30
        settime /= 10
    }
    let a = function () {
        setTimeout(() => {
            if (document.compatMode &&
                document.compatMode != "BackCompat") {

                document.documentElement.scrollTop = parseInt(nowScroll) + distance
                if (nowScroll === document.documentElement.scrollTop) {
                    return;  //加上滚动距离发现没有变化，说明已经到底部了
                }
                nowScroll = document.documentElement.scrollTop
                if (distance > 0) {
                    if (number - nowScroll < distance) {
                        distance = number - nowScroll
                        document.documentElement.scrollTop = nowScroll + distance
                        return;
                    }
                } else if (distance < 0) {
                    if (nowScroll - number < -distance) {
                        distance = number - nowScroll
                        document.documentElement.scrollTop = nowScroll + distance
                        return;
                    }
                }
            } else if (document.body) {
                document.body.scrollTop = parseInt(nowScroll) + distance
                if (nowScroll === document.body.scrollTop) {
                    return;
                }
                nowScroll = document.body.scrollTop
                if (distance > 0) {
                    if (number - nowScroll < distance) {
                        distance = number - nowScroll
                        document.body.scrollTop = nowScroll + distance
                        return;
                    }
                } else if (distance < 0) {
                    if (nowScroll - number < -distance) {
                        distance = number - nowScroll
                        document.body.scrollTop = nowScroll + distance
                        return;
                    }
                }
            }
            a();
        }, settime / 2)
    }
    a();
}

let type = function (o) {
    let s = Object.prototype.toString.call(o);
    return s.match(/\[object (.*?)\]/)[1].toLowerCase();
};
['Null',
    'Undefined',
    'Object',
    'Array',
    'String',
    'Number',
    'Boolean',
    'Function',
    'RegExp'
].forEach(function (t) {
    type['is' + t] = function (o) {
        return type(o) === t.toLowerCase();
    };
});
/**
 * 控制方法执行频率
 * @param  {Function} method
 * @param  {} context
 */
// let throttle = function (method, context, time) {
//     clearTimeout(method.ID);
//     method.ID = setTimeout(function () {
//         method.call(context);
//     }, time);
// }

let getDomHeight = function (dom) {
    try {
        if (window.getComputedStyle(dom).height) {
            return parseInt(window.getComputedStyle(dom).height)
        } else if (dom.getBoundingClientRect().height) {
            return parseInt(dom.getBoundingClientRect().height)
        } else {
            return dom.offsetHeight
        }
    } catch (e) {
        console.log(e)
    }

}
/**
 * 防反跳。func函数在最后一次调用时刻的wait毫秒之后执行！
 * @param func 执行函数
 * @param wait 时间间隔
 * @param immediate 为true，debounce会在wai 时间间隔的开始调用这个函数
 * @returns {Function}
 */
let debounce = function (func, wait, immediate) {
    let timeout, args, context, timestamp, result;
    let later = function () {
        let last = new Date().getTime() - timestamp; // timestamp会实时更新
        if (last < wait && last >= 0) {
            timeout = setTimeout(later, wait - last);
        } else {
            timeout = null;
            if (!immediate) {
                result = func.apply(context, args);
                if (!timeout) context = args = null;
            }
        }
    };
    return function () {
        context = this;
        args = arguments;
        timestamp = new Date().getTime();
        let callNow = immediate && !timeout;
        if (!timeout) {
            timeout = setTimeout(later, wait);
        }
        if (callNow) {
            result = func.apply(context, args);
            context = args = null;
        }
        return result;
    };
}
/**
 * 创建并返回一个像节流阀一样的函数，当重复调用函数的时候，最多每隔 wait毫秒调用一次该函数
 * @param func 执行函数
 * @param wait 时间间隔
 * @param options 如果你想禁用第一次首先执行的话，传递{leading: false}，
 *                如果你想禁用最后一次执行的话，传递{trailing: false}
 * @returns {Function}
 */
let throttle = function (func, wait, options) {
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
export { getScrollTop, getOffset, getClientHeight, changScrollTop, type, throttle, getDomHeight, debounce }