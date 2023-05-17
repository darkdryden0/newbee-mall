package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.entity.CardsDrawft;
import ltd.newbee.mall.service.NewBeeMallDrawftService;
import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class NewBeeMallDrawftController {
    @Resource
    private NewBeeMallDrawftService newBeeMallDrawftService;

    @GetMapping("/drawft")
    public String draftPage(HttpServletRequest request) {
        request.setAttribute("path", "drawft");
        return "admin/newbee_mall_drawft";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/drawft/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(newBeeMallDrawftService.getDrawftPage(pageUtil));
    }

    /**
     * add
     */
    @RequestMapping(value = "/drawft/add", method = RequestMethod.POST)
    @ResponseBody
    public void add(@RequestBody CardsDrawft cardsDrawft) {
        newBeeMallDrawftService.addDrawftData(cardsDrawft);
    }

    @GetMapping("/analyse")
    public String analysePage(HttpServletRequest request) {
        Map<String, List<Object>> resultMap = new HashMap<>();
        resultMap.put("avgScore", Arrays.asList(8, 12, 6));
        resultMap.put("minScore", Arrays.asList(7, 10, 4));
        List<Object> arrayList = new ArrayList<>();
        resultMap.put("statName", Arrays.asList("apple", "banana", "orange"));
        request.setAttribute("path", "analyse");
        request.setAttribute("resultMap",resultMap);
        return "admin/newbee_mall_analyse";
    }

    public static Map<String,Object> object2Map(Object obj) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }
}
