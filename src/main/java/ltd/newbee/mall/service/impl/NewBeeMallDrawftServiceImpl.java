package ltd.newbee.mall.service.impl;

import ltd.newbee.mall.dao.CardsDrawftMapper;
import ltd.newbee.mall.entity.CardsDrawft;
import ltd.newbee.mall.service.NewBeeMallDrawftService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class NewBeeMallDrawftServiceImpl implements NewBeeMallDrawftService {

    @Autowired
    private CardsDrawftMapper cardsDrawftMapper;

    @Override
    public PageResult getDrawftPage(PageQueryUtil pageUtil) {
        List<CardsDrawft> cardsDrawft = cardsDrawftMapper.findCardsDrawftList(pageUtil);
        int total = cardsDrawftMapper.getTotalDraftCount(pageUtil);
        PageResult pageResult = new PageResult(cardsDrawft, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public void addDrawftData(CardsDrawft cardsDrawft) {
        Random random = new Random(); // 随机数生成器
        double[] rates = {0.006, 0.046, 1}; // 每个角色的抽卡概率
        double randomNum = random.nextDouble(); // 生成一个随机概率值
        int countSSR = 0;

        cardsDrawft.setCreateTime(new Date());
        cardsDrawftMapper.insert(cardsDrawft);
    }
}
