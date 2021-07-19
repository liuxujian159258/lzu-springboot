package boot.util;

import boot.vo.MetaVO;
import boot.vo.ResultVO;

public class ResultVoUtil {

    public static ResultVO success(Object data, String msg, Integer status) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(data);
        MetaVO meta = new MetaVO(msg, status);
        resultVO.setMeta(meta);
        return resultVO;
    }
    public static ResultVO error(String msg, Integer status) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(null);
        MetaVO meta = new MetaVO(msg, status);
        resultVO.setMeta(meta);
        return resultVO;
    }
}
