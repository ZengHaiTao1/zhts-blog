export const localStorage = (function StorageInit() {
    Storage.prototype.setExpire = (key, value, expire) => {
        let obj = {
            data: value,
            time: Date.now(),
            expire: expire
        };
        //localStorage 设置的值不能为对象,转为json字符串
        localStorage.setItem(key, JSON.stringify(obj));
    }

    Storage.prototype.getExpire = key => {
        let val = localStorage.getItem(key);
        if (!val) {
            return val;
        }
        try {
            val = JSON.parse(val);
        } catch{
        }

        if (Date.now() - val.time > val.expire) {
            localStorage.removeItem(key);
            return null;
        }
        return val.data;
    }
    return window.localStorage
})()
