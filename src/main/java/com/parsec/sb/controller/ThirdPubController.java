package com.parsec.sb.controller;

import com.parsec.sb.entity.Game;
import com.parsec.sb.mapper.GameMapper;
import com.parsec.sb.util.POIUtil;
import com.parsec.sb.util.ThirdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "Third平台控制器", tags = {"PUBLIC/Third平台"})
@RestController
public class ThirdPubController {

    @Autowired
    GameMapper gameMapper;

    @ApiOperation(value = "获取图片验证码", notes = "用户登录时，获取图片验证码")
    @PostMapping("/public/admin/fetch")
    public String adminFetch() {
        return ThirdUtil.fetch();
    }

    @ApiOperation(value = "获取短信验证码", notes = "获取短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "电话号码", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "短信模版token（固定值：xxxx）", required = true, dataType = "Integer", paramType = "query")
    })
    @GetMapping("/api/sms/fetch")
    public String fetchSMS(String phone, String token) {
        return ThirdUtil.fetchSMS(phone, token);
    }

    /*
    @NotBlank(message = "电话不能为空")
    @ApiModelProperty(value = "电话")
    private String phone;

    @NotBlank
    @ApiModelProperty(value = "短信模版token（固定值：HX_code）")
    private String token;

    @NotBlank
    @ApiModelProperty(value = "短信验证码")
    private String captcha;
     */


    //导入示例
    @PostMapping("/your/url")
    public void importSensitiveWord(MultipartFile file, String yourParam) {

        //读取Excel数据
        List<String[]> list = null;
        try {
            list = POIUtil.readExcel(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String,Integer> map = new HashMap<>();
        String[] names = {"猫猫","裙子","刀叉","王冠","甜甜圈","肌肉","口红","游戏机","高跟鞋","购物车","面膜","鸡腿","狗狗","心率","爪子","茶","浴缸","便便","笑脸","柠檬"};
        String label = "";
        String slogan = "";
        for(String[] array : list){
            System.out.println(array.length);
            System.out.println(new JSONArray(array));

            String id = array[0];
            if(!StringUtils.isEmpty(id)){
                Game game = new Game();
                game.setId(Integer.parseInt(id));
                game.setTitle(array[1]);
                for(int i=0;i<names.length;i++){
                    if(names[i].equals(array[2])){
                        game.setFirst(i);
                    }
                    if(names[i].equals(array[3])){
                        game.setSecond(i);
                    }
                    if(names[i].equals(array[4])){
                        game.setThird(i);
                    }
                    if(!"".equals(array[5])){
                        label = array[5];
                        label = label.replace("博主","");
                    }
                    if(!"".equals(array[6])){
                        slogan = array[6];
                        slogan.replaceAll("\n",",");
                        System.out.println(slogan);
                    }
                    game.setLabel(label);
                    game.setSlogan(slogan);
                }

//                gameMapper.insertSelective(game);
            }
        }

        //业务代码
        System.out.println("导入成功");
    }


}
