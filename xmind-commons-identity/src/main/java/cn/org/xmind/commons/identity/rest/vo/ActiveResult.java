package cn.org.xmind.commons.identity.rest.vo;

import cn.org.xmind.commons.domain.RestResponse;

/**
 *
 * @author LuoWenqiang
 */
public class ActiveResult extends RestResponse {

    public static final String ERROR_CODE_INVALID_REQUEST = ActiveResult.class.getName() + ".INVALID_REQUEST";
    public static final String ERROR_CODE_ACTIVE_FAILED = ActiveResult.class.getName() + ".ACTIVE_FAILED";
}
