package com.sain.liferay.frontend.editor.ckeditor.api;

import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = DynamicInclude.class)
public class CKEditorOnEditorCreateDynamicInclude
	implements DynamicInclude {

	@Override
	public void include(
			HttpServletRequest request, HttpServletResponse response,
			String key)
		throws IOException {

		Bundle bundle = _bundleContext.getBundle();

		String toolbarSet = (String)request.getAttribute(
			"liferay-ui:input-editor:toolbarSet");

		System.out.println("toolbarSet = " + toolbarSet);
	}

	@Override
	public void register(
		DynamicInclude.DynamicIncludeRegistry dynamicIncludeRegistry) {

		dynamicIncludeRegistry.register(
				"com.liferay.frontend.editor.ckeditor.web#ckeditor#" +
					"onEditorCreate");
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundleContext = bundleContext;
	}

	private BundleContext _bundleContext;

}