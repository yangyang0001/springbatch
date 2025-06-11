package com.deepblue.common;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;

/**
 * 统一处理电话号码
 */
public class PhoneFormatter {

    public static void main(String[] args) {
//        String phone = "18765829080";     // 日本手机号
//        String regionCode = "CN";          // 国家代码（ISO 3166-1 alpha-2）

//        String phone = "+639943423776";     // 菲律宾手机号 已经验证
//        String phone = "639943423776";      // 菲律宾手机号 已经验证
//        String phone = "09943423776";       // 菲律宾手机号 已经验证
        String phone = "9943423776";        // 菲律宾手机号 已经验证
        String regionCode = "PH";           // 国家代码（ISO 3166-1 alpha-2）

        String e164Phone = getPhone(phone, regionCode, PhoneNumberFormat.E164);
        String intePhone = getPhone(phone, regionCode, PhoneNumberFormat.INTERNATIONAL);
        String natiPhone = getPhone(phone, regionCode, PhoneNumberFormat.NATIONAL);
        String rfc3Phone = getPhone(phone, regionCode, PhoneNumberFormat.RFC3966);

        System.out.println(e164Phone);
        System.out.println(intePhone);
        System.out.println(natiPhone);
        System.out.println(rfc3Phone);

    }

    public static String getPhone(String phone, String regionCode, PhoneNumberFormat format) {
        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            PhoneNumber numberProto = phoneUtil.parse(phone, regionCode);

            if (phoneUtil.isValidNumber(numberProto)) {
                System.out.println("phone = " + phone + ", 已经解析!!!!!");
                return phoneUtil.format(numberProto, format);
            }
        } catch (NumberParseException e) {
            System.out.println("解析出错: " + e.toString());
        }

        return phone;
    }
}
