/**
 * 
 */
package com.test.account.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebClientBuilder;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.test.SdmAppStarter;

/**
 * @author Kenny 2016年7月17日 上午9:37:47
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SdmAppStarter.class)
@WebIntegrationTest("server.port:9000") // 0随机端口
public class MenuControllerTest {

	@Autowired
	WebApplicationContext context;

	RestTemplate restTemplate = new TestRestTemplate();
	WebClient webClient;

	@Before
	public void setup() {
		webClient = MockMvcWebClientBuilder
				// demonstrates applying a MockMvcConfigurer (Spring Security)
				.webAppContextSetup(context)
				// for illustration only - defaults to ""
				.contextPath("")
				// By default MockMvc is used for localhost only;
				// the following will use MockMvc for example.com and
				// example.org as well
				.useMockMvcForHosts("localhost", "example.org").build();
	}

	@Test
	public void test() {
		try {
			HtmlPage createMsgFormPage = webClient.getPage("http://localhost:9000/login");
			HtmlForm form = createMsgFormPage.getHtmlElementById("messageForm");
			HtmlTextInput summaryInput = createMsgFormPage.getHtmlElementById("summary");
			summaryInput.setValueAttribute("Spring Rocks");
			HtmlTextArea textInput = createMsgFormPage.getHtmlElementById("text");
			textInput.setText("In case you didn't know, Spring Rocks!");
			HtmlSubmitInput submit = form.getOneHtmlElementByAttribute("input", "type", "submit");
			HtmlPage newMessagePage = submit.click();
			System.out.println("newMessagePage:"+newMessagePage.asText());
		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//HttpHeaders headers = restTemplate.getForEntity("http://localhost:8080", String.class).getHeaders();
		//Assert.hasText(headers.getLocation().toString(), "myotherhost");
	}

	@Test
	public void testAddMenu() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "用户管理2");
		map.put("url", "user/list2");
		String result = restTemplate.getForObject(
				"http://localhost:9000/menu/addSimpleMenu?parentId=root&name={name}&url={url}", String.class, map);
		System.out.println("menu test result:" + result);
		Assert.hasText("success", result);
	}

}
