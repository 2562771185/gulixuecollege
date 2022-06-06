package com.jhzz.servicemsm.service;

import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: Huanzhi
 * \* Date: 2022/6/5
 * \* Time: 23:39
 * \* Description:
 * \
 */
public interface MsmService {
    boolean send(String phone, String sms,  String code);
}
