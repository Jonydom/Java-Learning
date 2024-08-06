package org.example.learning.common;

import com.alibaba.fastjson.JSONObject;
import org.example.learning.common.dto.UserInfo;
import org.springframework.beans.BeanUtils;

public class testBeanCopy {
    public static void main(String[] args) {
        testBeanUtilsCopy();
        testMySpringUtilCopy();
    }

    /**
     * 测试 BeanUtils 属性拷贝
     * BeanUtils.copyProperties 所有属性进行拷贝，null值也会拷贝覆盖
     */
    private static void testBeanUtilsCopy() {
        String extJson = "{\"serviceItemList\":[{\"serviceName\":\"一般检查\",\"serviceItem\":[\"身高\",\"体重\",\"血压\",\"脉搏\",\"体重指数\",\"皮肤\"]},{\"serviceName\":\"外科检查\",\"serviceItem\":[\"浅表淋巴结\",\"甲状腺\",\"乳房\",\"脊柱\",\"四肢关节\",\"皮肤\"]},{\"serviceName\":\"实验室检查\",\"serviceItem\":[\"血常规\",\"尿常规\",\"肾功能\",\"肝功能\"]},{\"serviceName\":\"B超检查\",\"serviceItem\":[\"彩超\",\"心电图\"]}],\"recommendReason\":\"基础套餐包含常规检查项目，是评估健康的必要手段\",\"spuId\":\"7229447\",\"title\":\"男性基础套餐\"}";
        UserInfo mainUserInfo = JSONObject.parseObject(extJson, UserInfo.class);

        String inJson = "{\"salePrice\":\"399\",\"city\":\"10\",\"order\":0}";
        UserInfo subUserInfo = JSONObject.parseObject(inJson, UserInfo.class);

        System.out.println("mainUserInfo = " + mainUserInfo);
        BeanUtils.copyProperties(subUserInfo, mainUserInfo);
        System.out.println("mainUserInfo = " + mainUserInfo);
    }

    /**
     * 测试 SpringUtil 属性拷贝
     * SpringUtil.copyPropertiesIgnoreNull 只拷贝有值的属性，忽略null值拷贝
     */
    private static void testMySpringUtilCopy() {
        String extJson = "{\"serviceItemList\":[{\"serviceName\":\"一般检查\",\"serviceItem\":[\"身高\",\"体重\",\"血压\",\"脉搏\",\"体重指数\",\"皮肤\"]},{\"serviceName\":\"外科检查\",\"serviceItem\":[\"浅表淋巴结\",\"甲状腺\",\"乳房\",\"脊柱\",\"四肢关节\",\"皮肤\"]},{\"serviceName\":\"实验室检查\",\"serviceItem\":[\"血常规\",\"尿常规\",\"肾功能\",\"肝功能\"]},{\"serviceName\":\"B超检查\",\"serviceItem\":[\"彩超\",\"心电图\"]}],\"recommendReason\":\"基础套餐包含常规检查项目，是评估健康的必要手段\",\"spuId\":\"7229447\",\"title\":\"男性基础套餐\"}";
        UserInfo mainUserInfo = JSONObject.parseObject(extJson, UserInfo.class);

        String inJson = "{\"salePrice\":\"399\",\"city\":\"10\",\"order\":0}";
        UserInfo subUserInfo = JSONObject.parseObject(inJson, UserInfo.class);

        System.out.println("mainUserInfo = " + mainUserInfo);
        SpringUtil.copyPropertiesIgnoreNull(subUserInfo, mainUserInfo);
        System.out.println("mainUserInfo = " + mainUserInfo);
    }
}
