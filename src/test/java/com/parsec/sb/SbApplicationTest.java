/**
 *   ~ Copyright (c) 2017. 秒差距科技
 */

package com.parsec.sb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SbApplicationTest {

		@Autowired
		protected MockMvc mvc;

		@Test
		public void contextLoads() {

			TestUtil.MockTestObject mto = new TestUtil.MockTestObject().setUrl("/index.html").setMethod(HttpMethod.GET).setMediaType(MediaType.TEXT_HTML);
			TestUtil.assertContains(TestUtil.getMockData(mvc, mto), "API Server Online !");
		}

}
