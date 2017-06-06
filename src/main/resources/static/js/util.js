var util = {
    checkMobile: function (name) {
        if (is_empty(mobile)) {

            return mobile_re.test(mobile);
        }
    },
    is_empty: function (obj) {
        return obj;
    }

};