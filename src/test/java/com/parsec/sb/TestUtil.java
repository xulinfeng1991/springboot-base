package com.parsec.sb;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;



/**
 * 测试工具类
 *
 * 2018-08-07 初版
 */
@Slf4j
public class TestUtil {

    /**
     * 统一的测试方法
     * @param mvc 只要继承了SbApplicationTest 就有mvc对象
     * @param mto 参考静态类 MockTestObject 请填入对应的参数
     * @return
     */
    public static String getMockData(MockMvc mvc, MockTestObject mto){
        try{

            log.debug("【路由和方法:】 " + mto.getUrl() + "  :  " + mto.getMethod());

            if (mto.headers == null)
                mto.headers = new HttpHeaders();

            if (mto.getToken() != null)
                mto.headers.add(HttpHeaders.AUTHORIZATION,  "Bearer " +mto.getToken());
            log.debug("【请求参数:】 " + mto.getParams());
            MockHttpServletResponse response = mvc.perform(request(mto.getMethod(), mto.getUrl()).headers(mto.headers).contentType(mto.mediaType)
                    .content(mto.getParams()!=null?mto.getParams():"")).andReturn().getResponse();
            String ret = response.getContentAsString();
            log.debug( String.format("【请求返回:】 status %d \n\n %s", response.getStatus(), ret) );
            return ret;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 可配置参数
     */
    @Data
    @Accessors(chain = true)
    public static  class MockTestObject {

        private String params;

        private String url;

        private HttpMethod method = HttpMethod.GET;

        private String token;

        private MediaType mediaType = MediaType.ALL;

        private HttpHeaders headers;
    }

    /**
     * 断言是否为返回正确
     */
    public static void assert200(JSONObject obj){
    }

    /**
     * 判断返回的字串中是否包含expect字串
     * @param ret
     * @param expect
     */
    public static void assertContains(String ret, String expect){
        Assert.assertThat(ret, Matchers.containsString(expect));
    }


}
