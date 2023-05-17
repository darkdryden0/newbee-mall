package ltd.newbee.mall.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ltd.newbee.mall.dao.CardsDrawftMapper;
import ltd.newbee.mall.entity.CardsDrawft;
import ltd.newbee.mall.service.NewBeeMallDrawftService;
import ltd.newbee.mall.service.RedisService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NewBeeMallDrawftServiceImpl implements NewBeeMallDrawftService {
    private ObjectMapper objectMapper;
    @Autowired
    private CardsDrawftMapper cardsDrawftMapper;
    @Autowired
    private RedisService redisService;

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
        double randomNum;
        double randomWai;
        int countSSR = 1;
        int countSR = 1;
        String isWai = "F";
        if ((redisService.getValue("countSSR") != null)) {
            countSSR = Integer.parseInt(redisService.getValue("countSSR"));
        }
        if ((redisService.getValue("countSR") != null)) {
            countSR = Integer.parseInt(redisService.getValue("countSR"));
        }
        if ((redisService.getValue("countSR") != null)) {
            isWai = redisService.getValue("isWai");
        }

        for (int n=0; n < 10; n++) {
            randomNum = random.nextDouble(); // 生成一个随机概率值
            // 第90抽必出SSR
            if (countSSR >= 90) {
                randomWai = random.nextDouble(); // 生成一个随机概率值
                if ("T".equals(isWai) || randomWai <= 0.5 ) {
                    cardsDrawft.setCardName("SSR"); //没歪
                    isWai = "F";
                }
                else {
                    cardsDrawft.setCardName("WaiLe"); // 歪了
                    isWai = "T";
                }
                countSSR = 1;
                rates[0] =  0.006;
                rates[1] =  0.046;
                insertCardsDrawft(cardsDrawft, countSSR, countSR, isWai);
                continue;
            }
            if (countSR >= 10) {
                cardsDrawft.setCardName("SR"); // 第10抽必出SSR
                countSR = 1;
                countSSR++;
                insertCardsDrawft(cardsDrawft, countSSR, countSR, isWai);
                continue;
            }
            if (countSSR > 73) {
                rates[1] = rates[1] + 0.06 * (countSSR - 73);
                rates[0] = rates[0] + 0.06 * (countSSR - 73);
            }
            if (randomNum < rates[0]) {
                randomWai = random.nextDouble(); // 生成一个随机概率值
                if ("T".equals(isWai) || randomWai <= 0.5) {
                    cardsDrawft.setCardName("SSR"); //没歪
                    isWai = "F";
                }
                else {
                    cardsDrawft.setCardName("WaiLe"); // 歪了
                    isWai = "T";
                }
                rates[0] =  0.006;
                rates[1] =  0.022;
                countSSR = 1;
            } else if (randomNum < rates[1]) {
                cardsDrawft.setCardName("SR"); //没歪
                countSSR++;
                countSR = 1;
            } else {
                cardsDrawft.setCardName("R"); //没歪
                countSR++;
                countSSR++;
            }
            insertCardsDrawft(cardsDrawft, countSSR, countSR, isWai);
        }
    }

    private void insertCardsDrawft(CardsDrawft cardsDrawft, int countSSR, int countSR, String isWai) {
        cardsDrawft.setAdminUserId(123);
        cardsDrawft.setCreateTime(new Date());
        cardsDrawft.setDrawftCount(countSSR-1);
        cardsDrawft.setGameName("黑暗降临");

        redisService.setValue("countSSR", String.valueOf(countSSR));
        redisService.setValue("countSR", String.valueOf(countSR));
        redisService.setValue("isWai", isWai);
        cardsDrawftMapper.insert(cardsDrawft);
    }
}
