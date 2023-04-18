package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.dao.CardsDrawftMapper;
import ltd.newbee.mall.entity.GoodsCategory;
import ltd.newbee.mall.service.NewBeeMallDrawftService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewBeeMallDrawftServiceImpl implements NewBeeMallDrawftService {

    @Autowired
    private CardsDrawftMapper cardsDrawftMapper;

    @Override
    public PageResult getDrawftPage(PageQueryUtil pageUtil) {
        List<GoodsCategory> goodsCategories = cardsDrawftMapper.findCardsDrawftList(pageUtil);
        int total = cardsDrawftMapper.getTotalGoodsCategories(pageUtil);
        PageResult pageResult = new PageResult(goodsCategories, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
