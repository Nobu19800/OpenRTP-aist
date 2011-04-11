// -*- Java -*-
/*!
 * @file foo.java
 * @date $Date$
 *
 * @author Noriaki Ando <n-ando@aist.go.jp>3456789412345678951234567896123456789
 * 7123456789812345
 *
 * Copyright (C) 2006-2008 ライセンス1234567890123456789012345678901234567890123
 * 4567890
 *
 * $Id$
 */

import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.RTObject_impl;
import jp.go.aist.rtm.RTC.RtcDeleteFunc;
import jp.go.aist.rtm.RTC.RtcNewFunc;

/*!
 * @class foo
 * @brief MDesc
 */
public class foo implements RtcNewFunc, RtcDeleteFunc {

//  Module specification
//  <rtc-template block="module_spec">
    public static String component_conf[] = {
    	    "implementation_id", "foo",
    	    "type_name",         "foo",
    	    "description",       "MDesc",
    	    "version",           "1.0.1",
    	    "vendor",            "TA",
    	    "category",          "Manip",
    	    "activity_type",     "STATIC2",
    	    "max_instance",      "5",
    	    "language",          "Java",
    	    "lang_type",         "compile",
            // Configuration variables
            "conf.default.int_param0", "0",
            "conf.default.int_param1", "1",
            "conf.default.double_param0", "0.11",
            "conf.default.str_param0", "日本語",
            "conf.default.str_param1", "dara",
    	    ""
            };
//  </rtc-template>

    public RTObject_impl createRtc(Manager mgr) {
        return new fooImpl(mgr);
    }

    public void deleteRtc(RTObject_impl rtcBase) {
        rtcBase = null;
    }
}
