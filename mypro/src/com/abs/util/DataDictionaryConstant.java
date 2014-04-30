package com.abs.util;

/**
 * <P><B>说明：</B>描述这个类的作用</P>
 * <P><B>日期：</B>2013-6-7 上午11:20:49</P>
 * @author zhangjun
 * @version 1.0
 */
public class DataDictionaryConstant {
    //type
    public static final String NOTICE_TYPE = "0";
    public static final String ENABLE_OR_UNENABLE = "1";
    public static final String SEX = "2";
    public static final String APPROVE_STATE = "3";
    public static final String APPROVE_TYPE = "4";
    public static final String APPROVE_LEAVE_TYPE = "5";
    /**预计款项类型(0收 款   1放款)*/
    public static final String REFOUNDS_TYPE = "6";
    /**借款申请状态*/
    public static final String LEND_FOUNDS_APPROVE_STATE = "7";

    /**
     * @Fields USER_TYPE : 员工类型
     */
    public static final String USER_TYPE = "11";
    /** @Fields ITEM_TYPE : 项目类型*/
    public static final String ITEM_TYPE = "12";
    /** @Fields ITEM_STATE : 项目状态*/
    public static final String ITEM_STATE = "13";

    /** @Fields SEAL_TYPE : 用印事项类别 */
    public static final String SEAL_TYPE = "14";
    /** @Fields SEAL_TYPE : 地区 */
    public static final String AREA_TYPE = "15";
    /** @Fields PAY_TYPE : 报销类型 */
    public static final String PAY_TYPE = "16";
    /** @Fields PROFOUNDS_TYPE : 项目费用类型*/
    public static final String PROFOUNDS_TYPE = "17";
    /** @Fields MANAGE_TYPE : 管理类型 */
    public static final String MANAGE_TYPE = "18";
    
    //value
    /**审批状态 ： 保存*/
    public static final String APPROVE_SAVE = "0";
    /**审批状态 ： 待部门经理审批*/
    public static final String APPROVE_RUN = "1";
    /**审批状态 ： 待部长或副部长审批*/
    public static final String APPROVE_PERSONNEL_PASS = "2";
    /**审批状态 ： 通过*/
    public static final String APPROVE_PASS = "3";
    /**审批状态 ： 驳回*/
    public static final String APPROVE_REFUTE = "4";
    /**审批状态 ： 待项目经理审批*/
    public static final String APPROVE_PROJECT_MANAGER = "7";
    /**审批状态 ： 待秘书长审批*/
    public static final String APPROVE_SECRETARY_GENERAL = "6";
    /**审批意见类型 ： 通过*/
    public static final String APPROVE_TYPE_PASS = "1";
    /**审批意见类型 ： 驳回*/
    public static final String APPROVE_TYPE_REFUTE = "0";
    /**借款申请状态:待财务部门审批*/
    public static final String APPROVE_FINANCE_PASS = "5";
    /**员工类型：专家顾问*/
    public static final String USER_TYPE_EXPERT = "2";
    /**员工类型：法律顾问*/
    public static final String USER_TYPE_STATUTE = "3";
    /**员工类型：协会人员*/
    public static final String USER_TYPE_OTHER = "1";
    /**员工类型：临时员工*/
    public static final String USER_TYPE_LS = "4";
    /**项目状态：申报*/
    public static final String ITEM_STATE_SUBMIT = "1";
    /**项目状态：专家意见提交*/
    public static final String ITEM_STATE_EXPERT = "2";
    /**项目状态：法律意见提交*/
    public static final String ITEM_STATE_STATUTE = "3";
    /**项目状态：等待审批*/
    public static final String ITEM_STATE_APPROVE = "4";
    /**项目状态：通过*/
    public static final String ITEM_STATE_PASS = "5";
    /**项目状态：驳回*/
    public static final String ITEM_STATE_REFUTE = "6";
    /**地区：北京*/
    public static final String AREA_TYPE_BEIJING = "1";
    /**地区：天津*/
    public static final String AREA_TYPE_TIANJIN = "2";
    /** @Fields ITEM_TYPE_MEETING : 会议项目类别*/
    public static final String ITEM_TYPE_MEETING = "1";
    /** @Fields ITEM_TYPE_TEST : 试验项目类别 */
    public static final String ITEM_TYPE_TEST = "2";
    /** @Fields PROFOUNDS_TYPE_SHOULD : 应付款*/
    public static final String PROFOUNDS_TYPE_SHOULD="0";
    /** @Fields PROFOUNDS_TYPE_EXCUTE : 执行款*/
    public static final String PROFOUNDS_TYPE_EXCUTE="1";
    /** @Fields PROFOUNDS_TYPE_MANAGE : 管理费用*/
    public static final String PROFOUNDS_TYPE_MANAGE="2";
    /** @Fields PROFOUNDS_TYPE_STATISFY : 部门统筹（借支）*/
    public static final String PROFOUNDS_TYPE_STATISFY="3";
    /** @Fields PAY_TYPE_ITEM : 报销类型之项目费用 */
    public static final String PAY_TYPE_ITEM="0";
    /** @Fields PAY_TYPE_MANAGE : 报销类型之管理费用 */
    public static final String PAY_TYPE_MANAGE="1";
    /** @Fields MANAGE_TYPE_MANAGE : 管理费用类型之管理费用 */
    public static final String MANAGE_TYPE_MANAGE="1";
    /** @Fields MANAGE_TYPE_STATISTIC : 管理费用类型之统筹费用  */
    public static final String MANAGE_TYPE_STATISTIC="1";
    
}
