package com.sain.liferay.frontend.editor.ckeditor.api;

import com.liferay.frontend.editor.ckeditor.web.internal.constants.CKEditorConstants;
import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.xuggler.XugglerUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Nader
 */
@Component(
		property = {"editor.name=ckeditor", "service.ranking:Integer=100"},
		service = com.liferay.portal.kernel.editor.configuration.EditorConfigContributor.class
)
public class CKEditorConfigContributor extends BaseEditorConfigContributor {

	@Override
	public void populateConfigJSONObject(
		JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
		ThemeDisplay themeDisplay,
		RequestBackedPortletURLFactory requestBackedPortletURLFactory) {

		JSONArray toolbarLiferay = jsonObject.getJSONArray("toolbar_liferay");
		String extraPlugins = jsonObject.getString("extraPlugins");

		if (toolbarLiferay != null) {
			JSONArray newToolbar = null;
			try {
				newToolbar = JSONFactoryUtil.createJSONArray(toolbarLiferay.toString().replace("\"PasteFromWord\"",
					"\"PasteFromWord\",\"CodeSnippet\""));
				jsonObject.put("toolbar_liferay", newToolbar);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		jsonObject.put("codeSnippet_theme", "atelier-heath.dark");

		if (extraPlugins != null) {
			extraPlugins = extraPlugins + ",codesnippet";
			jsonObject.put("extraPlugins", extraPlugins);
		}
	}
}