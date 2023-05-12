package ltd.newbee.mall.service;

import ltd.newbee.mall.entity.CardsDrawft;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;

public interface NewBeeMallDrawftService {
    /**
     * 后台分页
     *
     * @param pageUtil
     * @return
     */
    PageResult getDrawftPage(PageQueryUtil pageUtil);
    void addDrawftData(CardsDrawft cardsDrawft);
}
