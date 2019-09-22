package mapper;

import models.CommonModel;

import java.util.List;

public interface CommonMapper {
     //限制传查询必须是int类型
     CommonModel getCommonModelId(Integer id);
     //限制更新必须是comm
     void updateCommonModelId(CommonModel commonModel);

     List<Integer> getAll();
}
