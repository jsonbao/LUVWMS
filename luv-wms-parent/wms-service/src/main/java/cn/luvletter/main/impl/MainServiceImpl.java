package cn.luvletter.main.impl;

import cn.luvletter.bean.ApiResult;
import cn.luvletter.constant.WMSConstant;
import cn.luvletter.main.MainService;
import cn.luvletter.main.dao.DictionaryMapper;
import cn.luvletter.main.model.Dictionary;
import cn.luvletter.main.vo.SelectDSVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zephyr
 * @Description:
 * @Date 2018/4/2
 */
@Service
public class MainServiceImpl implements MainService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Override
    @Cacheable(value = WMSConstant.REDIS_CACHE_NAME)
    public ApiResult getComboBox(String pid,String value) {
        ApiResult apiResult = new ApiResult();
        List<Dictionary> dictionaries = dictionaryMapper.selectByParaId(pid, value);
        if(dictionaries == null || dictionaries.isEmpty()){
            apiResult.isFalse();
            return apiResult;
        }
        apiResult.setData(dictionaries.get(0));
        return apiResult;
    }

    @Override
    @Cacheable(value = WMSConstant.REDIS_CACHE_NAME)
    public ApiResult getSelectDS(String pid) {
        ApiResult apiResult = new ApiResult();
        List<SelectDSVo> selectDSVos = dictionaryMapper.selectByPid(pid);
        if(selectDSVos == null || selectDSVos.isEmpty()){
            apiResult.isFalse();
            return apiResult;
        }
        apiResult.setData(selectDSVos);
        return apiResult;
    }
}
