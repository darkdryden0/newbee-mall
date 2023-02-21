package ltd.newbee.mall.controller.admin;

import ltd.newbee.mall.util.PageQueryUtil;
import ltd.newbee.mall.util.Result;
import ltd.newbee.mall.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class NewBeeMallDrawftController {
    @Resource
    private NewBeeMallDrawftService newBeeMallDrawftService;

    @GetMapping("/drawft")
    public String ordersPage(HttpServletRequest request) {
        request.setAttribute("path", "drawft");
        return "admin/newbee_mall_drawft";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/drawft/list", method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        /*if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit")) || StringUtils.isEmpty(params.get("categoryLevel")) || StringUtils.isEmpty(params.get("parentId"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }*/
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(newBeeMallDrawftService.getCategorisPage(pageUtil));
    }
}
