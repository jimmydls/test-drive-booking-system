package com.tss.tdbs.util.email;

import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import com.tss.tdbs.util.AppUtil;
import com.tss.tdbs.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Exception;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class EmailTemplate {
	final static Logger logger = LoggerFactory.getLogger(EmailTemplate.class);
	 
	private String templateId;
 
	private String template;
 
	private Map<String, String> replacementParams;
 
	public EmailTemplate(String templateId) {
		logger.info("templateId={}",templateId);
		this.templateId = templateId;
		try {
			this.template = loadTemplate(templateId);
		} catch (Exception e) {
			logger.error("loadTemplate={}",templateId,e);
			this.template = Constants.BLANK;
		}
	}
 
	private String loadTemplate(String templateId) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		//File file = new File(classLoader.getResource("email-templates/" + templateId).getFile());
		ClassPathResource cpr  = new ClassPathResource("email-templates/" + templateId);
		String content = Constants.BLANK;
		try {
			byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
			content = new String(bdata, StandardCharsets.UTF_8);						
		} catch (IOException e) {
			logger.error("loadTemplate={}",templateId,e);
			throw new Exception("Could not read template with ID = " + templateId);
		}
		return content;
	}
 
	public String getTemplate(Map<String, String> replacements) {
		String cTemplate = this.getTemplate();
 
		if (!AppUtil.isObjectEmpty(cTemplate)) {
			for (Map.Entry<String, String> entry : replacements.entrySet()) {
				cTemplate = cTemplate.replace("{{" + entry.getKey() + "}}", entry.getValue());
			}
		}
		
		return cTemplate;
	}

	/**
	 * @return the templateId
	 */
	public String getTemplateId() {
		return templateId;
	}

	/**
	 * @param templateId
	 *            the templateId to set
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return the template
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(String template) {
		this.template = template;
	}

	/**
	 * @return the replacementParams
	 */
	public Map<String, String> getReplacementParams() {
		return replacementParams;
	}

	/**
	 * @param replacementParams
	 *            the replacementParams to set
	 */
	public void setReplacementParams(Map<String, String> replacementParams) {
		this.replacementParams = replacementParams;
	}
}
