package com.fancyfamily.primarylibrary.commentlibrary.util;

import com.fancyfamily.primarylibrary.R;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.DisposeProgressEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.WorkLevelEnum;
import com.fancyfamily.primarylibrary.commentlibrary.commentbean.enums.WorkStatusEnum;

/**
 * author:janecer on 2016/4/10 10:05
 * email:janecer@sina.cn
 */

public class LogicUtil {


    public static int getLevelResIdw(Integer workState, Integer level, Integer disposeProgress) {

        if (disposeProgress < DisposeProgressEnum.HAS_EXPIRED.getNo()) {
            if (workState >= WorkStatusEnum.SUBMITTED.getNo().intValue()) {
                if (level == WorkLevelEnum.LEVEL_A.getNo().intValue()) {
                    return R.drawable.cc_mark_w_a;
                } else if (level == WorkLevelEnum.LEVEL_B.getNo().intValue()) {
                    return R.drawable.cc_mark_w_b;
                } else if (level == WorkLevelEnum.LEVEL_C.getNo().intValue()) {
                    return R.drawable.cc_mark_w_c;
                } else if (level == WorkLevelEnum.LEVEL_D.getNo().intValue()) {
                    return R.drawable.cc_mark_w_d;
                } else if (level == WorkLevelEnum.LEVEL_E.getNo().intValue()) {
                    return R.drawable.cc_mark_w_e;
                } else {
                    return R.drawable.cc_mark_to_be_commented;
                }
            } else if (workState == WorkStatusEnum.UNCOMMITTED.getNo().intValue()) {
                return R.drawable.cc_mark_not_submitted;
            } else if (workState == WorkStatusEnum.REPULSE.getNo()) {
                return R.drawable.cc_mark_hit_back;
            }
        } else {
            return R.drawable.cc_mark_uncompleted;
        }
        return R.drawable.cc_mark_uncompleted;
    }

    public static int getLevelResId(Integer workState, Integer level, Integer disposeProgress) {

        if (disposeProgress < DisposeProgressEnum.HAS_EXPIRED.getNo()) {
            if (workState >= WorkStatusEnum.SUBMITTED.getNo().intValue()) {
                if (level == WorkLevelEnum.LEVEL_A.getNo().intValue()) {
                    return R.drawable.cc_mark_a;
                } else if (level == WorkLevelEnum.LEVEL_B.getNo().intValue()) {
                    return R.drawable.cc_mark_b;
                } else if (level == WorkLevelEnum.LEVEL_C.getNo().intValue()) {
                    return R.drawable.cc_mark_c;
                } else if (level == WorkLevelEnum.LEVEL_D.getNo().intValue()) {
                    return R.drawable.cc_mark_d;
                } else if (level == WorkLevelEnum.LEVEL_E.getNo().intValue()) {
                    return R.drawable.cc_mark_e;
                } else {
                    return R.drawable.cc_mark_to_be_commented;
                }
            } else if (workState == WorkStatusEnum.UNCOMMITTED.getNo().intValue()) {
                return R.drawable.cc_mark_not_submitted;
            } else if (workState == WorkStatusEnum.REPULSE.getNo()) {
                return R.drawable.cc_mark_hit_back;
            }
        } else {
            return R.drawable.cc_mark_uncompleted;
        }
        return R.drawable.cc_mark_uncompleted;
    }

    public static int getLevelResId(Integer workState, Integer level) {

        if (workState >= WorkStatusEnum.SUBMITTED.getNo().intValue()) {
            if (level == WorkLevelEnum.LEVEL_A.getNo().intValue()) {
                return R.drawable.cc_mark_a;
            } else if (level == WorkLevelEnum.LEVEL_B.getNo().intValue()) {
                return R.drawable.cc_mark_b;
            } else if (level == WorkLevelEnum.LEVEL_C.getNo().intValue()) {
                return R.drawable.cc_mark_c;
            } else if (level == WorkLevelEnum.LEVEL_D.getNo().intValue()) {
                return R.drawable.cc_mark_d;
            } else if (level == WorkLevelEnum.LEVEL_E.getNo().intValue()) {
                return R.drawable.cc_mark_e;
            } else {
                return R.drawable.cc_mark_to_be_commented;
            }
        } else if (workState == WorkStatusEnum.UNCOMMITTED.getNo().intValue()) {
            return R.drawable.cc_mark_not_submitted;
        } else if (workState == WorkStatusEnum.REPULSE.getNo()) {
            return R.drawable.cc_mark_hit_back;
        }

        return R.drawable.cc_mark_uncompleted;

    }
}
